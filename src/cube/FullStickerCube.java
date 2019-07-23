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
		
		// Alex Note: I think you forgot to initialize size.
		size = n;
		if (randomized) {
			randomize();
		}
	}
	
	/**
	 * Returns the size of cube. For TestInterface use. Added by Alex on 7/22
	 * @return size of cube
	 */
	public int getSize() {
		return size;
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
	
	/**
	 * String debug. 
	 * @return
	 */
	public String[][][] getDebugArray() {
		String[][][] ans = new String[6][size][size];
		for (int i = 0; i < 6; i++) {
			for(int j = 0; j < size; j++) {
				for (int k = 0; k < size; k++) {
					ans[i][j][k] = FACE_STRING[(int)cube[i].get(j, k)] + " (" + j + ", " + k + ")";
				}
			}
		}
		return ans;
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
		applyMove(new Action(face, slice, clockwise));
	}
	
	private static final int[][] ROTATIONS = new int[][] {{3, 2, 1, 0}, {3, 3, 3, 1}, {0, 0, 0, 0}};

	@Override
	public void applyMove(Action move) {
		// If move is on B, D, or R, change it to F, L, U
		if (move.getFace() > 2) {
			move = move.getEquivalentAction(size);
		}
		
		int face = move.getFace();
		int slice = move.getSlice();
		boolean clockwise = move.isClockwise();
		
		
		int f1 = (face + 1) % 3;
		int f2 = (face + 2) % 3;
		int[] adjfaces = new int[] { f1, f2, 5 - f1, 5 - f2};
		
		if (slice == 0) {
			cube[face].clockwiseRotate((clockwise) ? 1 : 3);
		} else if (slice == size - 1) {
			cube[face].clockwiseRotate((clockwise) ? 3 : 1);
		} 
		
		int[] rotations = FullStickerCube.ROTATIONS[face];
		// Rotate the faces for easy handling.
		for (int i = 0 ; i < rotations.length; i++) {
			cube[adjfaces[i]].clockwiseRotate(rotations[i]);
		}
		
		// Actually rotate parts of cube.
		int direction = (clockwise) ? -1: 1;
		for (int i = 0; i < size; i++) {
			double temp = cube[adjfaces[0]].get(slice, i);
			int j;
			for (j = 0; j < adjfaces.length - 1; j++) {
				cube[adjfaces[(j * direction + adjfaces.length) % adjfaces.length]].set(slice, i, 
						cube[adjfaces[((j + 1) * direction + adjfaces.length) % adjfaces.length]].get(slice, i));
			}
			cube[adjfaces[(j * direction + adjfaces.length) % adjfaces.length]].set(slice, i, temp);
		}
		
		
		// Rotate the faces back. 
		for (int i = 0; i < rotations.length; i++) {
			cube[adjfaces[i]].clockwiseRotate(4 - rotations[i]);
		}
		
		
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
