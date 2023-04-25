package tp.logic;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class DolarAPI {
	public static String API_URL = "https://api.bluelytics.com.ar/v2/latest";
	public static double defaultValue = 440;

	public static double getDolarBlueValue() {
		double dolarBlueValue = 0;
		try {
			URL url = new URL(getApiUrl());
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			String json = response.toString();
			dolarBlueValue = parseDolarBlueValueFromJson(json);
		} catch (Exception e) {
			System.err.println(
					"Error al obtener el valor del dólar blue de la API, se utilizará un default de $400 como valor: "
							+ e.getMessage());
			dolarBlueValue = getDefaultValue();
		}
		return dolarBlueValue;
	}

	private static double parseDolarBlueValueFromJson(String json) {
		double dolarBlueValue = 0;
		try {
			JSONObject jsonObject = new JSONObject(json);
			JSONObject blueObject = jsonObject.getJSONObject("blue");
			dolarBlueValue = blueObject.getDouble("value_sell");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return dolarBlueValue;
	}

	public static double getDefaultValue() {
		return defaultValue;
	}

	public static String getApiUrl() {
		return API_URL;
	}
}
