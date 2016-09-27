package com.gaoyy.easyweather.ui;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gaoyy.easyweather.R;
import com.gaoyy.easyweather.bean.FutureWeather;
import com.gaoyy.easyweather.bean.Life;
import com.gaoyy.easyweather.bean.Pm25;
import com.gaoyy.easyweather.bean.Realtime;
import com.gaoyy.easyweather.utils.JsonUtils;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity
{
    private SwipeRefreshLayout mainSwiperefreshlayout;
    private AppBarLayout mainAppbarlayout;
    private CollapsingToolbarLayout mainCollapsingtoolbarlayout;
    private Toolbar mainToolbar;

    private TextView headerTemperature;
    private LinearLayout linearLayout;
    private TextView headerCity;
    private TextView headerWeatherInfo;
    private TextView headerWindDirect;
    private TextView headerWindPower;
    private TextView headerHumidity;
    private TextView headerQuality;
    private TextView headerCurrentpm;

    private ImageView fwLabel1;
    private LinearLayout fwDetail1;
    private TextView fwDay1;
    private TextView fwInfo1;
    private View view1;
    private TextView fwTemp1;
    private ImageView fwLabel2;
    private LinearLayout fwDetail2;
    private TextView fwDay2;
    private TextView fwInfo2;
    private View view2;
    private TextView fwTemp2;
    private ImageView fwLabel3;
    private LinearLayout linearLayout2;
    private TextView textView;
    private TextView textView2;
    private View view;
    private TextView fwTemp3;


    private void assignViews()
    {
        mainSwiperefreshlayout = (SwipeRefreshLayout) findViewById(R.id.main_swiperefreshlayout);
        mainAppbarlayout = (AppBarLayout) findViewById(R.id.main_appbarlayout);
        mainCollapsingtoolbarlayout = (CollapsingToolbarLayout) findViewById(R.id.main_collapsingtoolbarlayout);
        mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);

        //====header======
        headerTemperature = (TextView) findViewById(R.id.header_temperature);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        headerCity = (TextView) findViewById(R.id.header_city);
        headerWeatherInfo = (TextView) findViewById(R.id.header_weather_info);
        headerWindDirect = (TextView) findViewById(R.id.header_wind_direct);
        headerWindPower = (TextView) findViewById(R.id.header_wind_power);
        headerHumidity = (TextView) findViewById(R.id.header_humidity);
        headerQuality = (TextView) findViewById(R.id.header_quality);
        headerCurrentpm = (TextView) findViewById(R.id.header_currentpm);


        //========futureweather=============

        fwLabel1 = (ImageView) findViewById(R.id.fw_label1);
        fwDetail1 = (LinearLayout) findViewById(R.id.fw_detail1);
        fwDay1 = (TextView) findViewById(R.id.fw_day1);
        fwInfo1 = (TextView) findViewById(R.id.fw_info1);
        view1 = findViewById(R.id.view1);
        fwTemp1 = (TextView) findViewById(R.id.fw_temp1);
        fwLabel2 = (ImageView) findViewById(R.id.fw_label2);
        fwDetail2 = (LinearLayout) findViewById(R.id.fw_detail2);
        fwDay2 = (TextView) findViewById(R.id.fw_day2);
        fwInfo2 = (TextView) findViewById(R.id.fw_info2);
        view2 = findViewById(R.id.view2);
        fwTemp2 = (TextView) findViewById(R.id.fw_temp2);
        fwLabel3 = (ImageView) findViewById(R.id.fw_label3);
        linearLayout2 = (LinearLayout) findViewById(R.id.linearLayout2);
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        view = findViewById(R.id.view);
        fwTemp3 = (TextView) findViewById(R.id.fw_temp3);

    }



    private  Handler weatherHanlder  = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            mainSwiperefreshlayout.setRefreshing(false);
            String body = (String) msg.getData().get("data");
            Realtime realtime = JsonUtils.getRealTimeBean(body);
            Log.i("mm", realtime.toString());
            Life life = JsonUtils.getLifeBean(body);
            Log.i("mm", life.toString());
            List<FutureWeather> futureWeather = JsonUtils.getFutureWeatherBean(body);
            Log.i("mm", futureWeather.toString());
            Pm25 pm25 = JsonUtils.getPm25Bean(body);
            Log.i("mm", pm25.toString());
            headerTemperature.setText(realtime.getWeather().getTemperature()+"°");
            headerCity.setText(realtime.getCity_name());
            headerWeatherInfo.setText(realtime.getWeather().getInfo());
            headerWindDirect.setText(realtime.getWind().getDirect());
            headerWindPower.setText(realtime.getWind().getPower());
            headerHumidity.setText(realtime.getWeather().getHumidity()+"%");
            headerQuality.setText(pm25.getPm25().getQuality());
            headerCurrentpm.setText(pm25.getPm25().getCurPm());
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
        initToolbar();
        setProgressView();
        setListener();

        initWeatherData();

    }

    private void initWeatherData()
    {
        final OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://op.juhe.cn/onebox/weather/query?cityname=%E5%B9%BF%E5%B7%9E&dtype=&key=bc84c3d2fa0193fb53e49e91c5887fd0")
                .build();
        mainSwiperefreshlayout.setRefreshing(true);
        client.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e)
            {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {

                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                String body = response.body().string();
                Message msg = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("data",body);
                msg.setData(bundle);
                weatherHanlder.sendMessage(msg);
            }
        });
    }

    private void setListener()
    {
        mainSwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                initWeatherData();
            }
        });
        mainAppbarlayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener()
        {

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset)
            {
                Log.i("mm","==>"+verticalOffset);
                if(verticalOffset == 0)
                {
                    mainSwiperefreshlayout.setEnabled(true);
                }

                if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange())
                {
                    mainToolbar.setTitle("广州");
                    mainSwiperefreshlayout.setEnabled(false);
                }
                else
                {
                    mainToolbar.setTitle("");
                }
            }
        });
    }

    private void setProgressView()
    {
        mainSwiperefreshlayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        mainSwiperefreshlayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        mainSwiperefreshlayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
    }

    private void initToolbar()
    {
        mainCollapsingtoolbarlayout.setTitleEnabled(false);
        mainToolbar.setTitle("");
        mainToolbar.setBackgroundColor(getResources().getColor(R.color.transparent));
        setSupportActionBar(mainToolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
        } else
        {
            SystemBarTintManager systemBarTintManager = new SystemBarTintManager(MainActivity.this);
            systemBarTintManager.setStatusBarTintResource(R.color.colorPrimary);
            systemBarTintManager.setStatusBarTintEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
