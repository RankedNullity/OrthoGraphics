package graphics.sceneObjects;

import java.awt.Graphics;

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
	private Cube3D[][][] cube;
	private Cube3D[][] surfaceCubes;
	private int cubeletWidth;
	private int[] visibleFaces;
	
	public int getSize() {
		return cube.length;
	}

	public MegaCube(Vector3d center, int cubeletWidth, int size) {
		visibleFaces = new int[3];
		this.cubeletWidth = cubeletWidth;
		cube = new Cube3D[size][size][size];
		double offSet = size % 2 == 1 ? -0.5: 0;
		int width = 1;
		int half = size / 2;
		// Generate the cubes.
	
		for (int x = - half; x < half + size % 2 ; x += width) {
			for (int y = - half; y < half + size % 2; y += width) {
				for (int z = - half; z < half + size % 2; z += width) {
					Cube3D c = new Cube3D(x + offSet, y + offSet, z + offSet, width);
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
	
	
	@Override
	public void updateDrawable(Plane3d viewPlane, double zoom, int screenWidth, boolean lighting) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getAvgDistance(Vector3d point) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAvgDistance(Plane3d plane) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAvgDistance(double x, double y, double z) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void applyTransform(Matrix m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
