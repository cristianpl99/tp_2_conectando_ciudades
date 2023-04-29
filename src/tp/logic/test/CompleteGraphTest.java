package tp.logic.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import tp.logic.City;
import tp.logic.CompleteGraph;

import org.junit.Test;

public class CompleteGraphTest {
	
	@Test
	public void testCreateCompleteGraph() throws Exception {
		// Creamos una lista de ciudades de prueba
		List<City> cities = new ArrayList<City>();
		City city1 = new City("Buenos Aires", "Buenos Aires", -58.381592, -34.603722);
		City city2 = new City("Córdoba", "Córdoba", -64.188776, -31.420083);
		cities.add(city1);
		cities.add(city2);
		
		CompleteGraph completeGraph = new CompleteGraph();
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateCompleteGraphException() throws Exception {
		List<City> cities = new ArrayList<City>();
		CompleteGraph completeGraph = new CompleteGraph();
		completeGraph.createCompleteGraph(cities);
	}

	@Test
	public void testCalculateWeight() throws Exception {
		CompleteGraph completeGraph = new CompleteGraph();
		
		// Testeamos que el costo de la arista es correcto según la distancia y la provincia
		City city1 = new City("Buenos Aires", "Buenos Aires", -58.381592, -34.603722);
		City city2 = new City("Córdoba", "Córdoba", -64.188776, -31.420083);
		assertEquals(2755390.0, completeGraph.calculateWeight(city1, city2), 0.1);
		
		// Testeamos que el costo se incrementa en un 10% si la distancia es mayor a 300 km
		City city3 = new City("Rosario", "Santa Fe", -60.639097, -32.947261);
		City city4 = new City("La Plata", "Buenos Aires", -57.954534, -34.921452);
		assertEquals(1316147.0, completeGraph.calculateWeight(city3, city4), 0.1);
		
		// Testeamos que el costo se incrementa en 300 si las ciudades son de provincias distintas
		City city5 = new City("Mendoza", "Mendoza", -68.84405, -32.890183);
		City city6 = new City("Salta", "Salta", -65.41204, -24.782126);
		assertEquals(2135299.0, completeGraph.calculateWeight(city5, city6), 0.0);
	}


		@Test
		public void testDistanceInKilometers() {
			double lat1 = 45.4642;
			double lon1 = 9.1900;
			double lat2 = 48.8566;
			double lon2 = 2.3522;
			double expectedDistance = 639.56;
			
			CompleteGraph cg = new CompleteGraph();
			double actualDistance = cg.distanceInKilometers(lat1, lon1, lat2, lon2);
			assertEquals(expectedDistance, actualDistance, 0.1);
		}
	}
