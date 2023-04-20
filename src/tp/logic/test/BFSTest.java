package tp.logic.test;

import static org.junit.Assert.*;

import org.junit.Test;

import tp.logic.BFS;
import tp.logic.GrafoListaVecinos;

public class BFSTest {

	@Test
	public void grafoVacioTest() {
		assertTrue(BFS.isConnected(new GrafoListaVecinos(0)));
	}

	@Test
	public void grafoUnVerticeTest() {
		assertTrue(BFS.isConnected(new GrafoListaVecinos(1)));
	}

	@Test
	public void grafoDosVerticesAisladosTest() {
		assertFalse(BFS.isConnected(new GrafoListaVecinos(2)));
	}

	@Test
	public void grafoDosVerticesConexosTest() {
		GrafoListaVecinos g = new GrafoListaVecinos(2);
		g.agreagarArista(0, 1);

		assertTrue(BFS.isConnected(g));
	}

	@Test
	public void grafoInconexoTest() {
		GrafoListaVecinos g = crearGrafoInconexo();

		assertFalse(BFS.isConnected(g));
	}

	private GrafoListaVecinos crearGrafoInconexo() {
		GrafoListaVecinos ret = new GrafoListaVecinos(7);
		ret.agreagarArista(0, 1);
		ret.agreagarArista(0, 2);
		ret.agreagarArista(1, 2);
		ret.agreagarArista(1, 3);
		ret.agreagarArista(2, 4);
		ret.agreagarArista(3, 4);
		ret.agreagarArista(5, 6);
		return ret;
	}

	@Test
	public void grafoConexoTest() {
		GrafoListaVecinos g = crearGrafoConexo();

		assertTrue(BFS.isConnected(g));
	}

	private GrafoListaVecinos crearGrafoConexo() {
		GrafoListaVecinos ret = new GrafoListaVecinos(7);
		ret.agreagarArista(0, 1);
		ret.agreagarArista(0, 2);
		ret.agreagarArista(1, 2);
		ret.agreagarArista(1, 3);
		ret.agreagarArista(2, 4);
		ret.agreagarArista(3, 4);
		ret.agreagarArista(4, 5);
		ret.agreagarArista(5, 6);
		return ret;
	}

}
