package dsa;

import java.util.Iterator;

/**
 * 
 * @author AbhishekD
 *
 * @param <T>
 */

public class List<T> implements Iterable<T> {
	/**
	 * 
	 * @author AbhishekD
	 * Generic node
	 * @param <T>
	 */
	private class Node<T> {
		private T obj;
		private Node<T> next = null;
		private Node<T> prev = null;
		
		public Node(T object) {
			this.obj = object;
			this.next = null;
			this.prev = null;
		}
		
		public void set(T object) {
			this.obj = object;
		}
		
		public T get() {
			return this.obj;
		}
		
		public void next(Node<T> next) {
			this.next = next;
		}
		
		public Node<T> next() {
			return this.next;
		}
		
		public void prev(Node<T> prev) {
			this.prev = prev;
		}
		
		public Node<T> prev() {
			return this.prev;
		}
	}

	public class LsIterator implements Iterator<T> {
		private Node<T> curPos;
		
		private LsIterator() {
			this.curPos = head.next();
		}
		
		public boolean hasNext() {
			return curPos != null;
		}
		
		public T next() {
			if( !this.hasNext()) {
				throw new IllegalStateException();
			}
			
			T obj = curPos.get();
			curPos = curPos.next();
			
			return obj;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	private Node<T> head = new Node<T>(null);
	private Node<T> tail = head;
	private int size = 0;
	
	public Iterator<T> iterator() {
		return new LsIterator();
	}
	
	public void add(T obj) {
		Node<T> elem = new Node<T>(obj);
		
		/*First element*/
		if(head.next() == null) {
			head.next(elem);
			elem.prev(head);
		} else {
			tail.next(elem);
			elem.prev(tail);
		}

		/* move the tail node to point 
		 * to the newly added node*/
		tail = elem;
		++size;
	}
	
	public T remove(int index) {
		if(index >= this.size) {
			throw new IndexOutOfBoundsException();
		}
	
		Node<T> elem = head.next();
		for(int i = 0; i < index; ++i) { 
			elem = elem.next();
		}

		elem.prev().next(elem.next());

		if(elem == tail) {
			tail = elem.prev();
			tail.next(null);
		} else {
			elem.next().prev(elem.prev());
		}
		
		elem.next(null);
		elem.prev(null);
		--size;
		
		return elem.get();
	}
}
