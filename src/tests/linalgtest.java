package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import math.linalg.CRMatrix;
import math.linalg.TrMatrix;

//TODO: Write tests
class linalgtest {

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
		assertEquals(trm1, trm2);
	}
	
	@Test
	void testTrMatrixGets() {
		double[][] matrixContent = {{1, 2}, {3, 4}};
		TrMatrix trm1 = new TrMatrix(matrixContent);
		
		// test getRows() and getColumns()
		assertSame(trm1.getRows(), 2);
		assertSame(trm1.getColumns(), 2);
		
		// test get
		assertSame(trm1.get(1, 1), 4);
		
		trm1.set(1, 1, 5);
		
		// test set
		assertSame(trm1.get(1, 1), 5);
	}
	
	@Test
	void testTrMatrixTranspose() {
		// testing transpose
		double[][] matrixContent = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		TrMatrix trm1 = new TrMatrix(matrixContent);
		double[][] matrixTransposeContent = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
		TrMatrix trmT = new TrMatrix(matrixTransposeContent);
		
		trm1.transpose();
		assertEquals(trm1, trmT);

	}
	
	@Test
	// testing convert to traditional & rotate I guess
	void testCRMatrixConvertToTraditional() {
		double[][] matrixContent = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		TrMatrix trm1 = new TrMatrix(matrixContent);
		CRMatrix crm1 = new CRMatrix(matrixContent);
		
		assertEquals(trm1, crm1.convertToTraditional());
		
		double[][] matrixContent2 = {{9, 8, 7}, {6, 5, 4}, {3, 2, 1}};
		TrMatrix trm2 = new TrMatrix(matrixContent2);
		crm1.clockwiseRotate(2);
		
		assertEquals(trm2, crm1.convertToTraditional());
		
	}

}
