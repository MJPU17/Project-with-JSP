package co.edu.unbosque.util;

public class StackImpl<E> implements Stack<E> {
	
	private MyDequeList<E> data;

	public StackImpl() {
		data = new MyDequeList<E>();
	}

	@Override
	public void push(E info) {
		data.insertFirst(info);
	}

	@Override
	public E pop() {
		return data.removeFirst();
	}

	@Override
	public int size() {
		return data.size();
	}

	@Override
	public String toString() {
		return data.toString();
	}
}
