package tp.logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Primm {

	private static List<Integer> verticesMarcados;

	public static GrafoConPeso recorridoPrimm(GrafoConPeso grafoCompleto) {
		if (!BFS.esConexo(grafoCompleto)) {
			throw new IllegalArgumentException("el grafo es invalido por que no es conexo");
		}
		GrafoConPeso grafoAgm = new GrafoConPeso(grafoCompleto.tamano());
		int i = 1;
		verticesMarcados = new ArrayList<Integer>();
		verticesMarcados.add(0);
		while (i <= grafoCompleto.tamano() - 1) {
			Arista aristaMinima = elegirMinimaArista(grafoCompleto, grafoAgm);
			grafoAgm.agregarArista(aristaMinima.getVert1(), aristaMinima.getVert2(), aristaMinima.getPeso());
			i++;
		}
		return grafoAgm;
	}

	private static Arista elegirMinimaArista(GrafoConPeso grafoCompleto, GrafoConPeso grafoAgm) {
		int verticeNoMarcadoMinimo = 0;
		Arista minimaArista = null;

		minimaArista = new Arista(0, 1, Float.POSITIVE_INFINITY);

		for (Integer vertice : verticesMarcados) {
			Set<Integer> vecinos = grafoCompleto.vecinos(vertice);
			Iterator<Integer> iter = vecinos.iterator();

			while (iter.hasNext()) {
				int verticeActual = iter.next();
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
