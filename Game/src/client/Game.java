package client;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import engine.Camera;
import engine.Entity;
import engine.OBJLoader;
import engine.Shader;
import engine.TexturedMesh;
import engine.Window;

public class Game {

	private TexturedMesh mesh, house;
	private Shader shader;
	
	private Entity entity;
	
	private Camera cam;
	
	private int projectionMatrixLocation, viewMatrixLocation, modelMatrixLocation;

	public Game() {
		Window.createWindow(800, 600, 3.2);
		Window.enableDepthBuffer();
		Window.setViewport();

		init();
	}

	private void init() {
		mesh = OBJLoader.loadObjModel("models/stall.obj", "images/stall.png");
		house = OBJLoader.loadObjModel("models/Ideale 770.obj", "images/stall.png");
		shader = new Shader("vertex.glsl", "fragment.glsl");
		
		entity = new Entity(mesh, new Vector3f(0, 0, -50), 0, 0, 0, 1);
		
		cam = new Camera(70f, Window.getAspectRatio(), 0.1f, 100f);

		shader.bindAttribLocation(0, "in_Position");
		shader.bindAttribLocation(1, "in_texCoords");
		shader.linkAndValidate();
		
		projectionMatrixLocation = shader.getUniformLocation("projectionMatrix");
		viewMatrixLocation = shader.getUniformLocation("viewMatrix");
		modelMatrixLocation = shader.getUniformLocation("modelMatrix");
		
		gameLoop();
	}

	private void gameLoop() {
		while (!Window.isCloseRequested()) {
			cam.useView();
			Window.enableDepthBuffer();
			Window.clearAll(0.3f, 0.4f, 0.8f, 0.0f);
			render();
			update();
		}

		Window.close();
	}

	private void render() {
		shader.bind();
		mesh.draw();
		house.draw();
		shader.getUniform(projectionMatrixLocation, cam.getProjectionMatrix());
		shader.getUniform(viewMatrixLocation, cam.getViewMatrix());
		shader.getUniform(modelMatrixLocation, cam.getModelMatrix());
		shader.unbind();
	}
	
	private void update() {
		Input.update(cam);
		
		Window.update();
	}

	private float[] getVertices() {
		float[] vertices = new float[] {
				//
				-0.5f, 0.5f, 0f, //
				-0.5f, -0.5f, 0f,//
				0.5f, -0.5f, 0f,//
				0.5f, 0.5f, 0f //
		};

		return vertices;
	}

	public float[] getTexCoords() {
		float[] texCoords = new float[] {
				//
				0, 0, //
				0, 1, //
				1, 1, //
				1, 0 //
		};

		return texCoords;
	}

	private int[] getIndices() {
		int[] indices = new int[] {
				//
				0, 1, 2, //
				2, 3, 0 //
		};

		return indices;
	}

	// Vector

	private Vector3f[] getVectorVertices() {
		Vector3f[] vertices = new Vector3f[] {
				//
				new Vector3f(-1f, -1f, -0f), // Left top ID: 0
				new Vector3f(0f, 1f, -0f), // Left bottom ID: 1
				new Vector3f(1f, -1f, -0f), // Right bottom ID: 2
				new Vector3f(0f, -1f, -0f) // Right left ID: 3

		};

		return vertices;
	}

	private Vector2f[] getVectorTexCoords() {
		Vector2f[] texCoords = new Vector2f[] {
				//
				new Vector2f(0, 0), //
				new Vector2f(0, 1), //
				new Vector2f(1, 1), //
				new Vector2f(1, 0) //

		};

		return texCoords;
	}

}
