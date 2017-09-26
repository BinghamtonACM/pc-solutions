import java.util.List;

/**
 * An common interface that mant searching problems
 * can implement
 * 
 * Not necessary to have this interface. 
 * Only to show you that the DFS and BFSsearching
 * can be very templatized 
 * 
 * For example, the solve() methods in 2016G(magical knight) and 2016F(tight-fit sudoku)
 * are almost exactly the same. 
 * Its just that each problem has its own implementation of
 * Board class the its methods
 * 
 * 
 * @author kaitlin_nip
 *
 */
public interface SearchNode {
	
	public boolean isGood();
	public List<? extends SearchNode> getCandidates();

}
