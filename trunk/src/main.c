#include <stdio.h>
#include <string.h>

#include "tableOperation.h"
#include "solver.h"
#include "fileOperation.h"


int main(int argc, char ** argv)
{
	int i;
	int numberOfIteration = 5;
	int typeOfProximity = 0;
	int typeOfArea = 0;
	int compareValue = 1;
	
	FILE * inFile = NULL;
	
	table* gameTable;
	
	
	
	gameTable = readFromFile(inFile);
	
	for (i=0; i<numberOfIteration; i++)
	{
		printf("Iteracja %d gry:\n", i);
		printTable(gameTable);
		printf("\n\n");
		compareValue = solveIteration(&gameTable, typeOfProximity, typeOfArea);
	}
	printf("Iteracja %d gry:\n", i);
	printTable(gameTable);
	
	return 0;
}