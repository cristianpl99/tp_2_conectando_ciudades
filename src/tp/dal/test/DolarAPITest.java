
package tp.dal.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tp.dal.DolarAPI;

public class DolarAPITest {
	private DolarAPI dolarAPI;

	@Before
	public void setUp() {
		dolarAPI = new DolarAPI();
	}

	@Test
	public void testGetDolarBlueValidValue() {
		double dolarValue = dolarAPI.getDolarValue();
		assertTrue(dolarValue > 0);
	}

	@Test
	public void testGetDolarValueDefault() {

		// Hacemos fallar la API cambiando el URL por uno que no existe
		String originalUrl = dolarAPI.getApiUrl();
		dolarAPI.setAPI_URL("https://api.bluelytics.com.ar/v2/nonexistent_url");
		// Verificar que se devuelve el valor por defecto
		assertEquals(dolarAPI.getDefaultValue(), dolarAPI.getDolarValue(), 0.0);
		// Restaurar el valor original de la API
		dolarAPI.setAPI_URL(originalUrl);
	}
}
