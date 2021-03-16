#include <stdio.h>
#include "tableOperation.h"

int findX(table* gameTable, int x)
{
	if (x < 0)
		return x+gameTable->columns;
	else if (x >= gameTable->columns)
		return x%gameTable->columns;
	else
		return x;
}

int findY(table* gameTable, int y)
{
	if (y < 0)
		return y+gameTable->rows;
	else if (y >= gameTable->rows)
		return y%gameTable->rows;
	else
		return y;
}

int isRoundAlive(table* gameTable, int x, int y)
{
	x = findX(gameTable,x);
	y = findY(gameTable,y);
	if (gameTable->board[x][y] == '1')
		return 1;
	else 
		return 0;
}
int isFlatAlive(table* gameTable, int x, int y)
{
	if (x >= gameTable->columns)
		return 0;
	else if (y >= gameTable->rows)
		return 0;
	else if (gameTable->board[x][y] == '1')
		return 1;
	else 
		return 0;
}

int Neumann(table* gameTable, int x, int y, int typeOfArea)
{
	int neighbour = 0;
	if (typeOfArea == 0)
	{
		neighbour = neighbour + isRoundAlive(gameTable, x-1, y);
		neighbour = neighbour + isRoundAlive(gameTable, x, y-1);
		neighbour = neighbour + isRoundAlive(gameTable, x+1, y);
		neighbour = neighbour + isRoundAlive(gameTable, x, y+1);
	}
	else
	{
		neighbour = neighbour + isFlatAlive(gameTable, x-1, y);
		neighbour = neighbour + isFlatAlive(gameTable, x, y-1);
		neighbour = neighbour + isFlatAlive(gameTable, x+1, y);
		neighbour = neighbour + isFlatAlive(gameTable, x, y+1);
	}
	return neighbour;
}

int Moore(table* gameTable, int x, int y, int typeOfArea)
{
	int neighbour = 0;
	neighbour = Neumann(gameTable,x,y,typeOfArea);
	if (typeOfArea == 0)
	{
		neighbour = neighbour + isRoundAlive(gameTable, x-1, y-1);
		neighbour = neighbour + isRoundAlive(gameTable, x+1, y-1);
		neighbour = neighbour + isRoundAlive(gameTable, x-1, y+1);
		neighbour = neighbour + isRoundAlive(gameTable, x+1, y+1);
	}
	else
	{
		neighbour = neighbour + isFlatAlive(gameTable, x-1, y);
		neighbour = neighbour + isFlatAlive(gameTable, x, y-1);
		neighbour = neighbour + isFlatAlive(gameTable, x+1, y);
		neighbour = neighbour + isFlatAlive(gameTable, x, y+1);
	}
	return neighbour;
}

int numberOfNeighbours(table* gameTable, int x, int y, int TypeOfProximity, int typeOfArea)
{
	if (TypeOfProximity == 0)
	{
		return Moore(gameTable, x ,y, typeOfArea);
	}
	else if(TypeOfProximity == 1)
	{
		return Neumann(gameTable, x, y, typeOfArea);
	}
	return -1;
}