package App;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.*;
import java.net.Socket;

public class Controlador extends JFrame {

	private JPanel contentPane;
	private Socket clientSocket;
	private BufferedReader input;
	private PrintStream output;
	private OutputStream outputStream;
	private DataOutputStream dataOutputStream;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Controlador client = new Controlador();
		client.run();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Controlador frame = new Controlador();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void run() {
		try {
			clientSocket = new Socket("localhost",7935);
			outputStream = clientSocket.getOutputStream();
			dataOutputStream = new DataOutputStream(outputStream);
			dataOutputStream.writeUTF("Hello from the other side!");
			dataOutputStream.flush();
			dataOutputStream.writeUTF("Hello from the other side!");
			dataOutputStream.flush();
				

			System.out.println("Sending string to the ServerSocket");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */
	public Controlador() {
		setResizable(false);
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 456, 193);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel DPAD = new JPanel();
		DPAD.setBounds(10, 10, 212, 141);
		DPAD.setBorder(null);
		DPAD.setOpaque(false);
		contentPane.setLayout(null);
		
		JLabel DpadM = new JLabel("");
		DpadM.setBounds(90, 50, 30, 30);
		DpadM.setIcon(new ImageIcon(Controlador.class.getResource("/App/DPadMid.png")));
		contentPane.add(DPAD);
		DPAD.setLayout(null);
		DPAD.add(DpadM);
		
		JButton DPadR = new JButton("");
		DPadR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		DPadR.setIcon(new ImageIcon(Controlador.class.getResource("/App/DPadRight.png")));
		DPadR.setOpaque(false);
		DPadR.setFocusable(false);
		DPadR.setFocusTraversalKeysEnabled(false);
		DPadR.setFocusPainted(false);
		DPadR.setContentAreaFilled(false);
		DPadR.setBorderPainted(false);
		DPadR.setBounds(119, 50, 30, 30);
		DPAD.add(DPadR);
		
		JButton DPadU = new JButton("");
		DPadU.setIcon(new ImageIcon(Controlador.class.getResource("/App/DPadUp.png")));
		DPadU.setOpaque(false);
		DPadU.setFocusable(false);
		DPadU.setFocusTraversalKeysEnabled(false);
		DPadU.setFocusPainted(false);
		DPadU.setContentAreaFilled(false);
		DPadU.setBorderPainted(false);
		DPadU.setBounds(90, 21, 30, 30);
		DPAD.add(DPadU);
		
		JButton DPadL = new JButton("");
		DPadL.setIcon(new ImageIcon(Controlador.class.getResource("/App/DPadLeft.png")));
		DPadL.setOpaque(false);
		DPadL.setFocusable(false);
		DPadL.setFocusTraversalKeysEnabled(false);
		DPadL.setFocusPainted(false);
		DPadL.setContentAreaFilled(false);
		DPadL.setBorderPainted(false);
		DPadL.setBounds(61, 50, 30, 30);
		DPAD.add(DPadL);
		
		JButton DpadD = new JButton("");
		DpadD.setIcon(new ImageIcon(Controlador.class.getResource("/App/DPadDown.png")));
		DpadD.setOpaque(false);
		DpadD.setFocusable(false);
		DpadD.setFocusTraversalKeysEnabled(false);
		DpadD.setFocusPainted(false);
		DpadD.setContentAreaFilled(false);
		DpadD.setBorderPainted(false);
		DpadD.setBounds(90, 79, 30, 30);
		DPAD.add(DpadD);
		
		JLabel Button = new JLabel("");
		Button.setIcon(new ImageIcon(Controlador.class.getResource("/App/Button.png")));
		Button.setBounds(198, 43, 208, 70);
		contentPane.add(Button);
		
		JLabel Border = new JLabel("");
		Border.setBackground(Color.DARK_GRAY);
		Border.setOpaque(true);
		Border.setBounds(0, 143, 452, 12);
		contentPane.add(Border);
		
		JLabel Border_1 = new JLabel("");
		Border_1.setOpaque(true);
		Border_1.setBackground(Color.DARK_GRAY);
		Border_1.setBounds(0, 0, 452, 12);
		contentPane.add(Border_1);
		
		JLabel Border_2 = new JLabel("");
		Border_2.setOpaque(true);
		Border_2.setBackground(Color.DARK_GRAY);
		Border_2.setBounds(431, 10, 11, 155);
		contentPane.add(Border_2);
		
		JLabel Border_2_1 = new JLabel("");
		Border_2_1.setOpaque(true);
		Border_2_1.setBackground(Color.DARK_GRAY);
		Border_2_1.setBounds(0, 0, 11, 155);
		contentPane.add(Border_2_1);
	}
}