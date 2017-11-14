

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
/**
 * Tight_Fit_Sudoku solver
 * @author kaitlin_nip
 */
public class Tight_Fit_Sudoku {
	
	
	/*
	 * method to solve a TF Sudoku board, using Depth-First Search BFS
	 */
	private static Board solve(Board board) {
		/*
		 * "stack" keeps track of Board states
		 */
		
		LinkedList<Board> stack = new LinkedList<>();
		
		/*"history" - HashSet to keep track of all the Board configurations we have visited before
		 * so we won't visit the same Board config twice. 
		 * (if we do, we may fall into a cycle and never terminate)
		 * 
		 * NOTE: object must have hashCode() and equals() for hashing to work
		 * nested objects must have "hashCode() and equals() for all levels
		 * eclipse can generate these methods for you
		 */
		Set<Board> history = new HashSet<>();
		
		/*
		 * initialize "stack" and "history" with the initial Board config
		 */
		history.add(board);
		stack.addLast(board);
		
		/*
		 * the while loop will do DFS on the initial Board,
		 * each iteration, we try to fill one more cell on the Board
		 * there maybe 0, 1, or more ways to fill this cell
		 * for each possibility, we construct a new Board config and push it to the stack
		 * 
		 * NOTE: you MUST construct a new board config
		 * you cannot just make changes to the original board
		 * (unless you remember the changes and undo them)
		 *  
		 * If we find a Board config that is good, return it.
		 * 
		 * If we go through all the configs without finding a solution,
		 * exit loop and return null;
		 */
		while(!stack.isEmpty()){
			
			//get the last Board
			Board cur = stack.removeLast();
			
			//get the list of all possible next config
			List<Board> candidates = cur.getCandidates();
			for(Board b : candidates){
				
				//if we find a solved Board, return it
				if(b.good()){
					return b;
				}
				
				else{
					//check whether we have visited this Board before
					//if not, add it to "stack" and "history"
					if(!history.contains(b)){
						history.add(b);
						stack.addLast(b);
					}
				}
			}
		}
		
		//if we get here, we have visited every possible Board
		//without finding a solution
		return null;
		
	}
	
	/*
	 * read in the input for a Tight-Fit Sudoku board.
	 * return an Board instance
	 */
	private static Board readBoard(Scanner scanner) {
		Board board = new Board();
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 6; j++){
				String token = scanner.next();
				board.set(i, j, new Cell(token));
			}
		}
		return board;
	}
	
	/*
	 * main()
	 */
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		int cases = Integer.parseInt(scanner.next());
		for(int c = 0; c < cases; c++){
			int num = Integer.parseInt(scanner.next());
			System.out.println(num);
			Board board = readBoard(scanner); //read input
			Board sol = solve(board); // solve
			sol.print(); //print solution
		}
	}

}
