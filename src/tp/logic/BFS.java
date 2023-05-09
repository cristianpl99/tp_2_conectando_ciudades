package tp.logic;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class BFS {

	private boolean[] marked;
	private List<City> citiesList;

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

		while (citiesList.size() > 0) {
			City seleccionado = selectAndMark(g);
			ret.add(seleccionado);

			addNonMarkedNeighbors(g, seleccionado);

			removeMarked();
		}

		return ret;
	}

	private void removeMarked() {
		citiesList.remove(0);
	}

	private City selectAndMark(NeighborListGraph graph) {
		City selected = citiesList.get(0);
		marked[graph.getVertexes().indexOf(selected)] = true;
		return selected;
	}

	private void addNonMarkedNeighbors(NeighborListGraph graph, City selected) {
		for (City neighbor : graph.neighbors(selected)) {
			if (!marked[graph.getVertexes().indexOf(neighbor)] && !citiesList.contains(neighbor)) {
				citiesList.add(neighbor);
			}
		}
	}

	private void initializeSearch(NeighborListGraph graph, City city) {
		citiesList = new LinkedList<City>();
		citiesList.add(city);
		marked = new boolean[graph.size()];
	}
}
