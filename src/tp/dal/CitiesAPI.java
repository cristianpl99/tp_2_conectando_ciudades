package tp.dal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

import tp.logic.City;

public class CitiesAPI {
	private String apiUrl = "https://infra.datos.gob.ar/catalog/modernizacion/dataset/7/distribution/7.4/download/municipios.json";

	public List<City> getCities() throws IOException, JSONException {
		List<City> cities = new ArrayList<>();

		JSONArray municipios = readJsonDataFromUrl(apiUrl).getJSONArray("municipios");

		for (int i = 0; i < municipios.length(); i++) {
			JSONObject municipio = municipios.getJSONObject(i);
			String name = municipio.getString("nombre");
			String province = municipio.getJSONObject("provincia").getString("nombre");
			double latitude = municipio.getJSONObject("centroide").getDouble("lat");
			double longitude = municipio.getJSONObject("centroide").getDouble("lon");

			City city = new City(name, province, latitude, longitude);
			cities.add(city);
		}

		Collections.sort(cities, new Comparator<City>() {
			@Override
			public int compare(City c1, City c2) {
				return c1.getName().compareToIgnoreCase(c2.getName());
			}
		});

		return cities;
	}

	public List<String> getUniqueProvinces() throws IOException, JSONException {
		List<String> provinces = new ArrayList<>();

		JSONArray municipios = readJsonDataFromUrl(apiUrl).getJSONArray("municipios");

		for (int i = 0; i < municipios.length(); i++) {
			JSONObject municipio = municipios.getJSONObject(i);
			String province = municipio.getJSONObject("provincia").getString("nombre");
			provinces.add(province);
		}

		return new ArrayList<>(provinces);
	}

	private JSONObject readJsonDataFromUrl(String apiUrl) throws IOException, JSONException {
		URL url = new URL(apiUrl);
		try (java.util.Scanner scanner = new java.util.Scanner(url.openStream(), "UTF-8").useDelimiter("\\A")) {
			String jsonString = scanner.next();
			scanner.close();
			return new JSONObject(jsonString);
		}
	}
}
