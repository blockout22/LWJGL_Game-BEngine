package main;

import client.Game;

public class GameMain {

	public static void main(String[] args) {
		// new ServerGame(1234);
		// try {
		// Socket socket = new Socket("localhost", 1234);
		// PrintWriter out1 = new PrintWriter(socket.getOutputStream(), true);
		// BufferedReader in = new BufferedReader(new
		// InputStreamReader(socket.getInputStream()));
		// } catch (UnknownHostException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		new Game();
	}
}
