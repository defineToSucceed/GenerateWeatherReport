import java.util.List;

import org.openweathermap.api.DataWeatherClient;
import org.openweathermap.api.UrlConnectionDataWeatherClient;
import org.openweathermap.api.common.Coordinate;
import org.openweathermap.api.model.currentweather.CurrentWeather;
import org.openweathermap.api.model.forecast.ForecastInformation;
import org.openweathermap.api.model.forecast.hourly.HourlyForecast;
import org.openweathermap.api.model.uvi.Uvi;
import org.openweathermap.api.query.Language;
import org.openweathermap.api.query.QueryBuilderPicker;
import org.openweathermap.api.query.ResponseFormat;
import org.openweathermap.api.query.UnitFormat;
import org.openweathermap.api.query.currentweather.CurrentWeatherOneLocationQuery;
import org.openweathermap.api.query.currentweather.InCycle;
import org.openweathermap.api.query.uvi.UviQuery;
import org.openweathermap.api.query.uvi.current.ByGeographicCoordinates;

/**
 * @author navneethnarendra
 *
 */
public class WeatherRead {
	private static final String API_KEY = "d1158b740a06486ee301974b05f77baf";

	public static void main(String[] args) {
		getCurrentWeatherMultipleLocations();

		getCurrentWeatherOneLocation();

		getHourlyForecast();

		getUviCurrent();

		getUviForecast();

		// TODO: getDailyForecast();
	}

	private static void getCurrentWeatherMultipleLocations() {
		DataWeatherClient client = new UrlConnectionDataWeatherClient(API_KEY);

		InCycle inCycle = QueryBuilderPicker.pick().currentWeather() // get
																		// current
																		// weather
				.multipleLocations() // for multiple locations
				.inCycle(new Coordinate("36.230383", "49.9935"), 5) // get
																	// weather
																	// for 10
																	// closest
																	// to
																	// coordinate
																	// cities
				.language(Language.ENGLISH) // in English language
				.unitFormat(UnitFormat.IMPERIAL) // in metric units
				.build();
		List<CurrentWeather> currentWeatherList = client.getCurrentWeather(inCycle);
		for (CurrentWeather currentWeather : currentWeatherList) {
			System.out.println(printCurrentWeather(currentWeather));
		}
	}

	private static void getCurrentWeatherOneLocation() {
		DataWeatherClient client = new UrlConnectionDataWeatherClient(API_KEY);
		CurrentWeatherOneLocationQuery currentWeatherOneLocationQuery = QueryBuilderPicker.pick().currentWeather() // get
																													// current
																													// weather
				.oneLocation().byZipCode("94582", "US") // for one location
				// .type(Type.ACCURATE) // with Accurate search
				.language(Language.ENGLISH) // in English language
				.responseFormat(ResponseFormat.JSON)// with JSON response format
				.unitFormat(UnitFormat.IMPERIAL) // in metric units
				.build();
		CurrentWeather currentWeather = client.getCurrentWeather(currentWeatherOneLocationQuery);
		System.out.println(printCurrentWeather(currentWeather));
	}

	/*
	 * TODO: API issue private static void getDailyForecast() {
	 * DataWeatherClient client = new UrlConnectionDataWeatherClient(API_KEY);
	 * ByCityName byCityNameForecast = QueryBuilderPicker.pick().forecast() //
	 * get // forecast .daily() // it should be dailt .byCityName("Kharkiv") //
	 * for Kharkiv city .countryCode("UA") // in Ukraine
	 * .unitFormat(UnitFormat.METRIC) // in Metric units
	 * .language(Language.ENGLISH) // in English .build();
	 * ForecastInformation<DailyForecast> forecastInformation =
	 * client.getForecastInformation(byCityNameForecast);
	 * System.out.println(forecastInformation.getCity()); for (DailyForecast
	 * forecast : forecastInformation.getForecasts()) {
	 * System.out.println(String.format("Temperature on %s will be: %s",
	 * forecast.getDateTime().toString(),
	 * forecast.getTemperature().toString())); } }
	 */

	private static void getHourlyForecast() {
		DataWeatherClient client = new UrlConnectionDataWeatherClient(API_KEY);
		org.openweathermap.api.query.forecast.hourly.ByCityName byCityNameForecast = QueryBuilderPicker.pick()
				.forecast() // get
				// forecast
				.hourly() // it should be hourly forecast
				.byCityName("Kharkiv") // for Kharkiv city
				.countryCode("UA") // in Ukraine
				.unitFormat(UnitFormat.METRIC) // in Metric units
				.language(Language.ENGLISH) // in English
				.count(5) // limit results to 5 forecasts
				.build();
		ForecastInformation<HourlyForecast> forecastInformation = client.getForecastInformation(byCityNameForecast);
		System.out.println("Forecasts for " + forecastInformation.getCity() + ":");
		for (HourlyForecast forecast : forecastInformation.getForecasts()) {
			System.out.println(printHourlyForecast(forecast));
		}
	}

	private static void getUviCurrent() {
		DataWeatherClient client = new UrlConnectionDataWeatherClient(API_KEY);
		Coordinate coordinate = new Coordinate("36.230383", "49.9935");
		ByGeographicCoordinates query = UviQuery.Current.byGeographicCoordinates(coordinate).language(Language.ENGLISH)
				.unitFormat(UnitFormat.METRIC).build();
		Uvi result = client.getCurrentUvi(query);
		System.out.println(result);
	}

	private static void getUviForecast() {
		DataWeatherClient client = new UrlConnectionDataWeatherClient(API_KEY);
		Coordinate coordinate = new Coordinate("36.230383", "49.9935");
		org.openweathermap.api.query.uvi.forecast.ByGeographicCoordinates query = UviQuery.Forecast
				.byGeographicCoordinates(coordinate).language(Language.RUSSIAN).unitFormat(UnitFormat.IMPERIAL).count(4)
				.build();
		List<Uvi> result = client.getUviForecast(query);
		System.out.println(result);
	}

	private static String printHourlyForecast(HourlyForecast hourlyForecast) {
		return String.format("Forecast for %s:\ntemperature: %.1f ℃\nhumidity: %.1f %%\npressure: %.1f hPa\n",
				hourlyForecast.getDateTime().toString(), hourlyForecast.getMainParameters().getTemperature(),
				hourlyForecast.getMainParameters().getHumidity(), hourlyForecast.getMainParameters().getPressure());
	}

	private static String printCurrentWeather(CurrentWeather currentWeather) {
		return String.format("Current weather in %s(%s):\ntemperature: %.1f ℃\nhumidity: %.1f %%\npressure: %.1f hPa\n",
				currentWeather.getCityName(), currentWeather.getSystemParameters().getCountry(),
				currentWeather.getMainParameters().getTemperature(), currentWeather.getMainParameters().getHumidity(),
				currentWeather.getMainParameters().getPressure());
	}

}
