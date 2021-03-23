#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "tableOperation.h"
#include "solver.h"
#include "fileOperation.h"

void printWrongArgumentValue(char* argumentName, char* correctValueDescription)
{
	printf("Argument %s ma nieprwaidlową wartość. Prawidłowa wartość to %s.\n", argumentName, correctValueDescription);
}

void printWrongArgumentName(char* argumentName)
{
	printf("Następujący argument wywołania jest niepoprawny: %s. Sprawdźdostępne argumenty wywołania za pomocą flagi –help.\n", argumentName);
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
		if (strcmp(argv[i], "-dataFile") == 0)
		{
			if (inFileName != NULL)
			{
				free(inFileName);
			}
			i++;
			if (i < argc)
			{
				inFileName = calloc(1, strlen(argv[i])+1);
				strcat(inFileName, argv[i]);
				printf ("TU?\n");
				i++;
				while(i < argc)
				{
					if (argv[i][0]!='-')
					{
						printf ("TU?\n");
						if(realloc(inFileName, strlen(inFileName) + strlen(argv[i]) + 2) == NULL)
						{
							return 100;
						}
						printf ("TU?\n");
						strcat(inFileName, " \0");
						printf ("TU?\n");
						strcat(inFileName, argv[i]);
						printf ("TU?\n");
						i++;
					}
					else
					{
						break;
					}
				}
				printf ("%s\n",inFileName);
				inFile = fopen(inFileName, "r");
				if (inFile == NULL)
				{
					printf("Plik o podanej nazwie nie istnieje. Należy podać nazwę istniejącego pliku wejściowego.\n");
				}
			}
		}
		else if (strcmp(argv[i], "-flatArea") == 0)
		{
			i++;
			if (i < argc)
			{
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
					printWrongArgumentValue("-flatArea", "true | false");
					i++;
				}
			}
		}
		else if (strcmp(argv[i], "-help") == 0)
		{
			i++;
			isHelp = 1;
		}
		else if (strcmp(argv[i], "-iterations") == 0)
		{
			i++;
			if (i < argc)
			{
				if (isNumber(argv[i]))
				{
					numberOfIteration = atoi(argv[i]);
					if (numberOfIteration < 1)
					{
						printWrongArgumentValue("-iterations", "liczba naturalna większa od 0");
					}
					i++;
				}
				else if (argv[i][0] != '-')
				{
					printWrongArgumentValue("-iterations", "liczba naturalna większa od 0");	
				}
			}
		}
		else if (strcmp(argv[i],"–outputFilename") == 0)
		{
			i++;
			if (i < argc)
			{
				if (outFileName != NULL)
				{
					free(outFileName);
				}
				outFileName = calloc(1, strlen(argv[i])+1);
				strcat(outFileName, argv[i]);
				i++;
				while(i < argc)
				{
					if (argv[i][0]!='-')
					{
						if (realloc(outFileName, strlen(outFileName) + strlen(argv[i]) + 2) == NULL)
						{
							return 100;
						}
						strcat(outFileName, " \0");
						strcat(outFileName, argv[i]);
						i++;
					}
					else
					{
						break;
					}
				}
				printf ("%s\n",outFileName);
			}
		}
		else if (strcmp(argv[i], "–printOnScreen") == 0)
		{
			i++;
			if (i < argc)
			{
				if (strcmp(argv[i], "false") == 0)
				{
					ifPrintOnScreen = 0;
					i++;
				}
				else if (strcmp(argv[i], "true") == 0)
				{
					ifPrintOnScreen = 1;
					i++;
				}
				else if (argv[i][0] != '-')
				{
					printWrongArgumentValue("-printOnScreen", "false | true");
					i++;
				}
			}
		}
		else if (strcmp(argv[i], "-proximity") == 0)
		{
			i++;
			if (i < argc)
			{
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
				else if (argv[i][0] != '-')
				{
					printWrongArgumentValue("-proximity", "moore | neumann");
				}
			}
		}
		else if (strcmp(argv[i], "–saveAsPicture") == 0)
		{
			i++;
			if (i < argc)
			{
				if (strcmp(argv[i], "false") == 0)
				{
					ifSaveAsPicture = 0;
					i++;
				}
				else if (strcmp(argv[i], "true") == 0)
				{
					ifSaveAsPicture = 1;
					i++;
				}
				else if (argv[i][0] != '-')
				{
					printWrongArgumentValue("-saveAsPicture", "false | true");
					i++;
				}
			}
		}
		else if (strcmp(argv[i], "–saveAsTxt") == 0)
		{
			i++;
			if (i < argc)
			{
				if (strcmp(argv[i], "false") == 0)
				{
					ifSaveAsTxT = 0;
					i++;
				}
				else if (strcmp(argv[i], "true") == 0)
				{
					ifSaveAsTxT = 1;
					i++;
				}
				else if (argv[i][0] != '-')
				{
					printWrongArgumentValue("-saveAsTxt", "false | true");
					i++;
				}
			}
		}
		else if (strcmp(argv[i], "–saveEveryIteration") == 0)
		{
			i++;
			if (i < argc)
			{
				if (strcmp(argv[i], "0") == 0)
				{
					ifSaveEveryIteration = 0;
					i++;
				}
				else if (strcmp(argv[i], "1") == 0)
				{
					ifSaveEveryIteration = 1;
					i++;
				}
				else if (strcmp(argv[i], "2") == 0)
				{
					ifSaveEveryIteration = 1;
					i++;
				}
				else if (argv[i][0] != '-')
				{
					printWrongArgumentValue("-saveAsTxt", "0 | 1 | 2");
					i++;
				}
			}
		}
		else if (strcmp(argv[i], "-SBS") == 0)
		{
			i++;
			if (i < argc)
			{
				if (strcmp(argv[i], "false") == 0)
				{
					ifSbS = 0;
					i++;
				}
				else if (strcmp(argv[i], "true") == 0)
				{
					ifSbS = 1;
					i++;
				}
				else if (argv[i][0] != '-')
				{
					printWrongArgumentValue("-SBS", "false | true");
					i++;
				}
			}
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
		fclose(inFile);
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
	if (isHelp == 0)
	{
		for (i = 0; i < numberOfIteration; i++)
		{
			if (ifSaveEveryIteration == 2 && i != 0)
			{
				saveToFile(gameTable, outFileName, ifSaveAsTxT,  ifSaveAsPicture);
			}
			if (ifPrintOnScreen == 1)
			{
				printf("Iteracja %d gry:\n", i);
				printTable(gameTable);
				printf("\n\n");
			}
			compareValue = solveIteration(&gameTable, typeOfProximity, typeOfArea);
			
			if (compareValue == 0)
			{
				break;
			}
			if (ifSbS == 1)
			{
				printf("Naciśnij ENTER, aby przejść dalej.(Aby wyjść z trybu SBS wpisz 'n'). \n");
				if(getchar() == 'n')
				{
					ifSbS = 0;
				}
			}
		}
		if (compareValue == 1)
		{
			if (ifPrintOnScreen == 1)
			{
				printf("Iteracja %d gry:\n", i);
				printTable(gameTable);
			}
			if (ifSaveEveryIteration != 0)
			{
				saveToFile(gameTable, outFileName, ifSaveAsTxT,  ifSaveAsPicture);
			}
		}
	}
	else
	{
		printf("Pomoc programu lifeGameEmulator:\n");
	}
		
	return 0;
}