package tp.logic.test;

import org.junit.Before;
import org.junit.Test;

import tp.logic.City;
import tp.logic.WeightedGraph;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class WeightedGraphTest {

	private WeightedGraph weightedGraph;
	private List<City> cities;

	@Before
	public void setUp() {
		cities = new ArrayList<>();
		cities.add(new City("City1", "Province1", 0, 0));
		cities.add(new City("City2", "Province2", 1, 1));
		cities.add(new City("City3", "Province3", 2, 2));
		weightedGraph = new WeightedGraph(cities.size(), cities);
	}

	@Test
	public void testAddEdge() {
		City city1 = cities.get(0);
		City city2 = cities.get(1);
		double weight = 10.0;
		weightedGraph.addEdge(city1, city2, weight);

		assertEquals(weight, weightedGraph.getEdgeWeight(city1, city2), 0.001);
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testDeleteEdge() {
		City city1 = cities.get(0);
		City city2 = cities.get(1);
		double weight = 10.0;
		weightedGraph.addEdge(city1, city2, weight);
		assertEquals(weight, weightedGraph.getEdgeWeight(city1, city2), 0.001);
		weightedGraph.deleteEdge(city1, city2);
		try {
			weightedGraph.getEdgeWeight(city1, city2);
			fail("Se esperaba una excepción.");
		} catch (RuntimeException ex) {
			equals("No se encontro un peso entre " + city1.getName() + " y " + city2.getName());
		}
	}

	@Test
	public void testGetEdgeWeight() {
		City city1 = cities.get(0);
		City city2 = cities.get(1);
		double weight = 10.0;
		weightedGraph.addEdge(city1, city2, weight);
		assertEquals(weight, weightedGraph.getEdgeWeight(city1, city2), 0.001);
		assertEquals(weight, weightedGraph.getEdgeWeight(city2, city1), 0.001);
	}
}
