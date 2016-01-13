class Stack {
	private int[] arr; //stores the date
	//To simulate the stacks operation, we use the top[] and next[] array to 
	//store the head of stacks, and next pointer.
	//e.g. next[i] acts as "next" pointer at index i
	//top[] will store the top indices of all the stacks
	//next[] will store the "next" pointer  
	private int[] top, next;
	private int free; //gives the next available free space
	private int sNum, capacity; //sNum : number of stacks
	Stack(int sNum,int capacity) {
		arr = new int[capacity];
		top = new int[sNum];
		next= new int[capacity];
		free= 0;
		for(int i=0; i < top.length; i++) {
			top[i] = -1; // all stacks initially empty
		}
		for(int i=0; i < next.length-1; i++) {
			next[i] = i+1; 
		}
		next[next.length-1] = -1;
		
	}
	public boolean isFull() {
		return(free == -1);
	}
	public boolean isEmpty(int sn) {
		return (top[sn] == -1);
	}
	public void push(int sn, int data) {
		if (isFull()) {
			return;
		}
		int i= free;
		free = next[i];
		next[i] = top[sn];
		top[sn] = i;
		arr[i]  = data;
		 
	}
	public int pop(int sn) throws Exception {
		if (isEmpty(sn)) {
			throw new Exception("Stack underflow !");
			
		}
		int i = top[sn];
		top[sn] = next[i];
		next[i] = free;
		free = i;
		return arr[i];
	}
}
class KStacksInSingleArray {
	public static void main(String[] args) throws Exception{
		int K = 3; //we will implement 3 stack in one array, this scheme can be extended for K <= N
		int N = 10;
		Stack stack = new Stack(K,N);
		stack.push(0,12);
		stack.push(1,11);
		stack.push(0,13);
		
		System.out.println(stack.pop(1));
		System.out.println(stack.pop(0));
		System.out.println(stack.pop(0));
	}

}