package math.linalg;

import java.util.Random;

import common.misc.exceptions.NotYetImplementedException;



/**
 * Linear algebra static class for handling all linalg operators. 
 * @author Jaron Wang, Alex Guo
 *
 */
public class LinAlg {
	// TODO: Implement the various forms of matrix multiplication	

	public static Vector multiply(Matrix m, Vector v) {
		Matrix result = multiplyNaive(m, v);
		double[] ans = new double[result.getRows()];
		for(int i = 0; i < result.getRows(); i++) {
			ans[i] = result.get(i, 0);
		}
		return new Vector(ans, true);
	}
	
	

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
			throw new IllegalArgumentException("Dimension mismatch. Tried to multiply (" + m1.getRows() + ", " + m1.getColumns()
				+ ") by ("	+ m2.getRows() + ", " + m2.getColumns() + ")");
		}
		
		if (m1.getColumns() > 800) {
			return multiplyStrassen(m1, m2);
			
		} 
		
		return multiplyNaive(m1, m2);
	}
	
	// Uses the recursive Strassesn method of multiplying matrices.
	private static Matrix multiplyStrassen(Matrix m1, Matrix m2) {
		// TODO: Implement Strassen multiplication. 
		throw new NotYetImplementedException();
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
	
	
	/**
	 * Returns the n-norm of m.
	 * @param m Matrix
	 * @param n integer
	 * @return
	 */
	public static double norm(Matrix m, int n) {
		double sum = 0; 
		for (int i = 0; i < m.getRows(); i++) {
			for (int j = 0; j < m.getColumns(); j++) {
				sum += Math.pow(m.get(i, j), n);
			}
		}
		return Math.pow(sum, 1.0 / n);
	}
	/**
	 * Returns the elementwise difference of m1 and m2 (m1 - m2)
	 * @param m1
	 * @param m2
	 * @return
	 */
	public static Matrix elementWiseSubtraction(Matrix m1, Matrix m2) {
		if (m1.getRows() != m2.getRows() || m1.getColumns() != m2.getColumns()) {
			throw new IllegalArgumentException();
		} 
		Matrix m = new TrMatrix(m1.getRows(), m1.getColumns());
		for (int i = 0; i < m1.getRows(); i++) {
			for (int j = 0 ; j < m1.getColumns(); j++) {
				m.set(i, j, m1.get(i, j) - m2.get(i, j));
			}
		}
		return m;
	}
	
	
	/**
	 * Sums the matrix. 
	 * @param m
	 * @return
	 */
	public static double sum(Matrix m) {
		return norm(m, 1);
	}
	
	
	/**
	 * Returns the elementwise product of m1 and m2 if their dimensions are equal. 
	 * @throws IllegalArgumentException if m1 or m2 are null. 
	 * @throws IllegalArgumentException if m1 and m2 are of different size. 
	 * @param m1
	 * @param m2
	 * @return
	 */
	public static Matrix elementWiseMultiply(Matrix m1, Matrix m2) {
		if (m1 == null || m2 == null) {
			throw new IllegalArgumentException();
		}
		if (m1.getRows() != m2.getRows() && m1.getColumns() != m2.getColumns()) {
			// cannot multiply
			throw new IllegalArgumentException("Dimension mismatch. Tried to elementwise-multiply (" + m1.getRows() + ", " + m1.getColumns()
				+ ") by ("	+ m2.getRows() + ", " + m2.getColumns() + ")");
		}
		Matrix result = new TrMatrix(m1.getRows(), m1.getColumns());
		for (int i = 0; i < m1.getRows(); i++) {
			for (int j = 0; j < m1.getColumns(); j++) {
				result.set(i, j, m1.get(i, j) * m2.get(i, j));
			}
		}
		return result; 
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
			swapped[1][0] = - m.get(0, 1) / determinant;
			swapped[1][1] = m.get(0, 0) / determinant;
			return new TrMatrix(swapped);
		}
		
		return null;
	}
	
	
}
