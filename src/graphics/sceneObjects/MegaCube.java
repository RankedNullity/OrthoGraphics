package graphics.sceneObjects;

import java.awt.Color;
import java.awt.Graphics;

import common.datastructures.concrete.ArrayHeap;
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
 * MegaCube class which handles the class of cubes which is a single cube which consists of adjacent smaller cubes. 
 * @author Jaron Wang
 *
 */
public class MegaCube extends Cube3D implements SceneObject {
	// Contains all the cubelets inside the megacube with the following access convention [x][y][z]
	private Cube3D[][][] cubes;
	private boolean[][][] currentlyActive;
	private Vector3d center;
	private ISet<Cube3D> animatedCubes;
	public static final Color[] COLORS = new Color[] {};
	
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
	public MegaCube(Vector3d center, double cubeletWidth, int size, GameCube rc) {
		super(center.getX() - size / 2.0 * cubeletWidth, center.getY() - size / 2.0 * cubeletWidth, center.getZ() - size / 2.0 * cubeletWidth, size * cubeletWidth);
		this.center = center;
		visibles = new ArrayHeap<>();
		cubes = new Cube3D[size][size][size];
		currentlyActive = new boolean[size][size][size];
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
		
		updateColors(rc.getColorArray());
	}
	
	/**
	 * Constructs a mega cube with [size] cubes in a length of the cube. 
	 * @param size
	 */
	public MegaCube(int size, GameCube rc) {
		this(Lin3d.origin, 1, size, rc);
	}
	
	/**
	 * Returns the indices of the megacube which are identical to the given values from GameCube actions. 
	 * -1 are wildcards.
	 * @param face
	 * @param slice
	 * @return
	 */
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
	
	
	private int[] gameCubeToMegaCubeIndex(int face, int x, int y) {
		int[] ans = GameCubeToMegaCube(face, 0);
		switch(face) {
			case GameCube.FRONT:
				ans[1] = y;
				ans[2] = cubes.length - 1 - x;
				break;
			case GameCube.LEFT:
				ans[0] = y;
				ans[2] = cubes.length - 1 - x;
				break;
			case GameCube.UP:
				ans[0] = x;
				ans[1] = y;
				break;
			case GameCube.DOWN:
				ans[0] = cubes.length - 1 - x;
				ans[1] = y;
				break;
			case GameCube.RIGHT:
				ans[0] = cubes.length - 1 - y;
				ans[2] = cubes.length - 1 - x;
				break;
			case GameCube.BACK:
				ans[1] = cubes.length - 1 - y;
				ans[2] = cubes.length - 1 - x;
				break;
			default: 
				throw new IllegalArgumentException("Not a valid face value");
		}
		return ans;
	}
	
	/**
	 * Takes 
	 * @param colors
	 */
	public void updateColors(Color[][][] colors) {
		for (int i = 0; i < colors.length; i++) {
			for (int j = 0; j < colors[0].length; j++) {
				for (int k = 0; k < colors[0][0].length; k++) {
					int[] index = gameCubeToMegaCubeIndex(i, j, k);
					cubes[index[0]][index[1]][index[2]].setColor(colors[i][j][k], i);
				}
			}
		}
	}
	
	/**
	 * Processes the animation of the cube given an action a, and how much to rotate it by. Other parameters are the necessary parameters to update the drawables.
	 * @see updateDrawable
	 * @param a
	 * @param rotationInterval
	 * @param viewPlane
	 * @param zoom
	 * @param screenWidth
	 * @param lighting
	 */
	public void processAnimation(Action a, double rotationInterval, Plane3d viewPlane, double zoom, int screenWidth, boolean lighting) {
		if (a.getFace() > 2) {
			a = a.getEquivalentAction(cubes.length);
		}
		int[] usableIndices = gameCubeToMegaCubeIndex(a.getFace(), cubes.length / 2, cubes.length / 2);
		
		double centerDistance = - cubes[usableIndices[0]][usableIndices[1]][usableIndices[2]].getAvgDistance(viewPlane);
		
		usableIndices = GameCubeToMegaCube(a.getFace(), a.getSlice());
		int direction = a.isClockwise() ? -1 : 1;
		
		// Gets the correct transform depending on the face.
		Matrix transform;
		switch(a.getFace()) {
			case 0:
				transform = Lin3d.getRotationAroundX(rotationInterval * direction);
				break;
			case 1:
				transform = Lin3d.getRotationAroundY(- rotationInterval * direction);
				break;
			case 2:
				transform = Lin3d.getRotationAroundZ(rotationInterval * direction);
				break;
			default:
				throw new IllegalArgumentException("Action face not valid.");
		}
		
		for (int i = 0; i < cubes.length; i++) {
			for (int j = 0; j < cubes.length; j++) {
				int x,y,z;
				if (usableIndices[0] != -1) {
					x = usableIndices[0];
					y = i;
					z = j;
				} else if (usableIndices[1] != -1) {
					x = i;
					y = usableIndices[1];
					z = j;
				} else {
					x = i;
					y = j;
					z = usableIndices[2];
				}
				// Marks the index as visited so it is not overwritten in updateDrawable
				currentlyActive[x][y][z] = true;				
				
				Cube3D current = cubes[x][y][z];
				
				// Change the cubes in the slice, updates their drawables, and inserts them in the pq to be handled in render.
				current.applyTransform(transform);
				current.updateDrawable(viewPlane, zoom, screenWidth, lighting);
				visibles.insert(new ObjectDistancePair(current, -current.getAvgDistance(viewPlane)));
			}
		}
	}
	
	
	/**
	 * Updates the drawables for all components of the cube, NOT IN ANY ACTIVE ANIMATIONS. When a component
	 * is part of an active animation, that will be handled by the animation method. 
	 */
	@Override
	public void updateDrawable(Plane3d viewPlane, double zoom, int screenWidth, boolean lighting) {
		super.updateDrawable(viewPlane, zoom, screenWidth, lighting);
		boolean[][][] drawableVisited = new boolean[getSize()][getSize()][getSize()];
		for (int i = 0; i < visibleFaces.length; i++) {
			int[] usableIndices = GameCubeToMegaCube(visibleFaces[i], 0);
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
					if(!currentlyActive[x][y][z]) {
						if (!drawableVisited[x][y][z]) {
							current.clearVisibles();
							drawableVisited[x][y][z] = true;
						} 
						current.manualUpdateDrawables(visibleFaces[i], i, viewPlane, zoom, screenWidth, lighting);
						visibles.insert(new ObjectDistancePair(current, -current.getAvgDistance(viewPlane)));
						
						//currentlyActive[x][y][z] = true;
					} else if (drawableVisited[x][y][z]) {
						current.manualUpdateDrawables(visibleFaces[i], i, viewPlane, zoom, screenWidth, lighting);
						visibles.insert(new ObjectDistancePair(current, -current.getAvgDistance(viewPlane)));
					}
					
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
		currentlyActive = new boolean[getSize()][getSize()][getSize()];
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
