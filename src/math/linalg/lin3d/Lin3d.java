package math.linalg.lin3d;

import common.misc.exceptions.NotYetImplementedException;
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
	
	
	
}
