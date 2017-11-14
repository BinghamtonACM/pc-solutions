import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 2016-G Magical Mystery Knight's Tour
 * 
 * A Depth-first search KnightSolver
 * 
 * @author kaitlin_nip
 *
 */
public class KnightSolver {

	private static SearchNode solve(SearchNode first) {

		/*
		 * initialization:
		 * - create "boardList" - which acts like a stack to hold candidate boards 
		 * - create "history" 
		 * - to check for duplicates, don't want to visit the same
		 * board twice - add first board to "stack" and "history"
		 */
		LinkedList<SearchNode> boardList = new LinkedList<>();
		HashSet<SearchNode> history = new HashSet<>();
		boardList.addLast(first);
		history.add(first);

		/*
		 * standard DFS loop
		 * if you want to do BFS, change the line 
		 * Board cur = boardList.removeLast();
		 * 
		 * to:
		 * Board cur = boardList.removeFirst();
		 */

		// while there are more board configurations to check
		while (!boardList.isEmpty()) {

			// get a board from the top of stack
			SearchNode cur = boardList.removeLast();

			/*
			 * get candidates for the current board
			 *  then, for each candidate:
			 * - if it satisfies problem requirement, return it 
			 * - else, append it
			 * to the stack and continue search
			 */
			for (SearchNode next : cur.getCandidates()) {

				if (next.isGood()) {
					return next;
				} else {
					if (!history.contains(next)) {
						history.add(next);
						boardList.addLast(next);
					}
				}
			}

		}
		return null;
	}

	/*
	 * reads in one 8x8 board
	 */
	private static Board readInput(Scanner s) {
		Board board = new Board();
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				Integer val = Integer.parseInt(s.next());
				if (val > 0) {
					board.setValueAt(row, col, val);
				}
			}
		}
		return board;
	}

	/*
	 * main method
	 */
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		int cases = Integer.parseInt(s.next());

		// for each test case
		for (int c = 0; c < cases; c++) {
			// 1. read input
			int num = Integer.parseInt(s.next());
			System.out.println(num);
			Board board = readInput(s);

			// 2. find solution
			SearchNode soln = solve(board);

			// 3. print solution
			if (null != soln) {
				System.out.println(soln);
			} else {
				System.out.println("cannot find solution for testcase: " + num);
			}

		}

	}

}
