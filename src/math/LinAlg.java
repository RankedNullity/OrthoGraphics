package math;


public class LinAlg {
	// TODO: Implement the various forms of matrix multiplication
	
	// TODO: Eigenvalue decomposition
	
	// TODO: Inverse
	
	
	
	public Matrix multiply(Matrix m1, Matrix m2) {
		
		int m1_row = m1.shape().get(0);
		int m1_col = m1.shape().get(1);
		int m2_row = m2.shape().get(0);
		int m2_col = m2.shape().get(1);
		
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
	}
}
