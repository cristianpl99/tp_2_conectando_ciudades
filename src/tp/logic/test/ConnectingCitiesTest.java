package tp.logic.test;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tp.logic.City;
import tp.logic.ConnectingCities;

public class ConnectingCitiesTest {

	ConnectingCities connectingCities;

	@Before
	public void setUp() throws Exception {
		connectingCities = new ConnectingCities(440);
	}

	@Test
	public void testCreateValidCity() throws InvalidParameterException {
		City city = connectingCities.createCity("Buenos Aires", "Buenos Aires", -34.603722, -58.381592);
		assertNotNull(city);
	}

	@Test(expected = InvalidParameterException.class)
	public void testCreateInvalidCity() throws InvalidParameterException {
		connectingCities.createCity("La Plata", "xxxxx", 0, 0);
	}

	@Test
	public void testFetchCities() {
		List<City> cities = connectingCities.fetchCities();
		assertNotNull(cities);
		assertFalse(cities.isEmpty());
	}


	@Test
	public void testValidateCityParams() throws Exception {
		ConnectingCities connectingCities = new ConnectingCities(440);

		assertTrue(connectingCities.validateCityParams("Buenos Aires", "Buenos Aires", -34.603722, -58.381592));
		assertTrue(connectingCities.validateCityParams("San Salvador de Jujuy", "Jujuy", -24.185786, -65.299476));

		assertFalse(connectingCities.validateCityParams("Ushuaia", "Tierra del Fuego", -54.805890, -69.331392)); // Latitud
																													// fuera
																													// de
																													// rango
		assertFalse(connectingCities.validateCityParams("San Miguel de Tucumán", "Tucumán", -26.819819, -71.218904)); // Longitud
																														// fuera
																														// de
																														// rango
		assertFalse(connectingCities.validateCityParams("Mendoza", "xxxxx", -32.890183, -68.844050)); // Provincia
																										// invalida
	}

}
