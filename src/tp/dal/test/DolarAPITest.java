package tp.dal.test;

import static org.junit.Assert.*;

import org.junit.Test;

import tp.dal.DolarAPI;

public class DolarAPITest {

	@Test
	public void testGetDolarBlueValidValue() {
		double dolarBlueValue = DolarAPI.getDolarValue();
		assertTrue(dolarBlueValue > 0);
	}

	@Test
	public void testGetDolarBlueValueDefault() {

		// Hacemos fallar la API cambiando el URL por uno que no existe
		String originalUrl = DolarAPI.getApiUrl();
		DolarAPI.API_URL = "https://api.bluelytics.com.ar/v2/nonexistent_url";
		// Verificar que se devuelve el valor por defecto
		assertEquals(DolarAPI.defaultValue, DolarAPI.getDolarValue(), 0.0);
		// Restaurar el valor original de la API
		DolarAPI.API_URL = originalUrl;
	}
}
