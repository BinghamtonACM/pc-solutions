import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeSet;

/**
 * 
 * a TFsudoku board
 * 
 * @author kaitlin_nip
 */
public class Board {

	/*
	 * 6 x 6 array of Cells
	 */
	Cell[][] board = new Cell[6][6];

	/*
	 * copy constructor
	 */
	public Board(Board other) {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				this.board[i][j] = new Cell(other.get(i, j));
			}
		}
	}

	/*
	 * empty constructor
	 */
	public Board() {

	}

	/*
	 * return the list of next possible Board configurations that have one more
	 * cell filled than "this"
	 */
	public List<Board> getCandidates() {
		List<Board> candidates = new ArrayList<>();

		// iterate the board (row and col)
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {

				// current cell
				Cell cur = get(i, j);

				/*
				 * if current cell is filled, if Board is valid, return
				 */

				if (cur.filled()) {
					if (good()) {
						candidates.add(this);
						return candidates;
					}
					continue;
				}

				/*
				 * if current cell not filled, find all possible ways to fill it
				 */
				List<Cell> nextCell = getCellCandidates(i, j);

				/*
				 * if there's only 1 way to fill the cell, fill it if Board is
				 * good, return else move on to the next unfilled cell
				 */
				if (nextCell.size() == 1) {
					set(i, j, nextCell.get(0));
					if (good()) {
						candidates.add(this);
						return candidates;
					}
					continue;
				}

				/*
				 * if there's > 1 way to fill the cell, generate new Board
				 * configs for each of the ways return list of new configs
				 */
				for (Cell cand : nextCell) {
					Board board = new Board(this);
					board.set(i, j, cand);
					candidates.add(board);
				}
				return candidates;

			}
		}
		return candidates;
	}

	/*
	 * For Cell (i,j) on this current board, find all possible ways to fill it
	 * (without violating the sudoku restraints), and return as a List
	 */
	private List<Cell> getCellCandidates(int i, int j) {
		List<Cell> candidates = new ArrayList<>();

		// candidates = numbers 1-9
		Set<Integer> set = new HashSet<>();
		for (int n = 1; n <= 9; n++) {
			set.add(n);
		}
		// get numbers that are in the same row
		Set<Integer> row = getRow(i);
		// get numbers that are in the same col
		Set<Integer> col = getCol(j);
		// get numbers that are in the same box
		Set<Integer> box = getBox(i, j);

		/*
		 * remove these numbers from the set 1-9 the remaining are legal
		 */
		set.removeAll(row);
		set.removeAll(col);
		set.removeAll(box);
		Cell cur = get(i, j);

		// if the cell holds 1 val
		if (cur.type == 1) {
			for (int num : new TreeSet<Integer>(set)) {
				candidates.add(new Cell(num));
			}
		}
		// if the cell holds 2 vals
		else {

			/*
			 * if the top val is set, only use candidates > cell.top to form new
			 * candidates (due to the restriction that top < bot)
			 */
			if (cur.top > 0) {
				for (int num : new TreeSet<Integer>(set)) {
					if (num > cur.top) {
						candidates.add(new Cell(cur.top, num));
					}
				}
			}
			
			/*
			 * if the bot val is set, only use candidates < cell.bot to form
			 * new candidates (due to the restriction that top < bot)
			 */
			else if (cur.bot > 0) {
				for (int num : new TreeSet<Integer>(set)) {
					if (num < cur.bot) {
						candidates.add(new Cell(num, cur.bot));
					}
				}
			} 
			/*
			 * if neither top or bot has been set,
			 * find all pairs first < sec, and form new candidates
			 */
			else {
				for (Cell cell : getPairs(set)) {
					candidates.add(cell);
				}
			}
		}
		return candidates;
	}

	private List<Cell> getPairs(Set<Integer> set) {
		List<Integer> list = new ArrayList<>(set);
		Collections.sort(list);
		List<Cell> ret = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			for (int j = i + 1; j < list.size(); j++) {
				Cell cell = new Cell(list.get(i), list.get(j));
				ret.add(cell);
			}
		}
		return ret;
	}

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
	 * get numbers appearing in row i
	 */
	private Set<Integer> getRow(int i) {
		Set<Integer> set = new HashSet<>();
		for (int col = 0; col < 6; col++) {
			Cell cell = get(i, col);
			if (cell.top > 0) {
				set.add(cell.top);
			}
			if (cell.type == 2 && cell.bot > 0) {
				set.add(cell.bot);
			}
		}
		return set;
	}
	
	/*
	 * get numbers appearing in col i
	 */
	private Set<Integer> getCol(int i) {
		Set<Integer> set = new HashSet<>();
		for (int row = 0; row < 6; row++) {
			Cell cell = get(row, i);
			if (cell.top > 0) {
				set.add(cell.top);
			}
			if (cell.type == 2 && cell.bot > 0) {
				set.add(cell.bot);
			}
		}
		return set;
	}
	
	/*
	 * get numbers appearing in the same box as Cell(i,j)
	 */
	private Set<Integer> getBox(int i, int j) {
		Set<Integer> set = new HashSet<>();
		int cs = 0;
		int ce = 2;
		if (j >= 3) {
			cs = 3;
			ce = 5;
		}
		int rs = 0;
		int re = 1;
		if (i >= 4) {
			rs = 4;
			re = 5;
		} else if (i >= 2) {
			rs = 2;
			re = 3;
		}
		for (int row = rs; row <= re; row++) {
			for (int col = cs; col <= ce; col++) {
				Cell cell = get(row, col);
				if (cell.top > 0) {
					set.add(cell.top);
				}
				if (cell.type == 2 && cell.bot > 0) {
					set.add(cell.bot);
				}
			}
		}

		return set;
	}
	/*
	 * check board is solved
	 * since at each step we ensure the board follows
	 * sudoku rules, we only have to check whether 
	 * if all cells are filled here
	 */
	public boolean good() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				if (!board[i][j].filled()) {
					return false;
				}
			}
		}
		return true;
	}
	/*
	 * print for out put
	 */
	public void print() {
		for (int row = 0; row < 6; row++) {
			StringJoiner sjJoiner = new StringJoiner(" ");

			for (Cell c : board[row]) {
				sjJoiner.add(c.toString());
			}
			System.out.println(sjJoiner.toString());
		}
	}
	
	public void set(int i, int j, Cell cell) {
		board[i][j] = cell;
	}

	public Cell get(int i, int j) {
		return board[i][j];
	}

}
