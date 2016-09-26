package com.gaoyy.easyweather.utils;

import android.util.Log;

import com.gaoyy.easyweather.bean.FutureWeather;
import com.gaoyy.easyweather.bean.Life;
import com.gaoyy.easyweather.bean.Pm25;
import com.gaoyy.easyweather.bean.Realtime;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class JsonUtils
{
    public static boolean isNetSuccessed(String body)
    {
        JSONObject jsonObject = null;
        String reason = null;
        try
        {
            jsonObject = new JSONObject(body);
            reason = jsonObject.getString("reason");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return reason.equals("successed!");
    }

    public static JSONObject getData(String body)
    {
        JSONObject data = null;
        if (isNetSuccessed(body))
        {
            try
            {
                JSONObject a = new JSONObject(body);
                JSONObject b = (JSONObject) a.get("result");
                data = (JSONObject) b.get("data");
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        } else
        {
            Log.e("JsonUtils", "NetWork Failed");
        }
        return data;
    }


    public static Realtime getRealTimeBean(String body)
    {
        Gson gson = new Gson();
        JSONObject data = getData(body);
        Realtime realtime = null;
        try
        {
            realtime = gson.fromJson(data.get("realtime").toString(), new TypeToken<Realtime>()
            {
            }.getType());
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return realtime;
    }

    public static Life getLifeBean(String body)
    {
        Gson gson = new Gson();
        JSONObject data = getData(body);
        Life life = null;
        try
        {
            life = gson.fromJson(data.get("life").toString(), new TypeToken<Life>()
            {
            }.getType());
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return life;
    }

    public static List<FutureWeather> getFutureWeatherBean(String body)
    {
        Gson gson = new Gson();
        JSONObject data = getData(body);
        List<FutureWeather> futureWeather = null;
        try
        {
            futureWeather = gson.fromJson(data.get("weather").toString(), new TypeToken<List<FutureWeather>>()
            {
            }.getType());
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return futureWeather;
    }

    public static Pm25 getPm25Bean(String body)
    {
        Gson gson = new Gson();
        JSONObject data = getData(body);
        Pm25 pm25 = null;
        try
        {
            pm25 = gson.fromJson(data.get("pm25").toString(), new TypeToken<Pm25>()
            {
            }.getType());
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return pm25;
    }
}
