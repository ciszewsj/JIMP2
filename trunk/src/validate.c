#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "validate.h"

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

arguments* validateArguments(int argc, char ** argv)
{
	int i;
	arguments* argumentsList = calloc(1, sizeof(arguments));
	
	argumentsList->numberOfIteration = 1;
	argumentsList->typeOfProximity = 0;
	argumentsList->typeOfArea = 0;
	argumentsList->isHelp = 0;
	argumentsList->ifPrintOnScreen = 1;
	argumentsList->ifSaveEveryIteration = 0;
	argumentsList->ifSaveAsPicture = 0;
	argumentsList->ifSaveAsTxT = 0;
	argumentsList->ifSbS = 0;
	argumentsList->outFileName = NULL;
	argumentsList->inFileName = NULL;
	argumentsList->inFile = NULL;
	i = 1;
	while (i < argc)
	{
		if (strcmp(argv[i], "-dataFile") == 0)
		{
			if (argumentsList->inFileName != NULL)
			{
				free(argumentsList->inFileName);
			}
			i++;
			if (i < argc)
			{
				argumentsList->inFileName = calloc(1, strlen(argv[i])+1);
				strcat(argumentsList->inFileName, argv[i]);
				i++;
				while(i < argc)
				{
					if (argv[i][0]!='-')
					{
						if(realloc(argumentsList->inFileName, strlen(argumentsList->inFileName) + strlen(argv[i]) + 2) == NULL)
						{
							return NULL;
						}
						strcat(argumentsList->inFileName, " \0");
						strcat(argumentsList->inFileName, argv[i]);
						i++;
					}
					else
					{
						break;
					}
				}
				argumentsList->inFile = fopen(argumentsList->inFileName, "r");
				if (argumentsList->inFile == NULL)
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
					argumentsList->typeOfArea = 0;
					i++;
				}
				else if (strcmp(argv[i], "true") == 0)
				{
					argumentsList->typeOfArea = 1;
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
			argumentsList->isHelp = 1;
		}
		else if (strcmp(argv[i], "-iterations") == 0)
		{
			i++;
			if (i < argc)
			{
				if (isNumber(argv[i]))
				{
					argumentsList->numberOfIteration = atoi(argv[i]);
					if (argumentsList->numberOfIteration < 1)
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
		else if (strcmp(argv[i],"-outputFilename") == 0)
		{
			i++;
			if (i < argc)
			{
				if (argumentsList->outFileName != NULL)
				{
					free(argumentsList->outFileName);
				}
				argumentsList->outFileName = calloc(1, strlen(argv[i])+1);
				strcat(argumentsList->outFileName, argv[i]);
				i++;
				while(i < argc)
				{
					if (argv[i][0]!='-')
					{
						if (realloc(argumentsList->outFileName, strlen(argumentsList->outFileName) + strlen(argv[i]) + 2) == NULL)
						{
							return NULL;
						}
						strcat(argumentsList->outFileName, " \0");
						strcat(argumentsList->outFileName, argv[i]);
						i++;
					}
					else
					{
						break;
					}
				}
			}
		}
		else if (strcmp(argv[i], "–printOnScreen") == 0)
		{
			i++;
			if (i < argc)
			{
				if (strcmp(argv[i], "false") == 0)
				{
					argumentsList->ifPrintOnScreen = 0;
					i++;
				}
				else if (strcmp(argv[i], "true") == 0)
				{
					argumentsList->ifPrintOnScreen = 1;
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
					argumentsList->typeOfProximity = 0;
					i++;
				}
				else if (strcmp(argv[i], "neumann") == 0)
				{
					argumentsList->typeOfProximity = 1;
					i++;
				}
				else if (argv[i][0] != '-')
				{
					printWrongArgumentValue("-proximity", "moore | neumann");
				}
			}
		}
		else if (strcmp(argv[i], "-saveAsPicture") == 0)
		{
			i++;
			if (i < argc)
			{
				if (strcmp(argv[i], "false") == 0)
				{
					argumentsList->ifSaveAsPicture = 0;
					i++;
				}
				else if (strcmp(argv[i], "true") == 0)
				{
					argumentsList->ifSaveAsPicture = 1;
					i++;
				}
				else if (argv[i][0] != '-')
				{
					printWrongArgumentValue("-saveAsPicture", "false | true");
					i++;
				}
			}
		}
		else if (strcmp(argv[i], "-saveAsTxT") == 0)
		{
			i++;
			if (i < argc)
			{
				if (strcmp(argv[i], "false") == 0)
				{
					argumentsList->ifSaveAsTxT = 0;
					i++;
				}
				else if (strcmp(argv[i], "true") == 0)
				{
					argumentsList->ifSaveAsTxT = 1;
					i++;
				}
				else if (argv[i][0] != '-')
				{
					printWrongArgumentValue("-saveAsTxt", "false | true");
					i++;
				}
			}
		}
		else if (strcmp(argv[i], "-saveEveryIteration") == 0)
		{
			i++;
			if (i < argc)
			{
				if (strcmp(argv[i], "0") == 0)
				{
					argumentsList->ifSaveEveryIteration = 0;
					i++;
				}
				else if (strcmp(argv[i], "1") == 0)
				{
					argumentsList->ifSaveEveryIteration = 1;
					i++;
				}
				else if (strcmp(argv[i], "2") == 0)
				{
					argumentsList->ifSaveEveryIteration = 1;
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
					argumentsList->ifSbS = 0;
					i++;
				}
				else if (strcmp(argv[i], "true") == 0)
				{
					argumentsList->ifSbS = 1;
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
	return argumentsList;
}

void destroyArguments(arguments* argumentsList)
{
	free(argumentsList->inFileName);
	free(argumentsList->inFile);
	free(argumentsList->outFileName);
	free(argumentsList);
	
}


