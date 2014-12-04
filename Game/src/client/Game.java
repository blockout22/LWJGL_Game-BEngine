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

import engine.Window;
import engine.camera.Camera;
import engine.entity.Entity;
import engine.loader.OBJLoader;
import engine.mesh.Mesh;
import engine.sound.Music;
import browser.WebBrowser;

public class Game {

	private boolean isSettingUp = false;

	// Server
	private Socket socket = null;
	private PrintWriter out = null;
	private BufferedReader in = null;
	//

	private static WebBrowser browser;

	private Mesh mesh;
	private MyShader shader;

	public Camera cam;

	protected ArrayList<Mesh> meshList = new ArrayList<Mesh>();

	private Entity entity;
	private boolean isConnected = false;

	private static Music music = new Music("sounds/AN.wav");

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
		try {
			browser = new WebBrowser();
		} catch (Exception e) {
			e.printStackTrace();
		}
		init();
	}

	private void init() {
		shader = new MyShader();
		cam = new Camera(70f, Window.getAspectRatio(), 0.1f, 100f);
		mesh = OBJLoader.loadObjModel("models/stall.obj", "images/stall.png", shader.getProjectionMatrixLocation(), shader, cam);
		entity = new Entity(mesh, new Vector3f(0, 0, -20), 0, 0, 0, 1);

		gameLoop();

		System.out.println("Done!");
	}

	private void gameLoop() {
		while (!Window.isCloseRequested()) {
			if (isConnected) {
				serverComunication();
			}
			cam.move();
			Window.clearAll(0.3f, 0.4f, 0.8f, 0.0f);
			render();
			update();
		}

		Window.close();
	}

	private void render() {
		shader.bind();
		shader.loadViewMatrix(cam);
		mesh.draw(shader.getModelMatrixLocation(), entity);
		shader.unbind();

		Window.update();
		Window.sync(60);
	}

	private void update() {
		Input.update(this);
		// music.playAtLocation(cam, 25, -10, 25, 15);
	}

	// TODO
	private void serverComunication() {
		try {
			String input = in.readLine();
			if (input != null) {
				System.out.println(input);
			}

			Vector3f pos = cam.getPosition();
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

	public static WebBrowser getBrowser() {
		return browser;
	}

	public static Music getMusic() {
		return music;
	}

}
