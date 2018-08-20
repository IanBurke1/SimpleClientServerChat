package ChatAppWithGui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

import javax.swing.*;

public class SimpleChatClientA {

	//Instance Variables
	JTextField outgoing;
	PrintWriter writer;
	Socket sock;
	
	public void run() {
		
		// GUI
		JFrame frame = new JFrame("Simple Chat Client");
		JPanel mainPanel = new JPanel();
		outgoing = new JTextField(20);
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new SendButtonListener());
		mainPanel.add(outgoing);
		mainPanel.add(sendButton);
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		setUpNetworking();
		frame.setSize(400, 500);
		frame.setVisible(true);
		
	}//run() end
	
	//method to set up the socket and connections
	private void setUpNetworking() {
		try {
			sock = new Socket("127.0.0.1", 5000);
			writer = new PrintWriter(sock.getOutputStream());
			System.out.println("networking established");
			
		} catch (Exception e) {e.printStackTrace();}
	}//setUpNetworking() end
	
	//What happens when the user clicks the send button...
	public class SendButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ev) {
			try {
				writer.println(outgoing.getText());
				writer.flush();
				
			} catch (Exception e) {e.printStackTrace();}
			
			outgoing.setText("");
			outgoing.requestFocus();
		}
		
	}//SendButtonListener inner class end
	
	
	//Testing 
	public static void main(String[] args) {
		SimpleChatClientA clientA = new SimpleChatClientA();
		clientA.run();
	}
	
}//class end
