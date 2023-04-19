package tp.logic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GrafoListaVecinos {

	// Representamos el grafo por su lista de vecinos
	protected ArrayList<HashSet<String[]>> vecinos;
	protected List<String[]> ciudades;

	// La cantidad de vertices esta predeterminada desde el constructor
	public GrafoListaVecinos(int vertices, List<String[]> selectedCities) {
		vecinos = new ArrayList<HashSet<String[]>>();
		for (int i = 0; i < vertices; i++) {
			vecinos.add(new HashSet<String[]>());
		}
		ciudades = selectedCities;
	}

	// Agregado de aristas
	public void agregarArista(String[] i, String[] j) {
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		if (!existeArista(i, j)) {
			vecinos.get(ciudades.indexOf(i)).add(j);
			vecinos.get(ciudades.indexOf(j)).add(i);
		}
	}

	// Eliminacion de aristas
	public void eliminarArista(String[] i, String[] j) {
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		vecinos.get(ciudades.indexOf(i)).remove(j);
		vecinos.get(ciudades.indexOf(j)).remove(i);
	}

	// Informa si existe la arista especificada
	public boolean existeArista(String[] i, String[] j) {
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		return vecinos.get(ciudades.indexOf(i)).contains(j) && vecinos.get(ciudades.indexOf(j)).contains(i);
	}

	// Cantidad de vertices
	public int tamano() {
		return vecinos.size();
	}

	// Vecinos de un vertice
	public Set<String[]> vecinos(String[] i) {
		verificarVertice(i);

		return vecinos.get(ciudades.indexOf(i));
	}

	// Verifica que sea un vertice valido
	public void verificarVertice(String i[]) {
		if (i.equals(""))
			throw new IllegalArgumentException("El vertice no puede ser vacio: " + i);
	}

	// Verifica que i y j sean distintos
	public void verificarDistintos(String[] i, String j[]) {
		if (i.equals(j))
			throw new IllegalArgumentException("No se permiten loops: (" + i + ", " + j + ")");
	}

	public String[] getVertice(int i) {
		return ciudades.get(i);
	}

	public List<String[]> getCiudades() {
		return ciudades;
	}

}
