package co.edu.unbosque.util.algorithm;

import co.edu.unbosque.util.Edge;
import co.edu.unbosque.util.MyLinkedList;
import co.edu.unbosque.util.MyMap;
import co.edu.unbosque.util.QueueImpl;
import co.edu.unbosque.util.Vertex;

public class BreadthFirstSearch extends AbstractSearch {
	
	private MyMap<Vertex<?>, Integer> distance;
	private MyMap<Vertex<?>, Vertex<?>> previous;

	public BreadthFirstSearch(Vertex<?> sourceVertex, Vertex<?> destinationVertex) {
		super(sourceVertex, destinationVertex);
		distance=new MyMap<>();
		previous=new MyMap<>();
	}

	@Override
	public boolean runSearch() {

		distance.put(sourceVertex, 0);
		
		if (this.sourceVertex.equals(destinationVertex)) {
			System.out.println("El objetivo fue encontrado");
			System.out.println(this.sourceVertex.getInfo());
			return true;
		}
		
		QueueImpl<Vertex<?>> queueOfNodes = new QueueImpl<Vertex<?>>();
		
		queueOfNodes.enqueue(this.sourceVertex);
		
		while (queueOfNodes.size() != 0) {
			Vertex<?> current = queueOfNodes.dequeue();
			if (current.equals(this.destinationVertex)) {
				return true;
			} else {
				if (current.getAdyacentEdges().isEmpty()) {
					continue;
				} else {
					MyLinkedList<Edge> adyacents = current.getAdyacentEdges();
					for (Edge e: adyacents) {
						Vertex<?> v=e.getDestination();
						if(!distance.containsKey(v)) {
							queueOfNodes.enqueue(v);
							distance.put(v, distance.getValue(current)+1);
							previous.put(v, current);
						}
					}
				}
			}

		}
		return false;
	}
	
	public Vertex<?>[] way(){
		int size=distance.getValue(destinationVertex)+1;
		Vertex<?>[] way=new Vertex[size];
		Vertex<?>current=destinationVertex;
		for(int i=size-1;i>=0;i--) {
			way[i]=current;
			current=previous.getValue(current);
		}
		return way;
	}
	
	public int distance() {
		return distance.getValue(destinationVertex);
	}

	public MyMap<Vertex<?>, Integer> getDistance() {
		return distance;
	}

	public void setDistance(MyMap<Vertex<?>, Integer> distance) {
		this.distance = distance;
	}
	
	public MyMap<Vertex<?>, Vertex<?>> getPrevious() {
		return previous;
	}
	
	public void setPrevious(MyMap<Vertex<?>, Vertex<?>> previous) {
		this.previous = previous;
	}

}
