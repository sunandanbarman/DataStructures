/*
* program to show basic implementation of singly linked list
*/
/*
* the class Node will have basic structure of a node in the linkedlist
*/
import java.io.*;

/*Functionalities in program
* 1. Get Size
* 2. Get head element
* 3. Get tail element
* 4. Find Kth element from head
* 5. Find Kth element from tail
* 6. Remove head element
* 7. Remove tail element
* 8. Remove by index
* 9. Search element by value
*/
class LinkedList extends Node {
	private Node head;
	private boolean bHeadIsEmpty;
	private int listCount;
	/**
	*@param : void
	*constructor for the LinkedList
	*/	
	LinkedList() {
		head = new Node();
		listCount = 0;
		bHeadIsEmpty = true;
	}	
	/**
	*@param : element
	*return : true/false if element insertion is done okay
	*/	
	public boolean add(int element ) {
		if ( bHeadIsEmpty ) {
			head.setData(element);
			bHeadIsEmpty = false;
			listCount++;
			return true;
		}	
		Node newPointer = new Node(element);
		Node headTemp   = head;
		while(headTemp.getNext()!= null ) {
			headTemp = headTemp.getNext();
		}	
		headTemp.setNext(newPointer);
		listCount++;
		return true;
	}
	/**
	*@param : void
	*return : returns the linked list in format [index:value]->[<index>:<value>][<index>:<value>]
	*/	
	public String printLinkedList() {
		StringBuilder result = new StringBuilder( "[index:value]--> " );
		System.out.println("Inside");
		if ( listCount == 0 ) 
		{
			result = result.append(" [null]");	
			return result.toString();
		}
		
		Node tempPtr = head;
		int counter = 0;
		result = result.append("[0:" + tempPtr.getData() + "]");
		while(tempPtr.getNext() != null ) {
			tempPtr = tempPtr.getNext();
			counter++;
			result = result.append("[" + String.valueOf(counter) + ":"
									+ String.valueOf(tempPtr.data) + "]");
		}
		return result.toString();	
	}
	/**
	*@param : void
	*return : returns the current size of the linked list
	*/	
	public int size() {
		return listCount;
	}
	/**
	*@param : index
	*return : returns the value at the specified index
	*/	
	public int get(int index) {
		if ( index >= listCount ) { // index of linked list starts from 0
			return -1;
		}	
		Node tempPtr = head;
		int counter  = 0;
		while ( counter < index ) {
			tempPtr = tempPtr.getNext();
			counter++;
		}	
		return tempPtr.getData();
	}
	/**
	*@param : index
	*return : true/false, if element remove is successful
	*/	
	public boolean remove(int index) {
		int counter =0;
		if ( index >= listCount) {
			return false;
		}	
		if ( index == 0) {
			head = head.getNext();
			listCount--;
			return true;
		}	
		Node tempPtr = head;
		
		while ( counter < index-1) {
			tempPtr= tempPtr.getNext();
			counter++;
		}	
		tempPtr.setNext(tempPtr.getNext().getNext());
		listCount--;
		return true;
	}
	/**
	*@param : void
	*return : returns the head element of the linked list
	*/	
	public int getFirst() {
		return head.getData();
	}	
	/**
	*@param : void
	*return : returns the current size of the linked list
	*/	
	public int getLast() {
		return get(listCount-1);
	}
	/**
	*@param : void
	*return : returns the current size of the linked list
	*/
	public boolean removeFirst() {
		return remove(0);
	}
	/**
	*@param : the value to search in the linked list
	*returns the index at which element is found, returns -1 if not found
	*/
	public int searchByValue(int value) {
		Node tempPtr = head;
		int counter  = 0;
		boolean bValueFound = false;
		while ( counter < listCount) {
			if ( tempPtr.getData() == value) {
				bValueFound = true;
				break;
			}
			else {
				tempPtr = tempPtr.getNext();
			}	
		}	
		if ( !bValueFound) 
			counter = -1;
		return counter;
	}	
	/**
	*@param : void
	*return : returns the current size of the linked list
	*/	
	public boolean removeLast() {
		return remove(listCount-1);
	}
		
	//Class Node representing one node of the linked list
	private class Node {
		Node next;
		Integer data;
	   //Only the "data" object is passed, when we want to create a node that we don't want to point to anything ( just yet )
	   // E.g. the head node
	   
		Node() {
			next = null;
			data = null;
		}	
		Node(Integer data) {
			next = null;
			this.data = data;	
		}
		public Integer getData() {
			return this.data;
		}	
		public void setData(Integer data) {
			this.data = data;
		}	
		public Node getNext() {
			return this.next;
		}	
		public void setNext(Node next) {
			this.next = next;
		}		
	}		
}	

public class LinkedListProgram {
	/**
	* 
	*/
	public static void executeOperations(LinkedList list,BufferedReader br) {
		char ch = 'n';
		int choice;
		int K;
		do {
			System.out.println("Available options :\n"
								+ "1. Get Size\n"
								+ "2. Get head element\n"
								+ "3. Get tail element\n"
								+ "4. Find Kth element from head\n"
								+ "5. Find Kth element from tail\n"
								+ "6. Remove head element\n"
								+ "7. Remove tail element\n"
								+ "8. Remove by index\n"
								+ "9. Search element by value\n");								
			System.out.println("Enter your choice :");
			try {
				choice = Integer.parseInt(br.readLine());	
				switch(choice) {
					case 1:
						System.out.println("Size of LinkedList is :" + list.size());
						break;
					case 2:
						System.out.println("Head element of LinkedList is " + list.getFirst());
						break;
					case 3:
						System.out.println("Tail element of LinkedList is " + list.getLast());			
						break;
					case 4:
						System.out.println("Enter the index to display from head:");
						K = Integer.parseInt(br.readLine());
						System.out.println(K + " element is :" + list.get(K));
						break;
					case 5:
						System.out.println("Enter the index to display( from the end ) :");
						K = Integer.parseInt(br.readLine());
						System.out.println(K + " element is :" + list.get(list.size()-K));
						break;
					case 6:
						System.out.println("Removing head element succeeded :"+ Boolean.toString(list.removeFirst()));						
						break;
					case 7:
						System.out.println("Removing tail element succeeded :"+ Boolean.toString(list.removeLast()));					
						break;
					case 8:
						System.out.println("Enter the index to be removed:");
						K = Integer.parseInt(br.readLine());						
						System.out.println("Removing " + K + " element succeeded :"+ Boolean.toString(list.remove(K)));					
						break;
					case 9:
						System.out.println("Enter the element to search for :");
						K = Integer.parseInt(br.readLine());
						int index = list.searchByValue(K);
						if ( index != -1 ) 
							System.out.println("Element found at index :"+ index);
						else
							System.out.println("Element not found !");
						break;
					default:
					{
						System.out.println("Please enter valid choice!");
						ch = 'y';
					}	
						
						
						
				}	
			}
			catch(Exception ex) {
				System.out.println("Please enter valid number !");
				ch = 'y';
			}
		} while( ch == 'y' || ch == 'Y');
		
	}	
	public static void main(String[] args) {
		int elem = 0;
		String[] elements = {""};
		LinkedList list = new LinkedList();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the element to add to linked list in CSV format  :");
		try {
			
			elements = br.readLine().split(",");
			for ( String str:elements ) {
				str = str.trim();
				try	{
					list.add(new Integer(Integer.parseInt(str)));
				}
				catch(Exception ex) { // invalid characters are ignored
				}	
			}
			LinkedListProgram.executeOperations(list,br);
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