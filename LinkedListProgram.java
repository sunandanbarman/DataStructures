/*
* program to show basic implementation of singly linked list
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
*/
//Class Node representing one node of the linked list
class Node {
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
			if ( value == tempPtr.getData()) {
				bValueFound = true;
				break;
			}
			else {
				tempPtr = tempPtr.getNext();
				counter++;
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

	/**
	*
	*/
	public boolean findIfSequenceIsBitonic() {
		if ( listCount <= 0 ) {
			System.out.println("List is empty !");
			return false;
		}	
		Node temp = head;
		int item1   = temp.getData();
		int nIsInc = 0, nIsDec = 0, nLow, max, low; //keep count of how many times sequence was increasing/decreasing
		int val;
		max = item1;
		low = item1;
		boolean bIsInc = false, bIsDec = false;
		while ( temp.getNext() != null ) { 
				temp = temp.getNext();
				
				val  = temp.getData();
				if ( max < val ) { //keep track of largest element
					max = val;
				}
				if ( low > val ) { //keep track of lowest element
					low = val;
				}
				if ( item1 < val ) {  //increasing sequence
					if (!bIsInc && bIsDec) { // if there occurs another increasing sequence after a decreasing one
						bIsInc = true;
						bIsDec = false;
						nIsInc++;
					}
					if ( nIsInc == 0 ) { //initial condition, if sequence starts in increasing manner
						nIsInc++;
						bIsInc = true;
					}
					item1 = temp.getData();

					
				}
				else { //non-increasing sequence
					if (bIsInc && !bIsDec) { // if there occurs another decreasing sequence after a increasing one
						bIsInc = false;
						bIsDec = true;
						nIsDec++;
					}
					if ( nIsDec == 0 ) { //initial condition, if sequence starts in decreasing manner
						nIsDec++;
						bIsDec = true;
					}
					item1 = temp.getData();
				}
		}
		if ( (nIsInc == 1) && (nIsDec == 1) ) {
			System.out.println("Sequence is bitonic\n"+"Sequence max is :" + max + "\nLowest is " + low);
			return true;
		}	
		else {
			System.out.println("Sequence is not Bitonic. Increasing sequence and decreasing sequence count are ->");
			System.out.println(nIsInc + ":" + nIsDec);
			
			return false;	
		}	
	}	
}	

public class LinkedListProgram {
	/**
	* 
	*/
	public static void executeOperations(LinkedList list,BufferedReader br) throws IOException{
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
								+ "10.Check if sequence is bitonic\n");								
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
						list.findIfSequenceIsBitonic();
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
			System.out.println("List elements");
			System.out.println(list.printLinkedList());
			System.out.println("Do you want to continue ? (y/n)");
			ch = br.readLine().charAt(0);
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
			if ( elements.length > 0) {
				LinkedListProgram.executeOperations(list,br);
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