
public class Board {
	public static final int maxX = 3, maxY = 3;
	/*
	 * [1] [2] [3] ~ [0,0] [0,1] [0,2] 
	 * [4] [5] [6] ~ [1,0] [1,1] [1,2]
	 * [7] [8] [9] ~ [2,0] [2,1] [2,2]
	 */

	// H�ller i alla br�dpositioner
	private char[][] boardArray;

	/**
	 * Konstruktor f�r Board
	 * S�tter alla positioner som tomma
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
	 * @return String som representerar br�dtillst�ndet
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
	 * �ndrar positionens tecken
	 * @param c tecknet som ska s�ttas
	 * @param val positionen som tecknet ska s�ttas p�
	 */
	public void setMove(char c, int val) {
		// Skriver om positionen till kartesiskt koordinatsystem och byter tecken p� positionen
		boardArray[(val-1)/3][(val-1)%3] = c;
	}


	/**
	 * Kontrollerar om drag �r okej
	 * @param val positionen som kontrolleras
	 * @return sant om draget �r okej, annars falskt
	 */
	public boolean validMove(int val) {
		// Skriver om positionen till kartesiskt koordinatsystem och kollar om den �r tom
		if (boardArray[(val-1)/maxX][(val-1)%maxY] == ' ') {
			return true;	
		} else {
			return false; 	
		}
	}

	/**
	 * Kontrollerar om spelet �r �ver
	 * @return returnerar sant om n�gon har vunnit
	 */
	public boolean checkWin() {
		for(int i = 0; i < maxX; i++) {
			// Kollar om alla rader fr�n 0 till maxX innehar samma tecken och inte �r tomma
			if(boardArray[i][0] == boardArray[i][1] && boardArray[i][1] == boardArray[i][2]) {
				if(!(boardArray[i][0] == ' '))
					return true;
			}
			// Kollar om alla kolumner fr�n 0 till maxY innehar samma tecken och inte �r tomma
			if(boardArray[0][i] == boardArray[1][i] && boardArray[1][i] == boardArray[2][i]) {
				if(!(boardArray[0][i] == ' '))
					return true;
			}
		}
		// Kollar ena diagonalen om den innehar samma tecken och inte �r tom
		if(boardArray[0][0] == boardArray[1][1] && boardArray[1][1] == boardArray[2][2]) {
			if(!(boardArray[0][0] == ' '))
				return true;
		}
		// Kollar andra diagonalen om den innehar samma tecken och inte �r tom
		if(boardArray[2][0] == boardArray[1][1] && boardArray[1][1] == boardArray[0][2]) {
			if(!(boardArray[2][0] == ' '))
				return true;
		}

		return false;
	}
	
	/**
	 * G�r igenom hela vektorn f�r boardArray och kontrollorer om det finns ett tomt f�lt
	 * @return sant om ej finns n�gra tomma f�lt, falskt annars
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
