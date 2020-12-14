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

public class Pantalla extends JFrame {

	int boundx=5;
	int boundy=5;
	int sizex=10;
	int sizey=10;


	public static JLabel[][] listaBase = new JLabel[50][50];

	private JPanel contentPane;
	private static Pantalla frame = new Pantalla();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void UpdateS(String cambios) {
		
		JSONObject Changes = new JSONObject(cambios);
		JSONArray Cambios = Changes.getJSONArray("cambios");
		JSONArray ListaC = Cambios.getJSONArray(0);
		for (int i = 0; i<ListaC.length(); i++) {
			if (ListaC.getJSONArray(i).getInt(2)==0) {
				listaBase[(int)ListaC.getJSONArray(i).getInt(0)][(int)ListaC.getJSONArray(i).getInt(1)].setBackground(Color.BLUE);
			}
			else if (ListaC.getJSONArray(i).getInt(2)==3) {
				listaBase[(int)ListaC.getJSONArray(i).getInt(0)][(int)ListaC.getJSONArray(i).getInt(1)].setBackground(Color.BLACK);
			}
			else if (ListaC.getJSONArray(i).getInt(2)==4){
				listaBase[(int)ListaC.getJSONArray(i).getInt(0)][(int)ListaC.getJSONArray(i).getInt(1)].setBackground(Color.GREEN);
			}
			else if (ListaC.getJSONArray(i).getInt(2)==5){
				listaBase[(int)ListaC.getJSONArray(i).getInt(0)][(int)ListaC.getJSONArray(i).getInt(1)].setBackground(Color.CYAN);
			}
			else if (ListaC.getJSONArray(i).getInt(2)==6){
				listaBase[(int)ListaC.getJSONArray(i).getInt(0)][(int)ListaC.getJSONArray(i).getInt(1)].setBackground(Color.WHITE);
			}
			else if (ListaC.getJSONArray(i).getInt(2)==8){
				listaBase[(int)ListaC.getJSONArray(i).getInt(0)][(int)ListaC.getJSONArray(i).getInt(1)].setBackground(Color.YELLOW);
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
		
		for (int f = 0; f<50; f++) {
			for (int c = 0; c<50; c++) {
				JLabel lblNewLabel = new JLabel((String) null);
				lblNewLabel.setOpaque(true);
				lblNewLabel.setBackground(Color.BLUE);
				lblNewLabel.setBounds(boundx, boundy, sizex, sizey);
				contentPane.add(lblNewLabel);
				boundx+=11;
				listaBase[f][c] =lblNewLabel;			
		}
		boundx=5;
		boundy+=11;
		
		}
	}
}

