package App;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;

import java.io.*;
import java.net.Socket;

public class Conexion extends JFrame {
	
	private static Socket clientSocket;
	private static BufferedReader input;
	private static PrintStream output;
	private static OutputStream outputStream;
	private static DataOutputStream dataOutputStream;
	private static InputStream inputStream;
	private static DataInputStream dataInputStream;

	int boundx=5;
	int boundy=5;
	int sizex=10;
	int sizey=10;
	public static void main(String[]args) throws IOException{
			Pantalla frame = new Pantalla();
			frame.FirstUpdate();
			frame.setVisible(true);
			clientSocket = new Socket("localhost",7935);
			inputStream = clientSocket.getInputStream();
		    dataInputStream = new DataInputStream(inputStream);
		    String message = dataInputStream.readUTF();
		    frame.setTitle(message);
		    while (true) {				
		    	inputStream = clientSocket.getInputStream();
			    dataInputStream = new DataInputStream(inputStream);
			    message = dataInputStream.readUTF();
			    System.out.println("Comando recibido: "+message);
		    }
		   
		    
			
		}
	}