#include <stdio.h>
#include "tableOperation.h"
#include "proximity.h"

char conditionType(table* gameTable, int x, int y, int typeOfProximity, int typeOfArea)
{
	int neighbour = numberOfNeighbours(gameTable, x, y, typeOfProximity, typeOfArea);
	if (gameTable->board[x][y] == deadCell)
	{
		if (neighbour == 3)
			return aliveCell;
		else
			return deadCell;
	}
	else
	{
		if(neighbour == 2 || neighbour == 3)
			return aliveCell;
		else
			return deadCell;
	}
	return 'e';
}

int solveIteration(table** gameTable, int typeOfProximity, int typeOfArea)
{
	int diffrence = 0;
	int i, j;
	table* newGameTable = initTable(gameTable[0]->columns, gameTable[0]->rows);
	for (i = 0; i < gameTable[0]->rows; i++)
	{
		for (j = 0; j < gameTable[0]->columns; j++)
		{
			newGameTable->board[i][j] = conditionType(gameTable[0], i, j, typeOfProximity, typeOfArea);
		}
	}
	diffrence = compareTable(gameTable[0], newGameTable);
	destroyTable(gameTable[0]);
	*gameTable = newGameTable;
	return diffrence;
}