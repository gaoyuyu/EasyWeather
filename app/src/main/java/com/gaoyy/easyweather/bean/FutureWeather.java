package com.gaoyy.easyweather.bean;

public class FutureWeather
{
    private String date;
    private WeatherInfo info;
    private String week;
    private String nongli;

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public WeatherInfo getInfo()
    {
        return info;
    }

    public void setInfo(WeatherInfo info)
    {
        this.info = info;
    }

    public String getWeek()
    {
        return week;
    }

    public void setWeek(String week)
    {
        this.week = week;
    }

    public String getNongli()
    {
        return nongli;
    }

    public void setNongli(String nongli)
    {
        this.nongli = nongli;
    }

    @Override
    public String toString()
    {
        return "FutureWeather{" +
                "date='" + date + '\'' +
                ", info=" + info.toString() +
                ", week='" + week + '\'' +
                ", nongli='" + nongli + '\'' +
                '}';
    }
}
