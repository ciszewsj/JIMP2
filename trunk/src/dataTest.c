#include <stdio.h>
#include "tableOperation.h"
#include "fileOperation.h"
int main(int argc, char** argv)
{
	int i;
	FILE* in;
	table* gameTable;
	for (i = 1; i < argc; i++)
	{
		printf("Rozpoczęto test wczytania i zapisu pliku %s:\n", argv[i]);
		in = fopen(argv[i], "r");
		if (in == NULL)
		{
			printf("	Plik o podanej nazwie nie istnieje.\n");
			continue;
		}
		gameTable = readFromFile(in);
		if (gameTable == NULL)
		{
			printf("	Nie udało wczytać się struktury z powodu niepoprawnego formatu, bądź błędu programu.\n");
			continue;			
		}
		else
		{
			printf("	Wczytano dane z pliku do struktury.\n Wygląd wczytanych danych :\n\n");
			printTable(gameTable);
			printf("\n\n");
		}
		printf("	Rozpoczęto test zapisu danych do pliku:\n");
		saveToFile(gameTable, "testsFileResults/test", 1, 1);
		destroyTable(gameTable);
	}
	return 0;
}
