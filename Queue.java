import java.io.IOException;
import java.util.Iterator;

//FIFO principle
//enqueue to last, and dequeue from front
class QueueLinkedList<E> implements Iterable<E>{
	Node front = null , last = null;
	int size = 0;
	public void enqueue(E data) {
		Node temp = new Node(data);
		if (last == null) {
			front = temp;
			last  = temp;
			
		} else {
			last.next = temp;
			last = temp;
		}
		size++;
	}
	public E dequeue() throws Exception{
		if (front == null)
			throw new Exception("Queue is empty !");
		E nResult = front.data;
		front = front.next;
		size--;
		return nResult;
		
	}
	public boolean isEmpty() {
		return (front  == null) ;
	}
	public int size() {
		return size;	
	}
	private class Node {
		E data;
		Node next;
		Node(E data) {
			this.data = data;
			this.next = null;
			
		}
		
	}
	@Override
	public Iterator<E> iterator() {
		return new LinkedQueueIterator();
	}
	private class LinkedQueueIterator implements Iterator<E>{
		private int size_ = size;
		private Node head = front;
		@Override
		public boolean hasNext() {
			return (size > 0);
		}
		@Override
		public E next() {
			E temp;
			temp = head.data;
			head = head.next;
			size--;
			return temp;
		}
	}
}
class Queue {	
	public static void main(String []args) throws Exception{
		QueueLinkedList<String> queue = new QueueLinkedList<>();
		queue.enqueue("10");
		queue.enqueue("20");
		for(String s:queue) {
			System.out.println(s);
		}
		//System.out.println(queue.dequeue());

	}
	
	
}