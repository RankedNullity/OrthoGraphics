package graphics.sceneObjects;

import java.awt.Graphics;

import math.linalg.Matrix;
import math.linalg.lin3d.Plane3d;
import math.linalg.lin3d.Vector3d;

public interface SceneObject {
	public void updateDrawable(Plane3d viewPlane, double zoom, int screenWidth, boolean lighting);
	public double getAvgDistance(Vector3d point);
	public double getAvgDistance(double x, double y, double z);
	public void applyTransform(Matrix m);
	public void render(Graphics g);
}
