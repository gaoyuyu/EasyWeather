package com.gaoyy.easyweather.bean;

/**
 * Created by gaoyy on 2016/9/21 0021.
 */
public class Wind
{
    private String direct;
    private String power;
    private String offset;
    private String windspeed;


    public String getDirect()
    {
        return direct;
    }

    public void setDirect(String direct)
    {
        this.direct = direct;
    }

    public String getPower()
    {
        return power;
    }

    public void setPower(String power)
    {
        this.power = power;
    }

    public String getOffset()
    {
        return offset;
    }

    public void setOffset(String offset)
    {
        this.offset = offset;
    }

    public String getWindspeed()
    {
        return windspeed;
    }

    public void setWindspeed(String windspeed)
    {
        this.windspeed = windspeed;
    }

    @Override
    public String toString()
    {
        return "Wind{" +
                "direct='" + direct + '\'' +
                ", power='" + power + '\'' +
                ", offset='" + offset + '\'' +
                ", windspeed='" + windspeed + '\'' +
                '}';
    }
}
