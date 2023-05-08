package tp.dal;

import java.io.IOException;
import java.util.List;
import org.json.JSONException;
import tp.logic.City;

public class Data implements Idata {
	private List<City> cities;
	private List<String> provinces;

	public Data() throws IOException, JSONException {
		try {
			CitiesAPI citiesAPI = new CitiesAPI();
			cities = citiesAPI.getCities();
			provinces = citiesAPI.getUniqueProvinces();
		} catch (IOException | JSONException e) {
			System.out.println("Error al obtener los datos desde CitiesAPI. Obteniendo desde Persistence...");
			Persistence persistence = new Persistence();
			cities = persistence.getCities();
			provinces = persistence.getProvinces();
		}
	}

	@Override
	public List<City> getCities() throws JSONException {
		return cities;
	}

	@Override
	public List<String> getProvinces() throws JSONException {
		return provinces;
	}
}
