
public class Board {
	public static final int maxX = 3, maxY = 3;
	/*
	 * [1] [2] [3] ~ [0,0] [0,1] [0,2] 
	 * [4] [5] [6] ~ [1,0] [1,1] [1,2]
	 * [7] [8] [9] ~ [2,0] [2,1] [2,2]
	 */

	// Håller i alla brädpositioner
	private char[][] boardArray;

	/**
	 * Konstruktor för Board
	 * Sätter alla positioner som tomma
	 */
	public Board() {
		boardArray = new char[maxX][maxY]; 
		for (int i = 0; i < maxX; i++){
			for (int j = 0; j < maxY; j++) {
				boardArray[i][j] = ' ';
			}
		}
	}

	@Override
	/**
	 * @return String som representerar brädtillståndet
	 */
	public String toString() {
		String str = "";
		for(int i = 0; i < maxX; i++) {
			for(int j = 0; j < maxY; j++) {
				str += ("[" + boardArray[i][j] + "] ");
			}
			str +=("\n");
		}
		return str;
	}

	/**
	 * Ändrar positionens tecken
	 * @param c tecknet som ska sättas
	 * @param val positionen som tecknet ska sättas på
	 */
	public void setMove(char c, int val) {
		// Skriver om positionen till kartesiskt koordinatsystem och byter tecken på positionen
		boardArray[(val-1)/3][(val-1)%3] = c;
	}


	/**
	 * Kontrollerar om drag är okej
	 * @param val positionen som kontrolleras
	 * @return sant om draget är okej, annars falskt
	 */
	public boolean validMove(int val) {
		// Skriver om positionen till kartesiskt koordinatsystem och kollar om den är tom
		if (boardArray[(val-1)/maxX][(val-1)%maxY] == ' ') {
			return true;	
		} else {
			return false; 	
		}
	}

	/**
	 * Kontrollerar om spelet är över
	 * @return returnerar sant om någon har vunnit
	 */
	public boolean checkWin() {
		for(int i = 0; i < maxX; i++) {
			// Kollar om alla rader från 0 till maxX innehar samma tecken och inte är tomma
			if(boardArray[i][0] == boardArray[i][1] && boardArray[i][1] == boardArray[i][2]) {
				if(!(boardArray[i][0] == ' '))
					return true;
			}
			// Kollar om alla kolumner från 0 till maxY innehar samma tecken och inte är tomma
			if(boardArray[0][i] == boardArray[1][i] && boardArray[1][i] == boardArray[2][i]) {
				if(!(boardArray[0][i] == ' '))
					return true;
			}
		}
		// Kollar ena diagonalen om den innehar samma tecken och inte är tom
		if(boardArray[0][0] == boardArray[1][1] && boardArray[1][1] == boardArray[2][2]) {
			if(!(boardArray[0][0] == ' '))
				return true;
		}
		// Kollar andra diagonalen om den innehar samma tecken och inte är tom
		if(boardArray[2][0] == boardArray[1][1] && boardArray[1][1] == boardArray[0][2]) {
			if(!(boardArray[2][0] == ' '))
				return true;
		}

		return false;
	}
	
	/**
	 * Går igenom hela vektorn för boardArray och kontrollorer om det finns ett tomt fält
	 * @return sant om ej finns några tomma fält, falskt annars
	 */
	public boolean checkDraw() {
		for (char[] row : boardArray) {
			for (char character : row) {
				if (character == ' ') {
					return false;
				}
			}
		}
		return true;
	}
}
