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
	int isHelp = 0;
	int ifPrintOnScreen = 1;
	int ifSaveEveryIteration = 0;
	int ifSaveAsPicture = 0;
	int ifSaveAsTxT = 0;
	int ifSbS = 0;
	
	char * outFileName = NULL;
	
	FILE * inFile = NULL;
	
	table* gameTable;
	
	
	
	gameTable = readFromFile(inFile);
	
	for (i=0; i<numberOfIteration; i++)
	{
		printf("Iteracja %d gry:\n", i);
		printTable(gameTable);
		printf("\n\n");
		compareValue = solveIteration(&gameTable, typeOfProximity, typeOfArea);
		
		if (compareValue == 0)
		{
			break;
		}
	}
	if (compareValue == 1)
	{
		printf("Iteracja %d gry:\n", i);
		printTable(gameTable);
	}
		
	return 0;
}