/**
 * This class represents the stack ADT implemented using a doubly linked list
 * 
 * @author Kevin Chau 251215166
 *
 * 
 */
public class DLStack<T> implements DLStackADT<T> {

	DoubleLinkedNode<T> top;
	int numItems;

	/**
	 * Constructor that creates an empty stack
	 */
	public DLStack() {
		top = null;
		numItems = 0;
	}

	@Override
	/**
	 * This method adds the given dataItem to the top of the stack
	 * 
	 * @param T element
	 */
	public void push(T element) {
		// TODO Auto-generated method stub
		DoubleLinkedNode<T> curr = new DoubleLinkedNode<T>(element); // creates new node
		if (top != null) { // check to see if top is not equal to null
			curr.setPrevious(top); // points previous to top
			curr.getPrevious().setNext(curr); // points currents previous node to current
		}
		numItems++;
		top = curr; // sets top equal to new node

	}

	@Override
	/**
	 * This method removes and returns the data item at the top of the stack and
	 * throws an exception for empty stack
	 * 
	 * @return T popped element at the top of stack
	 */
	public T pop() throws EmptyStackException {
		// TODO Auto-generated method stub
		if (isEmpty()) { // checks to see if stack is empty
			throw new EmptyStackException("Invalid"); // throw exception
		}
		T popped = top.getElement(); // T popped is equal to the top element
		top = top.getPrevious(); // makes the previous node the new top
		numItems--; // decrement numITems
		return popped; // return popped
	}

	@Override
	/**
	 * This method returns the data item at the top of the stack without removing it
	 * and throws an exception for an empty stack
	 * 
	 * @return T element the element at the top of the stack
	 */
	public T peek() throws EmptyStackException {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			throw new EmptyStackException("Invalid");
		} else {
			return top.getElement(); // returns the element by using the getElement for the top node

		}
	}

	@Override
	/**
	 * This method removes and returns the k-th data item from the top of the stack
	 * 
	 * @param int k
	 * @return T popped the k-th data item
	 */
	public T pop(int k) throws InvalidItemException {
		if (k == 1) { // checks to see if they want to pop at the top
			return pop();
		}
		if (k > numItems || k <= 0) { //checks to see if k is greater than num items or less than or equal to 0
			throw new InvalidItemException("Error: Invalid pop index."); //throw exception
		} else {
			DoubleLinkedNode<T> curr = top; //assign curr node 
			T value; //assign value with any type

			for (int i = 1; curr != null && i < k; i++) { //loops through code while curr is not null and i is less than k
				curr = curr.getPrevious(); //go down the stack
			}

			value = curr.getElement(); //assign value with the current node

			if (curr.getPrevious() == null) { //if it is the last node in the stack
				curr.getNext().setPrevious(null); //points the next nodes tail pointer to null
			} else { 
				curr.getNext().setPrevious(curr.getPrevious()); //points the next nodes tail to the current nodes tail
				curr.getPrevious().setNext(curr.getNext()); //points the previous nodes head to the current nodes head

			}
			numItems--; //decrement
			return value;
		}
	}

	@Override
	/**
	 * This method checks to see if the stack is empty
	 * 
	 * @return true if top is equal to null
	 * @return false if top is not equal to null
	 */
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if (top == null) { // condition that checks if top is equal to null
			return true;
		}
		return false;

	}

	@Override
	/**
	 * This method returns the size of the stack
	 * 
	 * @return int numItems number of items in the stack
	 */
	public int size() {
		// TODO Auto-generated method stub
		return numItems; // return num items
	}

	@Override
	/**
	 * This method returns the top
	 * 
	 * @return T top
	 */
	public DoubleLinkedNode<T> getTop() {
		// TODO Auto-generated method stub
		return top; // return top
	}

	/**
	 * This method returns a string data1 data 2 ... data n with data 1 at the top
	 * 
	 * @return String message
	 */
	public String toString() {
		DoubleLinkedNode<T> curr = top;
		String message = ""; // create new string variable message
		while (curr != null) { // iterate through the stack until curr is null
			message += curr.getElement() + " "; // adds the curr nodes element and a space to message
			curr = curr.getNext(); // goes to next node
		}
		return message;
	}

}
