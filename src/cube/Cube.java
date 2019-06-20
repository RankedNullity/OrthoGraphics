package cube;

public interface Cube {
	
	// Face orientation of a cube with the convenient property that (i) and (5 - i) are opposite faces.
	public static final String[] FACES = new String[] {"Front", "Left", "Up", "Bottom", "Right", "Back"};
	public static final String[] COLORS = new String[] {"Green", "Orange", "White", "Blue", "Red", "Yellow"};
	
	// Returns a int containing the colors that should be displayed for graphics.
	public int[][] getColorArray();
	
	// Applies the specified move to the current cube. 
	public void applyMove(String face, int slice, boolean clockwise);
	
	// Applies the action to the current cube.
	public void applyMove(Action move);
	
	// Returns a new cube which is one move away 
	public Cube move(String face, int slice, boolean clockwise);
	
	// Returns a new cube which is one move away 
	public Cube move(Action move);
	
	// Returns a deepcopy of the current cube.
	public Cube deepCopy();
	
	// Get an array consisting of all the possible actions.
	public Action[] getActions();	
}
