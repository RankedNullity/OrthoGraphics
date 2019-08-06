package graphics;

import graphics.sceneObjects.SceneObject;

/**
 * A polygon/distance pair object used to placing objects into a heap by their distance from caemra. 
 * @author Jaron Wang
 *
 */
public class ObjectDistancePair implements Comparable<ObjectDistancePair> {
	private SceneObject obj;
	private double distance; 
	
	public ObjectDistancePair(SceneObject obj, double distance) {
		this.obj = obj;
		this.distance = distance;
	}
	
	public SceneObject getObject() {
		return obj;
	}
	
	public double getDistance() {
		return distance;
	}
	
	@Override
	public int compareTo(ObjectDistancePair other) {
		double difference = this.distance - other.distance;
		if (difference < 0) {
			return -1;
		} else if (difference > 0) {
			return 1;
		}
		return 0;
	}
	

}
