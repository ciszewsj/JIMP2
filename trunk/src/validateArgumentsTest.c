#include <stdio.h>
#include "validate.h"

int main(int argc, char** argv)
{
	printf("Rozpoczęto test walidacji danych wejściowych:\n");
	arguments* argumentList;
	argumentList = validateArguments(argc, argv);
	printf("\nWczytano dane:\n");
	printf("	Liczba iteracji: %d\n", argumentList->numberOfIteration);
	printf("	Typ sąsiedztwa: %d\n", argumentList->typeOfProximity);
	printf("	Zaokrąglenie planszy: %d\n", argumentList->typeOfArea);
	printf("	Pomoc: %d\n", argumentList->isHelp);
	printf("	Wypisywanie iteracji na ekran: %d\n", argumentList->ifPrintOnScreen);
	printf(" 	Zapis iteracji: %d\n", argumentList->ifSaveEveryIteration);
	printf("	Zapis iteracji do pliku graficznego: %d\n", argumentList->ifSaveAsPicture);
	printf("	Zapis iteracji do pliku tekstowego: %d\n", argumentList->ifSaveAsTxT);
	printf("	Tryb step-by-step: %d\n", argumentList->ifSbS);
	printf("	Ścieżka do pliku wyjściowego: %s\n", argumentList->outFileName);
	printf("	Ścieżka do pliku wejściowego: %s\n", argumentList->inFileName);
	if (argumentList->inFile != NULL)
	{
		printf("	Plik wejściowy istnieje.\n");
	}
	else
	{
		printf("	Plik wejściowy nie istnieje.\n");
	}
	destroyArguments(argumentList);
}
