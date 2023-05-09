package tp;

public class Config {
    private static String apiUrlCities = "https://infra.datos.gob.ar/catalog/modernizacion/dataset/7/distribution/7.4/download/municipios.json";
    private static String apiUrlUSD = "https://api.bluelytics.com.ar/v2/latest";
    private static double defaultUSDValue = 250;
    private static int maximumCostPerKilometer = 500;
    private static int crossProvinceCablingCost = 500;
    
	public static int getMaximumCostPerKilometer() {
		return maximumCostPerKilometer;
	}

	public static int getCrossProvinceCablingCost() {
		return crossProvinceCablingCost;
	}

	public static String getApiUrlCities() {
		return apiUrlCities;
	}

	public static String getApiUrlUSD() {
		return apiUrlUSD;
	}
	
	public static double defaultUSDValue() {
		return defaultUSDValue;
	}   
}

