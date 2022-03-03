/**
 * 
 */
package com.nab.ms.test.application.common;

/**
 * @author anshumanc
 *
 */
public class AppConstants {
	
	//Generic Messages
	public static String APP_START_UP_MSG = "###### WeatherApplication is up and running ##### ";
	
	//open weather map API
	public static String OPEN_WEATHER_MAP_QUERY = "http://api.openweathermap.org/data/2.5/weather?q=";
	
	//API Keys
	public static String API_KEY_1 = "05ff608e03afa55e7655de03be8dbb21";
	// The actual apiKey received from OpenWeather
	public static String API_KEY_2 = "4e55f829775473b41c8dec5b428fcd8e";
	public static String API_KEY_3 = "fc36ffcdf3e684e9ae92488d4e666ba1";
	public static String API_KEY_4 = "13274c411ab0597cc7236cd4a393053e";
	public static String API_KEY_5 = "73065ad2115c18dd149ab59bfca0af87";
	public static String API_KEY_6 = "ad7d472b70252c52348c19d6197079dc";
	public static String API_KEY_7 = "352baff549e9241a8d1da355d275ab4e";

	//Error messages
	public static String API_THROTTLE_ERROR_MESSAGE = "Too many requests! Maximum requests allowed per hour is - ";
	public static String INTERNAL_SERVER_EXCEPTION_MESSAGE = "Oops, something bad happened!"; 
	
	//Validations
	public static String CITY_VALIDATION_MESSAGE = "city field is required!";
	public static String COUNTRY_VALIDATION_MESSAGE = "country field is required!";
	public static String API_KEY_VALIDATION_MESSAGE = "API Key Validation failed - Provide API KEY!";
	public static String API_KEY_INVALID_MESSAGE = "API Key Validation failed - Invalid API KEY : ";
	
}
