
/**
 * Represents a (x,y) coordinate on the board
 * @author kaitlin_nip
 *
 */
public class Coord{
	int x;
	int y;
	
	/*
	 * constructor
	 */
	public Coord(int i, int j){
		this.x = i;
		this.y = j;
	}
	
	/*
	 * copy constuctor
	 */
	public Coord(Coord other) {
		this.x = other.x;
		this.y = other.y;
	}
	
	/*
	 * toString for debug
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Coord [x=" + x + ", y=" + y + "]";
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
		result = prime * result + x;
		result = prime * result + y;
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
		Coord other = (Coord) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
}
