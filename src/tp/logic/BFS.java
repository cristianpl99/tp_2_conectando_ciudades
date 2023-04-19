package tp.logic;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class BFS {

	private static boolean[] marcados;
	private static List<String[]> L;

	public static boolean esConexo(GrafoListaVecinos g) {
		if (g == null) {
			throw new IllegalArgumentException("Tiene que pasar un grafo como valor" + g);
		}
		if (g.tamano() == 0) {
			return true;
		}
		return (alcanzables(g, g.getVertice(0)).size() == g.tamano());
	}

	public static Set<String[]> alcanzables(GrafoListaVecinos g, String[] vertice) {
		Set<String[]> ret = new HashSet<String[]>();

		inicializarBusqueda(g, vertice);

		while (L.size() > 0) {
			String[] seleccionado = seleccionarYMarcar(g);
			ret.add(seleccionado);

			agregarVecinosNoMarcados(g, seleccionado);

			removerSeleccionado();
		}

		return ret;
	}

	private static void removerSeleccionado() {
		L.remove(0);
	}

	private static String[] seleccionarYMarcar(GrafoListaVecinos g) {
		String[] seleccionado = L.get(0);
		marcados[g.getCiudades().indexOf(seleccionado)] = true;
		return seleccionado;
	}

	private static void agregarVecinosNoMarcados(GrafoListaVecinos g, String[] seleccionado) {
		for (String[] vecino : g.vecinos(seleccionado)) {
			if (!marcados[g.getCiudades().indexOf(vecino)] && !L.contains(vecino)) {
				L.add(vecino);
			}
		}
	}

	private static void inicializarBusqueda(GrafoListaVecinos g, String[] vertice) {
		L = new LinkedList<String[]>();
		L.add(vertice);
		marcados = new boolean[g.tamano()];
	}

}
