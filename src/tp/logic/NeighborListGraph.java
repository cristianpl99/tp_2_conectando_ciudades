package tp.logic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NeighborListGraph {

	protected ArrayList<HashSet<City>> neighbors;
	protected List<City> vertexes;

	public NeighborListGraph(int numVertexes, List<City> selectedCities) {
		if (selectedCities == null) {
			throw new IllegalArgumentException("no hay una lista con ciudades creada");
		}
		if (selectedCities.size() != numVertexes) {
			throw new IllegalArgumentException("no hay las suficientes ciudades para cubrir cada vertice del grafo");
		}
		neighbors = new ArrayList<HashSet<City>>();
		for (int i = 0; i < numVertexes; i++) {
			neighbors.add(new HashSet<City>());
		}
		vertexes = selectedCities;
	}

	public void addEdge(City i, City j) {
		verifyVertex(i);
		verifyVertex(j);
		verifyDistinct(i, j);

		if (!existsEdge(i, j)) {
			neighbors.get(vertexes.indexOf(i)).add(j);
			neighbors.get(vertexes.indexOf(j)).add(i);
		}
	}

	public void deleteEdge(City i, City j) {
		verifyVertex(i);
		verifyVertex(j);
		verifyDistinct(i, j);

		neighbors.get(vertexes.indexOf(i)).remove(j);
		neighbors.get(vertexes.indexOf(j)).remove(i);
	}

	public boolean existsEdge(City i, City j) {
		verifyVertex(i);
		verifyVertex(j);
		verifyDistinct(i, j);

		return neighbors.get(vertexes.indexOf(i)).contains(j) && neighbors.get(vertexes.indexOf(j)).contains(i);
	}

	public int size() {
		return neighbors.size();
	}

	public Set<City> neighbors(City i) {
		verifyVertex(i);

		return neighbors.get(vertexes.indexOf(i));
	}

	public void verifyVertex(City i) {
		if (i.equals(null))
			throw new IllegalArgumentException("El vertice no puede ser null: " + i);
	}

	public void verifyDistinct(City i, City j) {
		if (i.equals(j))
			throw new IllegalArgumentException("No se permiten loops: (" + i + ", " + j + ")");
	}

	public City getVertex(int i) {
		return vertexes.get(i);
	}

	public List<City> getVertexes() {
		return vertexes;
	}

}
