package tp.logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Primm {

	private static List<String[]> verticesMarcados;

	public static GrafoConPeso recorridoPrimm(GrafoConPeso grafoCompleto, List<String[]> selectedCities) {
		if (!BFS.esConexo(grafoCompleto)) {
			throw new IllegalArgumentException("el grafo es invalido por que no es conexo");
		}
		GrafoConPeso grafoAgm = new GrafoConPeso(grafoCompleto.tamano(), selectedCities);
		int i = 1;
		verticesMarcados = new ArrayList<String[]>();
		verticesMarcados.add(grafoCompleto.getVertice(0));
		while (i <= grafoCompleto.tamano() - 1) {
			Arista aristaMinima = elegirMinimaArista(grafoCompleto, grafoAgm, selectedCities);
			grafoAgm.agregarArista(aristaMinima.getVert1(), aristaMinima.getVert2(), aristaMinima.getPeso());
			i++;
		}
		return grafoAgm;
	}

	private static Arista elegirMinimaArista(GrafoConPeso grafoCompleto, GrafoConPeso grafoAgm,
			List<String[]> selectedCities) {
		String[] verticeNoMarcadoMinimo = grafoCompleto.getVertice(0);
		Arista minimaArista = null;

		minimaArista = new Arista(grafoCompleto.getVertice(0), grafoCompleto.getVertice(1), Float.POSITIVE_INFINITY);

		for (String[] vertice : verticesMarcados) {
			Set<String[]> vecinos = grafoCompleto.vecinos(grafoCompleto.getVertice(selectedCities.indexOf(vertice)));
			Iterator<String[]> iter = vecinos.iterator();

			while (iter.hasNext()) {
				String[] verticeActual = iter.next();
				if (!BFS.alcanzables(grafoAgm, vertice).contains(verticeActual)) {
					if (grafoCompleto.getPesoArista(vertice, verticeActual) < minimaArista.getPeso()) {
						if (!grafoAgm.existeArista(vertice, verticeActual)) {
							verticeNoMarcadoMinimo = verticeActual;
							minimaArista = new Arista(vertice, verticeNoMarcadoMinimo,
									grafoCompleto.getPesoArista(vertice, verticeNoMarcadoMinimo));
						}
					}
				}
			}
		}

		verticesMarcados.add(verticeNoMarcadoMinimo);
		return minimaArista;
	}

}
