package highlow.maingame.sockets;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GameWindow extends JFrame {
	
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
					GameWindow frame = new GameWindow();
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
	public GameWindow() {
		
		
		setTitle(NAME);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, HEIGHT, WIDTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JButton btnPlay = new JButton("Play");
		btnPlay.setBounds(288, 235, 144, 50);
		btnPlay.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				SetupWindow.main(null);
				setVisible(false);
				dispose();
			}
		});
		contentPane.add(btnPlay);

		JButton btnNewButton = new JButton("Exit");
		btnNewButton.setBounds(288, 396, 144, 50);
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnInst = new JButton("How to play");
		btnInst.setBounds(288, 314, 144, 50);
		btnInst.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				InstructionsWindow.main(null);
				setVisible(false);
				dispose();
			}
		});
		contentPane.add(btnInst);
		
		JLabel lblNewLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/HighLowS.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(0, -12, 694, 483);
		contentPane.add(lblNewLabel);
		
//		JButton btnOnline = new JButton("Online");
//		btnOnline.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				//OnlineWindow.main(null);
//				//setVisible(false);
//				//dispose();
//				
//			}
//		});
//		btnOnline.setBounds(288, 289, 144, 50);
//		contentPane.add(btnOnline);
	}
}
