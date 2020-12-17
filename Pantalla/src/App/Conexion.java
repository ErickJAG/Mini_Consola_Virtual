package App;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.Color;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;

import java.io.*;
import java.net.Socket;

public class Conexion extends JFrame {
	
	private static Socket clientSocket;
	private static InputStream inputStream;
	private static DataInputStream dataInputStream;

	
	//Funcion que crea la conexion al server y envia los mensajes a la pantalla
	public static void main(String[]args) throws IOException{
			Pantalla frame = new Pantalla();
			frame.setVisible(true);
			clientSocket = new Socket("localhost",7935);
			inputStream = clientSocket.getInputStream();
		    dataInputStream = new DataInputStream(inputStream);
		    String message = dataInputStream.readUTF();
		    frame.setTitle(message);
		    while (true) {		
		    	//Recepcion del mensaje y envio a pantalla
		    	inputStream = clientSocket.getInputStream();
			    dataInputStream = new DataInputStream(inputStream);
			    message = dataInputStream.readUTF();
			    frame.UpdateS(message);
		    }
		   
		    
			
		}
	}