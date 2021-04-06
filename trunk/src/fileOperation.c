#include <stdio.h>
#include <string.h>
#include <stdlib.h>


#include "tableOperation.h"
#include "printBmp.h"

table* readFromFile(FILE* file)
{
	int size[2];
	int numberOfChar;
	int i;
	table* gameTable;
	char buf;
	
	for (i = 0; i < 2; i++)
	{
		if (fscanf(file, "%d", &size[i]) == EOF)
		{
			return NULL;
		}
		if (size[i] < 3)
		{
			return NULL;
		}
	}

	gameTable = initTable(size[0], size[1]);
	if(gameTable == NULL)
	{
		return NULL;
	}
	i=0;
	numberOfChar = size[0] * size[1];
	while(i < numberOfChar)
	{
		if(fscanf(file, "%c", &buf) == EOF)
		{
			destroyTable(gameTable);
			return NULL;
		}
		if (buf == aliveCell || buf == deadCell)
		{
			gameTable->board[i % gameTable->columns][i / gameTable->columns] = buf;
			i++;
		}
		else if (buf > 32 && buf < 128)
		{
			destroyTable(gameTable);
			return NULL;
		}
	}
	while(fscanf(file, "%c", &buf) != EOF)
	{
		if (buf > 32 && buf < 128)
		{
			destroyTable(gameTable);
			return NULL;
		}
	}
	return gameTable;
}

char* avalibleFileName(char* outFileName, char* format)
{
	int i = 0;
	char* newFileName = calloc(strlen(outFileName) + 18, sizeof(char));
	snprintf(newFileName, strlen(outFileName) + 17, "%s.%s", outFileName, format);
	while (fopen(newFileName,"r") != NULL)
	{
		snprintf(newFileName, strlen(outFileName) + 17, "%s_%d.%s", outFileName, i, format);
		i++;
	}
	return newFileName;
	
}

int saveToTxt(table* gameTable, char* outFileName)
{
	int i, j;
	FILE* out = fopen(outFileName, "w");
	if (out == NULL)
	{
		return 1;
	}
	fprintf(out, "%d %d\n", gameTable->columns, gameTable->rows);
	for (i = 0; i < gameTable->rows; i++)
	{
		for (j=0; j < gameTable->columns; j++)
		{
			fprintf(out, "%c ", gameTable->board[j][i]);
		}
		fprintf(out, "\n");
	}
	fclose(out);
	return 0;
}

void saveToFile(table* gameTable, char* outFileName, int toTxt, int toPicture)
{
	char* freeTxtOutName = NULL;
	char* freePictureName = NULL;
	if (toTxt == 1)
	{
		freeTxtOutName = avalibleFileName(outFileName, "txt\0");
		if (saveToTxt(gameTable, freeTxtOutName) == 0)
		{
			printf("Zapisano iterację do pliku tekstowego: %s\n", freeTxtOutName);
		}
		else
		{
			printf("Nie udało się zapisać iteracji do pliku tekstowego: %s\n", freeTxtOutName);
		}
		free(freeTxtOutName);
	}
	if (toPicture == 1)
	{
		freePictureName = avalibleFileName(outFileName, "bmp\0");
		if(generateGameTableBitmap(gameTable, freePictureName) == 0)
		{
			printf("Zapisano iterację do pliku graficznego: %s\n", freePictureName);
		}
		else
		{
			printf("Nie udało się zapisać iteracji do pliku graficznego: %s\n", freePictureName);
		}
		free(freePictureName);
	}
}