package BasicClientServer;
import java.io.PrintWriter;
import java.net.*;

/*
 * This class creates a server which listens out for the client and responds with a message.
 */
public class Server {
	
	//method to run the server.
	public void run() {
		//try/catch to deal with errors if any..
		try {
			//Create a ServerSocket object that listens for client requests on port 4242.
			ServerSocket serverSock = new ServerSocket(4242);
			
			System.out.println("Running...");
			
			//infinite loop to wait for client requests..
			while(true) {
				//Create our Socket object that uses accept method to wait for request to come in and return a socket for communication with client.
				Socket s = serverSock.accept();
				//Create PrintWriter object to chain to the OutputStream connection and send a string message.
				PrintWriter writer = new PrintWriter(s.getOutputStream());
				//Our message to send to connected client.
				String message = "Hello";
				//Just like System.out , we print out the message
				writer.println(message);
				//close all streams.
				writer.close();
				
				//Just some feedback to see if the connection to client is working
				if(s.isConnected()) {
				System.out.println("Connected to client");
				
				}else {
					System.out.println("No connection");
				}
			}
			
		} catch (Exception e) {e.printStackTrace();}
	}//run() end
	
	public static void main(String[] args) {
		Server server = new Server();
		server.run();
	}
}// class end
