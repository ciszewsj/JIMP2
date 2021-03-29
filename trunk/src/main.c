#include <stdio.h>

#include "tableOperation.h"
#include "solver.h"
#include "fileOperation.h"
#include "validate.h"



int main(int argc, char ** argv)
{
	int compareValue = 1;
	int i;
	
	table* gameTable = NULL;
	
	arguments* argumentsList = validateArguments(argc, argv);
	
	if (argumentsList == NULL)
	{
		return 100;
	}
	
	if (argumentsList->inFile != NULL)
	{
		gameTable = readFromFile(argumentsList->inFile);
		fclose(argumentsList->inFile);
	}
	else
	{
		gameTable = initDefaultTable();
	}
	
	if (gameTable == NULL)
	{
		printf ("Dane wejściowe zawarte w podanym pliku mają nieprawidłowy format. Zalecamy poprawienie danych wejściowych, lub podanie innego pliku.\n");
		return 1;
	}
	if (argumentsList->isHelp == 0)
	{
		for (i = 0; i < argumentsList->numberOfIteration; i++)
		{
			if (argumentsList->ifSaveEveryIteration == 2 && i != 0)
			{
				saveToFile(gameTable, argumentsList->outFileName, argumentsList->ifSaveAsTxT, argumentsList->ifSaveAsPicture);
			}
			if (argumentsList->ifPrintOnScreen == 1)
			{
				printf("Iteracja %d gry:\n", i);
				printTable(gameTable);
				printf("\n\n");
			}
			compareValue = solveIteration(&gameTable, argumentsList->typeOfProximity, argumentsList->typeOfArea);
			
			if (compareValue == 0)
			{
				break;
			}
			if (argumentsList->ifSbS == 1)
			{
				printf("Naciśnij ENTER, aby przejść dalej.(Aby wyjść z trybu SBS wpisz 'n'). \n");
				if(getchar() == 'n')
				{
					argumentsList->ifSbS = 0;
				}
			}
		}
		if (compareValue == 1)
		{
			if (argumentsList->ifPrintOnScreen == 1)
			{
				printf("Iteracja %d gry:\n", i);
				printTable(gameTable);
			}
			if (argumentsList->ifSaveEveryIteration != 0)
			{
				saveToFile(gameTable, argumentsList->outFileName, argumentsList->ifSaveAsTxT, argumentsList->ifSaveAsPicture);
			}
		}
	}
	else
	{
		printf("Pomoc programu lifeGameEmulator:\n");
	}
	
	destroyArguments(argumentsList);
	
	return 0;
}