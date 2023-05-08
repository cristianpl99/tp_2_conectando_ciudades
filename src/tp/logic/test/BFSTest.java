package tp.logic.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tp.logic.BFS;
import tp.logic.City;
import tp.logic.NeighborListGraph;

public class BFSTest {
	private BFS bfs;

	@Before
	public void setUp() {
		bfs = new BFS();
	}

	@Test(expected = IllegalArgumentException.class)
	public void NullGraphTest() {
		NeighborListGraph g = null;
		assertTrue(bfs.isConnected(g));
	}

	public void EmptyGraphTest() {
		List<City> c = new ArrayList<>();
		NeighborListGraph g = new NeighborListGraph(0, c);
		assertTrue(bfs.isConnected(g));
	}

	@Test
	public void OneVertexFraphTest() {
		List<City> c = new ArrayList<>();
		c.add(new City("a", "a", 23, 34));
		NeighborListGraph g = new NeighborListGraph(1, c);
		assertTrue(bfs.isConnected(g));
	}

	@Test
	public void IsolatedVertexesGraphTest() {
		List<City> c = new ArrayList<>();
		c.add(new City("a", "a", 23, 34));
		c.add(new City("b", "b", 24, 33));
		NeighborListGraph g = new NeighborListGraph(2, c);
		assertFalse(bfs.isConnected(g));
	}

	@Test
	public void connectedVertexesGraphTest() {
		List<City> cities = new ArrayList<>();
		cities.add(new City("a", "a", 23, 34));
		cities.add(new City("b", "b", 24, 33));
		NeighborListGraph graph = new NeighborListGraph(2, cities);
		graph.addEdge(graph.getVertex(0), graph.getVertex(1));
		assertTrue(bfs.isConnected(graph));
	}

	@Test
	public void disconnectedGraphTest() {
		NeighborListGraph graph = createDisconnectedGraph();
		assertFalse(bfs.isConnected(graph));
	}

	@Test
	public void connectedGraphTest() {
		NeighborListGraph graph = createConnectedGraph();
		assertTrue(bfs.isConnected(graph));
	}

	private static List<City> createCityList() {
		List<City> cities = new ArrayList<>();
		cities.add(new City("a", "a", 23, 34));
		cities.add(new City("b", "b", 24, 33));
		cities.add(new City("c", "c", 25, 32));
		cities.add(new City("d", "d", 26, 31));
		cities.add(new City("e", "e", 27, 30));
		cities.add(new City("f", "f", 28, 39));
		return cities;
	}

	private static NeighborListGraph createDisconnectedGraph() {
		NeighborListGraph graph = new NeighborListGraph(6, createCityList());
		graph.addEdge(graph.getVertex(0), graph.getVertex(1));
		graph.addEdge(graph.getVertex(0), graph.getVertex(2));
		graph.addEdge(graph.getVertex(1), graph.getVertex(2));
		graph.addEdge(graph.getVertex(1), graph.getVertex(3));
		graph.addEdge(graph.getVertex(2), graph.getVertex(4));
		graph.addEdge(graph.getVertex(3), graph.getVertex(4));
		return graph;
	}

	private static NeighborListGraph createConnectedGraph() {
		NeighborListGraph graph = new NeighborListGraph(6, createCityList());
		graph.addEdge(graph.getVertex(0), graph.getVertex(1));
		graph.addEdge(graph.getVertex(0), graph.getVertex(2));
		graph.addEdge(graph.getVertex(1), graph.getVertex(2));
		graph.addEdge(graph.getVertex(1), graph.getVertex(3));
		graph.addEdge(graph.getVertex(2), graph.getVertex(4));
		graph.addEdge(graph.getVertex(3), graph.getVertex(4));
		graph.addEdge(graph.getVertex(4), graph.getVertex(5));
		return graph;
	}

}
