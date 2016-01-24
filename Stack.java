/*
* program to show basic implementation of stack 
* 3 kinds of implementation are present :
* 1. Using dynamic arrays 
* 2. Using Linked lists
* 3. Using queues
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;

class StackException extends Exception {
	private static final long serialVersionUID = -3167800557469016257L;
	public StackException() {}
	public StackException(String message) { super(message);}
	
}
class Node<T> {
	private Node<T> next;
	private T data;
	boolean bIsHeadEmpty;
	Node() {
		this.next = null;
		this.data = null;
	}
	Node(T data) {
		this.next = null;
		this.data = data;
	}
	Node(T data,Node<T> next) {
		this.next = next;
		this.data = data;
	}	
	public T getData() {
		return this.data;
	}
	public void setData(T data) {
		this.data = data;
	}	
	public Node<T> getNext() {
		return this.next;
	}
	public void setNext(Node<T> next) {
		this.next = next;
	}
}

class StackUsingArrays {
	private int arr[];
	private int top;
	StackUsingArrays(int capacity) {
		
		arr = new int[capacity];
		top = -1;
	}
	public boolean push(int var) throws StackException {
		if ( top >= arr.length-1) {
			throw new StackException("Stack is full !");
			//return false;
		} 
		top++;
		arr[top] = var;
		//top++;
		return true;
	}
	public int pop() throws StackException {
		if ( top == -1) {
			throw new StackException("Stack is empty!");
			//return -1;
		}
		int temp = top;
		top--;
		return arr[temp];
	}
	public boolean isEmpty() {
		return (top == -1) ? true : false;
	}
	public boolean isFull() {
		return (top == arr.length-1) ? true : false;
	}
	public String print() throws StackException{
		StringBuilder sResult = new StringBuilder();
		if (top == -1) {
			throw new StackException("Empty stack");
		}			
		for(int i=top; i >= 0; i--) {
			sResult = sResult.append("[" + i + ":" + arr[i] +  "]") ;
		}
		return sResult.toString();
	}
	public int getStackSize() {
		return top + 1;
	}
	public int peek() {
		if (top > -1)
			return arr[top];
		else
			return -1;
	}
	public int capacity() {
		return arr.length;
	}	
	public void clear() {
		top = -1; //using int array as data structure, we cannot actually "remove" any data,
		//hence we simply reset top to -1, so that any future access is not valid anymore
		
	}
}


class StackUsingLinkedList<T>{
	protected Node<T> top;
	protected int size = 0;
	
	StackUsingLinkedList() {
		//nothing to do
	}
	public void push(T data) throws StackException{
		if ( data == null)
			throw new StackException("Cannot push NULL values");
		Node<T> temp = new Node<T>(data,top);
		size++;
		top = temp;
	}
	public Node<T> pop() throws StackException{
		if ( top == null)
			throw new StackException("Stack is empty");
		Node<T> temp = top;
		top = top.getNext();
		temp.setNext(null);
		size--;
		return temp;
	}
	public boolean isEmpty() {
		return (top == null) ? true : false;
	}
	public int getStackSize() {
		return size;
	}
	public String print() throws StackException{
		if ( top == null)
			throw new StackException("Stack is empty ! Cannot print...");
		StringBuilder sb = new StringBuilder();
		Node<T> temp = top;
		while ( temp.getNext() != null) {
			sb = sb.append(String.valueOf(temp.getData()) + ":");
			temp = temp.getNext();
		}
		sb = sb.append(String.valueOf(temp.getData()));
		return sb.toString();
	}

}

/**
* NOTE : This is not  a practical implementation, only an academic exercise.
* there can be 2 ways to implement stacks using 2 queues :
* a. Making pop costly, push in O(1)
	 Push all items to Q1. This is O(1)
	 For pop, we remove all items from Q1 and move it to Q2, except for last item. Pop last item from Q1
	 This is O(n) operation. Swap names from Q1 and Q2
	 
* b. Making push costly, pop in O(1)
	 Pop item from Q1. This is O(1) time
	 For push, we push item in Q2. Pop all items from Q2 and move them to Q1. This way last element inserted in Q2 comes in front of Q1. 
	 This is O(n) operation. Swap names from Q1 and Q2
*/
// Following strategy "a"
class StackUsingQueues<Item> {
	Queue<Item> q1 , q2;
	private int size;
	StackUsingQueues() {
		q1 = new LinkedList<>();
		q2 = new LinkedList<>();
	}	
	public boolean  push(Item item) {
		if ( q1== null)
			return false;
		size++;
		return q1.offer(item);
	}
	public Item pop() {
		while(q1.size() > 1) { //poll everything except the last element
			q2.offer(q1.poll());
		}
		Item data = q1.poll();
		Queue<Item> qTemp = q1; //swap Q2 and Q1
		q1 = q2;
		q2 = qTemp;
		size--;
		return data;
	}
	public boolean isEmpty() {
		return (size ==0);// all elements in q2 plus head of q1
	}
	public int getSize() {
		return size;
	}
	public String print() {
		StringBuilder sb = new StringBuilder();
		for(Item item:q1) 
			sb = sb.append("[ " + item + " ]");
		return sb.toString();
		
	}
}

class Stack {
	
	public static void main(String[] args) {
		int elem = 0;
		String[] elements = {""};
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the element to add to linked list in CSV format  :");
		try {
			
			elements = br.readLine().split(",");
			//System.out.println(elements.length);
			if (elements.length == 0) {
				System.out.println("No elements given ! Exiting...");
				return;
			}
			StackUsingQueues<Integer> stack = new StackUsingQueues<>();
			for ( String str:elements ) {
				str = str.trim();
				try	{
					stack.push(new Integer(Integer.parseInt(str)));
				}
				catch(Exception ex) { // invalid characters are ignored
					ex.printStackTrace();
				}	

			}
			System.out.println("POP :" + stack.pop());
			System.out.println("Elements :" + stack.print());
			System.out.println("POP :" + stack.pop());
			System.out.println("Elements :" + stack.print());

			/*StackUsingArrays stack = new StackUsingArrays(elements.length);
			for ( String str:elements ) {
				str = str.trim();
				try	{
					stack.push(new Integer(Integer.parseInt(str)));
//					System.out.println(stack.toString());
					
				}
				catch(Exception ex) { // invalid characters are ignored
					ex.printStackTrace();
				}	

			}
			System.out.println("Using implementation of arrays...");
			System.out.println("stack size is " +stack.getStackSize());
			System.out.println("Top is " + stack.pop());
			stack.clear();
			System.out.println(stack.print());
			*/
			/*StackUsingLinkedList<Integer> stack_ = new StackUsingLinkedList<>();
			for ( String str:elements ) {
				str = str.trim();
				try	{
					stack_.push(new Integer(Integer.parseInt(str)));
//					System.out.println(stack.toString());
					
				}
				catch(Exception ex) { // invalid characters are ignored
					ex.printStackTrace();
				}	

			}
			System.out.println("Using implementation of linkedlist...");
			System.out.println("stack size is " +stack_.getStackSize());
			System.out.println("Top element is :" +String.valueOf(stack_.pop().getData()));
			/*System.out.println("Top is " + stack.pop());
			stack.clear();
			System.out.println(stack_.print());*/
			
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