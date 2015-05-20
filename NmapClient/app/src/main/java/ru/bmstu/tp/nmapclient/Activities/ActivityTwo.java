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

import ru.bmstu.tp.nmapclient.Fragments.Adapters.SmartFragmentStatePagerAdapter;
import ru.bmstu.tp.nmapclient.Fragments.Adapters.TwoFragmentPagerAdapter;
import ru.bmstu.tp.nmapclient.Fragments.InputValuesFragment;
import ru.bmstu.tp.nmapclient.R;

public class ActivityTwo extends FragmentActivity implements View.OnClickListener, InputValuesFragment.Listener {

    final String TAG = "States";

    ViewPager pager;
    SmartFragmentStatePagerAdapter adapterViewPager;
    Button btnNewScan;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        Log.d(TAG, "ActivityTwo: onCreate()");

        btnNewScan = (Button) findViewById(R.id.btnNewScan);
        btnNewScan.setOnClickListener(this);

        pager = (ViewPager) findViewById(R.id.pager);
        adapterViewPager = new TwoFragmentPagerAdapter(getSupportFragmentManager(), this);
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.menu_history:
                Intent intent = new Intent(this, HistoryListActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void scanAgain() {
        adapterViewPager.getRegisteredFragment(0);
        Toast.makeText(this, "will be scan again", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        Log.e(TAG, "onClick in ActivityTwo");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "ActivityTwo: onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "ActivityTwo: onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "ActivityTwo: onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "ActivityTwo: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "ActivityTwo: onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "ActivityTwo: onDestroy()");
    }
}
