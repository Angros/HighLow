package highlow.maingame.sockets;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFormattedTextField;

public class SetupWindow extends JFrame {

	private JPanel contentPane;

	public static final int WIDTH = 500;
	public static final int HEIGHT = 700;
	public static final String NAME = "High/Low";

	private JTextField textField_PORT;
	private JTextField textField_IP;

	public static int PORT;
	public static String IP;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
					SetupWindow frame = new SetupWindow();
					frame.setVisible(true);
	}

	
	/**
	 * Create the frame.
	 */
	public SetupWindow() {

		setTitle(NAME);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, HEIGHT, WIDTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblSetUpConnection = new JLabel("SET UP CONNECTION");
		lblSetUpConnection.setForeground(Color.WHITE);
		lblSetUpConnection.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblSetUpConnection.setBounds(246, 11, 225, 47);
		contentPane.add(lblSetUpConnection);
		
		JLabel label = new JLabel("_______________________________________________________");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 21));
		label.setBounds(-13, 22, 794, 47);
		contentPane.add(label);


		textField_PORT = new JTextField();
		textField_PORT.setBounds(148, 237, 269, 50);
		contentPane.add(textField_PORT);
		textField_PORT.setColumns(10);
		
		textField_IP = new JTextField();
		textField_IP.setColumns(10);
		textField_IP.setBounds(148, 120, 269, 50);
		contentPane.add(textField_IP);
		
		JButton btnSetIP = new JButton("Set");
		btnSetIP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IP = textField_IP.getText();
				System.out.println(IP);
				textField_IP.setEditable(false);
				
			}
		});
		btnSetIP.setBounds(427, 120, 65, 50);
		contentPane.add(btnSetIP);
		
		JButton btnSet_1 = new JButton("Set");
		btnSet_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String PORTstring = textField_PORT.getText();
				PORT = Integer.parseInt(PORTstring);
				textField_PORT.setEditable(false);
				System.out.println(PORT);
			}
		});
		btnSet_1.setBounds(427, 237, 65, 50);
		contentPane.add(btnSet_1);

		final JLabel lblPort = new JLabel("Insert Port number");
		lblPort.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPort.setForeground(Color.WHITE);
		lblPort.setBounds(148, 212, 269, 14);
		contentPane.add(lblPort);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GameWindow.main(null);
				setVisible(false);
				dispose();
			}
		});
		btnBack.setBounds(431, 356, 144, 50);
		contentPane.add(btnBack);
		
		final JLabel lblIP = new JLabel("Insert IP Address");
		lblIP.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIP.setForeground(Color.WHITE);
		lblIP.setBounds(148, 95, 269, 14);
		contentPane.add(lblIP);
		
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Connect to client to server	
				setVisible(false);
				dispose();
				
				new Thread(new Connect()).start();

			}

		});
		btnConnect.setBounds(148, 356, 144, 50);
		contentPane.add(btnConnect);
		
		Image img = new ImageIcon(this.getClass().getResource("/bg1.jpg")).getImage();
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(0, 0, 994, 471);
		contentPane.add(lblNewLabel);
	}
	
}



