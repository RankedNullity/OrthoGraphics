package tests;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import math.linalg.*;

//TODO: Write tests
class linalgtest {

	@Test
	void test() {
		fail("Not implemented");
	}
	
	@Test
	// tests creation of zero matrix
	void testZeroMatrix() {
		int n1 = 2;
		int n2 = 20;
		int n3 = -3;
		int n4 = 0;
		
		assertEquals(new TrMatrix(n1, n2), LinAlg.zeroMatrix(n1, n2));
		assertEquals(new TrMatrix(n2, n1), LinAlg.zeroMatrix(n2, n1));
		assertThrows(IllegalArgumentException.class, () -> LinAlg.zeroMatrix(n1, n3));
		assertThrows(IllegalArgumentException.class, () -> LinAlg.zeroMatrix(n1, n4));
		assertThrows(IllegalArgumentException.class, () -> LinAlg.zeroMatrix(n3, n4));
	}
	
	@Test
	// work in progress
	void testElementWiseSubtraction() {
		TrMatrix tr1 = new TrMatrix(2, 1);
		TrMatrix tr2 = new TrMatrix(new double[][] {{3}, {3}});
		Vector v1 = new Vector(new double[]{3, 3}, true);
		CRMatrix cr1 = new CRMatrix(new double[][] {{3}, {3}});
		
		assertEquals(LinAlg.elementWiseSubtraction(v1, tr1), LinAlg.elementWiseSubtraction(tr2, tr1));
		assertEquals(LinAlg.elementWiseSubtraction(v1, v1), LinAlg.elementWiseSubtraction(tr2, tr2));
		assertEquals(LinAlg.elementWiseSubtraction(v1, cr1), LinAlg.elementWiseSubtraction(tr2, cr1));
		assertEquals(LinAlg.elementWiseSubtraction(v1, cr1), tr1);
		assertThrows(IllegalArgumentException.class, () -> LinAlg.elementWiseSubtraction(null, cr1));

	}
	
	@Test
	// tests element wise multiplication
	void testElementWiseMultiplication() {
		TrMatrix tr1 = new TrMatrix(2, 1);
		TrMatrix tr2 = new TrMatrix(5, 9);
		TrMatrix tr3 = new TrMatrix(new double[][] {{3}, {3}});
		Vector v1 = new Vector(new double[]{3, 3}, true);
		CRMatrix cr1 = new CRMatrix(new double[][] {{3}, {3}});
		
		// multiplying matrices of unequal size
		assertThrows(IllegalArgumentException.class, () -> LinAlg.elementWiseMultiply(tr1, tr2));
		assertThrows(IllegalArgumentException.class, () -> LinAlg.elementWiseMultiply(tr2, tr1));
		
		// passing null as parameter
		assertThrows(IllegalArgumentException.class, () -> LinAlg.elementWiseMultiply(null, tr1));

		assertEquals(LinAlg.elementWiseMultiply(v1, tr1), LinAlg.elementWiseMultiply(v1, cr1));
		assertEquals(LinAlg.elementWiseMultiply(v1, v1), LinAlg.elementWiseMultiply(tr2, tr2));
		assertEquals(LinAlg.elementWiseMultiply(v1, cr1), LinAlg.elementWiseMultiply(tr2, cr1));
	}
	
	@Test
	// tests creation of identity matrix
	void testIdentityMatrix() {
		int n1 = 2;
		int n2 = 1;
		int n3 = -3;
		int n4 = 0;
		TrMatrix tr1 = new TrMatrix(new double[][] {{1, 0}, {0, 1}});
		TrMatrix tr2 = new TrMatrix(new double[][] {{1}});
		assertEquals(tr1, LinAlg.identityMatrix(n1));
		assertEquals(tr2, LinAlg.identityMatrix(n2));
		assertThrows(IllegalArgumentException.class, () -> LinAlg.identityMatrix(n3));
		assertThrows(IllegalArgumentException.class, () -> LinAlg.identityMatrix(n4));

	}
	
	@Test
	// placeholder test, method not finished
	void testMatrixInverse() {
		
	}
	
}
