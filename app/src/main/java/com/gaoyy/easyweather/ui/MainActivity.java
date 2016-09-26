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
import android.widget.Toast;

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

    private void assignViews()
    {
        mainSwiperefreshlayout = (SwipeRefreshLayout) findViewById(R.id.main_swiperefreshlayout);
        mainAppbarlayout = (AppBarLayout) findViewById(R.id.main_appbarlayout);
        mainCollapsingtoolbarlayout = (CollapsingToolbarLayout) findViewById(R.id.main_collapsingtoolbarlayout);
        mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
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
                Toast.makeText(MainActivity.this,"aaa",Toast.LENGTH_SHORT).show();
            }
        });
        mainAppbarlayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener()
        {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset)
            {
                if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange())
                {
                    mainToolbar.setTitle("GZ");
                } else
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
