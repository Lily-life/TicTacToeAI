import java.util.*;
public class TicTacToe {
	private Board board;
	private Scanner scanner;
	
	public TicTacToe (){
		board = new Board();
		scanner = new Scanner(System.in);
	}
	
	private void play () {
		System.out.println("Starting a tic-tac-toe game:");
		
		while (!board.isGameOver()) {
			printBoard();
			nextMove();
		}
		printResult();
	}
	
	private void nextMove() {
		if (board.getTurn() == 1) { // turn = 1 for human player (X), 2 for computer (O).
			takeHumanMove();
		}
		else {		
			MiniMax.run(board.getTurn(), board);
//			RandomMove.run(board);
		}
		
	}
	
	private void printBoard() {
		System.out.println(board);
	}
	
	private void takeHumanMove() {
		System.out.print("Your move (0 to 8): ");
		int move = scanner.nextInt();
		if (move < 0 || move > 8) {
			System.out.println("\nInvalid move. Input integer between 0 and 8 (inclusive)");
		}
		else if (!board.placeMove(move)) {
			System.out.println("\nInvalid move. Select a blank space");
		}
	}
	
	private void printResult() {
		int winner = board.getWinner();
		System.out.println(board);
		if (winner == 0) {
			System.out.println("It's a draw!");
		}
		else if (winner == 1) {
			System.out.println("You win!"); 
		}
		else {
			System.out.println("You lost.");
		}
	}
	
	public static void main(String[] args) {
		TicTacToe ttt = new TicTacToe();
		ttt.play();
	}
	
}
