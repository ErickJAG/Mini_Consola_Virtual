package App;

import java.awt.List;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONObject;

public class Consola_A {
	
	private ServerSocket serverSocket;
	private Socket acceptSocket1;
	private Socket acceptSocket2;
	private OutputStream outputStream;
	private DataOutputStream dataOutputStream;
	private static int [][] Pacman = MatrizPacMan.matriz;
	private static int contador = 105;
	
	public static void main(String[]args) {
		Consola_A server = new Consola_A();
		server.run();
	}
	
	public void run () {
		try {
			//Union del primer cliente
			serverSocket = new ServerSocket(7935);
			acceptSocket1 = serverSocket.accept();
			acceptSocket1.setKeepAlive(true);
			outputStream = acceptSocket1.getOutputStream();
			dataOutputStream = new DataOutputStream(outputStream);
			dataOutputStream.writeUTF("Console_A");
			dataOutputStream.flush();
			System.out.println(acceptSocket1);
			//Union del segundo cliente
			acceptSocket2 = serverSocket.accept();
			acceptSocket2.setKeepAlive(true);
			outputStream = acceptSocket2.getOutputStream();
			dataOutputStream = new DataOutputStream(outputStream);
			dataOutputStream.writeUTF("Pacman");
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
			    	if (Pacman[42][0] != 8) {
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
	public  ArrayList<int[][]> Move(String comando,ArrayList<int[][]> respuesta) {
		if (comando.equals("Up")) {
			for (int i = 0; i < 50; i = i + 1) {
				for (int j = 0; j < 50; j = j + 1) {
					if (Pacman[i][j] == 8) {
						if (Pacman[i-1][j]!=3 ) {
							if (Pacman[i-1][j]==6) {
								contador-=1;
								if (contador<=0) {
									System.out.println("gano");
								}

							}
							if  (Pacman[i-1][j]==8 && Pacman[i-2][j]!=3 && Pacman[i-2][j+1]!=3 && Pacman[i-1][j+1]==8 ) {
								
								Pacman[i-1][j] = 8;
								Pacman[i-2][j] = 8;
								Pacman[i-1][j+1] = 8;
								Pacman[i-2][j+1] = 8;
								Pacman[i][j] = 0;
								Pacman[i][j+1] = 0;
								int [][] cambio= {{i-2,j,8},{i-2,j+1,8},{i,j,0},{i,j+1,0}};
								respuesta.add(cambio);
								j+=4;
								i+=4;
								}
							
						}
					}
					}
				}
		}else if (comando.equals("Down")) {
			for (int i = 0; i < 50; i = i + 1) {
				for (int j = 0; j < 50; j = j + 1) {
					if (Pacman[i][j] == 8) {
						if (Pacman[i+1][j]!=3) {
							if (Pacman[i+1][j]==6) {
								contador-=1;
								if (contador<=0) {
									System.out.println("gano");
								}
							}
							if  (Pacman[i+1][j]==8 && Pacman[i+2][j]!=3 && Pacman[i+2][j+1]!=3&& Pacman[i+1][j+1]==8) {
								Pacman[i+1][j] = 8;
								Pacman[i+2][j] = 8;
								Pacman[i+1][j+1] = 8;
								Pacman[i+2][j+1] = 8;
								Pacman[i][j] = 0;
								Pacman[i][j+1] = 0;
								
								int [][] cambio= {{i+2,j,8},{i+2,j+1,8},{i,j,0},{i,j+1,0}};
								respuesta.add(cambio);
								j+=4;
								i+=4;
								}
						}
					}
					}
				}
		}else if(comando.equals("Left")) {
			for (int i = 0; i < 50; i = i + 1) {
				for (int j = 0; j < 50; j = j + 1) {
					if (Pacman[i][j] == 8) {
						if (Pacman[i][j-1]!=3) {
							if (Pacman[i][j-1]==6) {
								contador-=1;
								if (contador<=0) {
									System.out.println("gano");
								}
							}
							if  (Pacman[i][j-1]==8 && Pacman[i+1][j-1]==8) {
								if (Pacman[i+1][j-2]!=3 && Pacman[i][j-2]!=3) {
									Pacman[i][j-1] = 8;
									Pacman[i][j-2] = 8;
									Pacman[i][j] = 0;
									Pacman[i+1][j-1] = 8;
									Pacman[i+1][j-2] = 8;
									Pacman[i+1][j] = 0;
									
									int [][] cambio= {{i,j-2,8},{i+1,j-2,8},{i,j,0},{i+1,j,0}};
									respuesta.add(cambio);
									j+=4;
									i+=4;
									}
								}
						}
					}
					}
				}
		}else {
			for (int i = 0; i < 50; i = i + 1) {
				for (int j = 0; j < 50; j = j + 1) {
					if (Pacman[i][j] == 8) {
						if (Pacman[i][j+1]!=3 ) {
							if (Pacman[i][j+1]==6) {
								contador-=1;
								if (contador<=0) {
									System.out.println("gano");
								}
							}
							if  (Pacman[i][j+1]==8 && Pacman[i][j+2]!=3 && Pacman[i+1][j+2]!=3) {
							Pacman[i][j+1] = 8;
							Pacman[i][j+2] = 8;
							Pacman[i][j] = 0;
							Pacman[i+1][j+1] = 8;
							Pacman[i+1][j+2] = 8;
							Pacman[i+1][j] = 0;
							
							int [][] cambio= {{i,j+2,8},{i+1,j+2,8},{i,j,0},{i+1,j,0}};
							respuesta.add(cambio);
							j+=4;
							i+=4;
							}else {
								i+=3;
							}
							
							
						}
					}
				}
			}
		}
		return respuesta;
	}
	//Funcion que envia la matriz al inicio para hacer el mapa
		public ArrayList<ArrayList<int[]>> SendMatrix(ArrayList<int[]> lista) {
			ArrayList<ArrayList<int[]>> respuesta = new ArrayList<ArrayList<int[]>>();
			for (int i = 0; i<50; i++) {
				for (int j = 0; j<50; j++) {
					int [] pixel = {i,j,Pacman[i][j]};
					lista.add(pixel);
				}
			}
			System.out.println("Matriz Inicial enviada");
			respuesta.add(lista);
			return respuesta;
		}
		
}