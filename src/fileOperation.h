#include "tableOperation.h"

#ifndef _FILE_OPERATION_
#define _FILE_OPERATION_


table* readFromFile(FILE* file);

void saveToFile(table* gameTable, char* outFileName, int toTxt, int toPicture);

#endif