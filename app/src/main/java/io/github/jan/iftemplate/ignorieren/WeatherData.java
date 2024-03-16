package io.github.jan.iftemplate.ignorieren;

import org.json.JSONObject;

public class WeatherData {

    private final String city;
    private final String country;
    private final String description;
    private final double temperature;
    private final double feelsLike;
    private final double minTemperature;
    private final double maxTemperature;
    private final double pressure;
    private final double humidity;
    private final double windSpeed;
    private final double windDegree;

    public WeatherData(JSONObject data) {
        city = data.optString("name");
        country = data.optJSONObject("sys").optString("country");
        description = data.optJSONArray("weather").optJSONObject(0).optString("description");
        temperature = data.optJSONObject("main").optDouble("temp");
        feelsLike = data.optJSONObject("main").optDouble("feels_like");
        minTemperature = data.optJSONObject("main").optDouble("temp_min");
        maxTemperature = data.optJSONObject("main").optDouble("temp_max");
        pressure = data.optJSONObject("main").optDouble("pressure");
        humidity = data.optJSONObject("main").optDouble("humidity");
        windSpeed = data.optJSONObject("wind").optDouble("speed");
        windDegree = data.optJSONObject("wind").optDouble("deg");
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getDescription() {
        return description;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getWindDegree() {
        return windDegree;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
            "city='" + city + '\'' +
            ", country='" + country + '\'' +
            ", description='" + description + '\'' +
            ", temperature=" + temperature +
            ", feelsLike=" + feelsLike +
            ", minTemperature=" + minTemperature +
            ", maxTemperature=" + maxTemperature +
            ", pressure=" + pressure +
            ", humidity=" + humidity +
            ", windSpeed=" + windSpeed +
            ", windDegree=" + windDegree +
            '}';
    }
}
