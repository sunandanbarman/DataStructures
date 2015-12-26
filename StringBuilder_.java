import java.util.Arrays;
/**
* Present since JDK 5, essentially a variant of StringBuffer , but performs no synchronization, hence it performs faster
*/
class StringBuilderImpl {
	private char array[];
	int count;
	StringBuilderImpl() {
		array = new char[16];
		count = 0;
	}
	StringBuilderImpl(int initialCapacity) {
		array = new char[initialCapacity];
		count= 0;
	}
	/**
	* returns the string representation of the array
	*/
	public String toString() {
		return new String(array);
	}
	public void expandCapacity(int minimumCapacity) {
		int newLength = ( array.length + 1 ) * 2;
		if ( newLength < 0 ) {
			newLength = Integer.MAX_VALUE;
		}
		else if ( newLength < minimumCapacity) {
			newLength = minimumCapacity;
		}
		array = Arrays.copyOf(array,newLength);
	} 
	/**
	* Appends the string to the end of the StringBuilderImpl; expanding the capacity if required
	*/
	public StringBuilderImpl append(String str) {
		if ( str == null) { str = "null";}
		int len = str.length();
		if ( len == 0 ) { return this ;} // nothing to do
		int newCount = count + len;
		if ( newCount > array.length) {
			//System.out.println("Capacity to be increased !");
			expandCapacity(newCount);
		} 
		//count is okay, simply add the string
		str.getChars(0,str.length(),array,count); //copy the entire string "str" to end of stringBuilder
		count = newCount;	
		return this;
	}
	
}


class StringBuilder_ {
	
	public static void main(String[] args ) {
		StringBuilderImpl sBuilder = new StringBuilderImpl(5);
		String[] words = {"abcd","defg","1"};
		for (String s:words) {
			sBuilder = sBuilder.append(s);
		}
		System.out.println("Printed output :" + sBuilder.toString());
	}
}