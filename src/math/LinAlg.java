package math;


public class LinAlg {
	// TODO: Implement the various forms of matrix multiplication
	
	// TODO: Eigenvalue decomposition
	
	// TODO: Inverse
	
	
	
	public Matrix multiply(Matrix m1, Matrix m2) {
		
		int m1_row = m1.getRows();
		int m1_col = m1.getColumns();
		int m2_row = m2.getRows();
		int m2_col = m2.getColumns();
		
		// initialize matrix1
		double[][] a = new double[m1_row][m1_col];
		for (int i = 0; i < m1_row; i++) {
			for (int j = 0; j < m1_col; j++) {
				a[i][j] = m1.get(i, j);
			}
		}
		
		
		// initialize matrix2
		double[][] b = new double[m2_row][m2_col];
		for (int i = 0; i < m2_row; i++) {
			for (int j = 0; j < m2_col; j++) {
				b[i][j] = m2.get(i, j);
			}
		}
		
		// root out cases when you can't do matrix multiply
		if (a == null || b == null || a.length == 0 || b.length == 0) {
			throw new IllegalArgumentException();
		}
		if (a[0].length != b.length) {
			// cannot multiply
			throw new IllegalArgumentException("Incorrect dimensions");
		}
		
		
		double [][] result = new double[a.length][b[0].length];
		
		// dot product of each row in m1 with each col in m2
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				result[i][j] = 0;
				for (int k = 0; k < result.length; k++) {
					result[i][j] += a[i][k] * b[k][j];
				}
			}
		}
		
		Matrix product = new Matrix(result);
		return product;
	}
	
	
	// dot product function just nice to have
	public double dotProuct(double[] v1, double[] v2) {
		if (v1 != null && v2 != null && v1.length != v2.length) {
			throw new IllegalArgumentException();
		}
		
		double result = 0;
		for (int i = 0; i < v1.length; i++) {
			result += v1[i] * v2[i];
		}
		
		return result;
		
		//test
	}
	
	
	// turns Matrix object into 2D array
	private double[][] arrayForm(Matrix m) {
		if (m == null) {
			throw new IllegalArgumentException("matrix size is null");
		}
		int row = m.getRows();
		int col = m.getColumns();
		
		double[][] result = new double[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				result[i][j] = m.get(i, j);
			}
		}
		return result;
	}
	
	public Matrix inverse(Matrix m) {
		
		double[][] matrix = arrayForm(m);
		if (matrix.length != matrix[0].length) {
			throw new IllegalArgumentException("not invertable matrix");
		}
		
		double determinant = 0;
		if (matrix.length == 2 && matrix.length == matrix[0].length) {
			determinant = (matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]);
			
			//swap abcd to d-b-ca
			double[][] swapped = new double[2][2];
			swapped[0][0] = matrix[1][1] / determinant;
			swapped[0][1] = -matrix[1][0] / determinant;
			swapped[1][0] = -matrix[0][1] / determinant;
			swapped[1][1] = matrix[0][0] / determinant;
			
		}
		
		return null;
	}
}
