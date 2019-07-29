package math.linalg;

import common.misc.exceptions.IllegalOperationException;
import math.linalg.lin3d.Vector3D;

public class Vector implements Matrix {
	private boolean column;
	private double[] container;
	
	public Vector3D get3DVector() {
		if (container.length != 3) {
			throw new IllegalOperationException();
		}
		return new Vector3D(container[0], container[1], container[2]);
	}
	
	/**
	 * Creates a new vector with length of size and true if it is a column vector. 
	 * @param size
	 * @param column
	 */
	public Vector(int size, boolean column) {
		this.column = column;
		container = new double[size];
	}
	
	/**
	 * Creates a new vector with a copy of contents and true if it is a column vector. 
	 * @param contents
	 * @param column
	 */
	public Vector(double[] contents, boolean column) {
		this(contents.length, column);
		for (int i = 0; i < contents.length; i++) {
			container[i] = contents[i];
		}
	}
	
	
	@Override
	public int getRows() {
		return (column) ? container.length: 1;
		
	}

	@Override
	public int getColumns() {
		return (column) ? 1: container.length;
	}
	 
	/**
	 * More convenient getter for vector. 
	 * @param i
	 * @return
	 */
	public double get(int i) {
		return container[i];
	}

	@Override
	/**
	 * For a vector, returns only i if it is row vector, 
	 */
	public double get(int i, int j) {
		return container[(column) ? i :j];
	}

	@Override
	public void set(int i, int j, double value) {
		container[(column) ? i :j] = value;
	}

	@Override
	public Vector transpose() {
		return new Vector(this.container, !column);
	}

	@Override
	public Vector deepCopy() {
		return new Vector(this.container, column);
	}

	/**
	 * Returns how many values this vector holds. 
	 * @return
	 */
	public int getDimension() {
		return container.length;
	}
	
	/**
	 * Returns the Euclidean length of this vector.
	 * @return
	 */
	public double getLength() {
		return LinAlg.norm(this, 2);
	}
	
	/**
	 * Mutates this vector to be of unit length. 
	 */
	public void normalize() {
		double length = getLength();
		for (int i = 0; i < container.length; i++) {
			container[i] = container[i] / length;
		}
	}
	
	
}
