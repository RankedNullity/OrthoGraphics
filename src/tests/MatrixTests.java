package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import math.linalg.CRMatrix;
import math.linalg.LinAlg;
import math.linalg.Matrix;
import math.linalg.TrMatrix;
import math.linalg.Vector;

class MatrixTests {
	
	@Test
	void test() {
		fail("Not yet implemented");
	}
	@Test
	void testTrMatrixCreation() {
		double[][] matrixContent = {{0, 0}, {0, 0}};
		TrMatrix trm1 = new TrMatrix(matrixContent);
		TrMatrix trm2 = new TrMatrix(2, 2);
		assertEquals(trm1, trm2);
	}
	
	@Test
	void testTrMatrixDeepCopy() {
		double[][] matrixContent = {{1, 2}, {3, 4}};
		TrMatrix trm1 = new TrMatrix(matrixContent);
		TrMatrix trm2 = trm1.deepCopy();
		//System.out.print(trm1.toString());
		//System.out.print(trm2.toString());
		assertEquals(trm1, trm2);
	}
	
	@Test
	void testTrMatrixGets() {
		double[][] matrixContent = {{1, 2}, {3, 4}};
		TrMatrix trm1 = new TrMatrix(matrixContent);
		
		// test getRows() and getColumns()
		assertEquals(trm1.getRows(), 2);
		assertEquals(trm1.getColumns(), 2);
		
		// test get
		assertEquals(trm1.get(1, 1), 4);
		
		trm1.set(1, 1, 5);
		
		// test set
		assertEquals(trm1.get(1, 1), 5);
	}
	
	@Test
	void testTrMatrixTranspose() {
		// testing transpose
		double[][] matrixContent = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		TrMatrix trm1 = new TrMatrix(matrixContent);
		double[][] matrixTransposeContent = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
		TrMatrix trmT = new TrMatrix(matrixTransposeContent);
		
		trm1 = trm1.transpose();
		assertEquals(trm1, trmT);

	}
	
	@Test
	// testing CRMatrix Rotations;
	void testCRMatrixRotations() {
		double[][] simple2x2 = {{1, 2}, {3, 4}};
		double[][] simple2x2Rotated = {{3, 1}, {4, 2}};
		CRMatrix m1 = new CRMatrix(simple2x2);
		CRMatrix m2 = new CRMatrix(simple2x2Rotated);
		m1.clockwiseRotate(1);
		//assertEquals(m2, m1);
		
		CRMatrix nonSq = new CRMatrix(new double[][]{{1,2,3},{4,5,6}});
		CRMatrix nonSqRot1 = new CRMatrix(new double[][]{{4,1}, {5, 2}, {6, 3}});
		CRMatrix nonSqRot2 = new CRMatrix(new double[][]{{6, 5, 4}, {3, 2, 1}});
		CRMatrix nonSqRot3 = new CRMatrix(new double[][]{{3, 6}, {2, 5}, {1, 4}});
		
		nonSq.clockwiseRotate(1);
		assertEquals(nonSqRot1, nonSq);
		nonSq.clockwiseRotate(1);
		assertEquals(nonSqRot2, nonSq);
		nonSq.clockwiseRotate(1);
		assertEquals(nonSqRot3, nonSq);
		
	}
	
	@Test
	// testing vector * matrix when dimensions are correct
	void testVectorMatrixMultiplyCorrectDimensions() {
		Vector v1 = new Vector(new double[]{1, 2, 3}, true);
		TrMatrix trm1 = new TrMatrix(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
		Matrix answer = new TrMatrix(new double[][]{{14}, {32}, {50}}); 
		assertEquals(answer, LinAlg.multiply(trm1, v1));
		
		Vector v2 = new Vector(new double[]{1, 2, 3}, false);
		Matrix answer2 = new TrMatrix(new double[][]{{30, 36, 42}});
		assertEquals(answer2, LinAlg.multiply(v2, trm1));
		
		Vector v3 = new Vector(new double[]{2}, true);
		TrMatrix trm3 = new TrMatrix(new double[][]{{4}});
		Matrix answer3 = new TrMatrix(new double[][]{{8}});
		assertEquals(answer3, LinAlg.multiply(v3, trm3));
		assertEquals(answer3, LinAlg.multiply(trm3, v3));
		
	}
	
	@Test
	// testing vector * matrix when dimensions are incorrect
	void testVectorMatrixMultiplyWrongDimensions() {
		Vector v1 = new Vector(new double[]{1, 2, 3}, true);
		TrMatrix trm1 = new TrMatrix(new double[][]{{1, 2}, {3, 4}, {5, 6}});
		assertThrows(IllegalArgumentException.class, () -> LinAlg.multiply(trm1, v1));

		TrMatrix trm2 = new TrMatrix(new double[][]{{1}, {2}, {3}});
		assertThrows(IllegalArgumentException.class, () -> LinAlg.multiply(trm2, v1));
		
		TrMatrix trm3 = new TrMatrix(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
		assertThrows(IllegalArgumentException.class, () -> LinAlg.multiply(v1, trm3));
	}
	
	@Test
	// testing vector * vector
	void testVectorMultiplySelf() {
		Vector v1 = new Vector (new double[]{1, 2, 3, 4}, true);
		Vector v2 = new Vector (new double[]{1, 2, 3, 4}, false);
		Vector v3 = new Vector (new double[]{}, true);
		Matrix answer1 = new TrMatrix(new double[][]{{1, 2, 3, 4}, {2, 4, 6, 8}, {3, 6, 9, 12}, {4, 8, 12, 16}});
		Matrix answer2 = new TrMatrix(new double[][]{{30}});
		
		assertEquals(answer1, LinAlg.multiply(v1, v2));
		assertEquals(answer2, LinAlg.multiply(v2, v1));
		assertThrows(IllegalArgumentException.class, () -> LinAlg.multiply(v1, v1));
		assertThrows(IllegalArgumentException.class, () -> LinAlg.multiply(v2, v2));
		assertThrows(IllegalArgumentException.class, () -> LinAlg.multiply(v1, v3));
		assertThrows(IllegalArgumentException.class, () -> LinAlg.multiply(v2, v3));
		assertThrows(IllegalArgumentException.class, () -> LinAlg.multiply(v3, v3));
		
	}

}
