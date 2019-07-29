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
		assertEquals(LinAlg.elementWiseSubtraction(v1, tr1), LinAlg.elementWiseSubtraction(tr2, tr1));
	}
	
}
