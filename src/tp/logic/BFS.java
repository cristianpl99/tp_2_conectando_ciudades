package tp.logic;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class BFS {

	private boolean[] marked;
	private List<City> L;

	public boolean isConnected(NeighborListGraph graph) {
		if (graph == null) {
			throw new IllegalArgumentException("Tiene que pasar un grafo como valor" + graph);
		}
		if (graph.size() == 0) {
			return true;
		}
		return (reachables(graph, graph.getVertex(0)).size() == graph.size());
	}

	public Set<City> reachables(NeighborListGraph g, City vertice) {
		Set<City> ret = new HashSet<City>();

		initializeSearch(g, vertice);

		while (L.size() > 0) {
			City seleccionado = selectAndMark(g);
			ret.add(seleccionado);

			addNonMarkedNeighbors(g, seleccionado);

			removeMarked();
		}

		return ret;
	}

	private void removeMarked() {
		L.remove(0);
	}

	private City selectAndMark(NeighborListGraph g) {
		City selected = L.get(0);
		marked[g.getVertexes().indexOf(selected)] = true;
		return selected;
	}

	private void addNonMarkedNeighbors(NeighborListGraph g, City seleccionado) {
		for (City neighbor : g.neighbors(seleccionado)) {
			if (!marked[g.getVertexes().indexOf(neighbor)] && !L.contains(neighbor)) {
				L.add(neighbor);
			}
		}
	}

	private void initializeSearch(NeighborListGraph g, City city) {
		L = new LinkedList<City>();
		L.add(city);
		marked = new boolean[g.size()];
	}

}
