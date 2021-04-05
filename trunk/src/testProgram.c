#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

int compareFile(FILE *file1, FILE *file2)
{
    char compare1, compare2;
    int res1, res2;

    while ((res1 = fscanf(file1, "%c", &compare1)) == 1 && (res2 = fscanf(file2, "%c", &compare2)) == 1)
    {
        if (compare1 != compare2 && !isspace(compare1) && !isspace(compare2))
            return 1;
    }
    if (res1 != res2 && (!isspace(compare1) || !isspace(compare1)))
        return 1;
    return 0;
}

int main(int argc, char *argv[])
{
    char filePath[100];
    int number_of_test;
    int typeOfArea;
    int typeOfProximity;
    FILE *file, *file2;

    if (argc != 5)
    {
        printf("\n Niepoprawna liczba argumentów \n");
        return -1;
    }
    number_of_test = atoi(argv[1]);

    if (number_of_test > 3 || number_of_test < 1)
    {
        printf("\n Niepoprawny numer testu \n");
        return -1;
    }

    if (strcmp(argv[2], "flat") == 0)
    {
        typeOfArea = 1;
    }
    else if (strcmp(argv[2], "round") == 0)
    {
        typeOfArea = 0;
    }
    else
    {
        printf("\n Niepoprawny typ planszy \n");
        return -1;
    }

    if (strcmp(argv[3], "moore") == 0)
    {
        typeOfProximity = 0;
    }
    else if (strcmp(argv[3], "neumann") == 0)
    {
        typeOfProximity = 1;
    }
    else
    {
        printf("\n Niepoprawny typ sasiedztwa \n");
        return -1;
    }

    sprintf(filePath, "../%s", argv[4]);

    file = fopen(filePath, "r");
    if (file == NULL)
    {
        printf("\n Niepoprawny plik testowany\n");
        return -1;
    }

    if (typeOfProximity == 0)
    {
        if (typeOfArea == 0)
        {
            switch (number_of_test)
            {
            case 1:
                file2 = fopen("../tests/testsProgramResults/round_moore/Wynik_1.txt", "r");
                break;

            case 2:
                file2 = fopen("../tests/testsProgramResults/round_moore/Wynik_2.txt", "r");
                break;
            case 3:
                file2 = fopen("../tests/testsProgramResults/round_moore/Wynik_3.txt", "r");
                break;
            }
        }
        else
        {
            switch (number_of_test)
            {
            case 1:
                file2 = fopen("../tests/testsProgramResults/flat_moore/Wynik_1.txt", "r");
                if (file2 == NULL)
                    printf("Bla\n");
                break;
            case 2:
                file2 = fopen("../tests/testsProgramResults/flat_moore/Wynik_2.txt", "r");
                break;
            case 3:
                file2 = fopen("../tests/testsProgramResults/flat_moore/Wynik_3.txt", "r");
                break;
            }
        }
    }
    else
    {
        if (typeOfArea == 0)
        {
            switch (number_of_test)
            {
            case 1:
                file2 = fopen("../tests/testsProgramResults/round_neumann/Wynik_1.txt", "r");
                break;
            case 2:
                file2 = fopen("../tests/testsProgramResults/round_neumann/Wynik_2.txt", "r");
                break;
            case 3:
                file2 = fopen("../tests/testsProgramResults/round_neumann/Wynik_3.txt", "r");
                break;
            }
        }
        else
        {
            switch (number_of_test)
            {
            case 1:
                file2 = fopen("../tests/testsProgramResults/flat_neumann/Wynik_1.txt", "r");
                break;

            case 2:
                file2 = fopen("../tests/testsProgramResults/flat_neumann/Wynik_2.txt", "r");
                break;
            case 3:
                file2 = fopen("../tests/testsProgramResults/flat_neumann/Wynik_3.txt", "r");
                break;
            }
        }
    }

    if (file2 == NULL)
    {
        printf("\n Niepoprawny plik testujący \n");
        return -1;
    }

    if (compareFile(file, file2) == 0)
    {
        printf("\n Wynikowy plik jest poprawny \n");
    }
    else
    {
        printf("\n Wynikowy plik nie jest poprawny \n");
    }
    fclose(file);
    fclose(file2);
    return 0;
}