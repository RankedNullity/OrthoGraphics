package graphics;

import java.awt.Color;

import math.linalg.Matrix;

public class SceneCube {
	private Polygon3D[] faces;
	private Scene3D scene;

	public SceneCube(Scene3D scene, double x, double y, double z, double width) {
		// TODO: Fix the faces arrray to contain the correct facing face by gameCube convention. 
		this.scene = scene;
		faces[0] = new Polygon3D(new double[] { x, x + width, x + width, x },
				new double[] { y, y, y + width, y + width }, new double[] { z, z, z, z }, Color.white);
		faces[1] = new Polygon3D(new double[] { x, x + width, x + width, x },
				new double[] { y, y, y + width, y + width },
				new double[] { z + width, z + width, z + width, z + width }, Color.blue);
		faces[2] = new Polygon3D(new double[] { x, x, x + width, x + width }, new double[] { y, y, y, y },
				new double[] { z, z + width, z + width, z }, Color.red);
		faces[3] = new Polygon3D(new double[] { x + width, x + width, x + width, x + width },
				new double[] { y, y, y + width, y + width }, new double[] { z, z + width, z + width, z }, Color.green);
		faces[4] = new Polygon3D(new double[] { x, x, x + width, x + width },
				new double[] { y + width, y + width, y + width, y + width },
				new double[] { z, z + width, z + width, z }, Color.yellow);
		faces[5] = new Polygon3D(new double[] { x, x, x, x }, new double[] { y, y, y + width, y + width },
				new double[] { z, z + width, z + width, z }, Color.cyan);

		for (int i = 0; i < faces.length; i++) {
			scene.addPolygon(faces[i]);
		} 
	}

	/**
	 * Applies the matrix transform to each point of the SceneCube.
	 * 
	 * @param transform
	 */
	public void applyTransform(Matrix transform) {
		if (transform.getColumns() != 3 || transform.getRows() != 3) {
			throw new IllegalArgumentException(" Not a valid transform: R3 -> R3");
		}
		for (int i = 0; i < faces.length; i++) {
			faces[i].applyTransform(transform);
		}
	}
	
	
	public void rotateAroundX(double ang) {
		
	}

}
