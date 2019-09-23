package highlow.maingame.sockets;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class InstructionsWindow extends JFrame {

	private JPanel contentPane;

	public static final int WIDTH = 500;
	public static final int HEIGHT = 700;
	public static final String NAME = "High/Low";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InstructionsWindow frame = new InstructionsWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InstructionsWindow() {
		setTitle(NAME);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, HEIGHT, WIDTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(555, 414, 129, 46);
		btnBack.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				GameWindow.main(null);
				dispose();
			}
		});
		contentPane.add(btnBack);
		
		JTextArea txtrInstructions_1 = new JTextArea();
		txtrInstructions_1.setEditable(false);
		txtrInstructions_1.setText("INSTRUCTIONS:");
		txtrInstructions_1.setForeground(Color.WHITE);
		txtrInstructions_1.setFont(new Font("Arial Narrow", Font.BOLD, 40));
		txtrInstructions_1.setBackground(Color.DARK_GRAY);
		txtrInstructions_1.setBounds(212, 51, 259, 51);
		txtrInstructions_1.setOpaque(false);
		contentPane.add(txtrInstructions_1);
		
		JTextArea txtrInstructions = new JTextArea();
		txtrInstructions.setEditable(false);
		txtrInstructions.setFont(new Font("Arial Narrow", Font.PLAIN, 22));
		txtrInstructions.setBackground(Color.DARK_GRAY);
		txtrInstructions.setForeground(Color.WHITE);
		txtrInstructions.setText("\u25BAChoose the amount you want to bet.\r\n\r\n\u25BAFrom a deck of cards (50) predict whether the next card will be\r\n either High(26-50) or Low(1-25). \r\n\r\n\u25BAThe first player to reach $15,000 or more wins the game.\r\n\r\n\u25BAIf the player runs out of money, they lose the game.");
		txtrInstructions.setBounds(38, 134, 646, 269);
		txtrInstructions.setOpaque(false);
		contentPane.add(txtrInstructions);
		
//		JTextArea textArea = new JTextArea();
//		textArea.setEditable(false);
//		textArea.setForeground(Color.WHITE);
//		textArea.setBackground(Color.DARK_GRAY);
//		textArea.setBounds(0, 0, 694, 471);
//		contentPane.add(textArea);
		
		JLabel lblNewLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/bg1.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(0, 0, 704, 471);
		contentPane.add(lblNewLabel);
	}
}
