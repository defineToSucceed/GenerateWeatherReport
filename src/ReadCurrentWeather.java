
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 * @author 	navneethnarendra
 * 
 * @desc	current weather state	
 *
 */
public class ReadCurrentWeather {

	private static final String API_KEY = "d1158b740a06486ee301974b05f77baf";

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter city : ");
		String city = scanner.next();
		System.out.println("Enter country : ");
		String country = scanner.next();

		invokeWeatherRESTapi(city, country);

	}

	private static void invokeWeatherRESTapi(String city, String country) throws Exception {

		String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "," + country + "&appid=" + API_KEY;
		final String USER_AGENT = "Mozilla/5.0";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);

		if (con.getResponseCode() == 200) {

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			readJSONoutput(response.toString());

		} else {
			System.out.println("City/Country combination Not Found");
		}

	}

	private static void readJSONoutput(String response) {
		Gson gson = new GsonBuilder().create();
		JsonObject job = gson.fromJson(response.toString(), JsonObject.class);

		System.out.println("CITY : " + job.get("name") 
				+ " COUNTRY : " + job.getAsJsonObject("sys").get("country")
				+ " RAIN : " + job.getAsJsonArray("weather").get(0).getAsJsonObject().get("description") 
				+ " HIGH : " + job.getAsJsonObject("main").get("temp_max") 
				+ " LOW : " + job.getAsJsonObject("main").get("temp_min"));
	}
	
	/* response payload
	{	
		"coord":{"lon":77.6,"lat":12.98},
		"weather":[{"id":701,"main":"Mist","description":"mist","icon":"50n"}],
		"base":"stations",
		"main":{"temp":289.15,"pressure":1016,"humidity":100,"temp_min":289.15,"temp_max":289.15},
		"visibility":5000,
		"wind":{"speed":1},
		"clouds":{"all":0},
		"dt":1540330200,
		"sys":{"type":1,"id":7823,"message":0.0042,"country":"IN","sunrise":1540255273,"sunset":1540297573},
		"id":1277333,
		"name":"Bangalore",
		"cod":200
	}
	*/
}
