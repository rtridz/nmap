package ru.bmstu.tp.nmapclient.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ru.bmstu.tp.nmapclient.Fragments.Adapters.HistoryDetailFragmentManagerAdapter;
import ru.bmstu.tp.nmapclient.Fragments.Adapters.SmartFragmentStatePagerAdapter;
import ru.bmstu.tp.nmapclient.R;

public class HistoryDetailActivity extends FragmentActivity implements View.OnClickListener {

        final String TAG = "States";

        ViewPager pager;
        SmartFragmentStatePagerAdapter adapterViewPager;
        Button btnNewScan;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_history_detail);

            Log.d(TAG, "ActivityTwo: onCreate()");

            btnNewScan = (Button) findViewById(R.id.btnNewScan);
            btnNewScan.setOnClickListener(this);

            pager = (ViewPager) findViewById(R.id.pager);
            adapterViewPager = new HistoryDetailFragmentManagerAdapter(getSupportFragmentManager(), this);
            pager.setAdapter(adapterViewPager);

            pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
                public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected, position = " + position);
            }

            @Override
                public void onPageScrolled(int position, float positionOffset,
                int positionOffsetPixels) {
            }

            @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.history, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_history:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "will be deleted", Toast.LENGTH_LONG).show();
    }
}
