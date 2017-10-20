import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation{
	public static void main(String[] args){
		int knum = Integer.parseInt(args[0]);
		String[] randstr = StdIn.readStrings();
		RandomizedQueue<String> test = new RandomizedQueue<String>();

		for (int i = 0; i < randstr.length; i++){
			test.enqueue(randstr[i]);
		}

		for (int i = 1; i <= knum; i++){
			System.out.println(test.dequeue());
		}
		
	}
}