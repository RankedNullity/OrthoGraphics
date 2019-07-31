package cube.evaluators;
import cube.GameCube;

/*
 * Basic placeholder evaluator.
 */

public class BasicEvaluator implements Evaluator{
	
	private GameCube cube;
	public BasicEvaluator(GameCube cube) {
		this.cube = cube;
	}

	@Override
	public String getType() {
		return "Basic af";
	}

	@Override
	public int evaluate() {
		// TODO Auto-generated method stub
		return 0;
	}

}
