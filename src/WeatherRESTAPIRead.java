
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 * @author navneethnarendra
 *
 */
public class WeatherRESTAPIRead {

	private static final String API_KEY = "d1158b740a06486ee301974b05f77baf";

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter city : ");
		String city = scanner.next();
		System.out.println("Enter country : ");
		String country = scanner.next();

		String jsonResponse = invokeWeatherRESTapi(city, country);

		readJSONoutput(jsonResponse);
	}

	private static String invokeWeatherRESTapi(String city, String country) throws Exception {

		String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "," + country + "&appid=" + API_KEY;
		final String USER_AGENT = "Mozilla/5.0";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response.toString();

	}

	private static void readJSONoutput(String response) {
		Gson gson = new GsonBuilder().create();
		JsonObject job = gson.fromJson(response.toString(), JsonObject.class);

		// if (job.get("cod").toString().equals("200")) {

		System.out.println("CITY : " + job.get("name") + " COUNTRY : " + job.getAsJsonObject("sys").get("country")
				+ " RAIN : " + job.getAsJsonArray("weather").get(0).getAsJsonObject().get("description") + " HIGH : "
				+ job.getAsJsonObject("main").get("temp_max") + " LOW : "
				+ job.getAsJsonObject("main").get("temp_min"));
		// } else {
		// System.out.println(job.get("message"));
		// }

	}

}
