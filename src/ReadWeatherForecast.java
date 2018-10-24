
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
 * @desc	5-day forecast
 *
 */
public class ReadWeatherForecast {

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

		String url = "http://api.openweathermap.org/data/2.5/forecast?q=" + city + "," + country + "&appid=" + API_KEY;
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
		JsonObject job = gson.fromJson(response, JsonObject.class);

		for (int i = 0; i <= job.getAsJsonArray("list").size() - 1; i++) {
			System.out.println("CITY : " + job.getAsJsonObject("city").get("name") 
					+ " COUNTRY : " + job.getAsJsonObject("city").get("country")
					+ " : DATE : " + job.getAsJsonArray("list").get(i).getAsJsonObject().get("dt_txt")
					+ " RAIN : " + job.getAsJsonArray("list").get(i).getAsJsonObject().get("weather").getAsJsonArray().get(0)
							.getAsJsonObject().get("description")
					+ " HIGH : " + job.getAsJsonArray("list").get(i).getAsJsonObject().get("main").getAsJsonObject().get("temp_max")
					+ " LOW : " + job.getAsJsonArray("list").get(i).getAsJsonObject().get("main").getAsJsonObject()
							.get("temp_min"));
		}

	}
	
	/* response payload 
	
	{
		  "cod": "200",
		  "message": 0.0037,
		  "cnt": 40,
		  "list": [
		    {
		      "dt": 1540425600,
		      "main": {
		        "temp": 287.3,
		        "temp_min": 287.3,
		        "temp_max": 289.124,
		        "pressure": 928.94,
		        "sea_level": 1026.1,
		        "grnd_level": 928.94,
		        "humidity": 88,
		        "temp_kf": -1.82
		      },
		      "weather": [
		        {
		          "id": 800,
		          "main": "Clear",
		          "description": "clear sky",
		          "icon": "02n"
		        }
		      ],
		      "clouds": {
		        "all": 8
		      },
		      "wind": {
		        "speed": 2.72,
		        "deg": 96.5004
		      },
		      "sys": {
		        "pod": "n"
		      },
		      "dt_txt": "2018-10-25 00:00:00"
		    },
		    {
		      "dt": 1540436400,
		      "main": {
		        "temp": 295.24,
		        "temp_min": 295.24,
		        "temp_max": 296.45,
		        "pressure": 930.65,
		        "sea_level": 1027.51,
		        "grnd_level": 930.65,
		        "humidity": 54,
		        "temp_kf": -1.21
		      },
		      "weather": [
		        {
		          "id": 800,
		          "main": "Clear",
		          "description": "clear sky",
		          "icon": "02d"
		        }
		      ],
		      "clouds": {
		        "all": 8
		      },
		      "wind": {
		        "speed": 2.88,
		        "deg": 78.5039
		      },
		      "sys": {
		        "pod": "d"
		      },
		      "dt_txt": "2018-10-25 03:00:00"
		    },
		    {
		      "dt": 1540447200,
		      "main": {
		        "temp": 301.59,
		        "temp_min": 301.59,
		        "temp_max": 302.193,
		        "pressure": 929.82,
		        "sea_level": 1025.82,
		        "grnd_level": 929.82,
		        "humidity": 47,
		        "temp_kf": -0.61
		      },
		      "weather": [
		        {
		          "id": 800,
		          "main": "Clear",
		          "description": "clear sky",
		          "icon": "01d"
		        }
		      ],
		      "clouds": {
		        "all": 0
		      },
		      "wind": {
		        "speed": 5.66,
		        "deg": 71.0032
		      },
		      "sys": {
		        "pod": "d"
		      },
		      "dt_txt": "2018-10-25 06:00:00"
		    },
		    {
		      "dt": 1540458000,
		      "main": {
		        "temp": 303.395,
		        "temp_min": 303.395,
		        "temp_max": 303.395,
		        "pressure": 927.44,
		        "sea_level": 1022.99,
		        "grnd_level": 927.44,
		        "humidity": 43,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 800,
		          "main": "Clear",
		          "description": "clear sky",
		          "icon": "01d"
		        }
		      ],
		      "clouds": {
		        "all": 0
		      },
		      "wind": {
		        "speed": 5.87,
		        "deg": 73.5104
		      },
		      "sys": {
		        "pod": "d"
		      },
		      "dt_txt": "2018-10-25 09:00:00"
		    },
		    {
		      "dt": 1540468800,
		      "main": {
		        "temp": 301.336,
		        "temp_min": 301.336,
		        "temp_max": 301.336,
		        "pressure": 927.57,
		        "sea_level": 1023.38,
		        "grnd_level": 927.57,
		        "humidity": 43,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 802,
		          "main": "Clouds",
		          "description": "scattered clouds",
		          "icon": "03d"
		        }
		      ],
		      "clouds": {
		        "all": 32
		      },
		      "wind": {
		        "speed": 4.75,
		        "deg": 82.0009
		      },
		      "sys": {
		        "pod": "d"
		      },
		      "dt_txt": "2018-10-25 12:00:00"
		    },
		    {
		      "dt": 1540479600,
		      "main": {
		        "temp": 299.072,
		        "temp_min": 299.072,
		        "temp_max": 299.072,
		        "pressure": 929.93,
		        "sea_level": 1026.34,
		        "grnd_level": 929.93,
		        "humidity": 53,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 804,
		          "main": "Clouds",
		          "description": "overcast clouds",
		          "icon": "04n"
		        }
		      ],
		      "clouds": {
		        "all": 100
		      },
		      "wind": {
		        "speed": 4.66,
		        "deg": 100.003
		      },
		      "sys": {
		        "pod": "n"
		      },
		      "dt_txt": "2018-10-25 15:00:00"
		    },
		    {
		      "dt": 1540490400,
		      "main": {
		        "temp": 295.92,
		        "temp_min": 295.92,
		        "temp_max": 295.92,
		        "pressure": 930.51,
		        "sea_level": 1027.36,
		        "grnd_level": 930.51,
		        "humidity": 68,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 800,
		          "main": "Clear",
		          "description": "clear sky",
		          "icon": "01n"
		        }
		      ],
		      "clouds": {
		        "all": 0
		      },
		      "wind": {
		        "speed": 4.11,
		        "deg": 111
		      },
		      "sys": {
		        "pod": "n"
		      },
		      "dt_txt": "2018-10-25 18:00:00"
		    },
		    {
		      "dt": 1540501200,
		      "main": {
		        "temp": 292.003,
		        "temp_min": 292.003,
		        "temp_max": 292.003,
		        "pressure": 929.48,
		        "sea_level": 1026.48,
		        "grnd_level": 929.48,
		        "humidity": 90,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 800,
		          "main": "Clear",
		          "description": "clear sky",
		          "icon": "01n"
		        }
		      ],
		      "clouds": {
		        "all": 0
		      },
		      "wind": {
		        "speed": 3.02,
		        "deg": 94.5026
		      },
		      "sys": {
		        "pod": "n"
		      },
		      "dt_txt": "2018-10-25 21:00:00"
		    },
		    {
		      "dt": 1540512000,
		      "main": {
		        "temp": 290.447,
		        "temp_min": 290.447,
		        "temp_max": 290.447,
		        "pressure": 929.67,
		        "sea_level": 1026.99,
		        "grnd_level": 929.67,
		        "humidity": 96,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 800,
		          "main": "Clear",
		          "description": "clear sky",
		          "icon": "01n"
		        }
		      ],
		      "clouds": {
		        "all": 0
		      },
		      "wind": {
		        "speed": 3.43,
		        "deg": 81.5021
		      },
		      "sys": {
		        "pod": "n"
		      },
		      "dt_txt": "2018-10-26 00:00:00"
		    },
		    {
		      "dt": 1540522800,
		      "main": {
		        "temp": 295.219,
		        "temp_min": 295.219,
		        "temp_max": 295.219,
		        "pressure": 931.53,
		        "sea_level": 1028.56,
		        "grnd_level": 931.53,
		        "humidity": 79,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 800,
		          "main": "Clear",
		          "description": "clear sky",
		          "icon": "02d"
		        }
		      ],
		      "clouds": {
		        "all": 8
		      },
		      "wind": {
		        "speed": 4.01,
		        "deg": 85.5035
		      },
		      "sys": {
		        "pod": "d"
		      },
		      "dt_txt": "2018-10-26 03:00:00"
		    },
		    {
		      "dt": 1540533600,
		      "main": {
		        "temp": 301.29,
		        "temp_min": 301.29,
		        "temp_max": 301.29,
		        "pressure": 930.96,
		        "sea_level": 1027.22,
		        "grnd_level": 930.96,
		        "humidity": 58,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 800,
		          "main": "Clear",
		          "description": "clear sky",
		          "icon": "02d"
		        }
		      ],
		      "clouds": {
		        "all": 8
		      },
		      "wind": {
		        "speed": 5.8,
		        "deg": 75.5014
		      },
		      "sys": {
		        "pod": "d"
		      },
		      "dt_txt": "2018-10-26 06:00:00"
		    },
		    {
		      "dt": 1540544400,
		      "main": {
		        "temp": 302.867,
		        "temp_min": 302.867,
		        "temp_max": 302.867,
		        "pressure": 928.27,
		        "sea_level": 1024.01,
		        "grnd_level": 928.27,
		        "humidity": 48,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 800,
		          "main": "Clear",
		          "description": "clear sky",
		          "icon": "01d"
		        }
		      ],
		      "clouds": {
		        "all": 0
		      },
		      "wind": {
		        "speed": 5.76,
		        "deg": 72.5071
		      },
		      "sys": {
		        "pod": "d"
		      },
		      "dt_txt": "2018-10-26 09:00:00"
		    },
		    {
		      "dt": 1540555200,
		      "main": {
		        "temp": 300.94,
		        "temp_min": 300.94,
		        "temp_max": 300.94,
		        "pressure": 928.29,
		        "sea_level": 1024.17,
		        "grnd_level": 928.29,
		        "humidity": 47,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 803,
		          "main": "Clouds",
		          "description": "broken clouds",
		          "icon": "04d"
		        }
		      ],
		      "clouds": {
		        "all": 80
		      },
		      "wind": {
		        "speed": 5.17,
		        "deg": 74.0049
		      },
		      "sys": {
		        "pod": "d"
		      },
		      "dt_txt": "2018-10-26 12:00:00"
		    },
		    {
		      "dt": 1540566000,
		      "main": {
		        "temp": 298.876,
		        "temp_min": 298.876,
		        "temp_max": 298.876,
		        "pressure": 930.77,
		        "sea_level": 1027.16,
		        "grnd_level": 930.77,
		        "humidity": 53,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 803,
		          "main": "Clouds",
		          "description": "broken clouds",
		          "icon": "04n"
		        }
		      ],
		      "clouds": {
		        "all": 64
		      },
		      "wind": {
		        "speed": 4.82,
		        "deg": 80.005
		      },
		      "sys": {
		        "pod": "n"
		      },
		      "dt_txt": "2018-10-26 15:00:00"
		    },
		    {
		      "dt": 1540576800,
		      "main": {
		        "temp": 294.994,
		        "temp_min": 294.994,
		        "temp_max": 294.994,
		        "pressure": 931.43,
		        "sea_level": 1028.28,
		        "grnd_level": 931.43,
		        "humidity": 68,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 800,
		          "main": "Clear",
		          "description": "clear sky",
		          "icon": "01n"
		        }
		      ],
		      "clouds": {
		        "all": 0
		      },
		      "wind": {
		        "speed": 3.86,
		        "deg": 85.0004
		      },
		      "sys": {
		        "pod": "n"
		      },
		      "dt_txt": "2018-10-26 18:00:00"
		    },
		    {
		      "dt": 1540587600,
		      "main": {
		        "temp": 292.037,
		        "temp_min": 292.037,
		        "temp_max": 292.037,
		        "pressure": 930.15,
		        "sea_level": 1027.2,
		        "grnd_level": 930.15,
		        "humidity": 83,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 800,
		          "main": "Clear",
		          "description": "clear sky",
		          "icon": "02n"
		        }
		      ],
		      "clouds": {
		        "all": 8
		      },
		      "wind": {
		        "speed": 3.16,
		        "deg": 77.0004
		      },
		      "sys": {
		        "pod": "n"
		      },
		      "dt_txt": "2018-10-26 21:00:00"
		    },
		    {
		      "dt": 1540598400,
		      "main": {
		        "temp": 290.226,
		        "temp_min": 290.226,
		        "temp_max": 290.226,
		        "pressure": 930.08,
		        "sea_level": 1027.35,
		        "grnd_level": 930.08,
		        "humidity": 87,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 801,
		          "main": "Clouds",
		          "description": "few clouds",
		          "icon": "02n"
		        }
		      ],
		      "clouds": {
		        "all": 12
		      },
		      "wind": {
		        "speed": 3.22,
		        "deg": 65.0013
		      },
		      "sys": {
		        "pod": "n"
		      },
		      "dt_txt": "2018-10-27 00:00:00"
		    },
		    {
		      "dt": 1540609200,
		      "main": {
		        "temp": 296.042,
		        "temp_min": 296.042,
		        "temp_max": 296.042,
		        "pressure": 931.48,
		        "sea_level": 1028.47,
		        "grnd_level": 931.48,
		        "humidity": 57,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 801,
		          "main": "Clouds",
		          "description": "few clouds",
		          "icon": "02d"
		        }
		      ],
		      "clouds": {
		        "all": 20
		      },
		      "wind": {
		        "speed": 3.86,
		        "deg": 65.001
		      },
		      "sys": {
		        "pod": "d"
		      },
		      "dt_txt": "2018-10-27 03:00:00"
		    },
		    {
		      "dt": 1540620000,
		      "main": {
		        "temp": 301.039,
		        "temp_min": 301.039,
		        "temp_max": 301.039,
		        "pressure": 930.94,
		        "sea_level": 1027.07,
		        "grnd_level": 930.94,
		        "humidity": 47,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 801,
		          "main": "Clouds",
		          "description": "few clouds",
		          "icon": "02d"
		        }
		      ],
		      "clouds": {
		        "all": 24
		      },
		      "wind": {
		        "speed": 5.8,
		        "deg": 60.5017
		      },
		      "sys": {
		        "pod": "d"
		      },
		      "dt_txt": "2018-10-27 06:00:00"
		    },
		    {
		      "dt": 1540630800,
		      "main": {
		        "temp": 302.42,
		        "temp_min": 302.42,
		        "temp_max": 302.42,
		        "pressure": 928.32,
		        "sea_level": 1024.12,
		        "grnd_level": 928.32,
		        "humidity": 43,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 801,
		          "main": "Clouds",
		          "description": "few clouds",
		          "icon": "02d"
		        }
		      ],
		      "clouds": {
		        "all": 12
		      },
		      "wind": {
		        "speed": 5.56,
		        "deg": 60.5074
		      },
		      "sys": {
		        "pod": "d"
		      },
		      "dt_txt": "2018-10-27 09:00:00"
		    },
		    {
		      "dt": 1540641600,
		      "main": {
		        "temp": 300.564,
		        "temp_min": 300.564,
		        "temp_max": 300.564,
		        "pressure": 928.4,
		        "sea_level": 1024.26,
		        "grnd_level": 928.4,
		        "humidity": 43,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 801,
		          "main": "Clouds",
		          "description": "few clouds",
		          "icon": "02d"
		        }
		      ],
		      "clouds": {
		        "all": 12
		      },
		      "wind": {
		        "speed": 4.51,
		        "deg": 59.5027
		      },
		      "sys": {
		        "pod": "d"
		      },
		      "dt_txt": "2018-10-27 12:00:00"
		    },
		    {
		      "dt": 1540652400,
		      "main": {
		        "temp": 298.263,
		        "temp_min": 298.263,
		        "temp_max": 298.263,
		        "pressure": 930.22,
		        "sea_level": 1026.72,
		        "grnd_level": 930.22,
		        "humidity": 52,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 804,
		          "main": "Clouds",
		          "description": "overcast clouds",
		          "icon": "04n"
		        }
		      ],
		      "clouds": {
		        "all": 92
		      },
		      "wind": {
		        "speed": 4.43,
		        "deg": 61.0005
		      },
		      "sys": {
		        "pod": "n"
		      },
		      "dt_txt": "2018-10-27 15:00:00"
		    },
		    {
		      "dt": 1540663200,
		      "main": {
		        "temp": 296.523,
		        "temp_min": 296.523,
		        "temp_max": 296.523,
		        "pressure": 930.63,
		        "sea_level": 1027.54,
		        "grnd_level": 930.63,
		        "humidity": 57,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 800,
		          "main": "Clear",
		          "description": "clear sky",
		          "icon": "02n"
		        }
		      ],
		      "clouds": {
		        "all": 8
		      },
		      "wind": {
		        "speed": 4.06,
		        "deg": 65.0007
		      },
		      "sys": {
		        "pod": "n"
		      },
		      "dt_txt": "2018-10-27 18:00:00"
		    },
		    {
		      "dt": 1540674000,
		      "main": {
		        "temp": 292.268,
		        "temp_min": 292.268,
		        "temp_max": 292.268,
		        "pressure": 929.19,
		        "sea_level": 1026.25,
		        "grnd_level": 929.19,
		        "humidity": 71,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 800,
		          "main": "Clear",
		          "description": "clear sky",
		          "icon": "01n"
		        }
		      ],
		      "clouds": {
		        "all": 0
		      },
		      "wind": {
		        "speed": 3.06,
		        "deg": 68.0005
		      },
		      "sys": {
		        "pod": "n"
		      },
		      "dt_txt": "2018-10-27 21:00:00"
		    },
		    {
		      "dt": 1540684800,
		      "main": {
		        "temp": 289.678,
		        "temp_min": 289.678,
		        "temp_max": 289.678,
		        "pressure": 929.13,
		        "sea_level": 1026.44,
		        "grnd_level": 929.13,
		        "humidity": 78,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 800,
		          "main": "Clear",
		          "description": "clear sky",
		          "icon": "01n"
		        }
		      ],
		      "clouds": {
		        "all": 0
		      },
		      "wind": {
		        "speed": 3.22,
		        "deg": 59
		      },
		      "sys": {
		        "pod": "n"
		      },
		      "dt_txt": "2018-10-28 00:00:00"
		    },
		    {
		      "dt": 1540695600,
		      "main": {
		        "temp": 295.295,
		        "temp_min": 295.295,
		        "temp_max": 295.295,
		        "pressure": 930.75,
		        "sea_level": 1027.81,
		        "grnd_level": 930.75,
		        "humidity": 61,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 800,
		          "main": "Clear",
		          "description": "clear sky",
		          "icon": "01d"
		        }
		      ],
		      "clouds": {
		        "all": 0
		      },
		      "wind": {
		        "speed": 3.62,
		        "deg": 54.5021
		      },
		      "sys": {
		        "pod": "d"
		      },
		      "dt_txt": "2018-10-28 03:00:00"
		    },
		    {
		      "dt": 1540706400,
		      "main": {
		        "temp": 300.82,
		        "temp_min": 300.82,
		        "temp_max": 300.82,
		        "pressure": 930.14,
		        "sea_level": 1026.25,
		        "grnd_level": 930.14,
		        "humidity": 49,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 800,
		          "main": "Clear",
		          "description": "clear sky",
		          "icon": "01d"
		        }
		      ],
		      "clouds": {
		        "all": 0
		      },
		      "wind": {
		        "speed": 6.56,
		        "deg": 46.0079
		      },
		      "sys": {
		        "pod": "d"
		      },
		      "dt_txt": "2018-10-28 06:00:00"
		    },
		    {
		      "dt": 1540717200,
		      "main": {
		        "temp": 302.423,
		        "temp_min": 302.423,
		        "temp_max": 302.423,
		        "pressure": 927.48,
		        "sea_level": 1023.12,
		        "grnd_level": 927.48,
		        "humidity": 41,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 800,
		          "main": "Clear",
		          "description": "clear sky",
		          "icon": "01d"
		        }
		      ],
		      "clouds": {
		        "all": 0
		      },
		      "wind": {
		        "speed": 6.61,
		        "deg": 48.5104
		      },
		      "sys": {
		        "pod": "d"
		      },
		      "dt_txt": "2018-10-28 09:00:00"
		    },
		    {
		      "dt": 1540728000,
		      "main": {
		        "temp": 300.425,
		        "temp_min": 300.425,
		        "temp_max": 300.425,
		        "pressure": 927.33,
		        "sea_level": 1023.08,
		        "grnd_level": 927.33,
		        "humidity": 40,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 800,
		          "main": "Clear",
		          "description": "clear sky",
		          "icon": "01d"
		        }
		      ],
		      "clouds": {
		        "all": 0
		      },
		      "wind": {
		        "speed": 5.46,
		        "deg": 51.5085
		      },
		      "sys": {
		        "pod": "d"
		      },
		      "dt_txt": "2018-10-28 12:00:00"
		    },
		    {
		      "dt": 1540738800,
		      "main": {
		        "temp": 296.28,
		        "temp_min": 296.28,
		        "temp_max": 296.28,
		        "pressure": 929.4,
		        "sea_level": 1025.86,
		        "grnd_level": 929.4,
		        "humidity": 52,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 800,
		          "main": "Clear",
		          "description": "clear sky",
		          "icon": "01n"
		        }
		      ],
		      "clouds": {
		        "all": 0
		      },
		      "wind": {
		        "speed": 4.76,
		        "deg": 57.5012
		      },
		      "sys": {
		        "pod": "n"
		      },
		      "dt_txt": "2018-10-28 15:00:00"
		    },
		    {
		      "dt": 1540749600,
		      "main": {
		        "temp": 293.371,
		        "temp_min": 293.371,
		        "temp_max": 293.371,
		        "pressure": 929.84,
		        "sea_level": 1026.85,
		        "grnd_level": 929.84,
		        "humidity": 58,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 800,
		          "main": "Clear",
		          "description": "clear sky",
		          "icon": "01n"
		        }
		      ],
		      "clouds": {
		        "all": 0
		      },
		      "wind": {
		        "speed": 4.31,
		        "deg": 67.0053
		      },
		      "sys": {
		        "pod": "n"
		      },
		      "dt_txt": "2018-10-28 18:00:00"
		    },
		    {
		      "dt": 1540760400,
		      "main": {
		        "temp": 290.423,
		        "temp_min": 290.423,
		        "temp_max": 290.423,
		        "pressure": 928.5,
		        "sea_level": 1025.73,
		        "grnd_level": 928.5,
		        "humidity": 77,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 800,
		          "main": "Clear",
		          "description": "clear sky",
		          "icon": "01n"
		        }
		      ],
		      "clouds": {
		        "all": 0
		      },
		      "wind": {
		        "speed": 3.31,
		        "deg": 62.5
		      },
		      "sys": {
		        "pod": "n"
		      },
		      "dt_txt": "2018-10-28 21:00:00"
		    },
		    {
		      "dt": 1540771200,
		      "main": {
		        "temp": 288.746,
		        "temp_min": 288.746,
		        "temp_max": 288.746,
		        "pressure": 928.54,
		        "sea_level": 1025.97,
		        "grnd_level": 928.54,
		        "humidity": 93,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 800,
		          "main": "Clear",
		          "description": "clear sky",
		          "icon": "01n"
		        }
		      ],
		      "clouds": {
		        "all": 0
		      },
		      "wind": {
		        "speed": 3.41,
		        "deg": 45.0055
		      },
		      "sys": {
		        "pod": "n"
		      },
		      "dt_txt": "2018-10-29 00:00:00"
		    },
		    {
		      "dt": 1540782000,
		      "main": {
		        "temp": 295.091,
		        "temp_min": 295.091,
		        "temp_max": 295.091,
		        "pressure": 930.12,
		        "sea_level": 1027.16,
		        "grnd_level": 930.12,
		        "humidity": 62,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 801,
		          "main": "Clouds",
		          "description": "few clouds",
		          "icon": "02d"
		        }
		      ],
		      "clouds": {
		        "all": 12
		      },
		      "wind": {
		        "speed": 4.46,
		        "deg": 38.0024
		      },
		      "sys": {
		        "pod": "d"
		      },
		      "dt_txt": "2018-10-29 03:00:00"
		    },
		    {
		      "dt": 1540792800,
		      "main": {
		        "temp": 300.75,
		        "temp_min": 300.75,
		        "temp_max": 300.75,
		        "pressure": 929.18,
		        "sea_level": 1025.32,
		        "grnd_level": 929.18,
		        "humidity": 48,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 801,
		          "main": "Clouds",
		          "description": "few clouds",
		          "icon": "02d"
		        }
		      ],
		      "clouds": {
		        "all": 12
		      },
		      "wind": {
		        "speed": 7.36,
		        "deg": 40.5031
		      },
		      "sys": {
		        "pod": "d"
		      },
		      "dt_txt": "2018-10-29 06:00:00"
		    },
		    {
		      "dt": 1540803600,
		      "main": {
		        "temp": 302.214,
		        "temp_min": 302.214,
		        "temp_max": 302.214,
		        "pressure": 926.52,
		        "sea_level": 1022.08,
		        "grnd_level": 926.52,
		        "humidity": 43,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 800,
		          "main": "Clear",
		          "description": "clear sky",
		          "icon": "02d"
		        }
		      ],
		      "clouds": {
		        "all": 8
		      },
		      "wind": {
		        "speed": 6.81,
		        "deg": 43.0015
		      },
		      "sys": {
		        "pod": "d"
		      },
		      "dt_txt": "2018-10-29 09:00:00"
		    },
		    {
		      "dt": 1540814400,
		      "main": {
		        "temp": 300.364,
		        "temp_min": 300.364,
		        "temp_max": 300.364,
		        "pressure": 926.37,
		        "sea_level": 1022.16,
		        "grnd_level": 926.37,
		        "humidity": 44,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 800,
		          "main": "Clear",
		          "description": "clear sky",
		          "icon": "01d"
		        }
		      ],
		      "clouds": {
		        "all": 0
		      },
		      "wind": {
		        "speed": 5.9,
		        "deg": 46.5002
		      },
		      "sys": {
		        "pod": "d"
		      },
		      "dt_txt": "2018-10-29 12:00:00"
		    },
		    {
		      "dt": 1540825200,
		      "main": {
		        "temp": 296.84,
		        "temp_min": 296.84,
		        "temp_max": 296.84,
		        "pressure": 928.39,
		        "sea_level": 1024.7,
		        "grnd_level": 928.39,
		        "humidity": 53,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 800,
		          "main": "Clear",
		          "description": "clear sky",
		          "icon": "01n"
		        }
		      ],
		      "clouds": {
		        "all": 0
		      },
		      "wind": {
		        "speed": 5.36,
		        "deg": 56.5015
		      },
		      "sys": {
		        "pod": "n"
		      },
		      "dt_txt": "2018-10-29 15:00:00"
		    },
		    {
		      "dt": 1540836000,
		      "main": {
		        "temp": 294.361,
		        "temp_min": 294.361,
		        "temp_max": 294.361,
		        "pressure": 928.9,
		        "sea_level": 1025.58,
		        "grnd_level": 928.9,
		        "humidity": 58,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 801,
		          "main": "Clouds",
		          "description": "few clouds",
		          "icon": "02n"
		        }
		      ],
		      "clouds": {
		        "all": 24
		      },
		      "wind": {
		        "speed": 4.72,
		        "deg": 71.5022
		      },
		      "sys": {
		        "pod": "n"
		      },
		      "dt_txt": "2018-10-29 18:00:00"
		    },
		    {
		      "dt": 1540846800,
		      "main": {
		        "temp": 292.118,
		        "temp_min": 292.118,
		        "temp_max": 292.118,
		        "pressure": 927.1,
		        "sea_level": 1023.94,
		        "grnd_level": 927.1,
		        "humidity": 76,
		        "temp_kf": 0
		      },
		      "weather": [
		        {
		          "id": 801,
		          "main": "Clouds",
		          "description": "few clouds",
		          "icon": "02n"
		        }
		      ],
		      "clouds": {
		        "all": 12
		      },
		      "wind": {
		        "speed": 3.91,
		        "deg": 58.0007
		      },
		      "sys": {
		        "pod": "n"
		      },
		      "dt_txt": "2018-10-29 21:00:00"
		    }
		  ],
		  "city": {
		    "id": 1277333,
		    "name": "Bangalore",
		    "coord": {
		      "lat": 12.9762,
		      "lon": 77.6033
		    },
		    "country": "IN",
		    "population": 5104047
		  }
		}
	*/
}
