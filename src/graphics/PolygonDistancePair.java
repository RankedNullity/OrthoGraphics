package graphics;

/**
 * A polygon/distance pair object used to placing objects into a heap by their distance from caemra. 
 * @author Jaron Wang
 *
 */
public class PolygonDistancePair implements Comparable<PolygonDistancePair> {
	private Polygon3D polygon;
	private double distance; 
	
	public PolygonDistancePair(Polygon3D p, double distance) {
		this.polygon = p;
		this.distance = distance;
	}
	
	public Polygon3D getPolygon() {
		return polygon;
	}
	
	@Override
	public int compareTo(PolygonDistancePair other) {
		double difference = this.distance - other.distance;
		if (difference < 0) {
			return -1;
		} else if (difference > 0) {
			return 1;
		}
		return 0;
	}
	

}
