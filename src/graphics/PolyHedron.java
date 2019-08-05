package graphics;

import common.datastructures.interfaces.IList;
import math.linalg.lin3d.Vector3d;

public abstract class PolyHedron {
	protected Polygon3D[] faces;
	protected int maxVertexDegree, minVertexDegree;
	
	public PolyHedron(IList<Polygon3D> l) {
	}
	
	public PolyHedron(Polygon3D[] l) {
	}
	
	abstract public Polygon3D[] getDrawables();
	
	public Polygon3D[] getFaces() {
		return faces;
	}
}