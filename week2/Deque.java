import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>{
	private int n;
	private Node first;
	private Node last;

	private class Node{
		private Item item;
		private Node next;
		private Node previous;
	}

	//Initialize an empty Deque()
	public Deque(){
		first = null;
		last = null;
		n = 0;
	}

	//is the deque empty?
	public boolean isEmpty(){
		return n==0;
	}

	//return the number of items on the deque
	public int size(){
		return n;
	}

	//add the item to the front
	public void addFirst (Item item){
		if(item == null) throw new java.lang.NullPointerException();
		Node oldfirst = first;
		first = new Node();
        first.item = item;
        first.previous = null;
        if(n==0) {
        	first.next = null;
        	last = first;
        }
        else{
        	first.next = oldfirst;
        	oldfirst.previous = first;
        }
        n++;
	}

	//add the item to the end
	public void addLast(Item item){
		if(item == null) throw new java.lang.NullPointerException();
		if(n==0){
			addFirst(item);
			return;
		}
		else {
			Node oldlast = last;
			last = new Node();
			last.item = item;
			last.next = null;
			last.previous = oldlast;
			oldlast.next = last;
		}
		n++;

	}

	//remove and return the item from the front
	public Item removeFirst(){
		if(isEmpty()) throw new java.util.NoSuchElementException();
		Item item = first.item;
		first = first.next;
		n--;
		return item;
	}

	//remove and return the item from the end
	public Item removeLast(){
		if(isEmpty()) throw new java.util.NoSuchElementException();
		Item item = last.item;
		last = last.previous;
		n--;
		if(n == 0){
			first = null;
			last = null;
		}
		else{
			last.next = null;
		}
		return item;
	}

	 // return an iterator over items in order from front to end
	public Iterator<Item> iterator(){ return new ListIterator();}

	private class ListIterator implements Iterator<Item>{
		private Node current = first;

		public boolean hasNext(){
			return(current != null);
		}

		public void remove(){
			throw new java.lang.UnsupportedOperationException();
		}

		public Item next(){
			if(current == null) throw new java.util.NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

	// unit testing (optional)
	public static void main(String[] args){
		Deque<String> stacktest = new Deque<String>();
		
		stacktest.addFirst("a");
        stacktest.addLast("b");
        stacktest.addLast("c");
        stacktest.addFirst("d");
        stacktest.removeLast();

		Iterator<String> iter = stacktest.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}

		for (String s : stacktest) {
			System.out.println(s);
		}
		

	}

}