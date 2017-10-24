package com.yd.ydpagestate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.yd.commonlibrary.pagestate.Indicator;
import com.yd.commonlibrary.pagestate.YdPageStateManager;
import com.yd.commonlibrary.pagestate.listener.OnEmptyRetryListener;
import com.yd.commonlibrary.pagestate.listener.OnErrorRetryListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private YdPageStateManager ydPageStateManager;
    private LinearLayout relativeLayout;
    private TextView textView;
    SimpleAdapter adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout = (LinearLayout) findViewById(R.id.rlay_parent);
        ydPageStateManager = YdPageStateManager.generate(this, R.id.rlay_parent);
        listView= (ListView) findViewById(R.id.listview);
        textView = (TextView) findViewById(R.id.textView);
        Map<String, String> keyValuePair = new HashMap<String, String>();
        keyValuePair.put("Name", "小智");
        keyValuePair.put("Age", "10");
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        list.add(keyValuePair);

        ListAdapter adapter = new SimpleAdapter(this, list,
                android.R.layout.simple_list_item_2, new String[] { "Name",
                "Age" }, new int[] { android.R.id.text1,
                android.R.id.text2 });

        listView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.content:
                switchState(0);
                break;
            case R.id.loading:
                switchState(1);
                break;
            case R.id.empty:
                switchState(2);
                break;
            case R.id.error:
                switchState(3);
                break;
        }
        return true;
    }

    private void switchState(int type) {
        switch (type) {
            case 0:
                ydPageStateManager.showContent();
                break;
            case 1:
                ydPageStateManager.showLoading(Indicator.PacmanIndicator);
                break;
            case 2:
                ydPageStateManager.showEmpty(getResources().getDrawable(R.mipmap.monkey_nodata),
                        getString(R.string.ydPageState_empty_title), getString(R.string.ydPageState_empty_details), new OnEmptyRetryListener() {
                            @Override
                            public void onEmptyRetry(View view) {
                                ydPageStateManager.showLoading(Indicator.BallBeatIndicator);
                            }
                        });
                break;
            case 3:
                //设置加载错误页显示
                ydPageStateManager.showError(getResources().getDrawable(R.mipmap.nointent),
                        getString(R.string.ydPageState_error_title), getString(R.string.ydPageState_error_details),
                        getString(R.string.ydPageState_retry), new OnErrorRetryListener() {
                            @Override
                            public void onErrorRetry(View view) {
                                ydPageStateManager.showLoading(Indicator.PacmanIndicator);
                            }
                        });
                break;
        }
    }
}
