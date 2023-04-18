package tp.logic;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class BFS {

	private static boolean[] marcados;
	private static List<Integer> L;

	public static boolean esConexo(GrafoListaVecinos g) {
		if (g == null) {
			throw new IllegalArgumentException("Tiene que pasar un grafo como valor" + g);
		}
		if (g.tamano() == 0) {
			return true;
		}
		return (alcanzables(g, 0).size() == g.tamano());
	}

	public static Set<Integer> alcanzables(GrafoListaVecinos g, int origen) {
		Set<Integer> ret = new HashSet<Integer>();

		inicializarBusqueda(g, origen);

		while (L.size() > 0) {
			int seleccionado = seleccionarYMarcar();
			ret.add(seleccionado);

			agregarVecinosNoMarcados(g, seleccionado);

			removerSeleccionado();
		}

		return ret;
	}

	private static void removerSeleccionado() {
		L.remove(0);
	}

	private static int seleccionarYMarcar() {
		int seleccionado = L.get(0);
		marcados[seleccionado] = true;
		return seleccionado;
	}

	private static void agregarVecinosNoMarcados(GrafoListaVecinos g, int seleccionado) {
		for (int vecino : g.vecinos(seleccionado)) {
			if (!marcados[vecino] && !L.contains(vecino)) {
				L.add(vecino);
			}
		}
	}

	private static void inicializarBusqueda(GrafoListaVecinos g, int origen) {
		L = new LinkedList<Integer>();
		L.add(origen);
		marcados = new boolean[g.tamano()];
	}

}
