package math.linalg.lin3d;

import math.linalg.Vector;

public class Vector3d extends Vector {
	
	/**
	 * Creates a new 3D vector with the given x, y, z positions. 
	 * @param x
	 * @param y
	 * @param z
	 */
	public Vector3d(double x, double y, double z) {
		super(new double[] {x, y, z}, true);
	}
	
	
	public double getX() {
		return get(0);
	}
	
	public double getY() {
		return get(1);
	}
	
	public double getZ() {
		return get(2);
	}
}
