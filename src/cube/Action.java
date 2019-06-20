package cube;

/**
 * Class for an action in a rubicks cube. Immutable.
 * @author Jaron Wang
 *
 */
public class Action {
	private final String face;
	private final int slice;
	private final boolean clockwise;
	
	public Action(String face, int slice, boolean clockwise) {
		this.face = face;
		this.slice = slice;
		this.clockwise = clockwise;
	}
	
	public Action getInverse() {
		return new Action(face, slice, !clockwise);
	}
	
	public String getFace() { 
		return face;
	}
	
	public int getSlice() {
		return slice;
	}
	
	public boolean isClockwise() {
		return clockwise;
	}
	
	
	
}
