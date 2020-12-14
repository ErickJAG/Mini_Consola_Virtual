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
	
	
	public static void main(String[]args) {
		Consola_B server = new Consola_B();
		server.run();
	}
	//Funcion principal para recibir/enviar mensajes
	public void run () {
		try {
			//Union del primer cliente
			serverSocket = new ServerSocket(7935);
			acceptSocket1 = serverSocket.accept();
			acceptSocket1.setKeepAlive(true);
			outputStream = acceptSocket1.getOutputStream();
			dataOutputStream = new DataOutputStream(outputStream);
			dataOutputStream.writeUTF("Console B");
			dataOutputStream.flush();
			System.out.println(acceptSocket1);
			//Union del segundo cliente
			acceptSocket2 = serverSocket.accept();
			acceptSocket2.setKeepAlive(true);
			outputStream = acceptSocket2.getOutputStream();
			dataOutputStream = new DataOutputStream(outputStream);
			dataOutputStream.writeUTF("Laberinto");
			dataOutputStream.flush();
			System.out.println(acceptSocket2);
			//Envio de la primera matriz
			ArrayList<int[]> pixelesIniciales = new ArrayList<int[]>();
			ArrayList<ArrayList<int[]>> PrimeraMatriz = SendMatrix(pixelesIniciales);
			JSONObject MI=new JSONObject();
			MI.put("cambios", PrimeraMatriz);
			String MatrizInicial = MI.toString();
			dataOutputStream.writeUTF(MatrizInicial);
			dataOutputStream.flush();
			//Ciclo para enviar/recibir mensajes
			while (true){
				InputStream inputStream = acceptSocket1.getInputStream();
			    DataInputStream dataInputStream = new DataInputStream(inputStream);
			    String message = dataInputStream.readUTF();
			    ArrayList<int[][]> response = new ArrayList<int[][]>();
			    ArrayList<int[][]> NewMessage = Move(message,response);
			    //Validacion en caso de victoria para no enviar mensajes
			    if (NewMessage.size()>0) {
			    	if (laberinto[42][0] != 8) {
			    		System.out.println("Comando realizado: "+message);
			    		JSONObject JSONR=new JSONObject();
					    JSONR.put("cambios",NewMessage);
					    String ScreenChange = JSONR.toString();
						dataOutputStream.writeUTF(ScreenChange);
						dataOutputStream.flush();
			    	}
			    }
			    else {
			    	System.out.println("No hubo movimiento");
			    }
			  
			}
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	//Funcion que prepara el mensaje con los movimientos realizados
	public ArrayList<int[][]> Move(String input,ArrayList<int[][]> respuesta) {
		if (input.equals("Up") || input.equals("Left")) {
			for (int i = 0; i<50; i++) {
				for (int j = 0; j<50; j++) {
					if (laberinto[i][j] == 8) {
						if (input.equals("Up")) {
							if (i>0) {
								if (laberinto[i-1][j]==3) {
									laberinto[i-1][j]=8;
									laberinto[i][j]=3;
									int [][] cambio = {{i-1,j,8},{i,j,3}};
									respuesta.add(cambio);
									
								}
							}
						}else if (input.equals("Left")) {
							if (j>0) {
								if (laberinto[i][j-1]==3) {
									laberinto[i][j-1]=8;
									laberinto[i][j]=3;
									int [][] cambio = {{i,j-1,8},{i,j,3}};
									respuesta.add(cambio);
								}
							}
						}
					}
				}
			}
		}
		else if (input.equals("Down") || input.equals("Right")) {
		for (int i = 49; i>=0; i--) {
			for (int j = 49; j>=0; j--) {
				if (laberinto[i][j] == 8) {
					if (input.equals("Down")) {
						if (i<49) {
							if (laberinto[i+1][j]==3) {
								laberinto[i+1][j]=8;
								laberinto[i][j]=3;
								int [][] cambio = {{i+1,j,8},{i,j,3}};
								respuesta.add(cambio);
							}
						}
					}
					else if (input.equals("Right")) {
						if (j<49) {
							if (laberinto[i][j+1]==3) {
								laberinto[i][j+1]=8;
								laberinto[i][j]=3;
								int [][] cambio = {{i,j+1,8},{i,j,3}};
								respuesta.add(cambio);
								}
							}
						}
					}
				}
			}
		}
		if (laberinto[42][0] == 8) {
			System.out.println("Game Over");
		}
		return respuesta;
	}
	//Funcion que envia la matriz al inicio para hacer el mapa
	public ArrayList<ArrayList<int[]>> SendMatrix(ArrayList<int[]> lista) {
		ArrayList<ArrayList<int[]>> respuesta = new ArrayList<ArrayList<int[]>>();
		for (int i = 0; i<50; i++) {
			for (int j = 0; j<50; j++) {
				int [] pixel = {i,j,laberinto[i][j]};
				lista.add(pixel);
			}
		}
		System.out.println("Matriz Inicial enviada");
		respuesta.add(lista);
		return respuesta;
	}
}