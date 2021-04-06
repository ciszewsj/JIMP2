all:main.o fileOperation.o tableOperation.o printBmp.o proximity.o solver.o validate.o
	gcc -Wall --pedantic src/main.c fileOperation.o tableOperation.o printBmp.o proximity.o solver.o validate.o -o bin/lifeGameEmulator
test_file_operation:fileOperation.o tableOperation.o dataTest.o printBmp.o
	gcc -Wall --pedantic dataTest.o fileOperation.o tableOperation.o printBmp.o -o tests/testFileOperation
test_arguments_validate:validateArgumentsTest.o validate.o
	gcc -Wall --pedantic validateArgumentsTest.o validate.o -o tests/testValidateArguments
test_program:testProgram.o
	gcc -Wall --pedantic testProgram.o -o tests/testProgram

do_file_test:
	tests/testFileOperation tests/testsFile/dane_1.txt tests/testsFile/dane_2.txt tests/testsFile/dane_3.txt tests/testsFile/dane_4.txt tests/testsFile/dane_4.txt tests/testsFile/dane_5.txt tests/testsFile/dane_6.txt tests/testsFile/dane_7.txt tests/testsFile/dane_8.txt tests/testsFile/dane_9.txt tests/testsFile/dane_10.txt tests/testsFile/zle_dane_1.txt tests/testsFile/zle_dane_2.txt tests/testsFile/zle_dane_3.txt tests/testsFile/zle_dane_4.txt tests/testsFile/zle_dane_5.txt
	
do_arg_test:
	tests/testValidateArguments
	tests/testValidateArguments -help -iterations 10 -proximity neumann -outputFilename test programu -SBS true -saveEveryIteration 1 -dataFile tests/testsFile/dane_1.txt
	tests/testValidateArguments -SBS true -proximity moore -saveAsTxT false -saveAsPicture true -flatArea true -printOnScreen false -saveAsTxT true
	tests/testValidateArguments -saveEveryIteration 0 -flatArea true -help -iterations 10 -outputFilename tests/test_zapisu_22 -printOnScreen false -proximity moore -saveAsTxT false -saveEveryIteration 0 -SBS fale
	tests/testValidateArguments -saveEveryIteration 2 -flatArea false -iterations 10000000000000 -outputFilename tests/test zapisu 22 -printOnScreen true -proximity neumann -saveAsTxT true -saveEveryIteration true -SBS true
	tests/testValidateArguments -help true -iterations a -outputFilename -123 -printOnScreen 1 -proximity neighbour -saveAsPicture yes -saveAsTxT no -SBS ofcourse

do_program_test:
	tests/testProgram
	

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
validateArgumentsTest.o:src/validateArgumentsTest.c
	cc -c src/validateArgumentsTest.c
testProgram.o:src/testProgram.c
	cc -c src/testProgram.c