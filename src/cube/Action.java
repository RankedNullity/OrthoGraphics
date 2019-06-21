package cube;

/**
 * Class for an action in a rubicks cube. Immutable.
 * Face: Plane parallel to rotation.
 * Slice: Which depth of slice is being rotated.
 * Clockwise: Which direction the move is in.
 * @author Jaron Wang
 *
 */
public class Action {
	private final String face;
	private final int slice;
	private final boolean clockwise;
	
	/**
	 * Creates a new action with the given parameters.
	 * @param face
	 * @param slice
	 * @param clockwise
	 */
	public Action(String face, int slice, boolean clockwise) {
		this.face = face;
		this.slice = slice;
		this.clockwise = clockwise;
	}
	
	/** 
	 * Returns the inverse of this action.
	 * @return
	 */
	public Action getInverse() {
		return new Action(face, slice, !clockwise);
	}
	
	/**
	 * Returns the action which is the equivalent action on the cube, 
	 * @param cubeSize. Size of the cube. (n x n x n)
	 * @return the action as seen from the other side of the cube. 
	 */
	public Action getEquivalentAction(int cubeSize) {
		String oppositeFace = Cube.FACES[Cube.FACES.length - 1 - Cube.getFaceIndex(face)];
		int oppositeSlice = cubeSize - 1 - slice;
		return new Action(oppositeFace, oppositeSlice, !clockwise);
	}
	
	/**
	 * return the face of this action.
	 * @return
	 */
	public String getFace() { 
		return face;
	}
	
	
	/**
	 * returns the slice of this action.
	 * @return
	 */
	public int getSlice() {
		return slice;
	}
	
	/**
	 * Returns the direction of this action.
	 * @return
	 */
	public boolean isClockwise() {
		return clockwise;
	}
	
	
	
}
