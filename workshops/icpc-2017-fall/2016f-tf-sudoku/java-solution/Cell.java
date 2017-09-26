/**
 * a cell on the TFsudoku board
 * 
 * @author kaitlin_nip
 *
 */
public class Cell {
	// type 1 = single value cell
	// type 2 = double value cell
	int type;
	int top;
	int bot;
	
	/*
	 * construct cell from input string
	 */
	public Cell(String token) {
		if (token.length() == 1) {
			type = 1;
			try {
				top = Integer.parseInt(token);
			} catch (NumberFormatException e) {
				top = 0;
			}
		} else {
			type = 2;
			try {
				top = Integer.parseInt(token.substring(0, 1));
			} catch (NumberFormatException e) {
				top = 0;
			}
			try {
				bot = Integer.parseInt(token.substring(2, 3));
			} catch (NumberFormatException e) {
				bot = 0;
			}
		}
	}
	/*
	 * copy constructor
	 */
	public Cell(Cell cell) {
		this.type = cell.type;
		this.top = cell.top;
		this.bot = cell.bot;
	}

	/*
	 * construct type-1 cell from number
	 */
	public Cell(int num) {
		this.type = 1;
		this.top = num;
		this.bot = 0;
	}
	
	/*
	 * construct type-2 cell from numbers
	 */

	public Cell(Integer integer, Integer integer2) {
		this.type = 2;
		this.top = Math.min(integer, integer2);
		this.bot = Math.max(integer, integer2);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bot;
		result = prime * result + top;
		result = prime * result + type;
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
		Cell other = (Cell) obj;
		if (bot != other.bot)
			return false;
		if (top != other.top)
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	public String toString() {
		if (type == 1) {
			return top > 0 ? String.valueOf(top) : "-";
		}
		StringBuilder sb = new StringBuilder();
		sb.append(top > 0 ? top : "-").append("/").append(bot > 0 ? bot : "-");
		return sb.toString();

	}

	/*
	 * whether the cell is completely filled
	 */
	public boolean filled() {
		if (type == 1) {
			return top > 0;
		}
		return top > 0 && bot > 0;
	}

}
