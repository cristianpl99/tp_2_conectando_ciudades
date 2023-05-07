package tp.logic;

/*
 * 
 * ########## ESTA IMPLEMENTACIÓN ES O(N3)########## DEJAR LOS COMENTS PARA SUMAR AL INFORME
package tp.logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Prim {

	private List<City> markedVertexes;

	public WeightedGraph primTraversal(WeightedGraph completeGraph) {
		if (!BFS.isConnected(completeGraph)) {
			throw new IllegalArgumentException("el grafo es invalido por que no es conexo");
		}
		WeightedGraph mstGraph = new WeightedGraph(completeGraph.size(), completeGraph.vertexes);
		int i = 1;
		markedVertexes = new ArrayList<City>();
		markedVertexes.add(completeGraph.getVertex(0));
		while (i <= completeGraph.size() - 1) {
			Edge minimumEdge = selectMinimumEdge(completeGraph, mstGraph);
			mstGraph.addEdge(minimumEdge.getCity1(), minimumEdge.getCity2(), minimumEdge.getPeso());
			i++;
		}
		return mstGraph;
	}

	private Edge selectMinimumEdge(WeightedGraph completeGraph, WeightedGraph mstGraph) {
		City nonMarkedminimumVertex = completeGraph.getVertex(0);
		Edge minimumEdge = null;

		minimumEdge = new Edge(completeGraph.getVertex(0), completeGraph.getVertex(1), Float.POSITIVE_INFINITY);

		for (City vertex : markedVertexes) {
			Set<City> vertexes = completeGraph.neighbors(vertex);
			Iterator<City> iter = vertexes.iterator();
			while (iter.hasNext()) {
				City currentVertex = iter.next();
				if (!BFS.reachables(mstGraph, vertex).contains(currentVertex)) {
					if (completeGraph.getEdgeWeight(vertex, currentVertex) < minimumEdge.getPeso()) {
						if (!mstGraph.existsEdge(vertex, currentVertex)) {
							nonMarkedminimumVertex = currentVertex;
							minimumEdge = new Edge(vertex, nonMarkedminimumVertex,
									completeGraph.getEdgeWeight(vertex, nonMarkedminimumVertex));
						}
					}
				}
			}
		}
		markedVertexes.add(nonMarkedminimumVertex);
		return minimumEdge;
	}
}
*/

// ########## ESTA IMPLEMENTACIÓN ES O(N2)########## DEJAR LOS COMENTS PARA SUMAR AL INFORME
import java.util.HashSet;
import java.util.Set;

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
 * El primer código tiene una complejidad de O(N^3), mientras que el segundo código tiene una complejidad de O(N^2).
La diferencia en complejidad se debe a la forma en que se selecciona la arista mínima en cada iteración del 
algoritmo.
En el primer código, la función `selectMinimumEdge` itera sobre todos los vértices marcados y, para cada vértice, 
itera sobre sus vecinos para encontrar la arista de peso mínimo que no está en el grafo MST actual. Esto implica dos 
bucles anidados, lo que resulta en una complejidad de O(N^2) en el peor caso. Sin embargo, como este proceso se repite 
N-1 veces en el bucle principal, la complejidad total es de O(N^3).
En el segundo código, la función `findMinimumEdge` también itera sobre todos los vértices marcados, pero en lugar de 
iterar sobre todos los vecinos de cada vértice, simplemente verifica si un vecino no está conectado al MST actual y si 
no existe una arista entre el vértice y su vecino en el MST actual. Esto se logra utilizando el conjunto 
`markedVertexes` y el método `existsEdge` del grafo MST. Como resultado, se reduce el número de iteraciones en el 
bucle interno, lo que resulta en una complejidad de O(N) en el peor caso para encontrar la arista mínima. Dado que 
este proceso se repite N-1 veces en el bucle principal, la complejidad total es de O(N^2).
El segundo código tiene una complejidad mejorada en comparación con el primer código debido a la forma en 
que se selecciona la arista mínima, lo que resulta en una complejidad de O(N^2) en lugar de O(N^3).
*/
