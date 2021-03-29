#ifndef _TABLE_OPERATION_
#define _TABLE_OPERATION_

extern char aliveCell;
extern char deadCell;

struct tableStruct{
	int columns;
	int rows;
	char ** board;
}typedef table;

table* initTable(int columns, int rows); 

table* initDefaultTable();

void destroyTable(table* Table);

int compareTable(table* gameTable1, table* gameTable2);

void printTable(table* gameTable);
#endif