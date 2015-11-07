/*
* program to show basic implementation of doubly linked list
*/
/*
* the class Node will have basic structure of a node in the linkedlist
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
* 10.Print lists in reverse
*/
/**
* class for one element of the linked list
*/
class Node {
	private int data;
	private Node prev, next;
	public Node() {
		//Node.prev = null;
		//Node.next = null;
	}
	/**
	* getter functions for the node 
	*/
	public Node getNext() {
		return this.next;
	}		
	public Node getPrev() {
		return this.prev;
	}		
	public int getData() {
		return this.data;
	}
	/**
	* setter functions for the node
	*/
	public void setNext(Node next) {
		this.next = next;
		
	}		
	public void setPrev(Node prev) {
		this.prev = prev;
	}		
	public void setData(int data) {
		this.data = data;
	}	
}	
/**
* class of the doubly linked list
* A doubly linked list contains a head pointer, and two links between each pair of nodes
* The "prev" and "next" in Node class denote the previous link and next link respectively.
*/
class DoublyLinkedList extends Node{
	private Node node, head;
	private boolean bHeadIsEmpty;
	int listSize;
	/**
	*@param : none
	*return : none
	*Use the default constructor to create empty list
	*/
	public DoublyLinkedList() {
		node = new Node();
		head = node;
		node.setPrev(null);
		node.setNext(null);
		bHeadIsEmpty = true;
		listSize = 0;
	}
	/**
	*@param : data to be added to head
	*return : none
	*Use the overloaded constructor form DoublyLinkedList(...) to create non-empty list
	*/	
	public DoublyLinkedList(int data) {
		head = new Node();
		head.setData(data);
		head.setNext(null);
		head.setPrev(null);			
		listSize ++;
		bHeadIsEmpty = false;
		
	}	
	/**
	*@param : value to be added to the list
	*return : none
	*/	
	public void insert(int data) {
		Node temp;
		node = head;
		if ( bHeadIsEmpty)  { 
			head.setData(data);
			bHeadIsEmpty = false;
			listSize++;
			return;
		}
		while ( node.getNext() != null) {
			node = node.getNext();	
		}	
		temp = new Node();
		temp.setData(data);
		node.setNext(temp);
		temp.setPrev(node);
		listSize++;
	}	
	/**
	*@param : none
	*return : the size of the list
	*/
	public int size() {
		return listSize;	
	}
	/**
	*@param : none
	*return : the first element in the list
	*/
	public int getFirst() {
		return get(0);
	}
	/**
	*@param : none
	*return : the last element in the list
	*/
	public int getLast() {
		return get(listSize-1);
	}	
	/**
	*@param : none
	*return : the Kth element in the list
	*/
	public int get(int K) {
		Node temp = head;
		int i     = 0 ;
		if (( K >= listSize) || (K < 0)) { 
			System.out.println("Index size exceeded ! List size is " + listSize);
			return -1;
		}	
		while ( i < K) {
			temp = temp.getNext();
			i++;
		}	
		return temp.getData();
	}		
	/**
	*@param : none
	*return : true if element is successfully removed
	*/
	public boolean removeFirst() {
		return remove(0);
	}	

	/**
	*@param : none
	*return : true if element is successfully removed
	*/
	public boolean remove(int K) {
		if (( listSize == 0)  || ( K  < 0 ) || (K > listSize))
			return false;
		int i = 0;
		Node temp = head;
		if ( K == 0) {
			if ( listSize == 1) { 
				listSize--;
				head.setPrev(null);
				head.setNext(null);
				head = null;
				return true;
			}	
			temp = temp.getNext();
			head.setNext(null);
			temp.setPrev(null);
			head = temp;
			listSize--;
			return true;
		}	
		while ( i < K-1) { 
			temp = temp.getNext();
			i++;
		}
		temp.getNext().setPrev(null);
		temp.setNext(temp.getNext().getNext());

		listSize--;	
		return true;
	}	
	/**
	*@param : none
	*return : true if element is successfully removed
	*/
	public boolean removeLast() {
		return remove(listSize-1);
	}	
	/**
	*@param : the value look for in the linked list
	*return : index of element ( if found ), else return -1
	*/
	public int searchByValue(int value) {
		Node temp = head;
		int i = 0;
		boolean bElemFound = false;
		while ( i < listSize) {
			if ( value == temp.getData()) {
				bElemFound = true;	
				break;
			}
			i++;
			temp = temp.getNext();
		}	
		if ( bElemFound)
			return i;
		else 
			return -1;
		//return (( bElemFound)?i : -1);
	}
	/**
	*@param : none
	*return : prints the linked list 
	*/
	public void printLinkedList() {
		Node temp = head;
		int i = 0;
		System.out.println("The elements in order are :");
		while ( i < listSize ) {
			System.out.print("[" + i + ":" + temp.getData() + "]-->"); 
			temp = temp.getNext();
			i++;
		}	
	}	
	/**
	*@param : none
	*return : prints the linked list in reverse
	*good way to demonstrate use of "prev" link in "node"
	*/
	public void printLinkedListInReverse() {
		Node temp = head;
		int i = 0;
		System.out.println("The elements in reverse are :");
		while ( i < listSize-1 ) {
			temp = temp.getNext();
			i++;
		}	
		while ( i >=0) { 
			System.out.print("[" + i + ":" + temp.getData() + "]-->"); 
			temp = temp.getPrev();
			i--;
		}	
	}
}	

public class DoublyLinkedListProgram {
	/**
	* 
	*/
	public static void executeOperations(DoublyLinkedList list,BufferedReader br) throws IOException{
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
								+ "9. Search element by value\n"
								+ "10.Print in reverse order\n");
								
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
						System.out.println(K + " element is :" + list.get(K-1));
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
					case 10:
						list.printLinkedListInReverse();
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
			if ( list.size() <= 0)
				break;
			System.out.println("List elements");
			list.printLinkedList();
			System.out.println("Do you want to continue ? (y/n)");
			ch = br.readLine().charAt(0);
		} while( ch == 'y' || ch == 'Y');
		
	}
	/**
	*
	*/	
	public static void main(String[] args) {
		int elem = 0;
		String[] elements = {""};
		DoublyLinkedList list = new DoublyLinkedList();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the element to add to linked list in CSV format  :");
		try {
			
			elements = br.readLine().split(",");
			for ( String str:elements ) {
				str = str.trim();
				try	{
					list.insert(new Integer(Integer.parseInt(str)));
					
				}
				catch(Exception ex) { // invalid characters are ignored
				}	
			}
			if ( elements.length > 0) {
				DoublyLinkedListProgram.executeOperations(list,br);
			}
			else {
				System.out.println("No elements present in list ! Exiting...");
			}		
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
