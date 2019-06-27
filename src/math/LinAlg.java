package math;


public class LinAlg {
	// TODO: Implement the various forms of matrix multiplication
	
	// TODO: Eigenvalue decomposition
	
	// TODO: Inverse
	
	
	
	public static Matrix multiply(Matrix m1, Matrix m2) {
		if (m1 == null || m2 == null) {
			throw new IllegalArgumentException();
		}
		if (m1.getColumns() != m2.getRows()) {
			// cannot multiply
			throw new IllegalArgumentException("Incorrect dimensions");
		}
		if (m1.getColumns() < 800) {
			return multiplyNaive(m1, m2);
		} 
		return multiplyStrassen(m1, m2);
		
	}
	
	
	
	private static Matrix multiplyStrassen(Matrix m1, Matrix m2) {
		// TODO: Implement Strassen multiplication. 
		return null;
	}
	
	
	/**
	 * Method which returns the matrix which is the matrix product of m1 and m2.
	 * @param m1
	 * @param m2
	 * @return result of multiplication
	 * @throws IllegalArgumentException if m1 or m2 is null, or if dimensions of m1, m2 are incorrect. 
	 */
	private static Matrix multiplyNaive(Matrix m1, Matrix m2) {
		// root out cases when you can't do matrix multiply
		if (m1 == null || m2 == null) {
			throw new IllegalArgumentException();
		}
		
		int m1_col = m1.getColumns();
		int m2_row = m2.getRows();
		
		if (m1_col != m2_row) {
			// cannot multiply
			throw new IllegalArgumentException("Incorrect dimensions");
		}
		
		int m1_row = m1.getRows();
		int m2_col = m2.getColumns();
		
		double [][] result = new double[m1_row][m2_col];
		
		// dot product of each row in m1 with each col in m2
		for (int i = 0; i < m1_row; i++) {
			for (int j = 0; j < m2_col; j++) {
				double total = 0;
				for (int k = 0; k < m1_col; k++) {
					total += m1.get(i, k) * m2.get(k, j);
				}
				result[i][j] = total;
			}
		}
		
		return new Matrix(result);
	}
	
	
	// dot product function just nice to have
	public static double dotProuct(double[] v1, double[] v2) {
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
}
