import java.util.ArrayList;
import java.util.Arrays;

public class Board{
	private int n;
	private int[][] blocks;

	//construct a board from an n-by-n array of blocks (where blocks[i][j] = block in row i, column j)
	public Board(int[][] blocks){
		n = blocks.length;
		this.blocks = copyblocks(blocks);
	}

	//board dimension n
	public int dimension(){
		return n;
	}

	//get the blocks from Board
	private int getBlock(int row, int col){
		return blocks[row][col];
	}

	//number of blocks out of place
	public int hamming(){
		int count = 0;
		int value = 1;
		//System.out.println(blocks[3][3]);
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				if ( (blocks[i][j] != value++) && (blocks[i][j] != 0)) {
					count++;
				}
			}
		}
		return count;
	}

	//sum of Manhattan distances between blocks and goal
	public int manhattan(){
		int sum = 0;
		int value = 1;
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				if ((blocks[i][j] != value++) && (blocks[i][j] != 0)){
					sum += caculateMove(i,j);
				}
			}
		}
		return sum;
	}

	//caculate the move 
	private int caculateMove(int row, int col){
		int move = 0;
		int block = blocks[row][col];
		int goalrow = (block-1) / n;
		int goalcol = (block-1) % n;
		move = (Math.abs(row-goalrow) + (Math.abs(col-goalcol)));
		return move;
	}

	//is this board the goal board?
	public boolean isGoal(){
		int value = 1;
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if ((blocks[i][j] != value++) && (blocks[i][j] != 0))
					return false;
			}
		}
		return true;
	}

	//get a copy of blocks array
	private int[][] copyblocks(int[][] blocks){
		int [][] copy = new int[n][n];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				copy[i][j] = blocks[i][j];
			}
		}
		return copy;
	}

	//a board that is obtained by exchanging any pair of blocks
	public Board twin(){
		int count = 0;
		int[][] copy = new int[n][n];
		copy = copyblocks(blocks);

		for(int i = 0; i < n; i++){
			for(int j = 0; j < n-1; j++){
				if((blocks[i][j] != 0) && (blocks[i][j+1] != 0)){
					int temp = copy[i][j];
					copy[i][j] = copy[i][j+1];
					copy[i][j+1] = temp;
					return new Board(copy);
				}
			}
		}
		throw new RuntimeException();
	}

	//does this board equal y?
	public boolean equals(Object y){
		if (this == y) return true;
		if (y == null) return false;

		Board yboard = (Board) y;
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if(blocks[i][j] != yboard.getBlock(i,j)) return false;
			}
		} 
		return true;
	}

	//find space block
	private int[] findSpace(int[][] blocks){
		int[] index = new int[2];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if(blocks[i][j] == 0){
					index[0]=i;
					index[1]=j;
					return index;
				}
			}
		}
		throw new RuntimeException();
	}

	//all neigboring boards
	public Iterable<Board> neighbors(){
		ArrayList<Board> neighbors = new ArrayList<Board>();
		int[] index = findSpace(blocks);
		int spaceRow = index[0];
		int spaceCol = index[1];

		if(spaceRow>0){
			int[][] copy = copyblocks(blocks);
			copy[spaceRow][spaceCol] = copy[spaceRow-1][spaceCol];
			copy[spaceRow-1][spaceCol] = 0;
			neighbors.add(new Board(copy));
		}
		if(spaceRow < n -1){
			int[][] copy = copyblocks(blocks);
			copy[spaceRow][spaceCol] = copy[spaceRow+1][spaceCol];
			copy[spaceRow+1][spaceCol] = 0;
			neighbors.add(new Board(copy));
		}
		if(spaceCol > 0){
			int[][] copy = copyblocks(blocks);
			copy[spaceRow][spaceCol] = copy[spaceRow][spaceCol-1];
			copy[spaceRow][spaceCol-1] = 0;
			neighbors.add(new Board(copy));
		}
		if(spaceCol < n -1){
			int[][] copy = copyblocks(blocks);
			copy[spaceRow][spaceCol] = copy[spaceRow][spaceCol+1];
			copy[spaceRow][spaceCol+1] = 0;
			neighbors.add(new Board(copy));
		}
		return neighbors;
	}

	//string representation of this board (in the output format specified below)
	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append(n + "\n");
		for (int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				s.append(String.format("%2d ", blocks[i][j]));
			}
			s.append("\n");
		}
		return s.toString();
	}

	public static void main(String[] args){
		// int[][] a = new int[][]{{0,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};
		int[][] b = new int[][]{{0,2,3,4},{5,6,7,8},{10,9,11,12},{13,15,14,1}};
		//Board test = new Board(a);
		Board test1 = new Board(b);
		// System.out.println("board dimension (4)");
		// System.out.println(test.dimension());
		// System.out.println("hamming (5)");
		// System.out.println(test.hamming());
		// System.out.println("manhattan (10)");
		// System.out.println(test.manhattan());
		// System.out.println("is goal");
		// System.out.println(test.isGoal());
		// System.out.println("is equal");
		// System.out.println(test.equals(test1));
		//ArrayList<Board> neighbors = new ArrayList<Board>();
		System.out.println(test1.toString());
		for (Board aboard: test1.neighbors()){
			System.out.println(aboard);
		}
	}

}

