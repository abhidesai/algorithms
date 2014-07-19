package lipi.dsa;

import java.lang.reflect.Array;

public class ArrayStack<T> implements StackInterface<T> {
	T []elems = null;
	private int capacity = 10;
	private int t = -1;

	public ArrayStack(Class<T> c, int capacity) {
		@SuppressWarnings("unchecked")
		final T[] e = (T[])Array.newInstance(c, capacity);
		this.elems = e;
		this.capacity = capacity;
	}

	public boolean isEmpty() {
		return (t == -1);
	}

	/*
	 * push an element into the stack
	 * */
	public void push(T obj) throws CollectionFullException {
		if(size() == capacity)
			throw new CollectionFullException();
		++t;
		elems[t] = obj;
	}

	/*
	 * popout the topmost element of the stack 
	 * */
	public T pop() throws CollectionEmptyException {
		if(isEmpty())
			throw new CollectionEmptyException();
		
		T obj = elems[t];
		/*release the reference*/
		elems[t] = null;
		--t;
		return obj;
	}

	/*
	 * Returns the size of the stack.
	 * */
	public int size() {
		return (t+1);
	}

	/*
	 * Peek into the stack to see the topmost element.
	 * 
	 * Returns the topmost element of the stack without poping it out.
	 * */
	
	public T top() throws CollectionEmptyException{
		if(isEmpty())
			throw new CollectionEmptyException();

		T obj = elems[t];
		return obj;
	}
}
