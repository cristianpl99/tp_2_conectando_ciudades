package tp.logic.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import tp.logic.City;
import tp.logic.NeighborListGraph;

public class NeighborListGraphTest {

	private NeighborListGraph graph;
	private List<City> cities;

	@Before
	public void setUp() {
		cities = Arrays.asList(new City("Buenos Aires", "Buenos Aires", 0, 0), new City("Cordoba", "Cordoba", 0, 0),
				new City("Rosario", "Rosario", 0, 0));
		graph = new NeighborListGraph(cities.size(), cities);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullCities() {
		new NeighborListGraph(cities.size(), null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWrongNumVertexes() {
		List<City> cities = Arrays.asList(new City("Buenos Aires", null, 0, 0), new City("Córdoba", null, 0, 0));
		new NeighborListGraph(3, cities);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddNullVertex() {
		graph.addEdge(cities.get(0), null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddSameVertex() {
		graph.addEdge(cities.get(0), cities.get(0));
	}

	@Test
	public void testAddEdge() {
		graph.addEdge(cities.get(0), cities.get(1));
		assertTrue(graph.existsEdge(cities.get(0), cities.get(1)));
		assertTrue(graph.existsEdge(cities.get(1), cities.get(0)));
	}

	@Test
	public void testDeleteEdge() {
		graph.addEdge(cities.get(0), cities.get(1));
		graph.deleteEdge(cities.get(0), cities.get(1));
		assertFalse(graph.existsEdge(cities.get(0), cities.get(1)));
		assertFalse(graph.existsEdge(cities.get(1), cities.get(0)));
	}

	@Test
	public void testNeighbors() {
		graph.addEdge(cities.get(0), cities.get(1));
		graph.addEdge(cities.get(0), cities.get(2));
		Set<City> neighbors = graph.neighbors(cities.get(0));
		assertTrue(neighbors.contains(cities.get(1)));
		assertTrue(neighbors.contains(cities.get(2)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testVerifyVertexNull() {
		graph.verifyVertex(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testVerifyDistinctSame() {
		graph.verifyDistinct(cities.get(0), cities.get(0));
	}

	@Test
	public void testSize() {
		assertEquals(3, graph.size());
	}

}
