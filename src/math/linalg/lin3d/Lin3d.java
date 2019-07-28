package math.linalg.lin3d;

import common.misc.exceptions.NotYetImplementedException;
import math.linalg.Matrix;
import math.linalg.TrMatrix;
import math.linalg.Vector;

public class Lin3d {
	/**
	 * Currently only supported for vectors of length 3.
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static Vector3D crossProduct(Vector3D v1, Vector3D v2) {
		return new Vector3D(v1.get(1) * v2.get(2) - v1.get(2) * v2.get(1), v1.get(2) * v2.get(0) - v1.get(0) * v2.get(2), v1.get(0) * v2.get(1) - v1.get(1) * v2.get(0)); 
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
	
}
