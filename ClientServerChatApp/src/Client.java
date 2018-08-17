import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

/*
 * This class connects with the Server by creating a Socket with the IP address
 * and Port. It then reads in any message sent by the server using a connection stream (InputStreamReader)
 * and chain stream (BufferedReader).
 */

public class Client {
	
	//Method to connect to server.
	public void connect() {
		
		//Using try/catch to deal with any errors that may occur..
		try {
			// Create our socket object containing the IP address and port to where the Server is hosted.
			Socket s = new Socket("127.0.0.1", 4242);
			
			//To receive data through a stream, we need to create a InputStreamReader that connects both client and server using the socket.
			InputStreamReader streamReader = new InputStreamReader(s.getInputStream());
			//Create our BufferedReader object to chain to the stream and read in the data.
			BufferedReader reader = new BufferedReader(streamReader);
			//The readLine method takes care of the reading in of data. Does not know or care where the data comes from.
			String message = reader.readLine();
			//Print to the console
			System.out.println("Server: " + message);
			
			//Close all the streams
			reader.close();
			
			//catch our exceptions if any..
		} catch (Exception e) {e.printStackTrace();}
		
	}//listening() end
	
	public static void main(String[] args) {
		Client client = new Client();
		client.connect();
	}

}//Class end
