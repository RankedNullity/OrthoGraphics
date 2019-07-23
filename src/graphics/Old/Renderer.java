package graphics.Old;

public class Renderer {
	public final int width;
	public final int height;
	public final int[] pixels;
	
	public Renderer(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}
	
	public void draw(Renderer r, int xOffSet, int yOffset) {
		for (int y = 0; y < r.height; y++) {
			int yPix = y + yOffset;
			
		}
	}
}
