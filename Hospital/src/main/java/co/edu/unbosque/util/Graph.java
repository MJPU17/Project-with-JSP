package co.edu.unbosque.util;



public class Graph {
	
	private MyLinkedList<Vertex<?>> listOfNodes;
	private MyMap<Vertex<?>, Integer> arrivals;
	
	public Graph() {
		listOfNodes= new MyLinkedList<Vertex<?>>();
		arrivals=new MyMap<>();
	}
	
	public void addVertex(Vertex<?> v) {
		listOfNodes.addLast(v);
	}
	
	public Vertex<?> getVertex(Vertex<?> vertex) {
		for(int i=0;i<listOfNodes.size();i++) {
			if(listOfNodes.get(i).getInfo().equals(vertex)) {
				return listOfNodes.get(i).getInfo();
			}
		}
		return null;
	}
	
	public void addEdge(Vertex<?> source,Vertex<?> destination,double weigth) {
		getVertex(source).addEdge(new Edge(source, destination, weigth));
		if(arrivals.containsKey(destination)) {
			arrivals.put(destination, arrivals.getValue(destination)+1);
		}
		else {
			arrivals.put(destination, 1);
		}
	}
	
	public Vertex<?> root(){
		for(Vertex<?> vertex:listOfNodes) {
			if(!arrivals.containsKey(vertex))return vertex;
		}
		return null;
	}
	
	public MyLinkedList<Vertex<?>> disconnectedNodes(){
		MyLinkedList<Vertex<?>> disconected=new MyLinkedList<>();
		for(Vertex<?> vertex: listOfNodes) {
			if(vertex.getAdyacentEdges().isEmpty())disconected.addLast(vertex);
		}
		return disconected;
	}

	public MyLinkedList<Vertex<?>> getListOfNodes() {
		return listOfNodes;
	}

	public void setListOfNodes(MyLinkedList<Vertex<?>> listOfNodes) {
		this.listOfNodes = listOfNodes;
	}
	
	@Override
	public String toString() {
		return "lista de nodos en el grafo: "+listOfNodes+" \n";
	}

	public MyMap<Vertex<?>, Integer> getArrivals() {
		return arrivals;
	}

	public void setArrivals(MyMap<Vertex<?>, Integer> arrivals) {
		this.arrivals = arrivals;
	}
	
}
