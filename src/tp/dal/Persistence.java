package tp.dal;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import tp.logic.City;

public class Persistence implements Saveable {
    private String fileLocation = "src" + java.io.File.separator + "tp" + java.io.File.separator + "dal"
            + java.io.File.separator + "citiesList.json";
    private java.io.File file = new java.io.File(fileLocation);

    @Override
    public List<City> getCities() {
        List<City> cities = new ArrayList<>();
        try {
            FileReader reader = new FileReader(file);
            Gson gson = new Gson();
            List<City> cityList = gson.fromJson(reader, new TypeToken<List<City>>() {}.getType());
            cities.addAll(cityList);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cities;
    }

    @Override
    public ArrayList<String> getProvinces() {
        ArrayList<String> provinces = new ArrayList<>();

        try {
            FileReader reader = new FileReader(file);
            Gson gson = new Gson();
            List<City> cityList = gson.fromJson(reader, new TypeToken<List<City>>() {}.getType());
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

