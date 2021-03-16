#include <stdio.h>
#include "tableOperation.h"

table* readFromFile(FILE* file)
{
	table* gameTable;
	int i,j;
	char c;
	if (file == NULL)
	{
		gameTable = initTable(3,3);
		for (i=0; i<3; i++)
		{
			if (i%2 == 0)
				c='0';
			else
				c='1';
			for (j=0; j<3; j++)
			{
				gameTable->board[i][j] = c;
			}
		}
	}
	return gameTable;
}