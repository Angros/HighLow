package highlow.maingame.sockets;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;


public class HighLowServer {
	
	/**
	 * Runs the application. Pairs up clients that connect.
	 */
	public static void main(String[] args) throws Exception {
		ServerSocket listener = new ServerSocket(900); //Set port
		System.out.println("High/Low Server is Running");
		try {
			while (true) {
				Game game = new Game();
				Game.Player player1 = game.new Player(listener.accept(), 'A');
				Game.Player player2 = game.new Player(listener.accept(), 'B');
				player1.setOpponent(player2);
				player2.setOpponent(player1);
				game.currentPlayer = player1;
				player1.start();
				player2.start();
			}
		} finally {
			listener.close();
		}
	}

}

/**
 * A two-player game.
 */
class Game {
	
	//int random_num;
//	Random rn = new Random();
//	int random_num = rn.nextInt(50) + 1;
	int random_num;

	public int P1_Choice;
	public int player1_money = 2000;
	
	
	


	Player currentPlayer;


	// Winning bet conditions
	public boolean hasWinBet(){
		return 
				(P1_Choice == 0 && random_num >= 26 ||
				P1_Choice == 1 && random_num <= 25); 
	}

	// Losing bet conditions
	public boolean hasLoseBet(){
		return 
				(P1_Choice == 1 && random_num >= 26 ||
				P1_Choice == 0 && random_num <= 25); 
	}

	// Winning conditions
	public boolean hasWinner() {
		return
				(player1_money >= 15000); // >=15000 >=3000
	}

	// Losing condition
	public boolean hasLoser() {
		return
				(player1_money <= 0);
	}


	public synchronized boolean legalMove(Player player) {
		if (player == currentPlayer && (P1_Choice == 0 || P1_Choice == 1)) {
			currentPlayer = currentPlayer.opponent;
			currentPlayer.otherPlayerMoved();
			return true;
		}
		return false;
	}


	class Player extends Thread {
		char mark;
		Player opponent;
		Socket socket;
		BufferedReader input;
		PrintWriter output;
		DataOutputStream don;
		DataInputStream din;

		public Player(Socket socket, char mark) {
			this.socket = socket;
			this.mark = mark;
			try {
				input = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				output = new PrintWriter(socket.getOutputStream(), true);
				din = new DataInputStream(socket.getInputStream());
				don = new DataOutputStream(socket.getOutputStream());
				output.println("WELCOME " + mark);
				output.println("MESSAGE Waiting for opponent to connect...");
			} catch (IOException e) {
				System.out.println("Player died: " + e);
			}
		}

		public void setOpponent(Player opponent) {
			this.opponent = opponent;
		}

		public void otherPlayerMoved() {
			//output.println("OPPONENT_MOVED");
			output.println(
					hasWinner() ? "DEFEAT" : hasLoser() ? "VICTORY" : ""); //hasTie() ? "TIE" : "");
			output.println("OPPONENT_MOVED");
		}



		public void run() {
			
			try{
				// The thread is only started after everyone connects.
				output.println("MESSAGE All players connected");

				// Tell the first player that it is her turn.
				if (mark == 'A') {
					output.println("MESSAGE Your move");
					don.writeInt(random_num);
				}
				
				while(true){
					//DataInputStream din = new DataInputStream(socket.getInputStream());					
					String command = input.readLine();
					
					if (command.startsWith("HIGH")){
						P1_Choice = 0;
						if (command.startsWith("HIGH")) {

							Random rn = new Random();
							random_num = rn.nextInt(50) + 1;
							System.out.println("Random number: " + random_num);
							String RandString = Integer.toString(random_num);
							
							if (legalMove(this)) {
								output.println("VALID_MOVE");
								output.println(hasWinner() ? "VICTORY"
										     : hasLoser() ? "DEFEAT"
										     : hasWinBet() ? "Win_bet"
											 : hasLoseBet() ? "Lose_bet"
											 : "");
								
								output.println("RANDOM " + RandString);
								
						        System.out.println("player money: " + player1_money);
								player1_money = din.readInt();
								
							} else {
								output.println("MESSAGE invalid");
							}
						} else if (command.startsWith("QUIT")) {
							return;
						}

					} 

					else if (command.startsWith("LOW")){
						P1_Choice = 1;
						if (command.startsWith("LOW")) {
							Random rn = new Random();
							random_num = rn.nextInt(50) + 1;
							System.out.println("Random number: " + random_num);
							String RandString = Integer.toString(random_num);
							
							if (legalMove(this)) {
								output.println("VALID_MOVE");
								output.println(hasWinner() ? "VICTORY"
										     : hasLoser() ? "DEFEAT"
											 : hasWinBet() ? "Win_bet"
											 : hasLoseBet() ? "Lose_bet"
											 : "");
								
								output.println("RANDOM " + RandString);
								
						        System.out.println("player money: " + player1_money);
								player1_money = din.readInt();
								
							} else {
								output.println("MESSAGE invalid");
							}
						} else if (command.startsWith("QUIT")) {
							return;
						}
					}

				}


			}catch(IOException e){
				System.out.println("Player died: " + e);
			}finally {
				try {socket.close();} catch (IOException e) {}
			}

		}
	}
}