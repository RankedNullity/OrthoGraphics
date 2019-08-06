package math.linalg.lin3d;

import math.linalg.LinAlg;
import math.linalg.Matrix;
import math.linalg.TrMatrix;
import math.linalg.Vector;

public class Lin3d {
	
	public static final Vector3d xBasis = new Vector3d(1,0,0); 
	public static final Vector3d yBasis = new Vector3d(0,1,0);
	public static final Vector3d zBasis = new Vector3d(0,0,1);
	
	public static final Vector3d origin = new Vector3d(0,0,0);
	
	
	public static double getDistance(Plane3d p, Vector3d point) {
		Vector3d q = elementwiseSubtract(point, p.getPoint());
		return Math.abs(LinAlg.dotProduct(q, p.getNormal()));
	}
	
	
	/**
	 * Currently only supported for vectors of length 3.
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static Vector3d crossProduct(Vector3d v1, Vector3d v2) {
		return new Vector3d(v1.get(1) * v2.get(2) - v1.get(2) * v2.get(1), v1.get(2) * v2.get(0) - v1.get(0) * v2.get(2), v1.get(0) * v2.get(1) - v1.get(1) * v2.get(0)); 
	} 
	
	/**
	 * Returns the Matrix which corresponds to an [angle] radians rotation about the X axis. 
	 * @param angle
	 * @return
	 */
	public static Matrix getRotationAroundX(double angle) { 
		return new TrMatrix(new double[][] {{1, 0, 0},
											{0, Math.cos(angle), -Math.sin(angle)},
											{0, Math.sin(angle), Math.cos(angle)}}); 
	}
	
	/**
	 * Returns the Matrix which corresponds to an [angle] radians rotation about the Y axis. 
	 * @param angle
	 * @return
	 */
	public static Matrix getRotationAroundY(double angle) {
		return new TrMatrix(new double[][] {{Math.cos(angle), 0, Math.sin(angle)},
											{0, 1, 0},
											{- Math.sin(angle), 0, Math.cos(angle)}}); 
	}
	
	/**
	 * Returns the Matrix which corresponds to an [angle] radians rotation about the Z axis. 
	 * @param angle
	 * @return
	 */
	public static Matrix getRotationAroundZ(double angle) {
		return new TrMatrix(new double[][] {{Math.cos(angle), -Math.sin(angle), 0},
											{Math.sin(angle), Math.cos(angle), 0},
											{0, 0, 1}}); 
	}
	
	public static Vector3d elementwiseSubtract(Vector3d a, Vector3d b) {
		return new Vector3d(a.getX() - b.getX(), a.getY() - b.getY(), a.getZ() - b.getZ());
	}
	
	public static Vector3d elementwiseAdd(Vector3d a, Vector3d b) {
		return new Vector3d(a.getX() + b.getX(), a.getY() + b.getY(), a.getZ() + b.getZ());
	}
	
}
