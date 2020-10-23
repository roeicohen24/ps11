## Block Problem: Dynamic Programming

The goal of this program is to build the tallest tower possible out of a collection
of rectangular blocks. You are given some number of types of blocks, each with its
height, width, and length (all positive integers) specified. You can use as many of
each type of block as you would like, and a block can be placed in any of the three
stable orientations. A block can be stacked on top of another block if and only if the
two dimensions on the base of the top block are smaller than the two dimensions on
the base of the lower block.

We used the following commands to run our program:

```bash
% javac Build.java
% java Build <infile.txt> <outfile.txt>
```

where <infile.txt> is the name of an input file whose contents are in the following
form:
```bash
3
2 6 8
4 4 4
1 10 4
```
The first line specifies the number of block types. Every other line specifies the
dimension of a single block. And <outfile.txt> will contain the output of the program.

### Output
The program prints to the screen the height of the tallest tower
it can build and the number of blocks it uses. To the output file, the program
prints out a list of blocks in the same format as the input. The first line specifies the
number of blocks in the tallest tower. The rest of the lines give the blocks in the
tower, from the base (largest) to the tip (smallest), with the height dimension last
and the first two dimensions in the same order for all the blocks (more specifically, the
smaller of the two numbers first). With the above input the program should print:

```bash
The tallest tower has 3 blocks and a height of 20
```

```bash
3
6 8 2
2 6 8
1 4 10
```

Since the input file specifies block types, not individual blocks, the 2 × 6 × 8 block can appear twice in the solution to the sample problem.



