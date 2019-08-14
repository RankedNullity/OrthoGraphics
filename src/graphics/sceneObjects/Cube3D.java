package graphics.sceneObjects;

import java.awt.Color;

import cube.GameCube;
import graphics.scenes.Scene3D;
import math.linalg.Matrix;
import math.linalg.lin3d.Vector3d;

public class Cube3D extends Polyhedron {
	private Vector3d center;
	public Cube3D(double x, double y, double z, double width) { 
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
		minVertexDegree = maxVertexDegree = 3;
		visibleFaces = new int[maxVertexDegree];
	}
}
