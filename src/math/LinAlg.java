package math;


public class LinAlg {
	// TODO: Implement the various forms of matrix multiplication
	
	// TODO: Eigenvalue decomposition
	
	// TODO: Inverse
	
	
	
	public Matrix multiply(Matrix m1, Matrix m2) {
		
		double[][] a = m1.getContainer();
		double[][] b = m2.getContainer();
				
		
		if (a == null || b == null || a.length == 0 || b.length == 0) {
			throw new IllegalArgumentException();
		}
		if (a[0].length != b.length) {
			// cannot multiply
			throw new IllegalArgumentException("Incorrect dimensions");
		}
		
		
		double [][] result = new double[a.length][b[0].length];
		
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
}
