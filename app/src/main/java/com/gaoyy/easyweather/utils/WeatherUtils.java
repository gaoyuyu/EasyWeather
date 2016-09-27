package com.gaoyy.easyweather.utils;

/**
 * Created by gaoyy on 2016/9/27 0027.
 */
public class WeatherUtils
{
    public static String handleWeatherStr(String weather)
    {
        if(weather.contains("到"))
        {
            String[] arr = weather.split("到");
            weather = arr[1];
        }
        return weather;
    }


}
