#include <stdio.h>
#include "tableOperation.h"

table* readFromFile(FILE* file) // tu przekazanay jest juz wczytany plik do odczytania

	//przenioslbym wszystkie deklaracje na gore
{	FILE *in= fopen( file, "r" ); // ta czesc niepotrzeba
    if( in == NULL ) {//
    fprintf( stderr, "Plik o podanej nazwie nie istnieje. Należy podać nazwę istniejęcego pliku  wejsciowego" );//
    return EXIT_FAILURE;// do tad
}

	int zmienne[2];
	for (int i = 0; i < 2; i++) {
		fscanf(in, "%d", &zmienne[i]);
	}
	table *gameTable;
	gameTable = initTable(zmienne[0],zmienne[1]);
	int wartosci[zmienne[0]*zmienne[1]] // nie mozna tq zrobic . trzeba uzyc malloc lepiej calloc :(
	
	for (int i = 0; i < zmienne[0] * zmienne[1]; i++)
	{
		fscanf(in, "%d", &wartosci[i]) ; // tu zamiast %d powinnien byc %c wartsosc[i] moze zostac. + dodac sprawdzenie czy nie za malo, (nie za duzo) zwrocic NULL
	}
	int k = 0;
	for (int i = 0; i < zmienne[0]; i++) 
	{
		for (int j = 0; j < zmienne[1]; j++)
			gameTable->board[i][j] = wartosci[k++];
	}

	return gameTable;
}