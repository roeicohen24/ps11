package Block;

/*
 * Roei Cohen and Becky Zhang
 * CS140 Pset 11 part I
 * March 12, 2020
 */
public class Dimensions implements Comparable<Dimensions>{

	private int b1, b2, h;
	
	/**
	 * Constructor initializes the inputted three dimensions of a block.
	 * 
	 * @param b1, b2, h
	 * 			the three dimensions (two base dimensions and height) of each block
	 */
	public Dimensions (int b1, int b2, int h) {
		this.b1 = b1;
		this.b2 = b2;
		this.h = h;
	}
	
	/**
	 * accessor method for first (smaller) base coordinate
	 * 
	 * @return b1
	 */
	public int getb1 () {
		return b1;
	}
	
	/**
	 * accessor method for second (larger) base coordinate
	 *  
	 * @return b2
	 */
	public int getb2 () {
		return b2;
	}
	
	/**
	 * accessor method for height of block
	 * 
	 * @return h
	 */
	public int getH () {
		return h;
	}
	
	/**
	 * @param that
	 *            Dimensions object to be compared
	 * @return -1, 0, or 1 depending on whether the b1 (smaller dimension of the base of the block) for THIS is
	 *		   lexicographically smaller, same or larger than THAT
	 */
	public int compareTo(Dimensions that) {
		// compare queries of current term argument term and return a matching int value
		if (b1 > that.getb1())
			return -1;
		else if (b1 < that.getb1())
			return 1;
		else // if two queries are the same
			return 0;
	}
	
	public String toString() {
		return b1 + " " + b2 + " " + h;
	}
}
