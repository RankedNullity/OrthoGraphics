package cube.solvers;
import java.util.ArrayList;
import common.datastructures.concrete.ChainedHashSet;
import common.datastructures.concrete.KVPair;
import cube.Action;
import cube.GameCube;
import cube.evaluators.Evaluator;

public class DepthFirstSolver implements Solver{
	private GameCube cube;
	private String solverType;
	private ChainedHashSet<GameCube> open;
	private ChainedHashSet<GameCube> closed;
	private int depthLimit;
	private Evaluator eval;
	private ArrayList<KVPair<GameCube, Double>> path;
	public DepthFirstSolver(GameCube cube, int limit, Evaluator eval) {
		this.cube = cube;
		solverType = "Depth First Search Solver";
		depthLimit = limit;
		this.eval = eval;
		open = new ChainedHashSet<>();
		closed = new ChainedHashSet<>();
		path = new ArrayList<>();
	}
	
	/**
	 * Returns the type of solver
	 * @return
	 */
	@Override
	public String getType() {
		return solverType;
	}

	@Override
	
	// Work In Progress Almost Done
	public Action getBestAction() {
		open.add(cube);
		ArrayList<KVPair<Action, GameCube>> successorActions = generateSuccessors(cube);
		double bestEvalScore = Double.POSITIVE_INFINITY;
		Action bestAction = successorActions.get(0).getKey();
		for (KVPair<Action, GameCube> actionPair : successorActions) {
			KVPair<GameCube, Double> result = depthFirstSearch(actionPair.getValue(), 0);
			if (result.getValue() < bestEvalScore) {
				// Returns the action that solves the cube.
				if (result.getValue() == 0) {
					return actionPair.getKey();
				}
				bestEvalScore = result.getValue();
				bestAction = actionPair.getKey();
			}
		}
		return bestAction;
	}
	
	/**
	 * Depth First Search method used to find the best next move for the current state of the rubik's cube.
	 * 
	 * @param currentState	The current state of the rubik's cube
	 * @param level			The current level of recursion
	 * @return				KVPair<GameCube, Double> of the best successor of the current state and its evaluation score
	 */
	
	private KVPair<GameCube, Double> depthFirstSearch(GameCube currentState, int level) {
		closed.add(currentState);
		open.remove(currentState);
		
		// Breaks off search if level exceeds depth limit.
		if (level > depthLimit) {
			return new KVPair<GameCube, Double>(currentState, eval.evaluate(currentState));
		}
		
		Action[] actions = GameCube.getActions(cube.getSize());
		KVPair<GameCube, Double> bestSuccessor = new KVPair<GameCube, Double>(currentState.move(actions[0]), Double.POSITIVE_INFINITY);
		
		// Iterates through all successor of currentState, find the best successor and returns it.
		for (Action action : actions) {
			GameCube successor = currentState.move(action);
			if (!closed.contains(successor)) {
				open.add(successor);
				KVPair<GameCube, Double> result = depthFirstSearch(successor, level++);
				double score = result.getValue();
				if (result.getValue() < bestSuccessor.getValue()) {
					
					// Solved cubes will have score of 0, return immediately if found.
					if (score == 0) {
						return result;
					}
					bestSuccessor = result;
				}
			}
			
		}
		return bestSuccessor;
	}
	
	/**
	 * Method that generates all possible successors and the actions used to reach them from a given GameCube. 
	 * Successors are automatically added to open.
	 * 
	 * @param cube									the parent GameCube object used to generate successors
	 * @return ArrayList<KVPair<GameCube, Action>>	containing pairs of all successors and the actions the parent cube took to reach each successor
	 */
	private ArrayList<KVPair<Action, GameCube>> generateSuccessors(GameCube cube) {
		Action[] actions = GameCube.getActions(cube.getSize());
		ArrayList<KVPair<Action, GameCube>> successors = new ArrayList<>();
		
		for (Action action : actions) {
			GameCube newCube = cube.move(action);
			successors.add(new KVPair<Action, GameCube>(action, newCube));
			open.add(newCube);
		}
		return successors;
	}
	
	private Action findAction(GameCube parent, GameCube child) {
		Action[] actions = GameCube.getActions(cube.getSize());
		for (Action action : actions) {
			if (child.equals(parent.move(action))) {
				return action;
			}
		}
		
		return null;
	}
	
}
