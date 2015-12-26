import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Iterable;
import java.util.Iterator;

class StackExceptionNew extends Exception {
	private static final long serialVersionUID = 8762496307538654501L;
	public StackExceptionNew() {}
	public StackExceptionNew(String message) { super(message);}
	
}

interface Stack <Item> extends Iterable<Item> {
	public void push(Item item);
	public Item pop();
	public boolean isEmpty();
	
}

class LinkedStack<Item> implements Stack <Item> {
	private class Node {
		Item item;
		Node next;	
	}
	private Node head;
	private int size ;
	public LinkedStack() {
		head = null;
		size = 0;
	}
	public void push(Item item) {
		Node node = new Node();
		node.item = item;
		node.next = head;
		head = node;
		size++;
	}
	
	public Item pop(){
		if ( head == null)
			return null;
		Node temp = head;
		head = head.next;
		size--;	
		return temp.item;
	}
	public boolean isEmpty() {
		return (head == null);
	}
	
	@Override
	public Iterator<Item> iterator() {
		return new LinkedStackIterator();
	}
	private class LinkedStackIterator implements Iterator<Item> {
		private Node first = head;
		private int size_   = size;
		@Override
		public boolean hasNext() {
			//System.out.println("Hi3 size :" + size);
			return ( size_ > 0);
		}
		@Override
		public Item next() {
			Item item = first.item;
			first = first.next;
			size_--;
			return item;
		}
	}
}

class StackUsingInterface {
	
	public static void main(String[] args) {
		int elem = 0;
		String[] elements = {""};
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the element to add to linked list in CSV format  :");
		try {
			
			elements = br.readLine().split(",");
			if (elements.length == 0) {
				System.out.println("No elements given ! Exiting...");
				return;
			}	
			Stack<Integer> stack = new LinkedStack<>();
			for ( String str:elements ) {
				str = str.trim();
				try	{
					stack.push(new Integer(Integer.parseInt(str)));
				}
				catch(Exception ex) { // invalid characters are ignored
					ex.printStackTrace();
				}	

			}
			System.out.println("Elements of stack are :");
			for (Integer i :stack) {
				System.out.println(i);
			}
			System.out.println("Top element of stack :" + stack.pop());
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally 
		{
			try {	
				br.close();
			}
			catch(Exception ex2) {
			}		
		}		
	
	}
}