package App;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
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

public class Pantalla extends JFrame {
	
	private Socket clientSocket;
	private BufferedReader input;
	private PrintStream output;
	
	int boundx=5;
	int boundy=5;
	int sizex=10;
	int sizey=10;
	public static JLabel[][] listaBase = new JLabel[50][50];

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Pantalla client = new Pantalla();
		client.run();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pantalla frame = new Pantalla();
					frame.setVisible(true);
					client.FirstUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void run() {
		try {
			clientSocket = new Socket("localhost",7935);
			output = new PrintStream(clientSocket.getOutputStream());
			output.println("Hi Server");
			input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String message = input.readLine();
			System.out.println(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void FirstUpdate() {
		for (int i = 0; i<50; i++) {
			for (int j = 0; j<50; j++) {
				if (matriz[i][j]==3) {
					listaBase[i][j].setBackground(Color.BLACK);
					System.out.println("fwjmifm");
				}
				else if (matriz[i][j]==4){
					listaBase[i][j].setBackground(Color.GREEN);
				}
				else if (matriz[i][j]==5){
					listaBase[i][j].setBackground(Color.CYAN);
				}
			}
		}
	}
	/**
	 * Create the frame.
	 */
	public Pantalla() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 572, 596);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		for (int i = 0; i<50; i++) {
			for (int j = 0; j<50; j++) {
				JLabel lblNewLabel = new JLabel((String) null);
				lblNewLabel.setOpaque(true);
				lblNewLabel.setBackground(Color.BLUE);
				lblNewLabel.setBounds(boundx, boundy, sizex, sizey);
				contentPane.add(lblNewLabel);
				boundx+=11;
				listaBase[i][j] =lblNewLabel;			}
		boundx=5;
		boundy+=11;
		
		}
	}
}
