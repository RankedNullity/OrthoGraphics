package linalg;

import java.util.Random;

import common.Tuple;

public class Matrix {
	private double[][] container;
	private int rows, columns;
	
	/**
	 * Creates a new matrix that is a deep copy of m
	 * @param m Matrix to deep copy
	 */
	public Matrix(Matrix m) {
		rows = m.rows;
		columns = m.columns;
		container = new double[rows][columns];
		for (int i =  0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				double value = m.container[i][j];
				container[i][j] = value;
			}
		}
	}
	
	/**
	 * Creates a new matrix with the number of rows and number of columns. (Initially all 0)
	 * @param rows Number of rows
	 * @param columns Number of columns
	 */
	public Matrix(int rows, int columns) {
		container = new double[rows][columns];
		this.rows = rows;
		this.columns = columns;
	}
	
	
	public int shape() {
		return 1;
	}
	
	/** 
	 * Returns the value at (m, n)
	 * @param m
	 * @param n
	 * @return
	 */
	public double get(int m, int n) {
		return container[m][n];
	}
	
	
	
	public void set(int m, int n, double value) {
		container[m][n] = value;
	}
	
	
	/**
	 * Returns a deep copy of the matrix transpose. 
	 * @return
	 */
	public Matrix transpose() {
		Matrix other = new Matrix(columns, rows);
		for (int i = 0; i < columns; i++) {
			for (int j = 0; j < rows; j++) {
				double value = this.container[j][i];
				other.container[i][j] = value;
			}
		}
		return other;
	}
	
	
	/**
	 * Changes the values inside the matrix to be a random value between 0.0 and 1.0
	 * @param seed Random number generator seed.
	 */
	public void randomize(int seed) {
		Random r = new Random(seed);
		for (int i = 0; i < rows; i++) {
			for (int j= 0; j < columns; j++) {
				container[i][j] = r.nextDouble();
			}
		}
	}
	
	/**
	 * Changes the values inside the matrix to be a random value between 0.0 and 1.0 using a
	 * random seed.
	 */
	public void randomize() {
		Random r = new Random();
		for (int i = 0; i < rows; i++) {
			for (int j= 0; j < columns; j++) {
				container[i][j] = r.nextDouble();
			}
		}
	}
	
	
	/**
	 * Returns a matrix with specified size and random values in each entry. 
	 * @param rows
	 * @param columns
	 * @return
	 */
	public static Matrix randomMatrix(int rows, int columns) {
		Matrix m = new Matrix(rows, columns);
		m.randomize();
		return m;
	}
	
	/**
	 * Returns the zero matrix of specified size. 
	 * @param rows
	 * @param columns
	 * @return
	 */
	public static Matrix zeroMatrix(int rows, int columns) {
		return new Matrix(rows, columns);
	}
	
	
	/**
	 * Returns the (dim x dim) identity matrix.  
	 * @param dim
	 * @return
	 */
	public static Matrix identityMatrix(int dim) {
		Matrix m = new Matrix(dim, dim);
		for (int i = 0; i < dim; i++) {
			m.container[i][i] = 1;
		}
		return m;
	}
	
	
}
