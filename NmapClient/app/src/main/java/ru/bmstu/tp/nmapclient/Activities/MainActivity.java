package ru.bmstu.tp.nmapclient.Activities;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import ru.bmstu.tp.nmapclient.Fragments.Adapters.MyFragmentPagerAdapter;
import ru.bmstu.tp.nmapclient.Fragments.Adapters.SmartFragmentStatePagerAdapter;
import ru.bmstu.tp.nmapclient.Fragments.Interfaces.DomainParam;
import ru.bmstu.tp.nmapclient.Fragments.Interfaces.IpParam;
import ru.bmstu.tp.nmapclient.Fragments.SpinnersFragment;
import ru.bmstu.tp.nmapclient.Fragments.Interfaces.Spinners;

import ru.bmstu.tp.nmapclient.R;
import ru.bmstu.tp.nmapclient.Validator.Query;
import ru.bmstu.tp.nmapclient.Validator.Validator;


public class MainActivity extends FragmentActivity implements View.OnClickListener {

    final String LOG_TAG = "myLogs";
    ViewPager pager;
    Validator validator;

    SmartFragmentStatePagerAdapter adapterViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        validator = new Validator();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        SpinnersFragment frag2 = SpinnersFragment.newInstance(this);
        ft.replace(R.id.fragment2, frag2);
        ft.commit();

        pager = (ViewPager) findViewById(R.id.pager);
        adapterViewPager = new MyFragmentPagerAdapter(getSupportFragmentManager(), this);
        pager.setAdapter(adapterViewPager);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                Log.d(LOG_TAG, "onPageSelected, position = " + position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        Log.w(LOG_TAG, "MainActivity onCreate");
    }

    @Override
    public void onClick(View v) {
        String queryString = "will be scan with parameters\n";
        Query query = new Query();
        switch (pager.getCurrentItem()) {
            case 0:
                if (adapterViewPager.getRegisteredFragment(pager.getCurrentItem()) instanceof DomainParam) {
                    DomainParam domainParam = (DomainParam)adapterViewPager.getRegisteredFragment(pager.getCurrentItem());
                    query.setType("domain");
                    query.setDomain(domainParam.getDomain());
                    query.setPortFrom(domainParam.getFrom());
                    query.setPortTo(domainParam.getTo());
                    queryString += domainParam.getDomain()+ "\n" + domainParam.getFrom() + "\n" + domainParam.getTo() + "\n";
                }
                else {
                    Toast.makeText(this, "not instance DomainParam", Toast.LENGTH_LONG).show();
                }
                break;
            case 1:
                if (adapterViewPager.getRegisteredFragment(pager.getCurrentItem()) instanceof IpParam) {
                    IpParam ipParam = (IpParam)adapterViewPager.getRegisteredFragment(pager.getCurrentItem());
                    query.setType("ip");
                    query.setIpFrom(ipParam.getIpFrom());
                    query.setIpTo(ipParam.getIpTo());
                    query.setPortFrom(ipParam.getPortFrom());
                    query.setPortTo(ipParam.getPortTo());
                    queryString += ipParam.getIpFrom()+ "\n" + ipParam.getIpTo() + "\n" + ipParam.getPortFrom() + "\n" + ipParam.getPortTo() + "\n";
                }
                else {
                    Toast.makeText(this, "not instance IpParam", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
        Fragment spinners = getSupportFragmentManager().findFragmentById(R.id.fragment2);
        if (!validator.validQuery(query).equals("ok")) {
            Toast.makeText(this, validator.validQuery(query), Toast.LENGTH_LONG).show();
        }
        else {
            if (spinners instanceof Spinners) {
                Spinners inter = (Spinners) spinners;
                queryString += inter.getSpinner1() + "\n" + inter.getSpinner2() + "\n" + inter.getSpinner3();
                Toast.makeText(this, queryString, Toast.LENGTH_LONG).show();
            }
            Intent intent = new Intent(this, ActivityTwo.class);
            startActivity(intent);
        }
    }

    protected void onStart() {
        super.onStart();
        Log.w(LOG_TAG, "MainActivity onStart");
    }

    protected void onResume() {
        super.onResume();
        Log.w(LOG_TAG, "MainActivity onResume");
    }

    protected void onPause() {
        super.onPause();
        Log.w(LOG_TAG, "MainActivity onPause");
    }

    protected void onStop() {
        super.onStop();
        Log.w(LOG_TAG, "MainActivity onStop");
    }

    protected void onDestroy() {

        super.onDestroy();
        Log.w(LOG_TAG, "MainActivity onDestroy");
    }
}
