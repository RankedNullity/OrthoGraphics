package math.linalg;


/**
 * Matrix class for handling any linear algebra. Uses the traditional bare bones double[][] approach to handling matrices. 
 * @author Jaron Wang
 * 
 */
public class TrMatrix implements Matrix {
	private double[][] container;
	
	
	/**
	 * Creates a new matrix with the number of rows and number of columns. (Initially all 0)
	 * @param rows Number of rows
	 * @param columns Number of columns
	 */
	public TrMatrix(int rows, int columns) {
		this(new double[rows][columns]);
	}
	
	public TrMatrix (double[][] nums) {
		if (nums == null || nums.length < 1 || nums[0].length < 1) {
			throw new IllegalArgumentException();
		}
		container = new double[nums.length][nums[0].length];
		for (int i =  0; i < nums.length; i++) {
			for (int j = 0; j < nums[0].length; j++) {
				container[i][j] =  nums[i][j];
			}
		}
	}
	
	/**
	 * Returns a deepcopy of this matrix. 
	 * @return
	 */
	public TrMatrix deepCopy() {
		int rows = getRows();
		int columns = getColumns();
		TrMatrix copy = new TrMatrix(rows, rows);
		for (int i =  0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				copy.container[i][j] =  container[i][j];
			}
		}
		return copy;
	}
	
	/**
	 * Returns the number of rows of this matrix.
	 * @return
	 */
	public int getRows() {
		return container.length;
	}
	
	/**
	 * Returns the number of columns of this matrix.
	 * @return
	 */
	public int getColumns() {
		return container[0].length;
	}
	
	
	/** 
	 * Returns the value at (m, n). Updated for rotations.
	 * @param m
	 * @param n
	 * @return
	 */
	public double get(int m, int n) {
		return container[m][n];
	}
	
	
	/**
	 * Sets the value at (m, n). Updated for rotations.
	 * @param m
	 * @param n
	 * @param value
	 */
	public void set(int m, int n, double value) {
		container[m][n] = value;
	}
	
	
	
	@Override
	public String toString() {
		String ans = "[";
		for (int i = 0; i < getRows(); i++) {
			ans += get(i, 0);
			for (int j = 1; j < getColumns(); j++) {
				ans += "  " + get(i, j);
			}
			ans += "\n";
		}
		return ans + "]";
	}
	
	
	/**
	 * Returns a deep copy of the matrix transpose. Updated with rotations. 
	 * @return
	 */
	public TrMatrix transpose() {
		TrMatrix other = new TrMatrix(getColumns(), getRows());
		for (int i = 0; i < getRows(); i++) {
			for (int j = 0; j < getColumns(); j++) {
				other.container[j][i] = get(i , j);
			}
		}
		return other;
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
