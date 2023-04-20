package tp.logic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NeighborListGraph {

	// Representamos el grafo por su lista de vecinos
	protected ArrayList<HashSet<City>> neighbors;
	protected List<City> vertexes;

	// La cantidad de vertices esta predeterminada desde el constructor
	public NeighborListGraph(int numVertexes, List<City> selectedCities) {
		neighbors = new ArrayList<HashSet<City>>();
		for (int i = 0; i < numVertexes; i++) {
			neighbors.add(new HashSet<City>());
		}
		vertexes = selectedCities;
	}

	// Agregado de arista
	public void addEdge(City i, City j) {
		verifyVertex(i);
		verifyVertex(j);
		verifyDistinct(i, j);

		if (!existsEdge(i, j)) {
			neighbors.get(vertexes.indexOf(i)).add(j);
			neighbors.get(vertexes.indexOf(j)).add(i);
		}
	}

	// Eliminacion de aristas
	public void deleteEdge(City i, City j) {
		verifyVertex(i);
		verifyVertex(j);
		verifyDistinct(i, j);

		neighbors.get(vertexes.indexOf(i)).remove(j);
		neighbors.get(vertexes.indexOf(j)).remove(i);
	}

	// Informa si existe la arista especificada
	public boolean existsEdge(City i, City j) {
		verifyVertex(i);
		verifyVertex(j);
		verifyDistinct(i, j);

		return neighbors.get(vertexes.indexOf(i)).contains(j) && neighbors.get(vertexes.indexOf(j)).contains(i);
	}

	// Cantidad de vertices
	public int size() {
		return neighbors.size();
	}

	// Vecinos de un vertice
	public Set<City> neighbors(City i) {
		verifyVertex(i);

		return neighbors.get(vertexes.indexOf(i));
	}

	// Verifica que sea un vertice valido
	public void verifyVertex(City i) {
		if (i.equals(""))
			throw new IllegalArgumentException("El vertice no puede ser vacio: " + i);
	}

	// Verifica que i y j sean distintos
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
