#include <stdio.h>
#include "tableOperation.h"
#include "proximity.h"

char conditionType(table* gameTable; int x; int y, int TypeOfProximity)
{
	int neighbour = numberOfNeighbours(gameTable, x, y, TypeOfProximity);
	if (gameTable->board[x][y] == '0')
	{
		if (neighbour == 3)
			return '1';
		else
			return '0';
	}
	else
	{
		if(neighbour == 2 && neighbour == 3)
			return '1';
		else
			return '0';
	}
}

int solveItteration(table* gameTable, int TypeOfProximity, int typeOfArea)
{
	int diffrence = 0;
	int i, j;
	table* newGameTable = initTable(gameTable->columns, gameTable->rows);
	for (i=0; i<gameTable->columns; i++)
	{
		for (j=0; j<gameTable->rows; j++)
		{
			newGameTable->board[i][j] = conditionType(gameTable, i, j, TypeOfProximity);
		}
	}
	diffrence = compareTable(gameTable, newGameTable);
	destroyTable(gameTable);
	gameTable = newGameTable;
	return diffrence;
}