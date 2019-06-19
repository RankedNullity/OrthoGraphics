package linalg;

public class Matrix {
	private int[][] internal;
	private int m, n;
	
	/**
	 * Creates a new matrix that is a deep copy of m
	 * @param m Matrix to deep copy
	 */
	public Matrix(Matrix m) {
		// TODO Finish
	}
	
	/**
	 * Creates a new matrix with the number of rows and number of columns.
	 * @param rows Number of rows
	 * @param columns Number of columns
	 */
	public Matrix(int rows, int columns) {
		internal = new int[rows][columns];
	}
	
	
	/** 
	 * Returns the value at (m, n)
	 * @param m
	 * @param n
	 * @return
	 */
	public int get(int m, int n) {
		return internal[m][n];
	}
	
	
	public void set(int m, int n, int value) {
		internal[m][n] = value;
	}
	
}
