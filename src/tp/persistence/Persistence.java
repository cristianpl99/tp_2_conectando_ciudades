package tp.persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Persistence {
	String fileLocation = "src" + File.separator + "tp" + File.separator + "persistence" + File.separator
			+ "citiesList.txt";;
	File file = new File(fileLocation);
	
	public void saveCity(String[]city) {
		try {
			FileWriter writer = new FileWriter(file, true);
			writer.write(city[0] + "," + city[0] + "," +
					city[0] + "\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<String[]> fetchCities() {
		List<String[]> cities = new ArrayList<>();
		try {
			FileReader reader = new FileReader(file);
			BufferedReader buffer = new BufferedReader(reader);
			String line;

			while ((line = buffer.readLine()) != null) {
				String[] parts = line.split(",");
				cities.add(new String[] {parts[0],parts[1],parts[2],parts[3]});	
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cities;
	}

}
