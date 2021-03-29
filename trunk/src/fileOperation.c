#include <stdio.h>
#include "tableOperation.h"


table* readFromFile(FILE* file)
{
	int size[2];
	int numberOfChar;
	int i;
	table *gameTable;
	char buf;
	
	for (i = 0; i < 2; i++)
	{
		if (fscanf(file, "%d", &size[i]) == EOF)
		{
			return NULL;
		}
		if (size[i] < 3)
		{
			printf ("t%d\n", 2);
			return NULL;
		}
	}

	gameTable = initTable(size[0],size[1]);
	if(gameTable == NULL)
	{
		return NULL;
	}
	
	i=0;
	numberOfChar = size[0] * size[1];
	while(i < numberOfChar)
	{
		if(fscanf(file, "%c",&buf) == EOF)
		{
			return NULL;
		}
		if (buf == aliveCell || buf == deadCell)
		{
			gameTable->board[i / gameTable->columns][i % gameTable->columns] = buf;
			i++;
		}
		else if (buf > 32 && buf <128)
		{
			return NULL;
		}
		else
		{
			
		}
	}
	
	if(fscanf(file, "%c",&buf) != EOF)
	{
		return NULL;
	}
	return gameTable;
}

void saveToFile(table* gameTable, char* outFileName, int toTxt, int toPicture)
{
	printf("Zapis");
}