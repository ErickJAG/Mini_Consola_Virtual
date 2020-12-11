package App;

import java.awt.List;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONObject;

public class Consola_B {
	
	private ServerSocket serverSocket;
	private Socket acceptSocket1;
	private Socket acceptSocket2;
	private OutputStream outputStream;
	private DataOutputStream dataOutputStream;
	private static int [][] laberinto = MatrizLaberinto.matriz;
	private static int [][] laberintoCopia = MatrizLaberinto.matriz;
	private static ArrayList<int[]> response = new ArrayList<int[]>();
	
	public static void main(String[]args) {
		Consola_B server = new Consola_B();
		server.run();
	}
	
	public void run () {
		try {
			serverSocket = new ServerSocket(7935);
			acceptSocket1 = serverSocket.accept();
			acceptSocket1.setKeepAlive(true);
			outputStream = acceptSocket1.getOutputStream();
			dataOutputStream = new DataOutputStream(outputStream);
			dataOutputStream.writeUTF("Console B");
			dataOutputStream.flush();
			System.out.println(acceptSocket1);
			acceptSocket2 = serverSocket.accept();
			acceptSocket2.setKeepAlive(true);
			outputStream = acceptSocket2.getOutputStream();
			dataOutputStream = new DataOutputStream(outputStream);
			dataOutputStream.writeUTF("Console B");
			dataOutputStream.flush();
			System.out.println(acceptSocket2);
			while (true){
				InputStream inputStream = acceptSocket1.getInputStream();
			    DataInputStream dataInputStream = new DataInputStream(inputStream);
			    String message = dataInputStream.readUTF();
			    System.out.println("Comando enviado: "+message);
			    Move(message);
			    ArrayList<int[]> NewMessage = Compare(response);
			    JSONObject JSONR=new JSONObject();
			    JSONR.put("cambios",NewMessage);
			    String ScreenChange = JSONR.toString();
			    outputStream = acceptSocket2.getOutputStream();
				dataOutputStream = new DataOutputStream(outputStream);
				dataOutputStream.writeUTF(ScreenChange);
				dataOutputStream.flush();
			  
			}
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void Move(String input) {
		for (int i = 0; i<50; i++) {
			for (int j = 0; j<50; j++) {
				if (laberinto[i][j] == 8) {
					System.out.println(input);
					if (input.equals("Up")) {
						if (i>0) {
							if (laberinto[i-1][j]==3) {
								laberinto[i-1][j]=8;
								laberinto[i][j]=3;
							}
						}
					}
					else if (input.equals("Left")) {
						if (j>0) {
							if (laberinto[i][j-1]==3) {
								laberinto[i][j-1]=8;
								laberinto[i][j]=3;
							}
						}
					}
					else if (input.equals("Down")) {
						if (i<49) {
							if (laberinto[i+1][j]==3) {
								laberinto[i+1][j]=8;
								laberinto[i][j]=3;
							}
						}
					}
					else if (input.equals("Right")) {
						if (j<49) {
							if (laberinto[i][j+1]==3) {
								laberinto[i][j+1]=8;
								laberinto[i][j]=3;
							}
						}
					}
				}
			}
		}
	}
	public ArrayList<int[]> Compare(ArrayList<int[]> respuesta) {
		for (int i = 0; i<50; i++) {
			for (int j = 0; j<50; j++) {
				System.out.print(laberinto[i][j]);

				if (!(String.valueOf(laberinto[i][j])).equals(String.valueOf(laberintoCopia[i][j]))) {
					int [] cambio = {i,j,laberinto[i][j]};
					respuesta.add(cambio);
				}
			}System.out.println();
		}	
		return respuesta;
	
	}
}