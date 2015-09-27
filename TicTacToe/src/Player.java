import java.util.Scanner;
import java.util.Random;

public class Player {
	private char character;
	private String name;
	private int numWins;
	/**
	 * Konstruktor för Player
	 * @param c tecknet för spelaren
	 * @param n namnet för spelaren
	 */
	public Player(char c, String n) {
		character = c;
		name = n;
		numWins = 0;
	}

	/**
	 * Hämtar spelarens drag från standardinput
	 * @param board brädet som draget ska spelas på
	 * @return draget
	 */
	public int getMove(Board board) {
		int move = 0;
		Scanner scanner = new Scanner(System.in);
		while(true) {
			move = 0;
			if(scanner.hasNextInt()) {
				move = scanner.nextInt();
				// Kontrollerar så att draget är tillåtet 
				if (move <= Board.maxX*Board.maxY && move > 0 && board.validMove(move)) {
					return move;	
				} else {
					System.out.println("Invalid Selection.");	
				}
			} else {
				System.out.println("Input must be a number.");	
				// Rensar buffern för system.in
				scanner.next();
			}
		}
	}

	/**
	 * Hämtar ett AI-drag
	 * @param board brädet som draget ska spelas på
	 * @return draget
	 */
	public int aiMove(Board board) {
		int move = 0;
		// Väljer slumpmässigt drag
		Random rand = new Random();
		do { 
			move = rand.nextInt(Board.maxX*Board.maxY)+1;
		} while (!board.validMove(move));

		return move;
	}

	public String getName() {
		return name;
	}

	public char getCharacter() {
		return character;
	}

	public int getNumWins() {
		return numWins;
	}

	public void increment() {
		numWins++;
	}

}