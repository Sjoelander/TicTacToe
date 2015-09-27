import java.util.Scanner;
public class Game {
	private Scanner scanner;
	// Boolean för om spelare är mänsklig
	private boolean[] human;
	private String tempString;
	private boolean running;
	// Array som håller i spelarna
	private Player[] player;
	private Board board;		

	/**
	 * Konstruktur för Game
	 */
	public Game() {
		board = new Board();
		scanner = new Scanner(System.in);
		running = true;
		player = new Player[2];
		human = new boolean[2];
	}						

	public static void main(String[] args) {
		Game game = new Game();
		game.options();
		game.gameLoop();
	}

	/**
	 * Loopar spelet så länge det är igång
	 * Kör spelarnas steg
	 */
	public void gameLoop() {
		int currentplayer = 0;
		int move = 0;
		while(running) {
			System.out.println("It is player " + player[currentplayer].getName() + " turn");
			if (human[currentplayer]) {
				System.out.println("\n"		
						+ "\n[1] [2] [3]"
						+ "\n[4] [5] [6]"
						+ "\n[7] [8] [9]" 
						+ "\n"	
						+ "\n Please select move. Type number (1-9)");
				move = player[currentplayer].getMove(board);
			} else {
				move = player[currentplayer].aiMove(board);	
			}

			board.setMove(player[currentplayer].getCharacter(), move);

			System.out.println("Player " + player[currentplayer].getName() +" placed a "
					+ player[currentplayer].getCharacter() + " on position " + move);
			System.out.println(board.toString());

			if (board.checkWin()) {
				System.out.println("Player " + player[currentplayer].getName() + " has won.");
				player[currentplayer].increment();
				System.out.println("Player " + player[0].getName() + " wins: " + player[0].getNumWins() +
						"\n" + "Player " + player[1].getName() + " wins: " + player[1].getNumWins());

				if(questionBoolean("Would you like to play again?")) {
					running = true;
					board = new Board();
				} else {
					running = false;
				}
			} else if(board.checkDraw()) {
				System.out.println("The game is a draw.");
				System.out.println("Player " + player[0].getName() + " wins: " + player[0].getNumWins() +
						"\n" + "Player " + player[1].getName() + " wins: " + player[1].getNumWins());
				if(questionBoolean("Would you like to play again?")) {
					running = true;
					board = new Board();
				} else {
					running = false;
				}
			} else {
				// Byter spelare
				currentplayer = (currentplayer+1)%2;
			}
		}
		scanner.close();
	}

	/**
	 * Ställer in inställningar efter spelarnas preferenser. 
	 */
	public void options() {
		System.out.println("TicTacToe is game");
		human[0] = questionBoolean("Is Player One Human?");
		if (human[0]) {
			tempString = questionString("Enter your name");
			player[0] = new Player('X', tempString);
		} else {
			player[0] = new Player('X', "AI 1");
		}

		human[1] = questionBoolean("Is Player Two Human?");
		if (human[1]) {
			tempString = questionString("Enter your name");
			player[1] = new Player('O', tempString);
		} else {
			player[1] = new Player('O', "AI 2");
		}


	}

	// Hjälpmetoder

	/**
	 * Tar in String, visar som en Ja/Nej-fråga och returnerar svaret som en boolean.
	 * @param q Ja/Nej Frågan 
	 * @return Svaret som Boolean
	 */
	private boolean questionBoolean(String q) {
		while(true) {
			String line;
			System.out.println(q + " (Y/N)");
			line = scanner.nextLine().toUpperCase();

			if(line.equals("Y")) {
				return true;
			} else if(line.equals("N")) {
				return false;
			}
		}
	}

	/**
	 * Tar in en String, ställer det som en fråga och tar in svaret som en String och returnerar den.
	 * @param q Frågan
	 * @return Svaret som String
	 */
	private String questionString(String q) {
		while(true) {
			String line;
			System.out.println(q);
			line = scanner.nextLine();
			if(line.length() > 0) {
				return line; 
			}
		}
	}


}
