package highlow.maingame.sockets;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.SystemColor;

import javax.swing.UIManager;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ClientWindow extends JFrame {

	//public static int P1_Choice; // 0 = High, 1 = Low
	//public static int player1_money = 2000;
	//public static int player1_bet = 0;
	//Game Player = new Game();

	//int Rand;// = 45;// = Player.random_num;
	public String RandString; //= Integer.toString(Rand);
	
	int clientMoney = 2000; //Player.player1_money = 2000;
	public String p1_MoneyString = Integer.toString(clientMoney);
	public static int player1_bet = 0;

	SetupWindow SW = new SetupWindow();
	
	private JPanel contentPane;

	public static final int WIDTH = 1000;
	public static final int HEIGHT = 500;
	public static final String NAME = "High/Low";
	
	JLabel lblMSG = new JLabel("message...");
	final JPanel panel = new JPanel();
	final JLabel label_1 = new JLabel("?");
	final JLabel labelRAND = new JLabel(RandString);
	JLabel label = new JLabel(p1_MoneyString);
	JLabel labelMoney = new JLabel(p1_MoneyString);
	JLabel lblPlayer = new JLabel("Player: ?");
	
	JLabel lblYouHaveWon = new JLabel("YOU HAVE WON THE GAME!");
	JLabel lblYouHaveWonBG = new JLabel("YOU HAVE WON THE GAME!");
	JLabel lblYouHaveLost = new JLabel("YOU HAVE LOST THE GAME!");
	JLabel lblYouHaveLost_1 = new JLabel("YOU HAVE LOST THE GAME!");

	String clientCash;
	
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private DataOutputStream dos;
	private DataInputStream din;

	public static int random_num;
	private JTextField YouHaveWonBG;
	private JTextField YouHaveWonBG2;
	private JTextField YouHaveLostBG;
	private JTextField YouHaveLostBG2;

	//private JFrame frame = new JFrame("High/Low");

	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) throws Exception{
					while(true){
						//String serverAddress = (args.length == 0) ? IP : args[1];  // 10.250.41.69   // localhost // 10.250.30.106
						ClientWindow client = new ClientWindow(); //serverAddress
						client.setVisible(true);
						client.play();

						if (!client.wantsToPlayAgain()) {
							break;
						}
					}

			
	}

	/**
	 * Create the frame.
	 */
	public ClientWindow()  { //String serverAddress

		// Setup networking
		try {
			socket = new Socket (SetupWindow.IP, SetupWindow.PORT);//("localhost", 900); //(SetupWindow.IP, SetupWindow.PORT);
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			dos = new DataOutputStream(socket.getOutputStream());
			din = new DataInputStream(socket.getInputStream());
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// GUI
		setTitle(NAME);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, WIDTH, HEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		lblYouHaveWon.setVisible(false);		
		lblYouHaveWon.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouHaveWon.setFont(new Font("Tahoma", Font.BOLD, 60));
		lblYouHaveWon.setForeground(Color.GREEN);
		lblYouHaveWon.setBounds(42, 180, 913, 109);
		
		
		lblYouHaveWonBG.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouHaveWonBG.setForeground(Color.BLACK);
		lblYouHaveWonBG.setFont(new Font("Tahoma", Font.BOLD, 61));
		lblYouHaveWonBG.setBounds(42, 180, 913, 109);
		
		
		YouHaveWonBG = new JTextField();
		YouHaveWonBG.setEditable(false);
		YouHaveWonBG.setBounds(-15, 201, 1024, 75);
		
		YouHaveWonBG.setColumns(10);
		
		YouHaveWonBG2 = new JTextField();
		YouHaveWonBG2.setBackground(Color.GREEN);
		YouHaveWonBG2.setEditable(false);
		YouHaveWonBG2.setColumns(10);
		YouHaveWonBG2.setBounds(0, 180, 1024, 117);
				
		lblYouHaveLost.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouHaveLost.setForeground(Color.RED);
		lblYouHaveLost.setFont(new Font("Tahoma", Font.BOLD, 60));
		lblYouHaveLost.setBounds(22, 180, 913, 109);
		
		lblYouHaveLost_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouHaveLost_1.setForeground(Color.BLACK);
		lblYouHaveLost_1.setFont(new Font("Tahoma", Font.BOLD, 61));
		lblYouHaveLost_1.setBounds(22, 180, 913, 109);
		
		YouHaveLostBG = new JTextField();
		YouHaveLostBG.setVisible(false);
		YouHaveLostBG.setEditable(false);
		YouHaveLostBG.setColumns(10);
		YouHaveLostBG.setBounds(0, 201, 1024, 75);
		
		YouHaveLostBG2 = new JTextField();
		YouHaveLostBG2.setVisible(false);
		YouHaveLostBG2.setEditable(false);
		YouHaveLostBG2.setColumns(10);
		YouHaveLostBG2.setBackground(Color.RED);
		YouHaveLostBG2.setBounds(0, 180, 1024, 117);
		
		contentPane.add(lblYouHaveWon);
		contentPane.add(lblYouHaveWonBG);
		contentPane.add(YouHaveWonBG);
		contentPane.add(YouHaveWonBG2);
		contentPane.add(lblYouHaveLost);
		contentPane.add(lblYouHaveLost_1);
		contentPane.add(YouHaveLostBG);
		contentPane.add(YouHaveLostBG2);
		lblYouHaveWonBG.setVisible(false);
		YouHaveWonBG.setVisible(false);
		YouHaveWonBG2.setVisible(false);
		lblYouHaveLost.setVisible(false);
		lblYouHaveLost_1.setVisible(false);
		
		//frame.getContentPane().add(contentPane, "Center");

		labelRAND.setHorizontalAlignment(SwingConstants.CENTER);
		labelRAND.setFont(new Font("Army Thin", Font.BOLD, 99));
		labelRAND.setBounds(10, 38, 228, 256);
		panel.add(labelRAND);
		labelRAND.setVisible(false);
		
		JPanel panelCard = new JPanel();
		panelCard.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panelCard.setBackground(Color.WHITE);
		panelCard.setBounds(629, 67, 268, 376);
		contentPane.add(panelCard);
		panelCard.setLayout(null);

//		final JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 5, true));
		panel.setBackground(Color.BLACK);
		panel.setOpaque(false);
		panel.setBounds(10, 11, 248, 354);
		panelCard.add(panel);
		panel.setLayout(null);

//		final JLabel label_1 = new JLabel("?");
//		final JLabel labelRAND = new JLabel("45");
		labelRAND.setBackground(Color.WHITE);
		labelRAND.setForeground(Color.BLACK);
		label_1.setForeground(Color.RED);
		
		label_1.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_1.setBounds(93, 38, 65, 256);
		panel.add(label_1);
		
		
		//JLabel lblMSG = new JLabel("message...");
		lblMSG.setHorizontalAlignment(SwingConstants.CENTER);
		lblMSG.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblMSG.setForeground(Color.WHITE);
		lblMSG.setBounds(0, 429, 457, 31);
		contentPane.add(lblMSG);

		JLabel lblRandomCard = new JLabel("Random Card");
		lblRandomCard.setForeground(Color.YELLOW);
		lblRandomCard.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblRandomCard.setBounds(685, 16, 154, 45);
		contentPane.add(lblRandomCard);
		
				
				lblPlayer.setForeground(Color.YELLOW);
				lblPlayer.setFont(new Font("Arial", Font.PLAIN, 27));
				lblPlayer.setBounds(22, 0, 119, 75);
				contentPane.add(lblPlayer);

		JLabel lblMoney = new JLabel("Money: $");
		lblMoney.setForeground(Color.WHITE);
		lblMoney.setFont(new Font("Arial", Font.PLAIN, 27));
		lblMoney.setBounds(22, 37, 169, 75);
		contentPane.add(lblMoney);

		JLabel lblHowMuchDo = new JLabel("How much do you want to bet?");
		lblHowMuchDo.setForeground(Color.WHITE);
		lblHowMuchDo.setFont(new Font("Arial", Font.PLAIN, 27));
		lblHowMuchDo.setBounds(10, 136, 477, 75);
		contentPane.add(lblHowMuchDo);

		String[] betAmount = {"5", "25", "50", "100", "500", "1000", "ALL IN"};

		final JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 27));
		comboBox.setModel(new DefaultComboBoxModel(betAmount));
		comboBox.setToolTipText("");
		comboBox.setBounds(81, 201, 113, 39);
		contentPane.add(comboBox);

		JLabel lblWouldYouLike = new JLabel("Would you like to play High or play Low?");
		lblWouldYouLike.setForeground(Color.WHITE);
		lblWouldYouLike.setFont(new Font("Arial", Font.PLAIN, 27));
		lblWouldYouLike.setBounds(10, 257, 755, 75);
		contentPane.add(lblWouldYouLike);

		JButton btnHigh = new JButton("High");
		btnHigh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnHigh.setBounds(81, 332, 134, 63);

		btnHigh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// function of High button
				//Player.P1_Choice = 0;
				System.out.println("P1 choice is: " + "High");

				// Select bet
				Object contents = comboBox.getSelectedItem();
				if(contents == "ALL IN"){
					//contents = p1_MoneyString;
					contents = Integer.toString(clientMoney);
				}
				player1_bet = Integer.parseInt((String) contents); //bet amount in integer

				if(player1_bet <= clientMoney ){
					System.out.println("P1 bet is: " + player1_bet);
		
					out.println("HIGH");
					//out.println("MOVE");
					
//					label_1.setVisible(false);
//					labelRAND.setVisible(true);

					
				} else if(player1_bet > clientMoney){
					JOptionPane.showMessageDialog(null, "Error! You can't bet more than what you have!");
				}


			}
		});

		contentPane.add(btnHigh);

		JButton btnLow = new JButton("Low");
		btnLow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// function of Low button
				//Player.P1_Choice = 1;
				
				System.out.println("P1 choice is: " + "Low");

				// Select bet
				Object contents = comboBox.getSelectedItem();
				if(contents == "ALL IN"){
					//contents = p1_MoneyString;
					contents = Integer.toString(clientMoney);
				}
				player1_bet = Integer.parseInt((String) contents);
				if(player1_bet <= clientMoney ){
					System.out.println("P1 bet is: " + player1_bet);
			
					out.println("LOW");
					//out.println("MOVE");
					
//					label_1.setVisible(false);
//					labelRAND.setVisible(true);

					
				} else if(player1_bet > clientMoney){
					JOptionPane.showMessageDialog(null, "Error! You can't bet more than what you have!");
				}
			}
		});
		btnLow.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLow.setBounds(226, 332, 134, 63);
		contentPane.add(btnLow);
		Image img = new ImageIcon(this.getClass().getResource("/bg1.jpg")).getImage();

		JPanel panelMoney = new JPanel();
		panelMoney.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelMoney.setBackground(Color.GRAY);
		panelMoney.setBounds(10, 11, 268, 101);
		contentPane.add(panelMoney);
		panelMoney.setLayout(null);
		
	
		labelMoney.setBounds(132, 47, 126, 32);
		panelMoney.add(labelMoney);
		labelMoney.setForeground(Color.CYAN);
		labelMoney.setFont(new Font("Arial", Font.PLAIN, 27));
		
				JLabel label_2 = new JLabel(p1_MoneyString);
				label_2.setForeground(Color.CYAN);
				label_2.setFont(new Font("Arial", Font.PLAIN, 27));
				label_2.setBounds(138, 37, 154, 75);
				contentPane.add(label_2);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(0, 0, 994, 471);
		contentPane.add(lblNewLabel);
		


	}

	public void play() throws Exception{
		String response = null;
		dos.writeInt(clientMoney);
		
		try{
			response = in.readLine();
			if (response.startsWith("WELCOME")) {
				char mark = response.charAt(8);
				lblPlayer.setText("Player " + mark);

			}

			while (true) {
				
				response = in.readLine();
				if (response.startsWith("VALID_MOVE")) {
					lblMSG.setText("Valid move, please wait");

					
				} else if (response.startsWith("OPPONENT_MOVED")) {
					lblMSG.setText("Opponent moved, your turn");
					labelRAND.setVisible(false);
					label_1.setVisible(true);

				}
				
				else if (response.startsWith("Win_bet")) {
					
					clientMoney = clientMoney + player1_bet;
					clientCash = Integer.toString(clientMoney);
					labelMoney.setText(clientCash);
					lblMSG.setText("You win " + player1_bet);
					
			        dos.writeInt(clientMoney);
			        	        
//					label_1.setVisible(false);
//					labelRAND.setVisible(true);
					
					
				} else if (response.startsWith("Lose_bet")) {
					
					clientMoney = clientMoney - player1_bet;
					clientCash = Integer.toString(clientMoney);
					labelMoney.setText(clientCash);
					lblMSG.setText("You lose " + player1_bet);
					
			        dos.writeInt(clientMoney);
			    			        
//					label_1.setVisible(false);
//					labelRAND.setVisible(true);
					
					
					
				} else if (response.startsWith("VICTORY")) {
					//lblMSG.setText("You win the game!");
					lblMSG.setText("You have lost the game!");
					
					lblYouHaveLost.setVisible(true);
					//lblYouHaveLost_1.setVisible(true);
					//YouHaveLostBG.setVisible(true);
					//YouHaveLostBG2.setVisible(true);
					
					break;
					
				} else if (response.startsWith("DEFEAT")) {
					//lblMSG.setText("You have lost the game!");
					lblMSG.setText("You have won the game!");
					
					lblYouHaveWon.setVisible(true);
					//lblYouHaveWonBG.setVisible(true);
					//YouHaveWonBG.setVisible(true);
					//YouHaveWonBG2.setVisible(true);
					
					break;
					
				} else if (response.startsWith("TIE")) {
					lblMSG.setText("You tied");
					break;
					
				} else if (response.startsWith("MESSAGE")) {
					lblMSG.setText(response.substring(8));

				} else if (response.startsWith("RANDOM")) {
					RandString = response.substring(7);
					labelRAND.setText(RandString);
					System.out.println("Random num in client class: "+ RandString);
					label_1.setVisible(false);
					labelRAND.setVisible(true);
				}
				
			}
			out.println("QUIT");

		}finally{
			socket.close();
		}

	}

	boolean wantsToPlayAgain() {
		int response = JOptionPane.showConfirmDialog(null,
				"Want to play again?",
				"High/Low",
				JOptionPane.YES_NO_OPTION);
		dispose();
		return response == JOptionPane.YES_OPTION;
	}
}
