package math.linalg;

public class Vector implements Matrix {
	private boolean column;
	private double[] container;
	
	/**
	 * Creates a new vector with length of size and true if it is a column vector. 
	 * @param size
	 * @param column
	 */
	public Vector(int size, boolean column) {
		this.column = column;
		container = new double[size];
	}
	
	public Vector(double[] contents, boolean column) {
		this.column = column;
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
	public Matrix transpose() {
		return new Vector(this.container, !column);
	}

	@Override
	public Matrix deepCopy() {
		return new Vector(this.container, column);
	}

}
