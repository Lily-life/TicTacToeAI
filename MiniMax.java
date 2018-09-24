
public class MiniMax {
	public static void run (int player, Board board) {
		System.out.println("Computer makes a move: ");
		runMiniMax(player, board);
	}
	
	public static int runMiniMax(int player, Board board) {
		if (board.isGameOver()) {
			return calScore(player, board);
		}
		if (board.getTurn() == player) {
			return getMax(player, board) - 1;
		}
		else {
			return getMin(player, board) + 1;
		}
	}
	
	private static int getMax(int player, Board board) {
		int maxScore = -10000;
		int bestMove = -1;
		for (Integer move: board.getValidMoves()) {
			Board newBoard = board.clone();
			newBoard.placeMove(move);
			
			int score = runMiniMax(player, newBoard);
			if (score > maxScore) {
				maxScore = score;
				bestMove = move;
			}
		}
		
		board.placeMove(bestMove);
		return maxScore;
	}
	
	private static int getMin(int player, Board board) {
		int minScore = 10000;
		int bestMove = -1;
		
		for (Integer move: board.getValidMoves()) {
			Board newBoard = board.clone();
			newBoard.placeMove(move);
			
			int score = runMiniMax(player, newBoard);
			if (score < minScore) {
				minScore = score;
				bestMove = move;
			}
		}
		
		board.placeMove(bestMove);
		return minScore;
	}
	
	private static int calScore(int player, Board board) {
		if (board.getWinner() == player) {
			return 10;
		} else if (board.getWinner() == 0) {
			return 0;
		} else {
			return -10;
		}
	}
}
