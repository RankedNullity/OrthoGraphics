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
	
	/**
	 * Creates a new vector with a copy of contents and true if it is a column vector. 
	 * @param contents
	 * @param column
	 */
	
	
	public Vector(double[] contents, boolean column) {
		this.column = column;
		
		// container was never initiated so it throws NullPointer in the for loop when container[i] is first called
		// -Alex Note
		container = new double[contents.length];
		for (int i = 0; i < contents.length; i++) {
			System.out.println(i);
			container[i] = contents[i];
		}
	}
	
	/**
	 * Creates a new vector in R3 with the given components. (Mainly for use in graphics). 
	 * @param x
	 * @param y
	 * @param z
	 */
	public Vector(double x, double y, double z) {
		container = new double[] { x, y, z};
		column = true; 
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
	public Matrix transpose() {
		return new Vector(this.container, !column);
	}

	@Override
	public Matrix deepCopy() {
		return new Vector(this.container, column);
	}

	
	public int getLength() {
		return container.length;
	}
}
