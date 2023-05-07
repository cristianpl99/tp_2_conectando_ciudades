package tp.dal;

import java.util.ArrayList;
import java.util.List;
import tp.logic.City;

public interface IdataLoader {
    List<City> getCities();
    ArrayList<String> getProvinces();
}

