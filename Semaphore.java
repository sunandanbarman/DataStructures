import java.lang.Thread;

class Semaphore_ {
	private boolean signal = false;
	public synchronized void take() { //set signal to true
		this.signal = true;
		this.notify(); // 
	}
	
	public synchronized void release() throws InterruptedException {
		while(!signal) {
			wait();
		}
		this.signal = false;
	}
}


class CountingSemaphore {
	private int count =0 ;
	public synchronized void take() {
		this.count++;
		this.notify();
	}
	public synchronized void release() throws InterruptedException {
		while(count == 0)
		{
			wait();
		}
		this.notify();
	}
}

class BlockingSemaphore {
	private int upperBound = 0;
	private int count = 0;
	BlockingSemaphore(int upperBound) {
		this.upperBound = upperBound;
	}
	public synchronized void take() throws InterruptedException{
		while(count == upperBound) {
			wait(); //no more locks available, wait till one becomes available
		}
		this.count++;
		this.notify();
	}
	public synchronized void release() throws InterruptedException {
		while(count == 0)
			wait(); // no lock acquired, wait till one is acquired
		this.count--;
		this.notify();
	}
}

class CriticalCode {
	int count =0;
	BlockingSemaphore bSem = null;
	CriticalCode(BlockingSemaphore bSem) {
		this.bSem = bSem;
	}
	//Using blocking semaphore guarantees that only one thread can enter the critical section
	//at a time. Can be used to ensure thread synchronization.
	//Note: no guarantee of which thread will be given access first.
	public void display() {
		try {
			bSem.take();
				count++;
				System.out.println("count " + count + " by Thread " + Thread.currentThread().getName());
	
			bSem.release();
		} catch (InterruptedException ex) {}	
	}
}
/*class SendingThread extends Thread {
	Semaphore sem = null;
	SendingThread(Semaphore sem) {
		this.sem = sem;
	}
	@Override
	public void run() {
		System.out.println("Signalled !");
		this.sem.take() ;
	}
}

class ReceivingThread extends Thread {
	Semaphore sem = null;
	ReceivingThread(Semaphore sem) {
		this.sem = sem;
	}
	@Override
	public void run(){
		try {
			this.sem.release();
		} catch(InterruptedException ex) {
			
		}	
		System.out.println("ReceivingThread done !");
	}
}
*/
class Semaphore {
	
	public static void main(String[] args) {
		Thread[] tArr = new Thread[10];
		BlockingSemaphore bSem = new BlockingSemaphore(1);
		CriticalCode c= new CriticalCode(bSem);
		
		for(int i=0; i < tArr.length; i++) {
			tArr[i] = new Thread("Thread-" + i) {
				public void run() {
					c.display();
				}
			};
			tArr[i].start();	
		}
		
	}
}