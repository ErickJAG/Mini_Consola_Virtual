package App;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Consola_B {
	
	private ServerSocket serverSocket;
	private Socket acceptSocket;
	private PrintStream output;
	private BufferedReader input;
	private Scanner scan = new Scanner(System.in);
	
	public static void main(String[]args) {
		Consola_B server = new Consola_B();
		server.run();
	}
	
	public void run () {
		try {
			serverSocket = new ServerSocket(7935);
			acceptSocket = serverSocket.accept();
			
			
			output = new PrintStream(acceptSocket.getOutputStream());
			input = new BufferedReader(new InputStreamReader(acceptSocket.getInputStream()));
			
			while(acceptSocket.isConnected()) {
			String message = input.readLine();
			System.out.println(message);
			
			String reply = scan.nextLine();
			output.println("servidor: "+reply);
			
			}
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}