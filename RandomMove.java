import java.util.*;

public class RandomMove {
	public static void run (Board board) {
		System.out.println("Computer makes a random move:");
		Random rand = new Random();
		int move = -1;
		Set<Integer> validMoves = board.getValidMoves();
		while (!validMoves.contains(move)) {
			move = rand.nextInt(9);
		}
		board.placeMove(move);
	}
}
