package lipi.dsa;

public interface StackInterface<T> {
	public void push(T obj) throws CollectionFullException;
	public T pop() throws CollectionEmptyException;
	public int size();
	public boolean isEmpty();
	public T top() throws CollectionEmptyException;
}
