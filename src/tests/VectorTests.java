package tests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import math.linalg.LinAlg;
import math.linalg.Matrix;
import math.linalg.TrMatrix;
import math.linalg.Vector;

public class VectorTests {
	
	@Test
	// testing whether or not vectors of the same content and orientation are indeed equal
	void testVectorEquals() {
		Vector v1 = new Vector(new double[]{1, 2, 3}, true);
		Vector v2 = new Vector(new double[]{1, 2, 3}, true);
		Vector v3 = new Vector(new double[]{1, 2, 3}, false);
		Vector v4 = new Vector(new double[]{9, 2, 5}, false);
		
		assertEquals(v1, v1);
		assertEquals(v1, v2);
		assertNotEquals(v2, v3);
		assertNotEquals(v3, v4);

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
