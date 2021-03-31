#include <stdio.h>
#include <stdlib.h>

#include "tableOperation.h"

#define BYTES_PER_PIXEL 3
#define FILE_HEADER_SIZE 14
#define INFO_HEADER_SIZE 40


unsigned char* createBitmapFileHeader (int height, int stride)
{
    int fileSize = FILE_HEADER_SIZE + INFO_HEADER_SIZE + (stride * height);

    static unsigned char fileHeader[] = {
        0,0,     
        0,0,0,0, 
        0,0,0,0, 
        0,0,0,0, 
    };

    fileHeader[ 0] = (unsigned char)('B');
    fileHeader[ 1] = (unsigned char)('M');
    fileHeader[ 2] = (unsigned char)(fileSize);
    fileHeader[ 3] = (unsigned char)(fileSize >>  8);
    fileHeader[ 4] = (unsigned char)(fileSize >> 16);
    fileHeader[ 5] = (unsigned char)(fileSize >> 24);
    fileHeader[10] = (unsigned char)(FILE_HEADER_SIZE + INFO_HEADER_SIZE);

    return fileHeader;
}

unsigned char* createBitmapInfoHeader (int height, int width)
{
    static unsigned char infoHeader[] = {
        0,0,0,0, 
        0,0,0,0, 
        0,0,0,0, 
        0,0,     
        0,0,     
        0,0,0,0, 
        0,0,0,0, 
        0,0,0,0, 
        0,0,0,0, 
        0,0,0,0, 
        0,0,0,0,
    };

    infoHeader[ 0] = (unsigned char)(INFO_HEADER_SIZE);
    infoHeader[ 4] = (unsigned char)(width      );
    infoHeader[ 5] = (unsigned char)(width >>  8);
    infoHeader[ 6] = (unsigned char)(width >> 16);
    infoHeader[ 7] = (unsigned char)(width >> 24);
    infoHeader[ 8] = (unsigned char)(height      );
    infoHeader[ 9] = (unsigned char)(height >>  8);
    infoHeader[10] = (unsigned char)(height >> 16);
    infoHeader[11] = (unsigned char)(height >> 24);
    infoHeader[12] = (unsigned char)(1);
    infoHeader[14] = (unsigned char)(BYTES_PER_PIXEL*8);

    return infoHeader;
}

void generateBitmapImage(unsigned char** image, int height, int width, char* imageFileName)
{
    int widthInBytes = width * BYTES_PER_PIXEL;

    unsigned char padding[3] = {0, 0, 0};
    int paddingSize = (4 - (widthInBytes) % 4) % 4;

    int stride = (widthInBytes) + paddingSize;

    FILE* imageFile = fopen(imageFileName, "wb");

    unsigned char* fileHeader = createBitmapFileHeader(height, stride);
    fwrite(fileHeader, 1, FILE_HEADER_SIZE, imageFile);

    unsigned char* infoHeader = createBitmapInfoHeader(height, width);
    fwrite(infoHeader, 1, INFO_HEADER_SIZE, imageFile);

    int i;
	
	
    for (i = 0; i < height; i++)
	{
        fwrite(image[i], BYTES_PER_PIXEL, width, imageFile);
        fwrite(padding, 1, paddingSize, imageFile);
    }
    fclose(imageFile);
	}


void generateGameTableBitmap(table* gameTable, char* outFileName)
{
	int i, j, k, h;
	unsigned char **image = NULL;
	unsigned char *helpImageTable = NULL;
	image = calloc(gameTable->rows, sizeof(unsigned char *));
	for (i = 0; i < gameTable->rows; i++)
	{
		image[i] = calloc(gameTable->columns * 3, sizeof(unsigned char));
	}
	
	h = 0;
	
	for (j = 0; j < gameTable->rows; j++)
	{
		for (i = 0; i < gameTable->columns; i++)
		{
			
			if (gameTable->board[j][i] == aliveCell)
			{
				for (k = 0; k < 3; k++)
				{					
					image[h/(gameTable->columns*3)][h%(gameTable->columns*3) * sizeof(unsigned char)] = 0;
					h++;
				}
			}
			else
			{
				for (k = 0; k < 3; k++)
				{
					image[h/(gameTable->columns*3)][h%(gameTable->columns*3) * sizeof(unsigned char)] = 255;
					h++;
				}
			}
		}
	}
	
	for (i = 0; i < gameTable->rows/2; i++)
	{
		helpImageTable = image[i];
		image[i] = image[gameTable->rows - i - 1];
		image[gameTable->rows - i - 1] = helpImageTable;
	}
	generateBitmapImage(image, gameTable->rows, gameTable->columns, outFileName);
	
	for (i = 0; i < gameTable->rows; i++)
	{
		free(image[i]);
	}
	
	free(image);
}