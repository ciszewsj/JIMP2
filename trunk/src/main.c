#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "tableOperation.h"
#include "solver.h"
#include "fileOperation.h"

void printWrongArgumentValue(char* argumentName, char* correctValueDescription)
{
	printf("Argument %s ma nieprwaidlow ,a warto´s´c. Prawid lowawarto´s´c to %s.", argumentName, correctValueDescription);
}

void printWrongArgumentName(char* argumentName)
{
	printf("Nast,epuj ,acy argument wywo laniajest niepoprawny: %s. Sprawd´zdost,epne argumenty wywo lania za pomoc ,a –help.", argumentName);
}
int isNumber(char* str)
{
	int i;
	for (i = 0; i < strlen(str); i++)
	{
		if (isdigit(str[i]) == 0)
			return 0;
	}
	return 1;
}

int main(int argc, char ** argv)
{
	int i;
	int numberOfIteration = 1;
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
	char * inFileName = NULL;
	
	FILE * inFile = NULL;
	
	table* gameTable = NULL;
	
	i = 1;
	while (i < argc)
	{
		printf("%s \n",argv[i]);
		if (strcmp(argv[i], "-dataFile") == 0)
		{
			i++;
			inFileName = argv[i];
			inFile = fopen(inFileName, "r");
			if (inFile == NULL)
			{
				printf("Plik o podanej nazwie nie istnieje. Należy podać nazwę istniejącego pliku wejściowego.\n");
			}
			i++;
		}
		else if (strcmp(argv[i], "-flatArea") == 0)
		{
			i++;
			if (strcmp(argv[i], "false") == 0)
			{
				typeOfArea = 0;
				i++;
			}
			else if (strcmp(argv[i], "true") == 0)
			{
				typeOfArea = 1;
				i++;
			}
			else if (argv[i][0] != '-')
			{
				printWrongArgumentValue("","");
				i++;
			}
			else
			{
				
			}
		}
		else if (strcmp(argv[i], "-help") == 0)
		{
			i++;
			isHelp = 1;
		}
		else if (strcmp(argv[i], "-iterations") == 0)
		{
			printf("Tu");
			i++;
			if (isNumber(argv[i]))
			{
				printf("tu");
				numberOfIteration = atoi(argv[i]);
				i++;
			}
			else if (argv[i][0] != '-')
			{
				
			}
			else
			{
				
			}
		}
		else if (strcmp(argv[i],"–outputFilename") == 0)
		{
			i++;
		}
		else if (strcmp(argv[i], "–printOnScreen") == 0)
		{
			i++;
		}
		else if (strcmp(argv[i], "-proximity") == 0)
		{
			i++;
			if (strcmp(argv[i], "moore") == 0)
			{
				typeOfProximity = 0;
				i++;
			}
			else if (strcmp(argv[i], "neumann") == 0)
			{
				typeOfProximity = 1;
				i++;
			}
		}
		else if (strcmp(argv[i], "–saveAsPicture") == 0)
		{
			i++;
		}
		else if (strcmp(argv[i], "–saveAsTxt") == 0)
		{
			i++;
		}
		else if (strcmp(argv[i], "–saveEveryIteration") == 0)
		{
			i++;
		}
		else if (strcmp(argv[i], "–SBS") == 0)
		{
			i++;	
		}
		else
		{
			printWrongArgumentName(argv[i]);
			i++;
		}
		
	}
	
	
	if (inFile != NULL)
	{
		gameTable = readFromFile(inFile);
		
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
	
	for (i = 0; i < numberOfIteration; i++)
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