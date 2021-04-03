all:main.o fileOperation.o tableOperation.o printBmp.o proximity.o solver.o validate.o
	gcc -Wall --pedantic src/main.c fileOperation.o tableOperation.o printBmp.o proximity.o solver.o validate.o -o bin/lifeGameEmulator
test_file_operation:fileOperation.o tableOperation.o dataTest.o
	gcc -Wall --pedantic dataTest.o fileOperation.o tableOperation.o printBmp.o -o tests/testFileOperation

main.o: src/main.c
	cc -c src/main.c
fileOperation.o:src/fileOperation.c
	cc -c src/fileOperation.c
tableOperation.o:src/tableOperation.c
	cc -c src/tableOperation.c
printBmp.o: src/printBmp.c
	cc -c src/printBmp.c
proximity.o: src/proximity.c
	cc -c src/proximity.c
solver.o: src/solver.c
	cc -c src/solver.c
validate.o: src/validate.c
	cc -c src/validate.c
dataTest.o:src/dataTest.c
	cc -c src/dataTest.c