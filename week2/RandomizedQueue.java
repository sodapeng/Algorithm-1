import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item>{
	private Item[] s;
	private int n;
	private int arraylen;

	// construct an empty randomized queue
	public RandomizedQueue(){
		n = 0;
		arraylen = 1;
		s = (Item[]) new Object[arraylen];
	}

	// is the queue empty?
	public boolean isEmpty(){
		return n ==0;
	}

	//return the number of items on the queue
	public int size(){
		return n;
	}

	//resize function
	private void resize(int newarraylen){
		Item[] news = (Item[]) new Object[newarraylen];
		for (int i = 0; i < n; i++){
			news[i] = s[i];
		}
		s = news;
		arraylen = newarraylen;

	}

	private int testlen(){
		return arraylen;
	}

	//add the item
	public void enqueue(Item item){ //?
		if(item == null) throw new java.lang.NullPointerException();
		if(n >= arraylen){
			resize(2 * arraylen);
		}
		s[n++] = item;
	}

	//remove and return a random item 
	public Item dequeue(){
		if(isEmpty()) throw new java.util.NoSuchElementException();
		int index = StdRandom.uniform(0,n);
		if(n > 0 && n == s.length / 4) {
			resize(arraylen/2);
		}
		Item item = s[index];
		s[index] = s[n-1];
		s[n-1] = null;
		n--;
		return item;
	}

	//return (but do not remove) a random item
	public Item sample(){
		if(isEmpty()) throw new java.util.NoSuchElementException();
		int index = StdRandom.uniform(0,n);
		return s[index];
	}

	// return an independent iterator over items in random order
	public Iterator<Item> iterator(){return new RandomListIterator();}

	private class RandomListIterator implements Iterator<Item>{
		private Item[] srandom; //same data type with 
		private int i=0;

		public RandomListIterator(){
			srandom = (Item[]) new Object[n];
			for (int j = 0; j< n; j++){
				srandom[j] = s[j];
			}
			StdRandom.shuffle(srandom);
		}

		public boolean hasNext(){
			return i < n;
		}

		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}

		public Item next(){
			if(i > n) throw new java.util.NoSuchElementException();
			return srandom[i++];
		}
	}

	//unit testing
	public static void main(String[] args){
		RandomizedQueue<String> test = new RandomizedQueue<String>();
		test.enqueue("apple");
		Iterator<String> itr = test.iterator();
		System.out.println(itr.next());
		test.enqueue("banana");
		
		test.enqueue("orange");
		test.enqueue("blueburry");
		test.enqueue("a1");
		test.enqueue("b1");
		test.enqueue("c1");

		test.enqueue("d1");
		Iterator<String> itr1 = test.iterator();
		System.out.println(itr1.next());
		

	}
}