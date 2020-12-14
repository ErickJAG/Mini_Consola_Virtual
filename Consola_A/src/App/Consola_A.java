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
			serverSocket = new ServerSocket(7935);
			acceptSocket1 = serverSocket.accept();
			acceptSocket1.setKeepAlive(true);
			outputStream = acceptSocket1.getOutputStream();
			dataOutputStream = new DataOutputStream(outputStream);
			dataOutputStream.writeUTF("Console A");
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
			    ArrayList<int[][]> response = new ArrayList<int[][]>();
			    ArrayList<int[][]> NewMessage = Move(message,response);
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
	public  ArrayList<int[][]> Move(String comando,ArrayList<int[][]> respuesta) {
		if (comando.equals("Up")) {
			for (int i = 0; i < 50; i = i + 1) {
				for (int j = 0; j < 50; j = j + 1) {
					if (Pacman[i][j] == 8) {
						if (Pacman[i-1][j]!=3 ) {
							if (Pacman[i-1][j]==6) {
								contador-=1;
								if (contador<=0) {
									System.out.println("ganó");
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
									System.out.println("ganó");
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
									System.out.println("ganó");
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
									System.out.println("ganó");
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
		
}
	