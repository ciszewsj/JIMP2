#include <stdio.h>
#include <stdlib.h>
#include "tableOperation.h"

char aliveCell = '1';
char deadCell = '0';

table* initTable(int columns, int rows)
{
	int i;
	table* newTable;
	newTable = (table*) calloc(1, sizeof(table));
	newTable->columns = columns;
	newTable->rows = rows;
	newTable->board = (char**) calloc(columns,sizeof(char*));
	for (i=0; i<columns; i++)
	{
		newTable->board[i] = (char*) calloc(rows, sizeof(char));
	}
	return newTable;
}

table* initDefaultTable()
{
	int i,j;
	table* newTable = initTable(3, 3);
	for (i = 0; i < 3; i++)
	{
		for (j = 0; j < 3; j++)
		{
			if (j == 1)
			{	
				newTable->board[i][j] = aliveCell;
			}
			else
			{
				newTable->board[i][j] = deadCell;
			}
		}
	}
	return newTable;
}

void destroyTable(table* Table)
{
	int i;
	for (i = 0; i < Table->columns; i++)
	{
		free(Table->board[i]);
	}
	free(Table->board);
	free(Table);
}

int compareTable(table* gameTable1, table* gameTable2)
{
	int i, j;
	for (i = 0; i < gameTable1->columns; i++)
	{
		for (j = 0; j < gameTable1->rows; j++)
		{
			if (gameTable1->board[i][j] != gameTable2->board[i][j])
				return 1;
		}
	}
	return 0;
}

void printTable(table* gameTable)
{
	int i,j;
	for (i = 0; i<gameTable->rows; i++)
	{
		for (j=0; j<gameTable->columns; j++)
		{
			printf("%c ", gameTable->board[i][j]);
		}
		printf("\n");
	}
}