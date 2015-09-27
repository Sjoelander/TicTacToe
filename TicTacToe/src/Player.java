import java.util.Scanner;
import java.util.Random;

public class Player {
	private char character;
	private String name;
	private int numWins;
	/**
	 * Konstruktor f�r Player
	 * @param c tecknet f�r spelaren
	 * @param n namnet f�r spelaren
	 */
	public Player(char c, String n) {
		character = c;
		name = n;
		numWins = 0;
	}

	/**
	 * H�mtar spelarens drag fr�n standardinput
	 * @param board br�det som draget ska spelas p�
	 * @return draget
	 */
	public int getMove(Board board) {
		int move = 0;
		Scanner scanner = new Scanner(System.in);
		while(true) {
			move = 0;
			if(scanner.hasNextInt()) {
				move = scanner.nextInt();
				// Kontrollerar s� att draget �r till�tet 
				if (move <= Board.maxX*Board.maxY && move > 0 && board.validMove(move)) {
					return move;	
				} else {
					System.out.println("Invalid Selection.");	
				}
			} else {
				System.out.println("Input must be a number.");	
				// Rensar buffern f�r system.in
				scanner.next();
			}
		}
	}

	/**
	 * H�mtar ett AI-drag
	 * @param board br�det som draget ska spelas p�
	 * @return draget
	 */
	public int aiMove(Board board) {
		int move = 0;
		// V�ljer slumpm�ssigt drag
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