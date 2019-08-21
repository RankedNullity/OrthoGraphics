package graphics.sceneObjects;

import java.awt.Graphics;

import common.datastructures.concrete.ArrayHeap;
import common.datastructures.concrete.ChainedHashSet;
import common.datastructures.interfaces.IPriorityQueue;
import common.datastructures.interfaces.ISet;
import cube.Action;
import cube.GameCube;
import graphics.ObjectDistancePair;
import math.linalg.LinAlg;
import math.linalg.Matrix;
import math.linalg.lin3d.Lin3d;
import math.linalg.lin3d.Plane3d;
import math.linalg.lin3d.Vector3d;

/**
 * MegaCube class which handles the class of cubes which is a single cube which consists of 
 * @author Jaron Wang
 *
 */
public class MegaCube implements SceneObject {
	// Conntains all the cubelets inside the megacube with the following access convention [x][y][z]
	private Cube3D[][][] cubes;
	private boolean[][][] visited;
	private Vector3d center;
	private ISet<Cube3D> animatedCubes;
	
	private IPriorityQueue<ObjectDistancePair> visibles;
	
	
	public double cubeletWidth() {
		return cubes[0][0][0].getWidth();
	}
	
	/**
	 * Returns the number of (small) cubes in the length of the megacube. 
	 * @return
	 */
	public int getSize() {
		return cubes.length;
	}

	/**
	 * Creates a new megacube with the following parameters.
	 * @param center. Point which is the center of the megacube. 
	 * @param cubeletWidth. width of the cubelets. 
	 * @param size. How many cubelets are in one length of the megacube. 
	 */
	public MegaCube(Vector3d center, double cubeletWidth, int size) {
		this.center = center;
		visibles = new ArrayHeap<>();
		cubes = new Cube3D[size][size][size];
		visited = new boolean[size][size][size];
		double offSet = size % 2 == 1 ? -0.5: 0;
		int half = size / 2;
		// Generate the cubes.
		for (int x = - half; x < half + size % 2 ; x ++) {
			for (int y = - half; y < half + size % 2; y ++) {
				for (int z = - half; z < half + size % 2; z ++) {
					Cube3D c = new Cube3D(x * cubeletWidth + offSet, y * cubeletWidth + offSet, z * cubeletWidth + offSet, cubeletWidth);
					cubes[x + half][y + half][z + half] = c;
				}
			}
		}	
	}
	
	/**
	 * Constructs a mega cube with [size] cubes in a length of the cube. 
	 * @param size
	 */
	public MegaCube(int size) {
		this(Lin3d.origin, 1, size);
	}
	
	
	public int[] GameCubeToMegaCube(int face, int slice) {
		int[] ans = new int[] {-1, -1, -1};
		switch(face) {
			case GameCube.FRONT:
				ans[0] = cubes.length - 1 - slice;
				break;
			case GameCube.LEFT:
				ans[1] = slice;
				break;
			case GameCube.UP:
				ans[2] = cubes.length - 1 - slice;
				break;
			case GameCube.DOWN:
				ans[2] = slice;
				break;
			case GameCube.RIGHT:
				ans[1] = cubes.length - 1 - slice;
				break;
			case GameCube.BACK:
				ans[0] = slice;
				break;
			default: 
				throw new IllegalArgumentException("Not a valid face value");
		}
		return ans;
	}
	
	
	
	
	public void processAnimation(Action a, double rotationInterval) {
		
	}
	
	
	/**
	 * Updates the drawables for all components of the cube, NOT IN ANY ACTIVE ANIMATIONS. When a component
	 * is part of an active animation, that will be handled to the animation method. 
	 */
	@Override
	public void updateDrawable(Plane3d viewPlane, double zoom, int screenWidth, boolean lighting) {
		// TODO Calculate which faces to show. 
		// Add all faces which need to be drawn to the priority queue and update their drawables. 
		// Handle active animation slices here as well?
		
		Cube3D referenceCube = cubes[getSize() / 2][getSize() / 2][getSize() / 2];
		referenceCube.updateDrawable(viewPlane, zoom, screenWidth, lighting);

		
		int[] drawableFaces = referenceCube.getDrawableIndices();
		
		for (int i = 0; i < drawableFaces.length; i++) {
			// TODO: For each slice, add all of the cubes to visibles and change the visible face to the drawableFaces[i]
			int[] usableIndices = GameCubeToMegaCube(drawableFaces[i], 0);
			
			for (int j = 0; j < getSize(); j++) {
				for (int k = 0; k < getSize(); k++) {
					int x, y, z;
					if (usableIndices[0] != -1) {
						x = usableIndices[0];
						y = j;
						z = k;
					} else if (usableIndices[1] != -1) {
						x = j;
						y = usableIndices[1];
						z = k;
					} else {
						x = j;
						y = k;
						z = usableIndices[2];
					}	
					Cube3D current = cubes[x][y][z];
					if(!visited[x][y][z]) {
						current.clearVisibles();
						visited[x][y][z] = true;
					}
					
					current.manualUpdateDrawables(drawableFaces[i], i, viewPlane, zoom, screenWidth, lighting);
					visibles.insert(new ObjectDistancePair(current, -current.getAvgDistance(viewPlane)));
				}
			}
		}
		
	}

	@Override
	public void render(Graphics g) {
		while(!visibles.isEmpty()) {
			SceneObject p = visibles.removeMin().getObject();
			p.render(g);
		}
		visited = new boolean[getSize()][getSize()][getSize()];
	}
	
	@Override
	public double getAvgDistance(Vector3d point) {
		return LinAlg.norm(LinAlg.elementWiseSubtraction(point, center), 2);
	}

	@Override
	public double getAvgDistance(Plane3d plane) {
		return Lin3d.getDistance(plane, center);
	}

	@Override
	public double getAvgDistance(double x, double y, double z) {
		return getAvgDistance(new Vector3d(x,y,z));
	}

	
	@Override
	public void applyTransform(Matrix m) {
		if (m.getColumns() != 3 || m.getRows() != 3) {
			throw new IllegalArgumentException(" Not a valid transform: R3 -> R3");
		}
		for (int i = 0; i < cubes.length; i++) {
			for (int j = 0; i < cubes[0].length; j++) {
				for(int k = 0; k < cubes[0][0].length; k++) {
					cubes[i][j][k].applyTransform(m);
				}
			}
		}
		center = LinAlg.multiply(m, center).get3DVector();
	}

	

}
