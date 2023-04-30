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
