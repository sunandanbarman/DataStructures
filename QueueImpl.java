import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Iterable;
import java.util.Iterator;

class QueueException extends Exception {
	private static final long serialVersionUID = -4222050880920323410L;
	QueueException() {}
	QueueException(String message) { super(message);}
}
interface Queue<Item> extends Iterable<Item>{
	public boolean isEmpty();
	public int size();
	public boolean contains(Item o);
	public void add(Item item);
	public Item remove();
	public Item poll(); //retrieve and removes head	
	public Item peek(); //only returns head, does not remove it

}


class LinkedQueue<Item> implements Queue<Item> {
	private Node head = null,last =null;
	private int size  = 0;
	private class Node {
		Item item;
		Node next;
		Node() {}
		Node(Item item) {
			this.item = item;
		}
	}
	public boolean isEmpty() {
		return (size > 0);
	}
	public int size() {
		return size;
	}
	public Item remove() { //remove from head
		if ( head == null)
			return null;
		size--;
		Node temp = head;
		head = head.next;
		return temp.item;
	}
	public boolean contains(Item o) {
		boolean bContains = false;
		Node temp = head;
		while ( temp != null ) {
			if ( temp.item.equals(o))
			{
				bContains = true;
				break;
			}
			temp = temp.next;
		}
		return bContains;
	}
	
	public void add(Item item) { //add to last
		Node temp = new Node(item);
		if (head ==null) {
			head = temp;
			size++;
			last = temp;
		} else {
			last.next = temp;
			last = temp;
			size++;
		}
	}
	
	public Item poll() {
		if ( head == null) 
			return null;
		Node temp = head;
		head = head.next;
		size--;
		return temp.item;
	}
	
	public Item peek() {
		if ( head == null)
			return null;
		return head.item;
	}
	@Override
	public Iterator<Item> iterator() {
		return new LinkedQueueIterator();
	}
	
	private class LinkedQueueIterator implements Iterator<Item> {
		private Node first = head;
		private int length  =size;
		@Override
		public boolean hasNext() {
			return (length > 0);
		}
		@Override
		public Item next() {
			Node temp = first;
			first = first.next;
			length--;
			return temp.item;
		}
	}
}

class QueueWithArrays {
	public String arr[];
	public int last,top = 0;
	QueueWithArrays() {
		arr  = new String[16];
		//size = 0;
		last = -1;
	}
	QueueWithArrays(int capacity) {
		//size = 0;
		arr  = new String[capacity];
		last = -1;
	}
	public void add(String data) throws QueueException{
		if ( last == arr.length-1) 
			throw new QueueException("Queue is full !");
		last++;
		arr[last] =  data;
		
	}
	private void shiftData() {
		
		for(int i = top ; i < last; i++) {
			arr[i] = arr[i+1];
			
		}
	}
	public String remove() throws QueueException {
		if ( last == -1)
			throw new QueueException("Queue is empty !");
		String data = arr[top];
		shiftData(); // overwrite arr[top] location
		last--;
		return data;		
		
	}
	public String poll() throws QueueException{
		return remove();
	}
	public String peek() throws QueueException{
		if ( last == -1)
			throw new QueueException("Queue is empty ! Cannot peek..");
		return arr[top];
	}
	public boolean isEmpty() {
		return (last >= 0);
	}
	public String print() throws QueueException{
		if ( last == -1)
			throw new QueueException("Queue is empty !! Cannot print");
		StringBuilder sb = new StringBuilder();
		System.out.println(top + " " + last);
		for(int i=top; i<=last;i++) {
			sb = sb.append("[ "+ arr[i] + " ]");
		}
		return sb.toString();
	}
}
class QueueImpl {
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
			Queue<String> queue = new LinkedQueue<>();
			QueueWithArrays queueArr = new QueueWithArrays(elements.length);
			for ( String str:elements ) {
				str = str.trim();
				try	{
					queue.add(str);
					queueArr.add(str);
				}
				catch(Exception ex) { // invalid characters are ignored
					ex.printStackTrace();
				}	

			}
			System.out.println("Elements in the queue are :");
			for(String str:queue) {
				System.out.println(str);
			}
			System.out.println("*******Queue using linked list implementation**********");
			System.out.println("Queue size :"  + queue.size());
			System.out.println("Queue contains element 1 (true/false ):" + queue.contains("1"));
			System.out.println("Head element is :" + queue.peek());
			System.out.println("Head element removed :" + queue.poll());
			System.out.println("Remove element :" + queue.remove());

			System.out.println("Elements in the queue now are :");
			for(String str:queue) {
				System.out.println(str);
			}

			//Queue using arrays
			System.out.println("*******Queue using arrays implementation**********");
			System.out.println("queue head " + queueArr.peek());
			System.out.println("remove head element: "  + queueArr.poll());
			System.out.println("Queue print :" + queueArr.print());
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