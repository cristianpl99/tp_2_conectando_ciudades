package tp.logic;

import java.util.Set;
import java.util.HashSet;


public class Prim {
	private Set<City> markedVertexes;

	public WeightedGraph primTraversal(WeightedGraph completeGraph) {
		BFS bfs = new BFS();
		if (!bfs.isConnected(completeGraph)) {
			throw new IllegalArgumentException("El grafo no es conexo.");
		}

		WeightedGraph mstGraph = new WeightedGraph(completeGraph.size(), completeGraph.vertexes);
		markedVertexes = new HashSet<>();
		markedVertexes.add(completeGraph.getVertex(0));

		while (markedVertexes.size() < completeGraph.size()) {
			Edge minimumEdge = findMinimumEdge(completeGraph, mstGraph);
			City city1 = minimumEdge.getCity1();
			City city2 = minimumEdge.getCity2();

			mstGraph.addEdge(city1, city2, minimumEdge.getPeso());
			markedVertexes.add(city1);
			markedVertexes.add(city2);
		}

		return mstGraph;
	}

	private Edge findMinimumEdge(WeightedGraph completeGraph, WeightedGraph mstGraph) {
		Edge minimumEdge = null;
		double minimumWeight = Float.POSITIVE_INFINITY;

		for (City city : markedVertexes) {
			Set<City> neighbors = completeGraph.neighbors(city);
			for (City neighbor : neighbors) {
				if (!isConnectedToMST(neighbor) && !mstGraph.existsEdge(city, neighbor)) {
					double weight = completeGraph.getEdgeWeight(city, neighbor);
					if (weight < minimumWeight) {
						minimumWeight = weight;
						minimumEdge = new Edge(city, neighbor, weight);
					}
				}
			}
		}
		return minimumEdge;
	}

	private boolean isConnectedToMST(City city) {
		return markedVertexes.contains(city);
	}
}

/*
 * El primer c�digo tiene una complejidad de O(N^3), mientras que el segundo
 * c�digo tiene una complejidad de O(N^2). La diferencia en complejidad se debe
 * a la forma en que se selecciona la arista m�nima en cada iteraci�n del
 * algoritmo. En el primer c�digo, la funci�n `selectMinimumEdge` itera sobre
 * todos los v�rtices marcados y, para cada v�rtice, itera sobre sus vecinos
 * para encontrar la arista de peso m�nimo que no est� en el grafo MST actual.
 * Esto implica dos bucles anidados, lo que resulta en una complejidad de O(N^2)
 * en el peor caso. Sin embargo, como este proceso se repite N-1 veces en el
 * bucle principal, la complejidad total es de O(N^3). En el segundo c�digo, la
 * funci�n `findMinimumEdge` tambi�n itera sobre todos los v�rtices marcados,
 * pero en lugar de iterar sobre todos los vecinos de cada v�rtice, simplemente
 * verifica si un vecino no est� conectado al MST actual y si no existe una
 * arista entre el v�rtice y su vecino en el MST actual. Esto se logra
 * utilizando el conjunto `markedVertexes` y el m�todo `existsEdge` del grafo
 * MST. Como resultado, se reduce el n�mero de iteraciones en el bucle interno,
 * lo que resulta en una complejidad de O(N) en el peor caso para encontrar la
 * arista m�nima. Dado que este proceso se repite N-1 veces en el bucle
 * principal, la complejidad total es de O(N^2). El segundo c�digo tiene una
 * complejidad mejorada en comparaci�n con el primer c�digo debido a la forma en
 * que se selecciona la arista m�nima, lo que resulta en una complejidad de
 * O(N^2) en lugar de O(N^3).
 */
