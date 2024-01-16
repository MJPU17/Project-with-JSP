package co.edu.unbosque.util;



public class MyDoubleLinkedList<T>{

	protected DNode<T> head,currentPosition;
	protected int posActual,size;
	
	public MyDoubleLinkedList() {
		posActual=-1;
		size=0;
	}

	public DNode<T> getHead() {
		return head;
	}

	public void setHead(DNode<T> head) {
		this.head = head;
	}

	public DNode<T> getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(DNode<T> currentPosition) {
		this.currentPosition = currentPosition;
	}
	
	public int getPosActual() {
		return posActual;
	}

	public int getSize() {
		return size;
	}

	public void forward(int numposition) {
		if(numposition>0 && this.head!=null) {
			int positionForward=numposition;
			if(this.currentPosition==null) {
				currentPosition=head;
				positionForward--;
				posActual++;
			}
			while (currentPosition.getNext()!=null && positionForward>0) {
				currentPosition=currentPosition.getNext();
				positionForward--;
				posActual++;
			}
		}
	}
	
	public void insert(T info) {
		DNode<T> tempNode=new DNode<T>(info);
		if(currentPosition==null) {
			tempNode.setNext(head);
			if(head!=null) {
				head.setPrevious(tempNode);
			}
			head=tempNode;
		}
		else {
			tempNode.setNext(currentPosition.getNext());
			tempNode.setPrevious(currentPosition);
			if(currentPosition.getNext()!=null) {
				currentPosition.getNext().setPrevious(tempNode);
			}
			currentPosition.setNext(tempNode);
		}
		currentPosition=tempNode;
		posActual++;
		size++;
	}
	
	public T extract() {
		T info=null;
		if(currentPosition != null) {
			info=currentPosition.getInfo();
			if(head == currentPosition) {
				head=currentPosition.getNext();
			}
			else {
				currentPosition.getPrevious().setNext(currentPosition.getNext());
			}
			if(currentPosition.getNext()!=null) {
				currentPosition.getNext().setPrevious(currentPosition.getPrevious());
			}
			currentPosition=currentPosition.getNext();
			if(size-1==posActual)posActual=-1;
			size--;
		}
		return info;
	}
	
	public void backwards(int numposition) {
		if(numposition<=0 || head== null || currentPosition==null) {
			return;
		}
		int positionBackward=numposition;
		while (currentPosition!=null && positionBackward>0) {
			currentPosition=currentPosition.getPrevious();
			positionBackward--;
			posActual--;
		}
	}
	
	public void set(int index, T info) {
		if(posActual>index) backwards(Math.abs(posActual-index));
		else forward(Math.abs(posActual-index));
		currentPosition.setInfo(info);
	}
	
	public void remove(int index) {
		if(posActual>index) backwards(Math.abs(posActual-index));
		else forward(Math.abs(posActual-index));
		extract();
	}
	
	public void addInit(T info) {
		backwards(size);
		insert(info);
	}
	
	public void addLast(T info) {
		forward(size);
		insert(info);
	}
	
	public void add(int index,T info) {
		index-=1;
		if(posActual>index) backwards(Math.abs(posActual-index));
		else forward(Math.abs(posActual-index));
		insert(info);
	}
	
	public T get(int index) {
		if(posActual>index) backwards(Math.abs(posActual-index));
		else forward(Math.abs(posActual-index));
		return currentPosition.getInfo();
	}
	
	public boolean isEmpty() {
		return size==0;
	}
	
	@Override
	public String toString() {
		DNode<T> tempNode = head;
		StringBuilder sb = new StringBuilder();
		while(tempNode != null) {
			sb.append(tempNode.getInfo());
			if(tempNode.getNext() != null) sb.append("<-->");
			tempNode=tempNode.getNext();
		}
		return sb.toString();
	}


}