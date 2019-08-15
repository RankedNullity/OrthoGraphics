package graphics.sceneObjects;

import java.awt.Graphics;

import common.datastructures.concrete.ArrayHeap;
import common.datastructures.interfaces.IPriorityQueue;
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
	// Conntains all the cubelets inside the megacube. [x][y][z]
	private Cube3D[][][] cubes;
	private Vector3d[] vertices;
	private Vector3d center;
	
	private IPriorityQueue<ObjectDistancePair> visibles;
	private int cubeletWidth;
	
	/**
	 * Returns the number of (small) cubes in the length of the megacube. 
	 * @return
	 */
	public int getSize() {
		return cubes.length;
	}

	public MegaCube(Vector3d center, int cubeletWidth, int size) {
		
		this.center = center;
		visibles = new ArrayHeap<>();
		this.cubeletWidth = cubeletWidth;
		cubes = new Cube3D[size][size][size];
		double offSet = size % 2 == 1 ? -0.5: 0;
		int half = size / 2;
		// Generate the cubes.
	
		for (int x = - half; x < half + size % 2 ; x += cubeletWidth) {
			for (int y = - half; y < half + size % 2; y += cubeletWidth) {
				for (int z = - half; z < half + size % 2; z += cubeletWidth) {
					Cube3D c = new Cube3D(x + offSet, y + offSet, z + offSet, cubeletWidth);
					//TODO: Add the cubes to the storage. 
				}
			}
		}
		
		// Sets the vertices
		vertices = new Vector3d[8];
		for (int i = 0; i < 4; i++) {
			for (int j = -1; j <= 1; j+= 2) {
				
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
	
	
	@Override
	public void updateDrawable(Plane3d viewPlane, double zoom, int screenWidth, boolean lighting) {
		// TODO Calculate which faces to show. 
		// Add all faces which need to be drawn to the priority queue and update their drawables. 
		// Handle active animation slices here as well?
		
	}

	@Override
	public void render(Graphics g) {
		while(!visibles.isEmpty()) {
			SceneObject p = visibles.removeMin().getObject();
			p.render(g);
		}
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
		for (int i = 0; i < cubes.length; i++) {
			for (int j = 0; i < cubes[0].length; j++) {
				for(int k = 0; k < cubes[0][0].length; k++) {
					cubes[i][j][k].applyTransform(m);
				}
			}
		}
	}

	

}
