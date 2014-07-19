package lipi.dsa;

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
	private class Node<S> {
		private S obj;
		private Node<S> next = null;
		private Node<S> prev = null;

		public Node(S object) {
			this.obj = object;
			this.next = null;
			this.prev = null;
		}

		public void set(S object) {
			this.obj = object;
		}

		public S get() {
			return this.obj;
		}

		public void next(Node<S> next) {
			this.next = next;
		}

		public Node<S> next() {
			return this.next;
		}

		public void prev(Node<S> prev) {
			this.prev = prev;
		}

		public Node<S> prev() {
			return this.prev;
		}
	}

	public class LsIterator<U> implements Iterator<U> {
		private Node<U> curPos;

		private LsIterator() {
			this.curPos = (Node<U>)head.next();
		}

		public boolean hasNext() {
			/*if we are at the tail node.*/
			return curPos.next() != null;
		}

		public U next() {
			if( !this.hasNext()) {
				throw new IllegalStateException();
			}

			U obj = curPos.get();
			curPos = curPos.next();
			
			return obj;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	/*Head and tail are null nodes*/
	private Node<T> head = new Node<T>(null);
	private Node<T> tail = new Node<T>(null);
	private int size = 0;
	
	public List() {
		head.next(tail);
		tail.prev(head);
	}
	
	public Iterator<T> iterator() {
		return new LsIterator<T>();
	}
	
	public void add(T obj) {
		Node<T> elem = new Node<T>(obj);

		tail.prev().next(elem);
		elem.prev(tail.prev());
		tail.prev(elem);
		elem.next(tail);

		++size;
	}
	
	public void add(T obj, int index) {
		Node<T> elem = new Node<T>(obj);
		Node<T> idxElem = null;
		if(index < 0 || index >= this.size) {
			idxElem = tail;
		} else {
			idxElem = head.next();
			for(int i = 0; i < index; ++i) {
				idxElem = idxElem.next();
			}
		}

		elem.prev(idxElem.prev());
		elem.next(idxElem);
		elem.prev.next(elem);
		idxElem.prev(elem);
		++size;
	}
	
	private T deleteElement(Node<T> elem) {
		elem.prev().next(elem.next());
		elem.next().prev(elem.prev());
		
		elem.next(null);
		elem.prev(null);
		
		T obj = elem.get();
		/*remove the reference to the object*/
		elem.set(null);
		return obj;
	}
	
	public T remove(int index) {
		if(index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException();
		}
	
		Node<T> elem = head.next();
		for(int i = 0; i < index; ++i) { 
			elem = elem.next();
		}
		--size;
		return deleteElement(elem);
	}
	
	public T removeFirst() {
		if(size <= 0) {
			throw new IndexOutOfBoundsException();
		}

		Node<T> elem = head.next();
		--size;
		return deleteElement(elem);
	}
	
	public T removeLast() {
		if(size <= 0) {
			throw new IndexOutOfBoundsException();
		}

		Node<T> elem = tail.prev();
		--size;
		return deleteElement(elem);
	}

	public int size() {
		return size;
	}
	
	public T get(int index) {
		if(index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException();
		}
	
		Node<T> elem = head.next();
		for(int i = 0; i < index; ++i) { 
			elem = elem.next();
		}
		
		return elem.get();
	}

	public void clear() {
		while(size > 0) {
			/* remove the first element of the list */ 
			Node<T> elem = head.next();
			deleteElement(elem);
			--size;
		}
	}
}
