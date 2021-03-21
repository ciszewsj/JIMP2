#include <stdio.h>
#include "tableOperation.h"

table* readFromFile(FILE* file)
{	FILE *in= fopen( file, "r" );
    if( in == NULL ) {
    fprintf( stderr, "Plik o podanej nazwie nie istnieje. Należy podać nazwę istniejęcego pliku  wejsciowego" );
    return EXIT_FAILURE;
}

	int zmienne[2];
	for (int i = 0; i < 2; i++) {
		fscanf(in, "%d", &zmienne[i]);
	}
	table *gameTable;
	gameTable = initTable(zmienne[0],zmienne[1]);
	int wartosci[zmienne[0]*zmienne[1]]
	
	for (int i = 0; i < zmienne[0] * zmienne[1]; i++){
		fscanf(in, "%d", &wartosci[i]) ;
	}
	int k = 0;
	for (int i = 0; i < zmienne[0]; i++){
		for (int j = 0; j < zmienne[1]; j++)
			gameTable->board[i][j] = wartosci[k++];
	}

	return gameTable;
}