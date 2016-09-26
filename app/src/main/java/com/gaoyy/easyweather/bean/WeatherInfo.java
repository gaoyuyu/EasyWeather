package com.gaoyy.easyweather.bean;

import java.util.List;

public class WeatherInfo
{
    private List<String> day;
    private List<String> night;

    public List<String> getDay()
    {
        return day;
    }

    public void setDay(List<String> day)
    {
        this.day = day;
    }

    public List<String> getNight()
    {
        return night;
    }

    public void setNight(List<String> night)
    {
        this.night = night;
    }

    @Override
    public String toString()
    {
        return "WeatherInfo{" +
                "day=" + day.toString() +
                ", night=" + night.toString() +
                '}';
    }
}
