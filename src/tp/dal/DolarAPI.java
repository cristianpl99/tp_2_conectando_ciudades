package tp.dal;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class DolarAPI {
	public static String API_URL = "https://api.bluelytics.com.ar/v2/latest";
	public static double defaultValue = 250;

	public static double getDolarValue() {
		double dolarValue = 0;
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
			dolarValue = parseDolarValueFromJson(json);
		} catch (Exception e) {
			System.err.println(
					"Error al obtener el valor del dólar blue de la API, se utilizará un default de $250 como valor: "
							+ e.getMessage());
			dolarValue = getDefaultValue();
		}
		return dolarValue;
	}

	private static double parseDolarValueFromJson(String json) {
		double dolarValue = 0;
		try {
			JSONObject jsonObject = new JSONObject(json);
			JSONObject blueObject = jsonObject.getJSONObject("oficial");
			dolarValue = blueObject.getDouble("value_avg");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return dolarValue;
	}

	public static double getDefaultValue() {
		return defaultValue;
	}

	public static String getApiUrl() {
		return API_URL;
	}
}
