package com.gaoyy.easyweather.bean;

public class Life
{
    private String date ;
    private Info info;

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public Info getInfo()
    {
        return info;
    }

    public void setInfo(Info info)
    {
        this.info = info;
    }

    @Override
    public String toString()
    {
        return "Life{" +
                "date='" + date + '\'' +
                ", info=" + info.toString() +
                '}';
    }
}
