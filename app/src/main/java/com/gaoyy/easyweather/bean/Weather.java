package com.gaoyy.easyweather.bean;

/**
 * Created by gaoyy on 2016/9/21 0021.
 */
public class Weather
{
    private String temperature;
    private String humidity;
    private String info;
    private String img;

    public String getTemperature()
    {
        return temperature;
    }

    public void setTemperature(String temperature)
    {
        this.temperature = temperature;
    }

    public String getHumidity()
    {
        return humidity;
    }

    public void setHumidity(String humidity)
    {
        this.humidity = humidity;
    }

    public String getInfo()
    {
        return info;
    }

    public void setInfo(String info)
    {
        this.info = info;
    }

    public String getImg()
    {
        return img;
    }

    public void setImg(String img)
    {
        this.img = img;
    }


    @Override
    public String toString()
    {
        return "Weather{" +
                "temperature='" + temperature + '\'' +
                ", humidity='" + humidity + '\'' +
                ", info='" + info + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
