package App;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Consola_A {
	
	private ServerSocket serverSocket;
	private Socket acceptSocket;
	private PrintStream output;
	private BufferedReader input;
	private Scanner scan = new Scanner(System.in);
	
	public static void main(String[]args) {
		Consola_A server = new Consola_A();
		server.run();
	}
	
	public void run () {
		try {
			serverSocket = new ServerSocket(7935);
			acceptSocket = serverSocket.accept();
			
			
			output = new PrintStream(acceptSocket.getOutputStream());
			InputStream inputStream = acceptSocket.getInputStream();
		        // create a DataInputStream so we can read data from it.
		    DataInputStream dataInputStream = new DataInputStream(inputStream);
		    String message = dataInputStream.readUTF();
		    System.out.println("The message sent from the socket was: " + message);
		    
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}