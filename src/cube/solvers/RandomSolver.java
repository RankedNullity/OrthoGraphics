package cube.solvers;

import java.util.Random;

import cube.Action;
import cube.GameCube;

/**
 * Simple bogus solver for testing animation displays
 * 
 * @author Jaron Wang
 *
 */
public class RandomSolver implements Solver {
	private Random r;
	private GameCube cube;

	
	public RandomSolver(GameCube c) {
		this.cube = c;
		r = new Random();
	}
	
	public RandomSolver(GameCube c, int seed) {
		this.cube = c;
		r = new Random(seed);
	}
	
	
	@Override
	public String getType() {
		return "Random Solver";
	}

	@Override
	public Action getBestAction() {
		Action[] possibleActions = GameCube.getActions(cube.getSize());
		return possibleActions[r.nextInt(possibleActions.length)];
	}

}
