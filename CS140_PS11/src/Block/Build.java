package Block;
import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/*
 * Roei Cohen and Becky Zhang
 * CS140 Pset 11 part I
 * March 12, 2020
 */
public class Build {
	
	private static final int NUM_CONFIGURATIONS = 3; //num of configurations a block can be placed in
	private int n; //number of blocks
	private static int[] dp; //dynamic programming table
	private List<Dimensions> blocks; //a list of Dimension objects that represent the blocks
	private static int[] stackOn; //an array that keeps track of index of the block that the i-th block is stacked upon
	
	/**
	 * instantiates all instance variables
	 * 
	 * @param blocks a list of Dimension objects that represent the blocks
	 */
	public Build (List<Dimensions> blocks) {
		this.blocks = blocks; 
		stackOn = new int[blocks.size()];
		n = blocks.size(); // number of possible blocks to use (includes configurations of each box type)
		dp = new int[n]; // create dp table using array with n entries
	}
	
	/**
	 * builds the dp table by comparing the size of the bases of each block to those following it. Computes the maximum height as 
	 * well as the bottom block in that maxheight tower.
	 * 
	 * @return an int array that contains the maximum tower height at index 0, and the first block at index 1
	 */
	public int[] tower() {
		int max = 0;
		for (int i = n-1; i >= 0; i--) { // build the dp table from right to left
			max = blocks.get(i).getH(); //sets starting max to just the height of that block (if it cannot be stacked under anything)
			stackOn[i] = -1;
			for (int j = i; j <= n-1; j++) { //loops through remaining blocks
				if (blocks.get(j).getb1() < blocks.get(i).getb1() && blocks.get(j).getb2() < blocks.get(i).getb2()) { // compare base dimensions of the two blocks
						if (blocks.get(i).getH() + dp[j] > max) {
							max = blocks.get(i).getH() + dp[j]; // update max
							stackOn[i] = j; //update block it is stacked under in maxheight tower
						}
				}
			}
			dp[i] = max; //update dp table
		}
		
		//extract maximum height and bottom block from the dp table
		max = 0;
		int bottomBlock = -1;
		for (int i = 0; i < n; i++) {
			if (dp[i] > max) {
				max = dp[i]; 
				bottomBlock = i;
			}
		}

		return new int[]{max, bottomBlock};
	}
	
	/**
	 * For testing this class and reading in the input file and writing into the output file.
	 * 
	 * @param args
	 * 			input file of block types
	 */
	public static void main(String[] args) {
		
		try {
			
			// read in and create array of the data
			BufferedReader input = new BufferedReader(new FileReader(args[0]));
			int numBlocks = NUM_CONFIGURATIONS*Integer.parseInt(input.readLine());
			List<Dimensions> blocks = new ArrayList<Dimensions>(numBlocks);
			int[] data = new int[NUM_CONFIGURATIONS];
			
			for (int i = 0; i < numBlocks; i = i + NUM_CONFIGURATIONS) {
				//read every line, create dimensions object for all possible configurations, add dimensions object to blocks
				int k = 0;
				for (String s:(input.readLine().split(" "))) {
					data[k++] = Integer.parseInt(s);
				}
				Arrays.sort(data);
				blocks.add(new Dimensions(data[0],data[1],data[2]));
				blocks.add(new Dimensions(data[0],data[2],data[1]));
				blocks.add(new Dimensions(data[1],data[2],data[0]));
			}
			input.close();

			Collections.sort(blocks); // sort the array by b1 values, as defined in compareTo method
			
			// analyze the data for the tallest tower
			Build bob = new Build(blocks); // Bob the Builder :)
			int[] maxHeight = bob.tower();
			
			// write solution into output file
			BufferedWriter output = new BufferedWriter(new FileWriter(args[1]));
			List<Dimensions> usedBlocks = new ArrayList<Dimensions>();
			int counter = 1;
			
			//trace back the path of used blocks
			usedBlocks.add(blocks.get(maxHeight[1]));
			int next = stackOn[maxHeight[1]];
			while (next != -1) {
				counter++;
				usedBlocks.add(blocks.get(next));
				next = stackOn[next];
			}
			
			//write out the blocks used
			output.write(counter + "");
			output.newLine();
			for (int i = 0; i < usedBlocks.size(); i++) {
				output.write(usedBlocks.get(i).toString());
				output.newLine();
			}
			
			System.out.println("The tallest tower has " + counter + " blocks and a height of " + maxHeight[0]);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
