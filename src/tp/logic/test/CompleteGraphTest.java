package tp.logic.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import tp.logic.City;
import tp.logic.CompleteGraph;
import tp.logic.ConnectingCities;

import org.junit.Before;
import org.junit.Test;

public class CompleteGraphTest {
	
	private ConnectingCities connectingCities;
	private CompleteGraph completeGraph;

	@Before
	public void setUp() throws Exception {
		connectingCities = new ConnectingCities(440);
		completeGraph = new CompleteGraph(connectingCities.getCostPerKilometer());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateCompleteGraphException() throws Exception {
		List<City> cities = new ArrayList<City>();
		completeGraph.createCompleteWeightedGraph(cities);
	}

	@Test
	public void testCalculateWeight() throws Exception {
		
		// Testeamos que el costo de la arista es correcto seg�n la distancia y la
		// provincia
		City city1 = new City("Buenos Aires", "Buenos Aires", -58.381592, -34.603722);
		City city2 = new City("C�rdoba", "C�rdoba", -64.188776, -31.420083);
		assertEquals(1342598.0, completeGraph.calculateWeight(city1, city2), 0.0);

		// Testeamos que el costo se incrementa en un 10% si la distancia es mayor a 300
		// km
		City city3 = new City("Rosario", "Santa Fe", -60.639097, -32.947261);
		City city4 = new City("La Plata", "Buenos Aires", -57.954534, -34.921452);
		assertEquals(641390.0, completeGraph.calculateWeight(city3, city4), 0.0);

		// Testeamos que el costo se incrementa en 300 si las ciudades son de provincias
		// distintas
		City city5 = new City("Mendoza", "Mendoza", -68.84405, -32.890183);
		City city6 = new City("Salta", "Salta", -65.41204, -24.782126);
		assertEquals(1040486.0, completeGraph.calculateWeight(city5, city6), 0.0);
	}

	@Test
	public void testDistanceInKilometers() {
		double lat1 = 45.4642;
		double lon1 = 9.1900;
		double lat2 = 48.8566;
		double lon2 = 2.3522;
		double expectedDistance = 639.56;

		CompleteGraph cg = new CompleteGraph(500.0);
		double actualDistance = cg.distanceInKilometers(lat1, lon1, lat2, lon2);
		assertEquals(expectedDistance, actualDistance, 0.01);
	}
}
