package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import math.linalg.*;

//TODO: Write tests
class linalgtest {

	@Test
	void test() {
		assertEquals(1,1);
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

}
