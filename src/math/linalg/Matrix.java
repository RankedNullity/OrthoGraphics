package math.linalg;

public interface Matrix {
	
	public int getRows();
	
	public int getColumns();
	
	public double get(int i, int j);
	
	public void set(int i, int j, double value);
	
	public Matrix transpose();
	
	public Matrix deepCopy();

}
