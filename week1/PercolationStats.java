import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats{
	private int size;
	private int numtrails;
	private Percolation perco;
	private double[] numopen;

	//perform trials independent experiments on an n-by-n grid
	public PercolationStats(int n, int trails){
		size = n;
		numtrails = trails;
		numopen = new double[numtrails];
		if(n <= 0 || numtrails <=0) throw new java.lang.IllegalArgumentException();
		for (int i = 0; i < trails; i++){
			Percolation perco = new Percolation(size);
			while(!perco.percolates()){
				perco.open(StdRandom.uniform(1, size+1), StdRandom.uniform(1,size+1));
			}
			numopen[i] = (double) perco.numberOfOpenSites()/(size * size);
		}
	}

	//sample mean of percolation threshold
	public double mean(){
		return (StdStats.mean(numopen));
	}

	//sample standard deviation of percolation threshold
	public double stddev(){
		return (StdStats.stddev(numopen));
	}

	//low endpoint of 95% confidence interval
	public double confidenceLo(){
		return (mean()-(1.96 * stddev())/Math.sqrt(numtrails));
	}

	//high endpoint of 95% confidence interval
	public double confidenceHi(){
		return (mean()+(1.96 * stddev())/Math.sqrt(numtrails));
	}

	//test client
	public static void main(String[] args){
		int size = Integer.parseInt(args[0]);
		int numtrails = Integer.parseInt(args[1]);
		PercolationStats perstate = new PercolationStats(size, numtrails);
		System.out.println("mean                      " + perstate.mean());      
        System.out.println("stddev                    " + perstate.stddev());      
        System.out.print("95% confidence interval   ");
        System.out.println(perstate.confidenceLo() + " " + perstate.confidenceHi());
	}

}