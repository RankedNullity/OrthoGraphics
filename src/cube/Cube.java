package cube;


/**
 * A interface for a rubicks cube.
 * @author Jaron Wang
 *
 */
public interface Cube {
	
	// Face orientation of a cube with the convenient property that (i) and (5 - i) are opposite faces.
	public static final String[] FACE_STRING = new String[] {"Front", "Left", "Up", "Down", "Right", "Back"};
	
	public static final int FRONT = 0, LEFT = 1, UP = 2, DOWN = 3, RIGHT = 4, BACK = 5;
	
	
	
	//public static final String[] COLORS = new String[] {"Green", "Orange", "White", "Blue", "Red", "Yellow"};
	public static final int[] COLORS = new int[] {0x008000, 0xFFA500, 0xFFFFFF, 0x0000FF, 0xFF0000, 0xFFFF00};
	
	/**
	 * Takes the int face and converts it to a string. 
	 * @param face
	 * @return
	 */
	public static String faceToString(int face) {
		return FACE_STRING[face];
	}
	
	/**
	 * Takes the string face and converts it to an int.
	 * @param face
	 * @return
	 */
	public static int faceToInt(String face) {
		for (int i = 0; i < FACE_STRING.length; i++)  {
			if (face == FACE_STRING[i])
				return i;
		}
		return -1;
	}
	
	/**
	 * Returns a int containing the colors that should be displayed for graphics.
	 * @return
	 */
	public int[][][] getColorArray();
	
	/**
	 *  Applies the specified move to the current cube. 
	 * @param face
	 * @param slice
	 * @param clockwise
	 */
	public void applyMove(int face, int slice, boolean clockwise);
	
	/**
	 *  Applies the action to the current cube.
	 * @param move
	 */
	public void applyMove(Action move);
	
	/* The type amorphism of just being a cube is not useful here, as each implementation of deepClone will need to use its own typing for this. 
	
	// Returns a deepcopy of the current cube.
	// Returns a new cube which is one move away 
	public Cube move(int face, int slice, boolean clockwise);
	
	// Returns a new cube which is one move away 
	public Cube move(Action move);
	
	
	public Cube deepCopy();
	
	*/
	
	public int getSize();

	
	// Get an array consisting of all the possible actions.
	public static Action[] getActions(int size) {
		Action[] actions = new Action[3 * 2 * size];
		for (int i = 0; i < 6; i++) {
			for (int j = 0 ; j < size; j++) {
				actions[i * size + j] = new Action(i, j, true);
			}
		}
		return actions;
	}
	
}
