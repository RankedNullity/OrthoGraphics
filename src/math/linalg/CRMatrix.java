package math.linalg;



/**
 * Matrix class for handling any linear algebra. Has matrix rotations cheap as they will be used frequently.
 * 0-indexed. (i.e. [[ 1 2 3]
 * 					 [4 5 6 ]] get(0,0) returns 1.) 
 * @author Jaron Wang
 * 
 */
public class CRMatrix implements Matrix {
	private double[][] container;
	private final int rows, columns;
	private int cwRotations;
	
	
	/**
	 * Creates a new matrix with the number of rows and number of columns. (Initially all 0)
	 * @param rows Number of rows
	 * @param columns Number of columns
	 */
	public CRMatrix(int rows, int columns) {
		this(rows, columns, 0);
	}
	
	
	public CRMatrix(int rows, int columns, int rotations) {
		container = new double[rows][columns];
		this.rows = rows;
		this.columns = columns;
		cwRotations = rotations % 4;
	}
	
	public CRMatrix(double[][] nums) {
		this.rows = nums.length;
		this.columns = nums[0].length;
		container = new double[rows][columns];
		for (int i =  0; i < nums.length; i++) {
			for (int j = 0; j < nums[0].length; j++) {
				container[i][j] =  nums[i][j];
			}
		}
		cwRotations = 0;
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
	public CRMatrix deepCopy() {
		CRMatrix copy = new CRMatrix(rows, columns, cwRotations);
		for (int i =  0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				double value = container[i][j];
				copy.container[i][j] = value;
			}
		}
		return copy;
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
		
		int[] realIndices = getRealIndices(m, n);
		return container[realIndices[0]][realIndices[1]];
	}
	
	
	/**
	 * Sets the value at (m, n). Updated for rotations.
	 * @param m
	 * @param n
	 * @param value
	 */
	public void set(int m, int n, double value) {
		int[] realIndices = getRealIndices(m, n);
		container[realIndices[0]][realIndices[1]] = value;
	}
	
	// Method for converting the indices that the user wants into the real index of the array we are storing. 
	private int[] getRealIndices(int i, int j) {
		int x, y;
		switch(cwRotations) {
			default:
				x = i;
				y = j;
				break;
			case 1:
				x = rows - 1 - j;
				y = i;
				break;
			case 2:
				x = rows - 1 - i;
				y = columns - 1 - j;
				break;
			case 3:
				x = j;
				y = columns - 1 - i;
				break;
		}
		return new int[] {x, y};
	}
	
	
	@Override
	public String toString() {
		String ans = "[";
		for (int i = 0; i < getRows(); i++) {
			ans += get(i, 0);
			for (int j = 1; j < getColumns(); j++) {
				double value = get(i, j);
				ans += "  " + value;
			}
			ans += "\n";
		}
		return ans + "]";
	}


	@Override
	public CRMatrix transpose() {
		int rows = getRows();
		int columns = getColumns();
		CRMatrix other = new CRMatrix(columns, rows);
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				other.container[j][i] = get(i , j);
			}
		}
		return other;
	}
	
	/**
	 * Returns a copy of this matrix using the traditionalMatrix representation. 
	 * @return
	 */
	public TrMatrix convertToTraditional() {
		int rows = getRows();
		int columns = getColumns();
		TrMatrix m = new TrMatrix(rows, columns);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				m.set(i,  j,  get(i, j));
			}
		}
		return m;
	}

	public boolean equals(Object cow) {
		if (!(cow instanceof Matrix)) {
			return false;
		}
		
		Matrix other = (Matrix)cow;
		
		if (getRows() != other.getRows() && getColumns() != other.getColumns()) {
			return false;
		}
		for (int i = 0; i < other.getRows(); i++) {
			for (int j = 0; j < other.getColumns(); j++) {
				if (get(i, j) != other.get(i, j)) {
					return false;
				}
			}
		}
		
		return true;
		
		
	}
	
	
}
