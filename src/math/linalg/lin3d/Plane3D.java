package math.linalg.lin3d;

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
	private double x,y,z; 
	
	/**
	 * Constructs a plane using a coordinate and the normal vector. 
	 * @param x
	 * @param y
	 * @param z
	 * @param normal
	 */
	public Plane3D(double x, double y, double z, Vector3D normal) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.normal = normal;
		this.b1 = Lin3d.crossProduct(Vector3D.xBasis, normal);
		this.b2 = Lin3d.crossProduct(Vector3D.yBasis, normal);
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
		this.x = x;
		this.y = y;
		this.z = z;
		this.b1 = b1;
		this.b2 = b2;
		this.normal = Lin3d.crossProduct(b1, b2);
	}
}
