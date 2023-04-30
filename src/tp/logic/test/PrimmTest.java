package tp.logic.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tp.logic.City;
import tp.logic.Prim;
import tp.logic.WeightedGraph;

public class PrimmTest {

	@Test(expected = IllegalArgumentException.class)
	public void grafoNoConexoTest() {
		WeightedGraph g = crearGrafoInconexo();
		Prim prim = new Prim();
		prim.primTraversal(g);
	}

	@Test
	public void happyTest() {
		WeightedGraph g = crearGrafoConexo();
		Prim prim = new Prim();
		WeightedGraph agm = prim.primTraversal(g);
		boolean acum = true;
		acum = acum && agm.existsEdge(agm.getVertex(0), agm.getVertex(1));
		acum = acum && agm.existsEdge(agm.getVertex(1), agm.getVertex(2));
		acum = acum && agm.existsEdge(agm.getVertex(2), agm.getVertex(4));
		acum = acum && agm.existsEdge(agm.getVertex(4), agm.getVertex(3));
		acum = acum && agm.existsEdge(agm.getVertex(4), agm.getVertex(5));
		assertTrue(acum);
	}

	@Test
	public void noAGMTest() {
	    WeightedGraph g = crearGrafoConexo();
	    Prim prim = new Prim();
	    WeightedGraph agm = prim.primTraversal(g);
	    boolean acum = true;
	    acum = acum && agm.existsEdge(agm.getVertex(0), agm.getVertex(1));
	    acum = acum && agm.existsEdge(agm.getVertex(1), agm.getVertex(2));
	    acum = acum && agm.existsEdge(agm.getVertex(1), agm.getVertex(3));
	    assertFalse(acum);
	}

	private List<City> crearListaDeCiudades() {
		List<City> cities = new ArrayList<>();
		cities.add(new City("a", "a", 23, 34));
		cities.add(new City("b", "b", 24, 33));
		cities.add(new City("c", "c", 25, 32));
		cities.add(new City("d", "d", 26, 31));
		cities.add(new City("e", "e", 27, 30));
		cities.add(new City("f", "f", 28, 39));
		return cities;
	}

	private WeightedGraph crearGrafoConexo() {
		WeightedGraph ret = new WeightedGraph(6, crearListaDeCiudades());
		ret.addEdge(ret.getVertex(0), ret.getVertex(1), 1);
		ret.addEdge(ret.getVertex(0), ret.getVertex(2), 100);
		ret.addEdge(ret.getVertex(1), ret.getVertex(2), 3);
		ret.addEdge(ret.getVertex(1), ret.getVertex(3), 100);
		ret.addEdge(ret.getVertex(1), ret.getVertex(5), 100);
		ret.addEdge(ret.getVertex(2), ret.getVertex(4), 34);
		ret.addEdge(ret.getVertex(4), ret.getVertex(3), 21);
		ret.addEdge(ret.getVertex(4), ret.getVertex(5), 45);
		return ret;
	}

	private WeightedGraph crearGrafoInconexo() {
		WeightedGraph ret = new WeightedGraph(6, crearListaDeCiudades());
		ret.addEdge(ret.getVertex(0), ret.getVertex(1), 23);
		ret.addEdge(ret.getVertex(0), ret.getVertex(2), 100);
		ret.addEdge(ret.getVertex(1), ret.getVertex(2), 93);
		ret.addEdge(ret.getVertex(1), ret.getVertex(3), 12);
		ret.addEdge(ret.getVertex(2), ret.getVertex(4), 14);
		ret.addEdge(ret.getVertex(3), ret.getVertex(4), 27);
		return ret;
	}
}
