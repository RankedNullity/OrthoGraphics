package math.linalg.lin3d;

import math.linalg.Vector;

public class Vector3D extends Vector{
	
	/**
	 * Creates a new 3D vector with the given x, y, z positions. 
	 * @param x
	 * @param y
	 * @param z
	 */
	public Vector3D(double x, double y, double z) {
		super(new double[] {x, y, z}, true);
		normalize();
	}
	
	
	public static final Vector3D xBasis = new Vector3D(1,0,0);
	public static final Vector3D yBasis = new Vector3D(0,1,0);
	public static final Vector3D zBasis = new Vector3D(0,0,1);
	

}
