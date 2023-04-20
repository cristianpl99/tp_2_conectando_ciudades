package tp.logic;

import java.util.ArrayList;
import java.util.List;

public class WeightedGraph extends NeighborListGraph {
	ArrayList<Edge> edges;

	public WeightedGraph(int vertices, List<City> selectedCities) {
		super(vertices, selectedCities);
		edges = new ArrayList<Edge>();
	}

	public void addEdge(City city1, City city2, double peso) {
		super.addEdge(city1, city2);

		Edge newEdge = new Edge(city1, city2, peso);
		if (!edges.contains(newEdge)) {
			edges.add(newEdge);
		}
	}

	public void deleteEdge(City city1, City city2) {
		super.deleteEdge(city1, city2);

		for (int i = 0; i < edges.size(); i++) {
			if (edges.get(i).getCity1() == city1 && edges.get(i).getCity2() == city2) {
				edges.remove(i);
				break;
			}
		}
	}

	public double getEdgeWeight(City city1, City city2) {
		for (int i = 0; i < edges.size(); i++) {
			if ((edges.get(i).getCity1() == city1 && edges.get(i).getCity2() == city2)
					|| (edges.get(i).getCity1() == city2 && edges.get(i).getCity2() == city1)) {
				return edges.get(i).getPeso();
			}
		}
		throw new RuntimeException("No se encontro un peso entre " + city1 + " y " + city2);
	}

	public String toString() {
		StringBuilder st = new StringBuilder();
		for (int i = 0; i < edges.size(); i++) {
			st.append(edges.get(i).toString() + "\n");
		}
		return st.toString();
	}

	public ArrayList<Edge> getAristas() {
		return edges;
	}

}