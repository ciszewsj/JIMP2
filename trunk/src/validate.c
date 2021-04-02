#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>

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
	char* outFileTmpName;
	
	time_t t = time(NULL);
    struct tm *tm = localtime(&t);
	
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
	argumentsList->inFileName = NULL;
	argumentsList->inFile = NULL;
	i = 1;
	
	
	
	outFileTmpName = calloc (21, sizeof(char));
	
	snprintf(outFileTmpName, 20,"%d-%d-%d-%d-%d-%d", tm->tm_year + 1900,
			tm->tm_mon + 1, tm->tm_mday, tm->tm_hour, tm->tm_min, tm->tm_sec);
			
	argumentsList->outFileName = outFileTmpName;
	
	while (i < argc)
	{
		if (strcmp(argv[i], "-dataFile") == 0)
		{
			if (argumentsList->inFileName != NULL)
			{
				free(argumentsList->inFileName);
				free(argumentsList->inFile);
			}
			i++;
			if (i < argc)
			{
				argumentsList->inFileName = calloc(1, strlen(argv[i])+1);
				strcat(argumentsList->inFileName, argv[i]);
				i++;
				while(i < argc)
				{
					
					if (argv[i][0] != '-')
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
					printf("Plik o podanej nazwie: %s nie istnieje. Należy podać nazwę istniejącego pliku wejściowego.\n", argumentsList->inFileName);
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
				if (isNumber(argv[i]) == 1)
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
					argumentsList->ifSaveEveryIteration = 2;
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

void printHelp()
{
	printf("\n\nPomoc Programu lifeGameEmulator:\n");
	printf("Program przeprowadza kolejne iterację gry w życie.\n");
	printf("Autorzy:\n");
	printf("-Ciszewski Jakub\n");
	printf("-Rachachevich Aliaksandra\n\n");
	printf("Program przyjmuje następujące argumenty wywołania:\n");
	printf("-dataFile filePath - ścieżka do pliku zawierającego strukturę planszy. Domyślnie struktura wbudowana.\n");
	printf("-flatArea false|true - zaokrąglenie planszy. Domyślnie zaokrąglona(false).\n");
	printf("-help - wyświetla pomoc programu\n");
	printf("-iterations n - ilość iteracji, którą ma wykonać program. n liczba naturalna większa od 0. Domyślnie 1.\n");
	printf("-outputFilename name - nazwa pliku zapisu struktury. Domyślnie wczytywana przy użyciu czasu lokalnego.\n");
	printf("-printOnScreen false|true - wyświetlanie planszy na ekran. Domyślnie jest wyświetlana(true).\n");
	printf("-proximity moore|neumann - wybór typu sąsiedztwa(Moore'a lub von Neumanna). Domyślnie sąsiedztwo Moore(moore).\n");
	printf("-saveAsPicture false|true - zapis do pliku graficznego. Domyślnie wyłączona(false).\n");
	printf("-saveAsTxT false|true - zapis do pliku tekstowego. Domyślnie wyłączona(false).\n");
	printf("-saveEveryIteration 0|1|2 - zapis do pliku następującej iteracji 0-niezapisuje żadnej, 1-zapisuje ostatnią iteracje, 2-zapisuje wszystkie iteracje. Domyślnie 0.\n");
	printf("-SBS false|true - przejście w tryb step-by-step. Domyślnie wyłączony(false).\n\n\n");
}


void destroyArguments(arguments* argumentsList)
{
	free(argumentsList->inFileName);
	free(argumentsList->inFile);
	free(argumentsList->outFileName);
	free(argumentsList);
	
}


