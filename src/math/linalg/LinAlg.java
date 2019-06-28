package math.linalg;

import java.util.Random;



/**
 * Linear algebra static class for handling all linalg operators. 
 * @author Jaron Wang, Alex Guo
 *
 */
public class LinAlg {
	// TODO: Implement the various forms of matrix multiplication	
	
	/**
	 * Method which returns the matrix which is the matrix product of m1 and m2.
	 * @param m1
	 * @param m2
	 * @return result of multiplication
	 * @throws IllegalArgumentException if m1 or m2 is null, or if dimensions of m1, m2 are incorrect. 
	 */
	public static Matrix multiply(Matrix m1, Matrix m2) {
		if (m1 == null || m2 == null) {
			throw new IllegalArgumentException();
		}
		if (m1.getColumns() != m2.getRows()) {
			// cannot multiply
			throw new IllegalArgumentException("Incorrect dimensions");
		}
		if (m1.getColumns() > 800) {
			return multiplyStrassen(m1, m2);
			
		} 
		
		return multiplyNaive(m1, m2);
	}
	
	// Uses the recursive Strassesn method of multiplying matrices.
	private static Matrix multiplyStrassen(Matrix m1, Matrix m2) {
		// TODO: Implement Strassen multiplication. 
		return null;
	}
	
	
	// Uses the general naive method for multiplying. Intended for small matrices. 
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
		
		return new TrMatrix(result);
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
	
	/**
	 * Returns the zero matrix of specified size. 
	 * @param rows
	 * @param columns
	 * @return
	 */
	public static Matrix zeroMatrix(int rows, int columns) {
		return new TrMatrix(rows, columns);
	}
	
	
	/**
	 * Returns the (dim x dim) identity matrix.  
	 * @param dim
	 * @return
	 */
	public static Matrix identityMatrix(int dim) {
		Matrix m = new TrMatrix(dim, dim);
		for (int i = 0; i < dim; i++) {
			m.set(i, i, 1);
		}
		return m;
	}
	
	
	/**
	 * Returns a matrix with specified size and random values in each entry. 
	 * @param rows
	 * @param columns
	 * @return
	 */
	public static Matrix randomMatrix(int rows, int columns) {
		Random r = new Random();
		Matrix m = new TrMatrix(rows, columns);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				m.set(i, j, r.nextDouble());
			}
		}
		return m;
	}
	
	
	/**
	 * Inverts a matrix. Currently only implemented for 2x2s.
	 * @param m
	 * @return
	 */
	public static Matrix inverse(Matrix m) {
		
		if (m.getRows() != m.getColumns()) {
			throw new IllegalArgumentException("not invertable matrix");
		}
		
		double determinant = 0;
		if (m.getRows() == 2 && m.getColumns() == 2) {
			determinant = (m.get(0, 0) * m.get(1, 1) - m.get(0, 1) * m.get(1, 0));
			
			//swap abcd to d-b-ca
			double[][] swapped = new double[2][2];
			swapped[0][0] = m.get(1, 1) / determinant;
			swapped[0][1] = - m.get(1, 0) / determinant;
			swapped[1][0] = -m.get(0, 1) / determinant;
			swapped[1][1] = m.get(0, 0) / determinant;
			return new TrMatrix(swapped);
			
		}
		
		return null;
	}
	
	
}
