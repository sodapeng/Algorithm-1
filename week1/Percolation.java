import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation{
	private boolean[][] grid;
	private int size;
	private int index;
	private WeightedQuickUnionUF wqu;

	//create n-by-n grid, with all sites blocked
	public Percolation(int n){
		validateN(n);
		size = n;
		index = n * n;
		grid = new boolean[n+1][n+1];
		wqu = new WeightedQuickUnionUF(index + 2);
		for(int i=1; i <= n; i++){
			for(int j = 1; j <=n; j++){
				grid[i][j] = false;
			}
		}
	}

	//check whether N is out of bound or not
	private void validateN(int n){
		if (n <= 0) throw new java.lang.IllegalArgumentException();
	} 

	//check whether row and column are out of bound or not
	private void validateRC(int row, int col){
		if(row <= 0 || row > size) throw new java.lang.IndexOutOfBoundsException();
		if(col <= 0 || col > size) throw new java.lang.IndexOutOfBoundsException();
	}

	//convert grid index to weightedquickunionUF index
	private int convertIndex(int row, int col){
		return ( size * (row-1) + col);
	}

	//open site (row col) if it is not open already
	public void open(int row, int col){
		validateRC(row, col);
		int i = row;
		int j = col;
		if(grid[i][j]) return;
		else{
			grid[i][j] = true;
			if(i == 1) {
				wqu.union(convertIndex(i,j),0);
			}
			if(i == size){
				wqu.union(convertIndex(i,j), index+1);
			}
			if((i-1) >= 1 && isOpen(i-1,j)){
				wqu.union(convertIndex(i,j), convertIndex(i-1, j));
			}
			if((i+1) <= size && isOpen(i+1,j)){
				wqu.union(convertIndex(i,j), convertIndex(i+1, j));
			}
			if((j-1) >= 1 && isOpen(i,j-1)){
				wqu.union(convertIndex(i,j), convertIndex(i, j-1));
			}
			if((j+1) <= size && isOpen(i,j+1)){
				wqu.union(convertIndex(i,j), convertIndex(i, j+1));
			}
		}
	}

	//is site (row,col) open?
	public boolean isOpen(int row, int col){
		validateRC(row,col);
		return (grid[row][col]);
	}

	//is site (row,col) full? (connected to the wqu[0])
	public boolean isFull(int row, int col){
		validateRC(row,col);
		if(isOpen(row,col)){
			return (wqu.connected(convertIndex(row,col), 0));
		}
		else return false;
	}
	

	//number of open sites
	public int numberOfOpenSites(){
		int count = 0;
		for (int i =1; i<=size; i++){
			for (int j=1; j<=size; j++){
				if(grid[i][j]) count +=1;
			}
		}
		return count;
	}

	public boolean percolates(){
		return (wqu.connected(0, index + 1));
	}

	public static void main(String[] args){
		Percolation perctest = new Percolation(5);
		System.out.println();
		perctest.open(1,1);
		perctest.open(1,2);
		perctest.open(2,2);
		perctest.open(2,3);
		perctest.open(3,2);
		perctest.open(3,3);
		perctest.open(4,3);
		perctest.open(4,4);
		perctest.open(5,4);
		System.out.println(perctest.isOpen(1,1));
		System.out.println(perctest.isFull(4,3));
		System.out.println(perctest.percolates());
		System.out.println(perctest.numberOfOpenSites());
	}
	
}