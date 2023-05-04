package tp.dal;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import tp.logic.City;

public class Persistence {
	String fileLocation = "src" + java.io.File.separator + "tp" + java.io.File.separator + "dal"
			+ java.io.File.separator + "citiesList.json";
	java.io.File file = new java.io.File(fileLocation);

	public List<City> fetchCities() {
		List<City> cities = new ArrayList<>();
		try {
			FileReader reader = new FileReader(file);
			Gson gson = new Gson();
			List<City> cityList = gson.fromJson(reader, new TypeToken<List<City>>() {
			}.getType());
			cities.addAll(cityList);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cities;
	}

	public ArrayList<String> fetchProvinces() {
		ArrayList<String> provinces = new ArrayList<>();

		try {
			FileReader reader = new FileReader(file);
			Gson gson = new Gson();
			List<City> cityList = gson.fromJson(reader, new TypeToken<List<City>>() {
			}.getType());
			for (City city : cityList) {
				String province = city.getProvince();
				if (!provinces.contains(province)) {
					provinces.add(province);
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return provinces;
	}
}
