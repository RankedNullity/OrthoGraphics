package graphics.Old;

import java.awt.Canvas;

public class Display extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	private int width;
	private int height;
	private static final String TITLE = "Project BigCube - 0.0.0";
	
	private Thread thread;
	private boolean running;
	
	
	public Display() { 
		this(1920, 1080);
	};
	
	public Display(int width, int height) { 
		this.width = width;
		this.height = height; 
	}
	
	private void start() {
		if (!running) {
			running = true;
			thread = new Thread(this);
			thread.start();
		}
	}

	@Override
	public void run() {
		while (running) {
			
		}
	}
	
	public void stop() {
		if (! running) {
			return;
		}
		running = false;
		try {
			thread.join();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
	}
	
	
	
}
