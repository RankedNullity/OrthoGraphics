package graphics.openglrender;

import java.nio.*;
import java.lang.Math;
import javax.swing.*;

import static com.jogamp.opengl.GL.GL_ARRAY_BUFFER;
import static com.jogamp.opengl.GL.GL_COLOR_BUFFER_BIT;
import static com.jogamp.opengl.GL.GL_DEPTH_BUFFER_BIT;
import static com.jogamp.opengl.GL.GL_DEPTH_TEST;
import static com.jogamp.opengl.GL.GL_FLOAT;
import static com.jogamp.opengl.GL.GL_LEQUAL;
import static com.jogamp.opengl.GL.GL_STATIC_DRAW;
import static com.jogamp.opengl.GL.GL_TRIANGLES;
import static com.jogamp.opengl.GL4.*;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.*;

import cube.Cube;
import cube.FullStickerCube;

import com.jogamp.common.nio.Buffers;

import org.joml.*;

public class CubeRenderer extends JFrame implements GLEventListener {
	// -------------------------Constants--------------------------------
	private static final float DEFAULT_CAMERA_ANGLE_X = 45.0f;
	private static final float DEFAULT_CAMERA_ANGLE_Y = 45.0f;
	private static final float DEFAULT_ZOOM = -18.0f;
	private static final float CUBLET_GAP = 0.1f;
	private  static final float[] UNIT_CUBE =
		{-1.0f,  1.0f, -1.0f,
				-1.0f, -1.0f, -1.0f,
				1.0f, -1.0f, -1.0f,
				
				1.0f, -1.0f, -1.0f,
				1.0f,  1.0f, -1.0f,
				-1.0f,  1.0f, -1.0f,
				
				1.0f, -1.0f, -1.0f,
				1.0f, -1.0f,  1.0f,
				1.0f,  1.0f, -1.0f,
				
				1.0f, -1.0f,  1.0f,
				1.0f,  1.0f,  1.0f,
				1.0f,  1.0f, -1.0f,
				
				1.0f, -1.0f,  1.0f,
				-1.0f, -1.0f,  1.0f,
				1.0f,  1.0f,  1.0f,
				
				-1.0f, -1.0f,  1.0f, 
				-1.0f,  1.0f,  1.0f,
				1.0f,  1.0f,  1.0f,
				
				-1.0f, -1.0f,  1.0f,
				-1.0f, -1.0f, -1.0f,
				-1.0f,  1.0f,  1.0f,
				
				-1.0f, -1.0f, -1.0f,
				-1.0f,  1.0f, -1.0f,
				-1.0f,  1.0f,  1.0f,
				
				-1.0f, -1.0f,  1.0f,
				1.0f, -1.0f,  1.0f,
				1.0f, -1.0f, -1.0f,
				
				1.0f, -1.0f, -1.0f,
				-1.0f, -1.0f, -1.0f,
				-1.0f, -1.0f,  1.0f,
				
				-1.0f,  1.0f, -1.0f,
				1.0f,  1.0f, -1.0f,
				1.0f,  1.0f,  1.0f,
				
				1.0f,  1.0f,  1.0f,
				-1.0f,  1.0f,  1.0f,
				-1.0f,  1.0f, -1.0f
			};
	
	
	// ---------------------------Fields----------------------------------
	private GLCanvas myCanvas;
	private float cameraX, cameraY, cameraZ;
	private Cube myCube;
	private int vao[] = new int[1];
	private int vbo[] = new int[2];
	private int renderingProgram;
	
	
	private FloatBuffer vals = Buffers.newDirectFloatBuffer(16);  // buffer for transfering matrix to uniform
	private Matrix4f pMat = new Matrix4f();  // perspective matrix
	private Matrix4f vMat = new Matrix4f();  // view matrix
	private Matrix4f mMat = new Matrix4f();  // model matrix
	private Matrix4f mvMat = new Matrix4f(); // model-view matrix
	private int mvLoc, projLoc;
	private float aspect;
	private float cubeLocX, cubeLocY, cubeLocZ;
	public CubeRenderer(int size) {
		myCube = new FullStickerCube(size);
		setTitle("Graphics Practice");
		setSize(1920, 1080);
		myCanvas = new GLCanvas();
		myCanvas.addGLEventListener(this);
		this.add(myCanvas);
		this.setVisible(true);
		Animator animator = new Animator(myCanvas);
		animator.start();
	}
	
	@Override
	public void display(GLAutoDrawable drawable) {
		GL4 gl = drawable.getGL().getGL4();
		gl.glClear(GL_COLOR_BUFFER_BIT);
		gl.glClear(GL_DEPTH_BUFFER_BIT);
		gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		mvLoc = gl.glGetUniformLocation(renderingProgram, "mv_matrix");
		projLoc = gl.glGetUniformLocation(renderingProgram, "proj_matrix");

		aspect = (float) myCanvas.getWidth() / (float) myCanvas.getHeight();
		pMat.setPerspective((float) Math.toRadians(60.0f), aspect, 0.1f, 1000.0f);

		vMat.translation(-cameraX, -cameraY, -cameraZ);

		mMat.translation(cubeLocX, cubeLocY, cubeLocZ);

		mvMat.identity();
		mvMat.mul(vMat);
		mvMat.mul(mMat);

		gl.glUniformMatrix4fv(mvLoc, 1, false, mvMat.get(vals));
		gl.glUniformMatrix4fv(projLoc, 1, false, pMat.get(vals));

		gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[0]);
		gl.glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		gl.glEnableVertexAttribArray(0);

		gl.glEnable(GL_DEPTH_TEST);
		gl.glDepthFunc(GL_LEQUAL);

		gl.glDrawArrays(GL_TRIANGLES, 0, 36);
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		GL4 gl = drawable.getGL().getGL4();
		renderingProgram = Utils.createShaderProgram("src/graphics/vertShader.glsl", "src/graphics/fragShader.glsl");
		setupCube();
		cameraX = 0.0f; cameraY = 0.0f; cameraZ = 8.0f;
		cubeLocX = 0.0f; cubeLocY = -2.0f; cubeLocZ = 0.0f;
	}
	
	private void setupCube() {
		GL4 gl = (GL4) GLContext.getCurrentGL();
		int size = myCube.getSize();

		gl.glGenVertexArrays(vao.length, vao, 0);
		gl.glBindVertexArray(vao[0]);
		gl.glGenBuffers(vbo.length, vbo, 0);
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				for (int z = 0; z < size; z++) {
					float[] currentCube = new float[UNIT_CUBE.length];
					for (int i = 0; i < UNIT_CUBE.length; i++) {
						if (i % 3 == 0) {
							currentCube[i] = UNIT_CUBE[i] + 2 * (x + CUBLET_GAP);
						} else if (i % 3 == 1) {
							currentCube[i] = UNIT_CUBE[i] + 2 * (y + CUBLET_GAP);
						} else { 
							currentCube[i] = UNIT_CUBE[i] + 2 * (z + CUBLET_GAP);
						}
					}
					gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[0]);
					FloatBuffer vBuff = Buffers.newDirectFloatBuffer(currentCube);
					gl.glBufferData(GL_ARRAY_BUFFER, vBuff.limit()*4, vBuff, GL_DYNAMIC_DRAW);
				}
			}
		}
	}
	
	
	public static void main(String[] args) { new CubeRenderer(1); }
	
	@Override
	public void dispose(GLAutoDrawable drawable) {	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL4 gl = drawable.getGL().getGL4();
		if (height == 0) height = 1;
		float aspect = (float) width/height;
		
		gl.glViewport(0, 0, width, height);
		
		// TODO: Update Resources based on window size. Projection matrix etc.
	}	
}