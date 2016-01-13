class Stack {
	int top1, top2;
	private int[] arr;
	public Stack(int capacity) {
		arr = new int[capacity];
		top1= -1;
		top2= arr.length;
	}
	public void push1(int data) {
		if (top1 < top2 -1) {
			top1++;
			arr[top1] = data;
			
		} else {
			System.out.println("filled up");
		}
	
	}
	public void push2(int data) {
		if (top1 < top2-1) {
			top2--;
			arr[top2] = data;
			
		} else {
			System.out.println("filled up");
		}
	
	}
	public int pop1() {
		if (top1 == -1) {
			System.out.println("Underflow ");
			return -1;
		}	
		else {
			int temp = arr[top1];
			top1--;
			return temp;
		}	
	} 
	public int pop2() {
		if (top2 == arr.length) {
			System.out.println("Underflow ");
			return -1;
		}	
		else {
			int temp = arr[top2];
			top2++;
			return temp;
		}	
	} 
	public void print1() {
		for( int i=0; i <= top1; i++) {
			System.out.print(arr[i] + " : ");
		}
	}
	public void print2() {
		for( int i=arr.length-1; i >= top2; i--) {
			System.out.print(arr[i] + " : ");
		}
	
	}
	public void emptyStack1() {
		System.out.println("top1 :" + top1);
		int nTemp ;
		while(true) {
			nTemp = pop1();
			if (nTemp == -1)
				break;
			System.out.print(nTemp + " : ");
		}
		System.out.println("---------------");
	}
	public void emptyStack2() {
		for(int i= top2; i <= arr.length-1; i++) {
			System.out.print(pop2() + " : ");
		}	
	}	
}	
class TwoStacksInOneArray {
	public static void main(String[] args) {
		Stack stack = new Stack(5);
		stack.push1(5);
		stack.push2(10);
		stack.push2(15);
		stack.push2(7);
		stack.push1(11);
		
		stack.print1();
		System.out.println("***");
		stack.print2();
		System.out.println("***1.1***");
		
		stack.emptyStack1();
		stack.emptyStack2();
		System.out.println("***1.2***");

		stack.push1(25);
		stack.push2(30);
		System.out.println("***1.3***");

		stack.print1();
		stack.print2();
	}

}