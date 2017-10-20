import java.util.*;
import edu.princeton.cs.algs4.MinPQ;


public class Solver{

	private class SearchNode implements Comparable<SearchNode> {
		private SearchNode pre;
		private int move = 0;
		private Board board;
		private int priority;


		//two constructor for initial searchnode, and non-initial searchnode
		public SearchNode(Board aboard) {
			//initial searchnode, move = 0;
			
			this.board = aboard;
			this.priority = board.manhattan() + move;

		}

		public SearchNode(Board aboard, SearchNode pre) {
			//non-initial searchnode, move = premove + 1;

			this.pre = pre;
			this.move = pre.move + 1;
			this.board = aboard;
			this.priority = board.manhattan() + move;
		}

		public int compareTo(SearchNode that){
			if(this.board.equals(that.board)) return 0;
			if(this.priority < that.priority) return -1;
			return 1;
		}
	}

	private SearchNode lastSN;
	private SearchNode lastSNTwin;

	//find a solution to the initial board (using the A* algorithm)
	public Solver(Board initial){
		//make two new MinPQ with Twin or initial
		MinPQ<SearchNode> searchnodes = new MinPQ<SearchNode>();
		MinPQ<SearchNode> searchnodesTwin = new MinPQ<SearchNode>();

		Board initialTwin = initial.twin();

		//make initial SearchNode
		SearchNode initSearchNode = new SearchNode(initial);
		SearchNode initSearchNodeTwin = new SearchNode(initialTwin);

		//insert initial SearchNode to minPQ
		searchnodes.insert(initSearchNode);
		searchnodesTwin.insert(initSearchNodeTwin);

		lastSN = solve(searchnodes);
		lastSNTwin = solve(searchnodesTwin);
		while (lastSN == null && lastSNTwin == null) {
			lastSN = solve(searchnodes);
			lastSNTwin = solve(searchnodesTwin);
		}	
	}

	private SearchNode solve(MinPQ<SearchNode> sn){
		if (sn.isEmpty()) return null;
		SearchNode highprioritySN = sn.delMin();
		if (highprioritySN.board.isGoal()) return highprioritySN;
		for(Board aboard : highprioritySN.board.neighbors()){
			if(highprioritySN.pre == null ||! aboard.equals(highprioritySN.pre.board)){
				sn.insert(new SearchNode(aboard,highprioritySN));
			}
		}
		return null;
	}

	// is the initial board solvable?
	public boolean isSolvable(){
		return lastSN != null;
	}

	//min number of moves to solve initial board; -1 if unsolveable;
	public int moves(){
		if(isSolvable()) return lastSN.move;
		return -1;
	}

	//sequence of boardsin a shortest solution, null is unsolvable
	public Iterable<Board> solution(){
		ArrayList<Board> solution = new ArrayList<Board>();
		if(!isSolvable()) return null;
		SearchNode current = lastSN;
		while(current.pre != null){
			solution.add(current.board);
			current = current.pre;
		}
		Collections.reverse(solution);
		return solution;
	}

	public static void main(String[] args){
		int[][] a = new int[][]{{0,1,3},{4,2,5},{7,8,6}};
		Board testboard = new Board(a);
		Solver solver = new Solver(testboard);
		//System.out.println(solver.isSolvable());
		System.out.println(solver.moves());

		for (Board aboard: solver.solution()){
			System.out.println(aboard.toString());
		}

	}
}