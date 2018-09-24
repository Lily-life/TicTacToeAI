import java.util.*;

public class Board {
	private int[][] state;
	private int turn;
	private int winner;
	private HashSet<Integer> validMoves;
	private boolean gameOver;
	
	public Board() {
		state = new int[3][3];
		turn = 1;  // turn = 1 for human player (X), 2 for computer (O).
		winner = 0;
		gameOver = false;
		validMoves = new HashSet<>();
		for (int i = 0; i < 9; i++) {
			validMoves.add(i);
		}
	}
	
	public boolean placeMove(int index) {
		int row = index/3;
		int col = index%3;
		if (state[row][col] != 0) return false;
		state[row][col] = turn;
		validMoves.remove(index);
				
		if (checkRow(row) || checkCol(col) || checkDiagonal(row, col) || checkAntiDiagonal(row, col)) {
			winner = turn;
			gameOver = true;
		}
		
		if (validMoves.isEmpty()) {
			gameOver = true;
		}
		changePlayer();	
		return true;
	}
	
	private void changePlayer() {
		turn = turn == 1 ? 2 : 1;
	}
	
	
	private boolean checkRow(int row) {
		if(state[row][0] == state[row][1] && state[row][1] == state[row][2] && state[row][2] != 0) {
			return true;
		}
		return false;
	}
	
	private boolean checkCol(int col) {
		if (state[0][col] == state[1][col] && state[1][col] == state[2][col] && state[2][col] != 0) {
			return true;
		}
		return false;
	}
	
	private boolean checkDiagonal(int row, int col) {
		if(row == col && state[0][0] == state[1][1] && state[1][1] == state[2][2] && state[2][2] != 0) {
			return true;
		}
		return false;
	}
	
	private boolean checkAntiDiagonal(int row, int col) {
		if(row == 2-col && state[0][2] == state[1][1] && state[1][1] == state[2][0] && state[2][0] != 0) {
			return true;
		}
		return false;
	}
	
	public boolean isGameOver() {
		return gameOver;
	}
	
	public int getTurn() {
		return turn;
	}
	
	public int getWinner() {
		return winner;
	}
	
	public Set<Integer> getValidMoves() {
		return validMoves;
	}
	
	public Board clone() {
		Board newBoard = new Board();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				newBoard.state[i][j] = this.state[i][j];
			}
		}
		newBoard.turn = this.turn;
		newBoard.winner = this.winner;
		newBoard.gameOver = this.gameOver;
		newBoard.validMoves = new HashSet<Integer>(this.validMoves);
		
		return newBoard;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				switch (state[i][j]) {
				case 0: 
					sb.append("-");
					break;
				case 1:
					sb.append("X");
					break;
				case 2:
					sb.append("O");
					break;					
				}
				sb.append(" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
}
