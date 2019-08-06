package graphics.polyhedra;


import java.util.Iterator;

import common.datastructures.concrete.ArrayHeap;
import common.datastructures.concrete.KVPair;
import common.datastructures.concrete.dictionaries.ChainedHashDictionary;
import common.datastructures.interfaces.IDictionary;
import common.datastructures.interfaces.IPriorityQueue;
import graphics.Polygon3D;
import graphics.PolygonDistancePair;
import math.linalg.Matrix;
import math.linalg.lin3d.Plane3d;
import math.linalg.lin3d.Vector3d;

public class Polyhedron {
	protected Polygon3D[] faces;
	protected int maxVertexDegree, minVertexDegree;
	protected Polygon3D[] visibleFaces;
	
	public Polyhedron(Polygon3D[] faces) {
		this.faces = faces;
		calculateVertexDegrees();
		visibleFaces = new Polygon3D[maxVertexDegree];
	}
	
	// Default Empty constructor. Should only be used when inheriting from this superclass. 
	protected Polyhedron() {
		
	}
	
	/**
	 * Calculates the max and min vertex. Should be called once on initialization. 
	 */
	protected void calculateVertexDegrees() {
		IDictionary<Vector3d, Integer> dict = new ChainedHashDictionary();
		for (int i = 0; i < faces.length; i++) {
			Polygon3D face = faces[i];
			Vector3d[] vertices = face.getVertices();
			for (int j = 0; j < vertices.length; i++) {
				Vector3d vertex = vertices[j];
				if (!dict.containsKey(vertex)) {
					dict.put(vertex, 0);
				}
				dict.put(vertex, dict.get(vertex) + 1);
			}
		}
		minVertexDegree = Integer.MAX_VALUE;
		maxVertexDegree = 0;
		Iterator<KVPair<Vector3d, Integer>> iter = dict.iterator();
		while(iter.hasNext()) {
			KVPair<Vector3d, Integer> current = iter.next();
			int value = current.getValue();
			if(value < minVertexDegree) {
				minVertexDegree = value;
			} else if (value > maxVertexDegree) {
				maxVertexDegree = value;
			}
		}
		
		if (minVertexDegree < 3) {
			throw new IllegalArgumentException("This is not a valid Polyhedron.");
		}
	}
	
	/**
	 * Updates the parts of the polyhedron which needs to be drawn. 
	 * @param viewPlane
	 * @param zoom
	 * @param screenWidth. Screen width in pixels. Assumes equal screen height.
	 * @param lighting. Whether or not to render lighting effects. 
	 */
	public void updateDrawable(Plane3d viewPlane, double zoom, int screenWidth, boolean lighting) {
		for (int i = 0; i < visibleFaces.length; i++) {
			visibleFaces[i].updateDrawable(viewPlane, zoom, screenWidth);
			if(lighting) {
				visibleFaces[i].calculateLighting(viewPlane);
			}
			
		}
	}
	
	
	/**
	 * Gets the average distance from this polyhedron to the specified point. Also calculates and stores drawableFaces
	 * because these operations can be done in parallel to reduce computation. 
	 * @param point
	 * @return
	 */
	public double getAvgDist(Vector3d point) {
		IPriorityQueue<PolygonDistancePair> pq = new ArrayHeap<>();
		for (int i = 0; i < faces.length; i++) {
			pq.insert(new PolygonDistancePair(faces[i], faces[i].getAverageDistance(point)));
		}
		int count = 0;
		double distance = 0.0;
		while(!pq.isEmpty()) {
			PolygonDistancePair current =  pq.removeMin();
			Polygon3D p = current.getPolygon();
			distance += current.getDistance();
			if(count < maxVertexDegree) {
				visibleFaces[count++] = p;
			}
		}
		
		return distance / faces.length;
	}
	
	/**
	 * Gets the average distance from this polyhedron to the specified point (x, y, z). Also calculates and stores drawableFaces
	 * because these operations can be done in parallel to reduce computation. 
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public double getAvgDist(double x, double y, double z) {
		return getAvgDist(new Vector3d(x,y,z));
	}
	
	public Polygon3D[] getVisibleFaces() {
		return visibleFaces;
	}
	
	public Polygon3D[] getFaces() {
		return faces;
	}
	
	public void applyTransform(Matrix transform) {
		if (transform.getColumns() != 3 || transform.getRows() != 3) {
			throw new IllegalArgumentException(" Not a valid transform: R3 -> R3");
		}
		for (int i = 0; i < faces.length; i++) {
			faces[i].applyTransform(transform);
		}
	}
}