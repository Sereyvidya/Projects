all: proc_manager matrixmult

proc_manager: proc_manager.c
	gcc -o proc_manager proc_manager.c

matrixmult: ../A1/matrixmult.c
	gcc -o ../A1/matrixmult ../A1/matrixmult.c

clean:
	rm -f *.out -f *.err proc_manager ../A1/matrixmult
