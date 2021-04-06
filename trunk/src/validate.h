#ifndef _VALIDATE_
#define _VALIDATE_

struct argumentsStruct{
	int numberOfIteration;
	int typeOfProximity;
	int typeOfArea;
	int isHelp;
	int ifPrintOnScreen;
	int ifSaveEveryIteration;
	int ifSaveAsPicture;
	int ifSaveAsTxT;
	int ifSbS;
	char* outFileName;
	char* inFileName;
	FILE * inFile;
}typedef arguments;

arguments* validateArguments(int argc, char** argv);
void printHelp();
void destroyArguments(arguments* argumentsList);
#endif