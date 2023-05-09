package tp.dal;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import org.json.JSONException;

import tp.Config;
import tp.logic.City;

public class Data implements Idata {
	private List<City> cities;
	private List<String> provinces;
	private final int connectionTimeOut = Config.getTimerConnection();

	public Data() throws IOException, JSONException {
		try {
			if (isApiAvailable()) {
				CitiesAPI citiesAPI = new CitiesAPI();
				cities = citiesAPI.getCities();
				provinces = citiesAPI.getProvinces();
			} else {
				System.out.println("API no disponible. Obteniendo desde Persistence.");
				Persistence persistence = new Persistence();
				cities = persistence.getCities();
				provinces = persistence.getProvinces();
			}
		} catch (IOException | JSONException e) {
			System.out.println("Error al obtener los datos: " + e.getMessage());
			Persistence persistence = new Persistence();
			cities = persistence.getCities();
			provinces = persistence.getProvinces();
		}
	}

	private boolean isApiAvailable() {
		try {
			URL url = new URL(Config.getApiUrlCities());
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(connectionTimeOut);
			connection.connect();
			int responseCode = connection.getResponseCode();
			return responseCode == HttpURLConnection.HTTP_OK;
		} catch (IOException e) {
			return false;
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

