package tp.persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tp.logic.City;

public class Persistence {
/*
	String fileLocation = "src" + File.separator + "tp" + File.separator + "persistence" + File.separator
			+ "citiesList.txt";;
	File file = new File(fileLocation);
	*/

	String file = new File("C:\\Users\\crist\\eclipse-workspace\\tp_2_conectando_ciudades\\tp_2_conectando_ciudades\\src\\tp\\persistence\\citiesList.txt").getAbsolutePath();

	public List<City> fetchCities() {
		List<City> cities = new ArrayList<>();
		try {
			FileReader reader = new FileReader(file);
			BufferedReader buffer = new BufferedReader(reader);
			String line;

			while ((line = buffer.readLine()) != null) {
				String[] parts = line.split(",");
				cities.add(new City(parts[0], parts[1], Double.parseDouble(parts[2]), Double.parseDouble(parts[3])));
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cities;
	}

}
