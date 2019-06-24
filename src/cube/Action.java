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
	private final int face;
	private final int slice;
	private final boolean clockwise;
	
	/**
	 * Creates a new action with the given parameters.
	 * @param face
	 * @param slice
	 * @param clockwise
	 */
	public Action(int face, int slice, boolean clockwise) {
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
		int oppositeFace = 5 - face;
		int oppositeSlice = cubeSize - 1 - slice;
		return new Action(oppositeFace, oppositeSlice, !clockwise);
	}
	
	@Override
	public String toString( ) {
		return "(" + Cube.FACE_STRING[face] + ", " + slice + ", " + (clockwise ? "": "counter") + "clockwise)";
	}
	
	/**
	 * Returns true if other is the inverse of this action. False otherwise. 
	 * @param other
	 * @return
	 */
	public boolean isInverse(Action other) {
		return other.equals(this.getInverse());
	}
	
	
	/**
	 * Checks if the other action is equivalent to this one. 
	 * @param other
	 * @return
	 */
	public boolean equals(Action other) {
		return this.face == other.face && this.slice == other.slice && this.clockwise == other.clockwise;
	}
	
	/**
	 * return the face of this action.
	 * @return
	 */
	public int getFace() { 
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
