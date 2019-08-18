package cube.solvers;

import cube.*;

public interface Solver {
	
	/**
	 * Returns as a string the type of solver 
	 * @return
	 */
	public String getType();
	
	/**
	 * Uses the current solver method to give the best action from the current state. 
	 * @return
	 */
	public Action getBestAction();
	
}
