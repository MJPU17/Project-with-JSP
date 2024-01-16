package co.edu.unbosque.util.algorithm;

import co.edu.unbosque.util.Edge;
import co.edu.unbosque.util.MyLinkedList;
import co.edu.unbosque.util.StackImpl;
import co.edu.unbosque.util.Vertex;

public class DepthFirstSearch extends AbstractSearch {

	private StackImpl<Vertex<?>> stackOfNodes = new StackImpl<Vertex<?>>();
	private MyLinkedList<Vertex<?>> visitedNodes = new MyLinkedList<Vertex<?>>();

	public DepthFirstSearch(Vertex<?> sourceVertex, Vertex<?> destinationVertex) {
		super(sourceVertex, destinationVertex);
	}

	@Override
	public boolean runSearch() {

		if (this.sourceVertex.equals(destinationVertex)) {
			System.out.println("Nodo destino encontrado a 0 de profundidad");
			System.out.println(sourceVertex.getInfo());
		}

		stackOfNodes.push(sourceVertex);
		System.out.println("Ruta a seguir para ubicar el nodo");

		while (stackOfNodes.size() != 0) {

			Vertex<?> current = stackOfNodes.pop();
			if (current.equals(destinationVertex)) {
				System.out.println(destinationVertex.getInfo());
				System.out.println("nodo buscado encontrado\n");
				return true;
			} else {
				System.out.print(current.getInfo() + " -> ");
				visitedNodes.addLast(current);
				MyLinkedList<Edge> adyacents = current.getAdyacentEdges();
				while (!adyacents.isEmpty()) {
					stackOfNodes.push(adyacents.extract().getDestination());
				}
			}
		}
		return false;

	}

	public StackImpl<Vertex<?>> getStackOfNodes() {
		return stackOfNodes;
	}

	public void setStackOfNodes(StackImpl<Vertex<?>> stackOfNodes) {
		this.stackOfNodes = stackOfNodes;
	}

	public MyLinkedList<Vertex<?>> getVisitedNodes() {
		return visitedNodes;
	}

	public void setVisitedNodes(MyLinkedList<Vertex<?>> visitedNodes) {
		this.visitedNodes = visitedNodes;
	}

}
