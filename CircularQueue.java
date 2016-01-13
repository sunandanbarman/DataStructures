//It is enough to add a last.next = head to the implementation
//Also, while iterating take care not to go into endless loop ; break it off at the point
//last.next == head
class CircularQueueLinkedList {

}
//The trickier of the two approaches
//we will resize the queue when required
class Queue_Array {
	private final int defaultCapacity = 8;
	int size , front, rear;
	private int[] array;
	public Queue_Array(int capacity) {
		array = new int[capacity];
		front = 0; // no data yet in queue, hence -1
		rear  = 0;
		size  = 0;
	}
	public Queue_Array() { //no capacity given , use by default 8
		array = new int[defaultCapacity];
		front = 0;
		rear  = 0;
		size  = 0;
	}
	public int remove() {
		if (!isEmpty()) {
			int temp = array[front];
			front = (front + 1) % (array.length);
			size--;
			return temp;
		} else {
			System.out.println("Queue underflow !");
			return -1;
		}
	}
	public boolean add(int data) {
		if (!isFull()) {
			int temp = (rear + 1) % (array.length);
			size++;
			array[rear] = data;
			rear = temp;
			return true;
		} else {
			System.out.println("Queue overflow !");
			return false;
		}
	}
	public boolean isFull() {
		return (size == array.length);
	}
	public boolean isEmpty() {
		return (size == 0);
	}
	public void print() {
		
	}
}
class CircularQueue {
	public static void main(String[] args) {
		Queue_Array queue = new Queue_Array(2);
		queue.add(10);
		queue.add(20);
		//System.out.println(queue.add(30));
		try {
			queue.print();
			System.out.println(queue.remove());
			queue.add(30);
			System.out.println(queue.remove());
			System.out.println(queue.remove());
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	} 
	// queue.add(20);
	// System.out.println(queue.add(30));
}