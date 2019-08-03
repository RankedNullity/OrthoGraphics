package graphics;

import java.awt.Color;

import cube.GameCube;
import graphics.scenes.Scene3D;
import math.linalg.Matrix;

public class SceneCube {
	private Polygon3D[] faces;
	private Scene3D scene;

	public SceneCube(Scene3D scene, double x, double y, double z, double width) { 
		this.scene = scene;
		faces = new Polygon3D[6];
		faces[GameCube.DOWN] = new Polygon3D(new double[] { x, x + width, x + width, x },
								 new double[] { y, y, y + width, y + width },
								 new double[] { z, z, z, z }, Color.white);
		faces[GameCube.UP] = new Polygon3D(new double[] { x, x + width, x + width, x },
								 new double[] { y, y, y + width, y + width },
							 	new double[] { z + width, z + width, z + width, z + width }, Color.blue);
		faces[GameCube.LEFT] = new Polygon3D(new double[] { x, x, x + width, x + width }, 
								 new double[] { y, y, y, y },
							 	 new double[] { z, z + width, z + width, z }, Color.red); 
		faces[GameCube.FRONT] = new Polygon3D(new double[] { x + width, x + width, x + width, x + width },
								new double[] { y, y, y + width, y + width },
								new double[] { z, z + width, z + width, z }, Color.green);
		faces[GameCube.RIGHT] = new Polygon3D(new double[] { x, x, x + width, x + width },
								new double[] { y + width, y + width, y + width, y + width },
								new double[] { z, z + width, z + width, z }, Color.yellow);
		faces[GameCube.BACK] = new Polygon3D(new double[] { x, x, x, x }, 
								new double[] { y, y, y + width, y + width },
								new double[] { z, z + width, z + width, z }, Color.cyan);

		for (int i = 0; i < faces.length; i++) {
			scene.addPolygon(faces[i]); 
		} 
	}

	/**
	 * Applies the matrix transform to each point of the SceneCube. (Dev Tool)
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
	

}
