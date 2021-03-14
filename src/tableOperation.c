#include <stdio.h>
#include "tableOperation.h"

table* initTable(int columns, int rows);
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

void destroyTable(table* Table)
{
	int i, j;
	for (i=0; i<Table->columns; i++)
	{
		for (j=0, j<Table->rows; j++)
		{
			free(Table->board[i][j]);
		}
		free(Table->board[i]);
	}
	free(Table);
}

int compareTable(table* gameTable1, table* gameTable2)
{
	int i, j;
	for (i=0; i<gameTable1->columns; i++)
	{
		for (j=0; j<gameTable1->rows; j++)
		{
			if (gameTable1->board[i][j] != gameTable2->[i][j])
				return 1;
		}
	}
	return 0;
}

void printTable(table* gameTable)
{
	int i,j;
	for (i=0; i<gameTable->columns; i++)
	{
		for (j=0; j<gameTable->rows; j++)
		{
			printf("%c ", gameTable->board[i][j]);
		}
		printf("\n");
	}
}
