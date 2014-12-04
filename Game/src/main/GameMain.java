package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

import client.Game;

public class GameMain {
	
	private static Socket socket = null;
	private static PrintWriter out = null;
	private static BufferedReader in = null;
	
	public static void main(String[] args) {
		try {
			
//			socket= new Socket("localhost", 123);
//			out = new PrintWriter(socket.getOutputStream(), true);
//			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
//			
//			String userName = JOptionPane.showInputDialog("Input A UserName");
//			out.println("#user" + userName);
//			
//			send();
//			recieve();
			new Game(false);
//			new Game2();
			
//			new Thread(new Runnable() {
//				public void run() {
//					try{
//						String input;
//						while((input = stdIn.readLine()) != null)
//						{
//							out.println(input);
//						}
//					}catch(Exception e)
//					{
//						e.printStackTrace();
//					}
//				}
//			}).start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void send() {
		System.out.println("send Called");
		new Thread(new Runnable() {
			public void run() {
				System.out.println("Send running");
				while (true) {
					System.out.println("loop");
					try {
//						Vector3f pos = game.cam.getCameraPos();
//						out.print("#position" + pos);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	private static void recieve() {
		new Thread(new Runnable() {
			public void run() {
				String input;
				try {
					while ((input = in.readLine()) != null) {
						System.out.println(input);
					}
				} catch (IOException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e + "\n Closing Application");
					System.exit(1);
				}
			}
		}).start();
	}
}
