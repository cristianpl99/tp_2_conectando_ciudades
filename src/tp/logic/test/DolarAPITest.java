package tp.logic.test;

import static org.junit.Assert.*;

import org.junit.Test;

import tp.logic.DolarAPI;

public class DolarAPITest {

	@Test
	public void testGetDolarBlueValidValue() {
		double dolarBlueValue = DolarAPI.getDolarBlueValue();
		assertTrue(dolarBlueValue > 0);
	}

	@Test
	public void testGetDolarBlueValueDefault() {

		// Hacemos fallar la API cambiando el URL por uno que no existe
		String originalUrl = DolarAPI.getApiUrl();
		DolarAPI.API_URL = "https://api.bluelytics.com.ar/v2/nonexistent_url";

		// Verificar que se devuelve el valor por defecto
		assertEquals(DolarAPI.defaultValue, DolarAPI.getDolarBlueValue(), 0.01);

		// Restaurar el valor original de la API
		DolarAPI.API_URL = originalUrl;
	}
}
