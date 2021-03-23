#include <stdio.h>
#include "tableOperation.h"


table* readFromFile(FILE* file)

{
	int size[2];
	table *gameTable;
	char buf;

	for (int i = 0; i < 2; i++) {
		fscanf(file, "%d", &size[i]);
	}

	gameTable = initTable(size[0],size[1]);
	if(gameTable == NULL)
		return NULL;

	for (int i = 0; i < size[0]; i++) 
	{
		for (int j = 0; j < size[1]; j++){
			if(fscanf(file, "%c",&buf) == 0)
				return NULL;
			if (buf != '0' && buf != '1')
				return NULL;
			gameTable->board[i][j] = buf;
		}
	}
	
	if(fscanf(file, "%c",&buf) != 0)
		return NULL;
	
	return gameTable;
}