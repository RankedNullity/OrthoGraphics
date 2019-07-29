package math.linalg.lin3d;

import math.linalg.LinAlg;
import math.linalg.Matrix;
import math.linalg.Vector;

/**
 * A Plane in 3 dimensions. 
 * @author Jaron Wang
 *
 */
public class Plane3D {
	// Two basis vectors.
	private Vector3D b1, b2;
	
	// a unit normal to the plane. 
	private Vector3D normal;
	
	// A point on the plane.
	private Vector3D point; 
	
	/**
	 * Constructs a plane using a coordinate and the normal vector. 
	 * @param x
	 * @param y
	 * @param z
	 * @param normal
	 */
	public Plane3D(double x, double y, double z, Vector3D normal) {
		point = new Vector3D(x, y, z);
		this.normal = normal;
		this.b1 = Lin3d.crossProduct(Lin3d.xBasis, normal);
		this.b2 = Lin3d.crossProduct(Lin3d.yBasis, normal);
		b1.normalize();
		b2.normalize();
		normal.normalize();
	}
	
	
	/**
	 * Constructs a plane using two basis vectors and a point. 
	 * @param x
	 * @param y
	 * @param z
	 * @param b1
	 * @param b2
	 */
	public Plane3D(double x, double y, double z, Vector3D b1, Vector3D b2) {
		point = new Vector3D(x, y, z);
		this.b1 = b1;
		this.b2 = b2;
		this.normal = Lin3d.crossProduct(b1, b2);
		b1.normalize();
		b2.normalize();
		normal.normalize();
	}
	
	public void applyTransform(Matrix m) {
		b1 = LinAlg.multiply(m, b1).get3DVector();
		b2 = LinAlg.multiply(m, b2).get3DVector();
		normal = LinAlg.multiply(m, normal).get3DVector();
		point = LinAlg.multiply(m, point).get3DVector();
	}

	public Vector3D getNormal() {
		return normal;
	}

	public Vector3D getPoint() {
		return point;
	}
}
