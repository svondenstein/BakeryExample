Lamport's Bakery Algorithm in Java
==================

## About This Program

This is an example implementation of a Bakery Lock in Java for _n_ threads. The example execution scenario with _n_ threads counts to _n_ * 10000 using a shared variable with each thread incrementing the variable 10000 times.

## Running Locally

1. Clone locally via GitHub Desktop, or via CLI: `git clone https://github.com/svondenstein/BakeryExample.git`
2. Compile the source files in src/: `javac *.java`
3. Run BakeryExample: `java BakeryExample`
4. Enter the number of threads you wish to execute (running with a higher thread count may take longer depending on your machine)
5. The program will execute the counting example and will output the highest value each thread wrote
to the shared variable.

## Licensing

The code in class ThreadID is not mine, and was given in the textbook The Art of Multiprocessor Programming by Maurice Herlihy & Nir Shavit. This program was created as an assignment for CSC 4585 - Multicore Programming at LSU.
