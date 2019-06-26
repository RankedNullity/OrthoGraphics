package cube;

import math.Matrix;

/**
 * Class for representing a rubicks cube using a minimal one-hot encoding,
 * which has the least amount of redundant information in a rubicks cube. 
 * @author Jaron Wang
 * @version 6-19-2019
 */
public class MinimalOneHotCube implements Cube {
	private Matrix cube;
	
	/**
	 * Creates a cube that is n x n x n. 
	 * 
	 * @param n , n >= 2
	 */
	public MinimalOneHotCube(int n) {
		// First
	}
	
	

	@Override
	public int[][][] getColorArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void applyMove(int face, int slice, boolean clockwise) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void applyMove(Action move) {
		// TODO Auto-generated method stub
		
	}

	
}
