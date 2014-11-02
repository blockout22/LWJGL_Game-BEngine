package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import engine.Camera;
import engine.Entity;
import engine.OBJLoader;
import engine.TexturedMesh;
import engine.Window;

public class Game {

	// Server
	private Socket socket = null;
	private PrintWriter out = null;
	private BufferedReader in = null;
	//

	private TexturedMesh mesh, mesh2, playerMesh;
	private MyShader shader;

	public Camera cam;

	protected ArrayList<TexturedMesh> meshList = new ArrayList<TexturedMesh>();

	private Entity entity, entity2, thePlayer;
	private boolean isConnected = false;

	public Game(boolean connect) {
		Window.createWindow(800, 600, 3.2);
		Window.enableDepthBuffer();
		Window.setViewport();

		if (connect) {
			try {
				socket = new Socket("localhost", 123);
				out = new PrintWriter(socket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(System.in));

				String userName = JOptionPane.showInputDialog("Input A UserName");
				out.println("#user" + userName);
				isConnected = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		init();
	}

	private void init() {
		mesh = OBJLoader.loadObjModel("models/stall.obj", "images/stall.png");
		mesh2 = OBJLoader.loadObjModel("models/stall.obj", "images/image0.png");
		shader = new MyShader();

		cam = new Camera(70f, Window.getAspectRatio(), 0.1f, 100f);

		entity = new Entity(mesh2, new Vector3f(-10, 0, -10), 0, 0, 0, 1);
		entity2 = new Entity(mesh2, new Vector3f(0, 0, 0), 0, 0, 0, 1);

		playerMesh = OBJLoader.loadObjModel("models/stall.obj", "images/stall.png");
		thePlayer = new Entity(playerMesh, new Vector3f(-cam.getCameraPos().x, -cam.getCameraPos().y, -cam.getCameraPos().z), 0, 0, 0, 1);

		gameLoop();
	}

	private void gameLoop() {
		while (!Window.isCloseRequested()) {
			if (isConnected) {
				serverComunication();
			}
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

		for (TexturedMesh m : meshList) {
			m.draw();
		}

		shader.loadProjectionMatrix(cam.getProjectionMatrix());
		shader.loadViewMatrix(cam.getViewMatrix());
		shader.loadModelMatrix(cam.getModelMatrix());
		mesh.drawEntity(entity2, shader, shader.getModelMatrixLocation());
		mesh2.drawEntity(entity, shader, shader.getModelMatrixLocation());

		playerMesh.drawEntity(thePlayer, shader, shader.getModelMatrixLocation());
		thePlayer.setPosition(new Vector3f(-cam.getCameraPos().x, -cam.getCameraPos().y - 10, -cam.getCameraPos().z - 10));

		Window.setTitle(cam.getCameraPosAsString() + " | " + thePlayer.getPosition());

		entity.setRotation(0, 180f, 0);
		shader.unbind();
	}

	private void update() {
		Input.update(this);

		Window.update();
	}

	//TODO
	private void serverComunication() {
		try {
			String input = in.readLine();
			if (input != null) {
				System.out.println(input);
			}

			Vector3f pos = cam.getCameraPos();
			out.println("#position" + pos);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
