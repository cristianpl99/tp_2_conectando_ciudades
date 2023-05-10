package tp.logic.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Random;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import tp.logic.City;
import tp.logic.ConnectingCities;

public class ConnectingCitiesTest {

	ConnectingCities connectingCities;

	@Before
	public void setUp() throws Exception {
		Random rand = new Random();
		int costPerKilometerRandom = rand.nextInt(500) + 1; // genera un n�mero aleatorio del 1 al 500
		int increaseLongDistanceRandom = rand.nextInt(101); // genera un n�mero aleatorio de tipo int del 0 al 100
		int fixedCrossProvincialCostRandom = rand.nextInt(501); // genera un n�mero aleatorio de tipo int del 0 al 500
		connectingCities = new ConnectingCities(costPerKilometerRandom, increaseLongDistanceRandom,
				fixedCrossProvincialCostRandom);
	}

	@Test
	public void testCreateValidCity() throws InvalidParameterException, JSONException, IOException {
		City city = connectingCities.createCity("Buenos Aires", "Buenos Aires", -34.603722, -58.381592);
		assertNotNull(city);
	}

	@Test(expected = InvalidParameterException.class)
	public void testCreateInvalidCity() throws InvalidParameterException, JSONException, IOException {
		connectingCities.createCity("La Plata", "xxxxx", 0, 0);
	}
	
	@Test
	public void testAddCity() {
	    City city = new City("Buenos Aires", "Buenos Aires", -34.603722, -58.381592);
	    connectingCities.addCity(city);
	    assertEquals(1, connectingCities.getSize());
	    assertTrue(connectingCities.containsCity(city));
	}

	@Test
	public void testContainsCity() {
	    City city = new City("Buenos Aires", "Buenos Aires", -34.603722, -58.381592);
	    assertFalse(connectingCities.containsCity(city));
	    connectingCities.addCity(city);
	    assertTrue(connectingCities.containsCity(city));
	}

	@Test
	public void testRemoveCity() {
	    City city = new City("Buenos Aires", "Buenos Aires", -34.603722, -58.381592);
	    connectingCities.addCity(city);
	    assertEquals(1, connectingCities.getSize());
	    connectingCities.removeCity(0);
	    assertEquals(0, connectingCities.getSize());
	    assertFalse(connectingCities.containsCity(city));
	}

	@Test
	public void testGetSize() {
	    City city1 = new City("Buenos Aires", "Buenos Aires", -34.603722, -58.381592);
	    City city2 = new City("C�rdoba", "C�rdoba", -31.420083, -64.188776);
	    connectingCities.addCity(city1);
	    connectingCities.addCity(city2);
	    assertEquals(2, connectingCities.getSize());
	}

	@Test
	public void testGetCitiesList() {
	    City city1 = new City("Buenos Aires", "Buenos Aires", -34.603722, -58.381592);
	    City city2 = new City("C�rdoba", "C�rdoba", -31.420083, -64.188776);
	    connectingCities.addCity(city1);
	    connectingCities.addCity(city2);
	    List<City> cities = connectingCities.getCitiesList();
	    assertNotNull(cities);
	    assertEquals(2, cities.size());
	    assertTrue(cities.contains(city1));
	    assertTrue(cities.contains(city2));
	}

	@Test
	public void testFetchCities() throws JSONException, IOException {
		List<City> cities = connectingCities.fetchCities();
		assertNotNull(cities);
		assertFalse(cities.isEmpty());
	}

	@Test
	public void testValidateCityParams() throws Exception {
		// Happy path
		assertTrue(connectingCities.validateCityParams("Buenos Aires", "Buenos Aires", -34.603722, -58.381592));
		assertTrue(connectingCities.validateCityParams("San Salvador de Jujuy", "Jujuy", -24.185786, -65.299476));
		// Latitud fuera de rango
		assertFalse(connectingCities.validateCityParams("Ushuaia", "Tierra del Fuego", -54.805890, -69.331392));
		// Longitud fuera de rango // d // rango
		assertFalse(connectingCities.validateCityParams("San Miguel de Tucum�n", "Tucum�n", -26.819819, -71.218904));
		// Provincia invalida
		assertFalse(connectingCities.validateCityParams("Mendoza", "xxxxx", -32.890183, -68.844050));
	}
}
