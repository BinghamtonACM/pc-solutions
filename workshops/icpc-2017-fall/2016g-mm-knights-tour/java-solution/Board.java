import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeSet;

/**
 * Represents a chess board
 * 
 * @author kaitlin_nip
 *
 */
public class Board implements SearchNode {
	
	//constants!
	static final int SIZE = 8;
	static final int MAGIC_NUM = 260;
	
	//each xSteps[i], ySteps[i] pair is a valid knight move
	//total 8 moves
	static final int[] xSteps = { 2, 1, -1, -2, -2, -1, 1, 2 };
	static final int[] ySteps = { 1, 2, 2, 1, -1, -2, -2, -1 };

	// double array of ints to represent the board
	int[][] board = new int[SIZE][SIZE];

	// row/colSums[i] = the sum of the ith row/column;
	int[] rowSums = new int[SIZE];
	int[] colSums = new int[SIZE];
	
	// freeSpotsInRow/Col = number of free squares in the ith row/column
	int[] freeSpotsInRow = new int[SIZE];
	int[] freeSpotsInCol = new int[SIZE];

	// maps a value to its coordinate on the board
	Map<Integer, Coord> stepsTaken = new HashMap<>();
	
	//keeps track which values have not been set
	TreeSet<Integer> stepsNotTaken = new TreeSet<>();

	/*
	 * Default constructor
	 */
	public Board() {
		for (int i = 1; i <= SIZE * SIZE; i++) {
			this.stepsNotTaken.add(i);
		}
		for(int i = 0; i < SIZE; i++){
			this.freeSpotsInRow[i] = SIZE;
			this.freeSpotsInCol[i] = SIZE;
		}
	}

	/*
	 * Deep copy constructor
	 */
	public Board(Board other) {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				this.board[row][col] = other.board[row][col];
			}
		}
		for (Integer i : other.stepsTaken.keySet()) {
			this.stepsTaken.put(i, new Coord(other.stepsTaken.get(i)));
		}
		this.stepsNotTaken = new TreeSet<>(other.stepsNotTaken);
		this.rowSums = Arrays.copyOf(other.rowSums, other.rowSums.length);
		this.colSums = Arrays.copyOf(other.colSums, other.colSums.length);
		this.freeSpotsInCol = Arrays.copyOf(other.freeSpotsInCol, other.freeSpotsInCol.length);
		this.freeSpotsInRow = Arrays.copyOf(other.freeSpotsInRow, other.freeSpotsInRow.length);
	}

	/*
	 * Set value "val" at board[row][col]
	 * and update the internal data structure accordingly
	 */
	public void setValueAt(int row, int col, Integer val) {
		board[row][col] = val;	//set board
		rowSums[row] += val;	//update rowSums and colSums
		colSums[col] += val;
		stepsTaken.put(val, new Coord(row, col));	//record this new step
		stepsNotTaken.remove(val);					//remove this step from stepsNotTaken
		freeSpotsInCol[col]--;		//decrease # free spots in row+col by 1
		freeSpotsInRow[row]--;
	}

	//find all possible configurations one move from current board
	public List<Board> getCandidates(){
		List<Board> candidates = new ArrayList<>();
		
		List<Coord> stepCandidates = null;
			
		/*
		 * 1. find an empty square on the board
		 * 2. find all possible candidate for this square
		 */
		
		Integer nextStep = getNextStepToTake();
		
		//while there's only 1 candidate, modify the current board and continue
		while( (stepCandidates = getCandidateForStep(nextStep)).size() == 1){
			Coord pair = stepCandidates.get(0);
			this.setValueAt(pair.x, pair.y, nextStep);
			if (this.isGood()) {
				candidates.add(this);
				return candidates;
			}
			nextStep = getNextStepToTake();
		}
		
		//otherwise, for each candidate for this square
		//create a new board, and add to the return list
		for (Coord pair : stepCandidates) {
			Board board = new Board(this);
			board.setValueAt(pair.x, pair.y, nextStep);
			if (!board.isImpossible()) {
				candidates.add(board);
			} 
		}
		
		return candidates;
	}
	
	/*
	 * Checks whether a board is impossible to reach the requirements 
	 * narrow down search space
	 */
	private boolean isImpossible() {
		
		//4 cases
		for (int num = 0; num < SIZE; num++) {
			
			// if a row already too big
			if (rowSums[num] > MAGIC_NUM) {
				return true;
			}
			
			// if a row will never reach 260
			int max = maxPossible(freeSpotsInRow[num]);

			if (MAGIC_NUM - rowSums[num] > max) {
				return true;
			}
			
			// if a col already too big
			if (colSums[num] > MAGIC_NUM) {
				return true;
			}
			
			// if a col will never reach 260
			max = maxPossible(freeSpotsInCol[num]);
			if (MAGIC_NUM - colSums[num] > max) {
				return true;
			}
		}
		return false;
	}

	//given some number of freeSpots in row/col
	//find the maxSum you get filling them
	private int maxPossible(int freeSpots) {
		
		//simply add up the x largest elems from stepNotTaken
		int sum = 0;
		Iterator<Integer> iterator = stepsNotTaken.descendingIterator();
		for(int count = 0; count < Math.min(freeSpots, stepsNotTaken.size()); count++){
			sum += iterator.next();
		}
		return sum;
	}
	
	/*
	 * "step" is the step we are trying to take
	 * find all possible coordinates for it
	 */
	private List<Coord> getCandidateForStep(Integer step) {
		List<Coord> candidates = new ArrayList<>();
		if(step == null){
			return candidates;
		}
		/*
		 * try to narrow down the search space by
		 * 1. get all valid knight moves from step-1
		 * 2. get all valid knight moves from step+1
		 * (if either step-1 ot step+1 is not on board, then candidates = all of steps not taken)
		 * (exclude the coordinate that are already filled)
		 * 
		 * - 1 intersect 2 is the candidates we want
		 */
		
		Set<Coord> candidatePrev = getNextStep(step - 1);
		Set<Coord> candidateNext = getNextStep(step + 1);
		candidatePrev.retainAll(candidateNext);
		candidates.addAll(candidatePrev);
		return candidates;
	}
	
	/*
	 * given a step i, find all valid knight moves from it
	 * i could be on the board or not on the board
	 */
	private Set<Coord> getNextStep(int i) {
		Set<Coord> result = new HashSet<>();
		
		//if i is on the board, get its coordinate
		//and find all valid knight moves that are not already
		//occupied
		if (stepsTaken.containsKey(i)) {
			int curX = stepsTaken.get(i).x;
			int curY = stepsTaken.get(i).y;
			for (int n = 0; n < xSteps.length; n++) {
				int nX = curX + xSteps[n];
				int nY = curY + ySteps[n];
				Coord pair = new Coord(nX, nY);
				if (isOnBoard(nX, nY) && board[nX][nY] == 0) {
					result.add(pair);
				}
			}
		} 
		//if i is not on the board, then every empty square is valid
		else {
			for (int row = 0; row < SIZE; row++) {
				for (int col = 0; col < SIZE; col++) {
					if (board[row][col] == 0) {
						Coord pair = new Coord(row, col);
						result.add(pair);
					}
				}
			}
		}
		return result;
	}
	
	/*
	 * check whether (x,y) is within the board boundary
	 */
	private boolean isOnBoard(int x, int y) {
		return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
	}
	
	/*
	 * try to find the next best step to take
	 */
	private Integer getNextStepToTake() {
		
		//if not un-taken steps, return null
		if(this.stepsNotTaken.isEmpty()){
			return null;
		}
		
		//first try to find a step between 2 taken steps
		for (int i : this.stepsNotTaken) {
			if (stepsTaken.containsKey(i - 1) && stepsTaken.containsKey(i + 1)) {
				return i;
			}
		}
		//next, try to find a step that's before or after a taken step
		for (int i : this.stepsNotTaken) {
			if (stepsTaken.containsKey(i - 1) || stepsTaken.containsKey(i + 1)) {
				return i;
			}
		}
		//just return a step
		return this.stepsNotTaken.iterator().next();
	}
	
	/*
	 * Verifies whether this board satisfies problem requirement
	 */
	public boolean isGood() {
		return stepsNotTaken.size() == 0 && isSemiMagical();
	}

	/*
	 * Verifies whether this board is semi-magical
	 * by checking row/col sums
	 */
	private boolean isSemiMagical() {
		for (int num = 0; num < SIZE; num++) {
			if (rowSums[num] != MAGIC_NUM) {
				return false;
			}
			if (colSums[num] != MAGIC_NUM) {
				return false;
			}
		}
		return true;
	}

	/*
	 * eclipsed-generated code, in order for 
	 * HashSet membership test to work
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(board);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (!Arrays.deepEquals(board, other.board))
			return false;
		return true;
	}
	
	/*
	 * prints the board as required, numbers right aligned
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringJoiner sj1 = new StringJoiner("\n");
		for (int row = 0; row < SIZE; row++) {
			StringJoiner sj2 = new StringJoiner(" ");
			for (Integer i : board[row]) {
				sj2.add(String.format("%2d", i));
			}
			sj1.add(sj2.toString());
		}
		return sj1.toString();
	}

}
