package cube;

import math.linalg.Matrix;

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
		
	}

	@Override
	public int[][] getColorArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void applyMove(String face, int slice, boolean clockwise) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void applyMove(Action move) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cube move(String face, int slice, boolean clockwise) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cube move(Action move) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cube deepCopy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Action[] getActions 	() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
