package App;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Consola_B {
	
	private ServerSocket serverSocket;
	private Socket acceptSocket1;
	private Socket acceptSocket2;
	private OutputStream outputStream;
	private DataOutputStream dataOutputStream;

	
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
			while (acceptSocket1.isConnected()){
				InputStream inputStream = acceptSocket1.getInputStream();
			    DataInputStream dataInputStream = new DataInputStream(inputStream);
			    String message = dataInputStream.readUTF();
			    System.out.println("Comando enviado: "+message);
			    outputStream = acceptSocket2.getOutputStream();
				dataOutputStream = new DataOutputStream(outputStream);
				dataOutputStream.writeUTF(message);
				dataOutputStream.flush();
			  
			}
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	private void pacman(String comando, int [][] matriz) {
		if (comando == "DpadU") {
			for (int i = 0; i <= 50; i = i ++) {
				for (int j = 0; j <= 50; j = j ++) {
					if (matriz[i][j] == 8) {
						if (matriz[i-1][j]!=3) {
							if (matriz[i-1][j]==6) {
								contador-=1;
								if (contador<=0) {
									System.out.println("ganó");
								}

							}
							matriz[i-1][j] = 8;
							matriz[i][j] = 0;
							
						}
					}
					}
				}
		}else if (comando == "DpadD") {
			for (int i = 0; i <= 50; i = i ++) {
				for (int j = 0; j <= 50; j = j ++) {
					if (matriz[i][j] == 8) {
						if (matriz[i][j+1]!=3) {
							if (matriz[i][j+1]==6) {
								contador-=1;
								if (contador<=0) {
									System.out.println("ganó");
								}
							}
							matriz[i][j+1] = 8;
							matriz[i][j] = 0;
						}
					}
					}
				}
		}else if(comando == "DpadL") {
			for (int i = 0; i <= 50; i = i ++) {
				for (int j = 0; j <= 50; j = j ++) {
					if (matriz[i][j] == 8) {
						if (matriz[i][j-1]!=3) {
							if (matriz[i][j-1]==6) {
								contador-=1;
								if (contador<=0) {
									System.out.println("ganó");
								}
							}
							matriz[i][j-1] = 8;
							matriz[i][j] = 0;
						}
					}
					}
				}
		}else {
			for (int i = 0; i <= 50; i = i ++) {
				for (int j = 0; j <= 50; j = j ++) {
					if (matriz[i][j] == 8) {
						if (matriz[i+1][j]!=3) {
							if (matriz[i+1][j]==6) {
								contador-=1;
								if (contador<=0) {
									System.out.println("ganó");
								}
							}
							matriz[i+1][j] = 8;
							matriz[i][j] = 0;
						}
					}
					}
				}
		}
			
	}
}