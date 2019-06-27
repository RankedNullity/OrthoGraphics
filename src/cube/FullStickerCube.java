package cube;

import java.util.Random;

import math.linalg.*;

public class FullStickerCube implements Cube {
	private CRMatrix[] cube;
	private int size;
	private static Random r;
	
	private static CRMatrix[] solvedCube(int n) {
		CRMatrix[] cube = new CRMatrix[6];
		for(int i = 0; i < 6; i++) {
			cube[i] = new CRMatrix(n, n);
			for(int j = 0; j < n; j++) {
				for(int k = 0; k < n; k++) {
					cube[i].set(j, k, i);
				}
			}
 		}
		return cube;
	}
	
	public FullStickerCube(int n) {
		this(n, false);
	}
	
	/**
	 * Creates a new cube of size (n x n x n). Cube is in goal position unless randomized is true. 
	 * @param n. Size of cube
	 * @param randomized. Whether or not to randomize the cube. 
	 */
	public FullStickerCube(int n, boolean randomized) {
		if (r == null) {
			r = new Random();
		}
		cube = solvedCube(n);
		
		if (randomized) {
			randomize();
		}
	}
	
	/**
	 * Randomizes the cube by estimating god's number (using n ^ 2 / log10(n)), then picking an amount of moves between 0 and that number, 
	 * and applying that many moves at random. (with the condition that any two moves adjacent in the sequence are not inverses of each other)
	 */
	public void randomize() {
		int approxMax = (int)Math.ceil(size * size / Math.log10(size));
		int rotations = r.nextInt(approxMax);
		Action[] actions = Cube.getActions(size);
		Action previousMove = null;
		for (int i = 0; i < rotations; i++) { 
			int nextMoveIndex;
			Action nextMove;
			do {
				nextMoveIndex = r.nextInt(actions.length);
				nextMove = actions[nextMoveIndex];
			} while(nextMove.isInverse(previousMove));
			previousMove = nextMove;
			applyMove(nextMove);
		}
	}
	
	
	@Override
	public int[][][] getColorArray() {
		int[][][] ans = new int[6][size][size];
		for (int i = 0; i < 6; i++) {
			for(int j = 0; j < size; j++) {
				for (int k = 0; k < size; k++) {
					ans[i][j][k] = Cube.COLORS[(int)cube[i].get(j, k)];
				}
			}
		}
		return ans;
	}

	
	@Override
	public void applyMove(int face, int slice, boolean clockwise) {
		//TODO: Write the method move making a new move.
		int f1 = (face + 1) % 3;
		int f2 = (face + 2) % 3;
		int[] adjfaces = new int[] { f1, f2, 5 - f1, 5 - f2};
		
		
	}

	@Override
	public void applyMove(Action move) {
		applyMove(move.getFace(), move.getSlice(), move.isClockwise());
	}

	/**
	 * Returns a duplicate state on which the specified move has been applied.
	 * @param face
	 * @param slice
	 * @param clockwise
	 * @return
	 */
	public FullStickerCube move(int face, int slice, boolean clockwise) {
		FullStickerCube copy = deepCopy();
		copy.applyMove(face, slice, clockwise);
		return copy;
	}

	
	/**
	 * returns a duplicate state on which the specified move has been applied.
	 * @param move
	 * @return
	 */
	public FullStickerCube move(Action move) {
		return move(move.getFace(), move.getSlice(), move.isClockwise());
	}

	/**
	 * Returns a deepcopy of this cube.
	 * @return
	 */
	public FullStickerCube deepCopy() {
		FullStickerCube copy = new FullStickerCube(size);
		for(int i = 0; i < 6; i++) {
			for (int j = 0; j < size; j++) {
				for(int k = 0; k < size; k++) {
					copy.cube[i].set(j, k, cube[i].get(j, k));
				}
			}
		}
		return copy;
	}
	
	/**
	 * Returns the contents of this cube in a single matrix, constructed with each face stacked on top of each 
	 * other in the order specified in Cube.FACE_STRINGS
	 * @return
	 */
	public Matrix getCubeMatrix() {
		Matrix m = new TrMatrix(6 * size, size);
		
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < size; k++) {
					m.set(i * size + j, k, cube[i].get(j, k));
				}
			}
		}
		return m;
	}
	
}
