package tp.logic;

import java.util.ArrayList;
import java.util.List;

public class GrafoConPeso extends GrafoListaVecinos {
	ArrayList<Arista> aristas;

	public GrafoConPeso(int vertices, List<String[]> selectedCities) {
		super(vertices, selectedCities);
		aristas = new ArrayList<Arista>();
	}

	public void agregarArista(String[] vertice1, String[] vertice2, double peso) {
		super.agregarArista(vertice1, vertice2);

		Arista nuevo = new Arista(vertice1, vertice2, peso);
		if (!aristas.contains(nuevo)) {
			aristas.add(nuevo);
		}
	}

	public void eliminarArista(String[] vertice1, String[] vertice2) {
		super.eliminarArista(vertice1, vertice2);

		for (int i = 0; i < aristas.size(); i++) {
			if (aristas.get(i).getVert1() == vertice1 && aristas.get(i).getVert2() == vertice2) {
				aristas.remove(i);
				break;
			}
		}
	}

	public double getPesoArista(String[] vertice1, String[] vertice2) {
		for (int i = 0; i < aristas.size(); i++) {
			if ((aristas.get(i).getVert1() == vertice1 && aristas.get(i).getVert2() == vertice2)
					|| (aristas.get(i).getVert1() == vertice2 && aristas.get(i).getVert2() == vertice1)) {
				return aristas.get(i).getPeso();
			}
		}
		throw new RuntimeException("No se encontro un peso entre " + vertice1 + " y " + vertice2);
	}

	public String toString() {
		StringBuilder st = new StringBuilder();
		for (int i = 0; i < aristas.size(); i++) {
			st.append(aristas.get(i).toString() + "\n");
		}
		return st.toString();
	}

	public ArrayList<Arista> getAristas() {
		return aristas;
	}

}