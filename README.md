# DataStructures
Contains native implementation of common data structures

Implemented so far :

1. Singly linked list with following operations supported

Functionalities in program
1. Get Size
2. Get head element
3. Get tail element
4. Find Kth element from head
5. Find Kth element from tail
6. Remove head element
7. Remove tail element
8. Remove by index
9. Search element by value
10. Find if sequence is bitonic

2. Doubly linked list with all the operations supported in (1) plus printing list in reverse 

3. Stack using 3 different implementations : a) Arrays b) Linked lists c) Queues

4. Stack for generic data type with custom iterator added

5. StringBuilder implementation using a char[] array ( as is used in Java )

6. Priority Queue ( Min and Max) with following operations supported :
	For Max priority queue
	a. ExtractMax
	b. IncreaseKey(x,k) where k>= x
	c. Insert(x)
	For min priority queue
	a. ExtractMin
	b. DecreaseKey(x,k) where k<= x
	c. Insert(x)
	Priority queues are heaps, hence the program will follow the convention heap for ease.

7. Queue implementation using both arrays and linked lists

8. Implemented Blocking Semaphore, Counting semaphore, and signalling semaphore using primitve objects and wait(), notify() mechanism.
	Inspired by : http://tutorials.jenkov.com/java-concurrency/semaphores.html