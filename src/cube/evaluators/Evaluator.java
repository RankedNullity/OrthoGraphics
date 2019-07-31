package cube.evaluators;

import cube.GameCube;

public interface Evaluator {
	/**
	 * Returns as a string the method of evaluation.
	 * @return
	 */
	public String getType();
	
	/**
	 * Evaluates the current state with the method of evaluation and returns the score as a double.
	 * @return
	 */
	public double evaluate(GameCube cube);
	
}
