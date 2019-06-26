package math;

import java.util.Random;

import common.Tuple;
/**
 * Matrix class for handling any linear algebra. Has matrix rotations cheap as they will be used frequently.
 * 0-indexed. (i.e. [[ 1 2 3]
 * 					 [4 5 6 ]] get(0,0) returns 1.) 
 * @author Jaron Wang
 *
 */
public class Matrix {
	private double[][] container;
	private final int rows, columns;
	private int cwRotations;
	
	
	/**
	 * Creates a new matrix with the number of rows and number of columns. (Initially all 0)
	 * @param rows Number of rows
	 * @param columns Number of columns
	 */
	public Matrix(int rows, int columns) {
		this(rows, columns, 0);
	}
	
	
	public Matrix(int rows, int columns, int rotations) {
		container = new double[rows][columns];
		this.rows = rows;
		this.columns = columns;
		cwRotations = rotations % 4;
	}
	
	/**
	 * Rotates the matrix by the number of quarter clockwise turns.
	 * 
	 */
	public Matrix (double[][] nums) {
		if (nums == null || nums.length < 1 || nums[0].length < 1) {
			// error
		}
		container = nums;
		rows = nums.length;
		columns = nums[0].length;
		cwRotations = 4;
	}
	
	public double[][] getContainer() {
		return container;
	}
	
	/**
	 * Rotates the matrix by the number of quarter clockwise turns.
	 * @param rotations The number of rotations.
	 */
	public void clockwiseRotate(int rotations) {
		cwRotations = (cwRotations + rotations) % 4;
	}
	
	/**
	 * Returns a deepcopy of this matrix. Updated for rotations. 
	 * @return
	 */
	public Matrix deepCopy() {
		Matrix copy = new Matrix(rows, columns, cwRotations);
		for (int i =  0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				double value = container[i][j];
				copy.container[i][j] = value;
			}
		}
		return copy;
	}
	
	
	/**
	 * Returns the dims of the Matrix. Updated for rotations.
	 * @return
	 */
	public Tuple shape() {
		return new Tuple(getRows(), getColumns());
	}
	
	/**
	 * Returns the number of rows of this matrix.
	 * @return
	 */
	public int getRows() {
		if(cwRotations == 0 || cwRotations == 2) {
			return rows;
		} else {
			return columns;
		}
	}
	
	/**
	 * Returns the number of columns of this matrix.
	 * @return
	 */
	public int getColumns() {
		if(cwRotations == 0 || cwRotations == 2) {
			return columns;
		} else {
			return rows;
		}
	}
	
	
	/** 
	 * Returns the value at (m, n). Updated for rotations.
	 * @param m
	 * @param n
	 * @return
	 */
	public double get(int m, int n) {
		if (m < 0 || m >= getRows() || n < 0 || n >= getColumns()) {
			throw new IndexOutOfBoundsException();
		}
		
		Tuple realIndices = getRealIndices(m, n);
		return container[realIndices.get(0)][realIndices.get(1)];
	}
	
	
	/**
	 * Sets the value at (m, n). Updated for rotations.
	 * @param m
	 * @param n
	 * @param value
	 */
	public void set(int m, int n, double value) {
		Tuple realIndices = getRealIndices(m, n);
		container[realIndices.get(0)][realIndices.get(1)] = value;
	}
	
	// Method for converting the indices that the user wants into the real index of the array we are storing. 
	private Tuple getRealIndices(int i, int j) {
		int x, y;
		switch(cwRotations) {
			default:
				x = i;
				y = j;
				break;
			case 1:
				x = rows - i;
				y = j;
				break;
			case 2:
				x = rows - i;
				y = columns - j;
				break;
			case 3:
				x = i;
				y = columns - j;
				break;
		}
		return new Tuple(x, y);
	}
	
	
	@Override
	public String toString() {
		String ans = "[";
		for (int i = 0; i < getRows(); i++) {
			ans += get(i, 0);
			for (int j = 1; j < getColumns(); j++) {
				ans += "  " + get(i, j);
			}
			ans += "/n";
		}
		return ans + "]";
	}
	
	
	/**
	 * Returns a deep copy of the matrix transpose. Updated with rotations. 
	 * @return
	 */
	public Matrix transpose() {
		Matrix other = new Matrix(columns, rows);
		for (int i = 0; i < getRows(); i++) {
			for (int j = 0; j < getColumns(); j++) {
				other.container[j][i] = get(i , j);
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
