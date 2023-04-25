package tp.logic.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tp.logic.BFS;
import tp.logic.City;
import tp.logic.NeighborListGraph;

public class BFSTest {

	@Test(expected = IllegalArgumentException.class)
	public void grafoNullTest() {
		NeighborListGraph g = null;
		assertTrue(BFS.isConnected(g));
	}

	public void grafoVacioTest() {
		List<City> c = new ArrayList<>();
		NeighborListGraph g = new NeighborListGraph(0, c);
		assertTrue(BFS.isConnected(g));
	}

	@Test
	public void grafoUnVerticeTest() {
		List<City> c = new ArrayList<>();
		c.add(new City("a", "a", 23, 34));
		NeighborListGraph g = new NeighborListGraph(1, c);
		assertTrue(BFS.isConnected(g));
	}

	@Test
	public void grafoDosVerticesAisladosTest() {
		List<City> c = new ArrayList<>();
		c.add(new City("a", "a", 23, 34));
		c.add(new City("b", "b", 24, 33));
		NeighborListGraph g = new NeighborListGraph(2, c);
		assertFalse(BFS.isConnected(g));
	}

	@Test
	public void grafoDosVerticesConexosTest() {
		List<City> c = new ArrayList<>();
		c.add(new City("a", "a", 23, 34));
		c.add(new City("b", "b", 24, 33));
		NeighborListGraph g = new NeighborListGraph(2, c);
		g.addEdge(g.getVertex(0), g.getVertex(1));
		assertTrue(BFS.isConnected(g));
	}

	@Test
	public void grafoInconexoTest() {
		NeighborListGraph g = crearGrafoInconexo();
		assertFalse(BFS.isConnected(g));
	}

	@Test
	public void grafoConexoTest() {
		NeighborListGraph g = crearGrafoConexo();
		assertTrue(BFS.isConnected(g));
	}

	private static List<City> crearListaDeCiudades() {
		List<City> cities = new ArrayList<>();
		cities.add(new City("a", "a", 23, 34));
		cities.add(new City("b", "b", 24, 33));
		cities.add(new City("c", "c", 25, 32));
		cities.add(new City("d", "d", 26, 31));
		cities.add(new City("e", "e", 27, 30));
		cities.add(new City("f", "f", 28, 39));
		return cities;
	}

	private static NeighborListGraph crearGrafoInconexo() {
		NeighborListGraph ret = new NeighborListGraph(6, crearListaDeCiudades());
		ret.addEdge(ret.getVertex(0), ret.getVertex(1));
		ret.addEdge(ret.getVertex(0), ret.getVertex(2));
		ret.addEdge(ret.getVertex(1), ret.getVertex(2));
		ret.addEdge(ret.getVertex(1), ret.getVertex(3));
		ret.addEdge(ret.getVertex(2), ret.getVertex(4));
		ret.addEdge(ret.getVertex(3), ret.getVertex(4));
		return ret;
	}

	private static NeighborListGraph crearGrafoConexo() {
		NeighborListGraph ret = new NeighborListGraph(6, crearListaDeCiudades());
		ret.addEdge(ret.getVertex(0), ret.getVertex(1));
		ret.addEdge(ret.getVertex(0), ret.getVertex(2));
		ret.addEdge(ret.getVertex(1), ret.getVertex(2));
		ret.addEdge(ret.getVertex(1), ret.getVertex(3));
		ret.addEdge(ret.getVertex(2), ret.getVertex(4));
		ret.addEdge(ret.getVertex(3), ret.getVertex(4));
		ret.addEdge(ret.getVertex(4), ret.getVertex(5));
		return ret;
	}

}
