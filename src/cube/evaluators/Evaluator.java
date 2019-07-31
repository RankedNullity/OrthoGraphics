package cube.evaluators;


public interface Evaluator {
	/**
	 * Returns as a string the method of evaluation.
	 * @return
	 */
	public String getType();
	
	/**
	 * Evaluates the current state with the method of evaluation and returns the score as an int.
	 * @return
	 */
	public int evaluate();
	
}
