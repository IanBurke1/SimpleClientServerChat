package ChatAppWithGui;

import java.io.*;
import java.net.*;
import java.util.*;

public class SimpleChatServer {
	
	ArrayList clientOutputStreams;
	
	public static void main(String[] args) {
		new SimpleChatServer().go();
	}
	
	public void go() {
		clientOutputStreams = new ArrayList();
		try {
			ServerSocket ss = new ServerSocket(5000);
			
			while(true) {
				Socket clientSocket = ss.accept();
				PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
				clientOutputStreams.add(writer);
				
				Thread t = new Thread(new ClientHandler(clientSocket));
				t.start();
				System.out.println("Connected");
			}//while end
		} catch (Exception e) {e.printStackTrace();}
	}//go() end
	
	public void tellEveryone(String message) {
		Iterator it = clientOutputStreams.iterator();
		while(it.hasNext()) {
			try {
			PrintWriter writer = (PrintWriter) it.next();
			writer.println(message);
			writer.flush();
			
			}catch (Exception e) {e.printStackTrace();}
		}//while end
	}//tellEveryone() end

	public class ClientHandler implements Runnable {
		
		BufferedReader reader;
		Socket sock;
		
		public ClientHandler(Socket clientSocket) {
			try {
				sock = clientSocket;
				InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
				reader = new BufferedReader(isReader);
				
			} catch (Exception e) {e.printStackTrace();}
		}// constructor end

		@Override
		public void run() {
			String message;
			try {
				
				while ((message = reader.readLine()) != null) {
					System.out.println("read " + message);
					tellEveryone(message);
				}//while end
				
			} catch (Exception e) {e.printStackTrace();}
			
		}// run() end
		
	}//inner class end
	
}// class end
