package com.gaoyy.easyweather.bean;

public class Pm25
{
    private String key;
    private int show_desc;
    private Pm25Detail pm25;
    private String dateTime;
    private String cityName;

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public int getShow_desc()
    {
        return show_desc;
    }

    public void setShow_desc(int show_desc)
    {
        this.show_desc = show_desc;
    }

    public Pm25Detail getPm25()
    {
        return pm25;
    }

    public void setPm25(Pm25Detail pm25)
    {
        this.pm25 = pm25;
    }

    public String getDateTime()
    {
        return dateTime;
    }

    public void setDateTime(String dateTime)
    {
        this.dateTime = dateTime;
    }

    public String getCityName()
    {
        return cityName;
    }

    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }

    @Override
    public String toString()
    {
        return "Pm25{" +
                "key='" + key + '\'' +
                ", show_desc=" + show_desc +
                ", pm25=" + pm25 +
                ", dateTime='" + dateTime + '\'' +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
