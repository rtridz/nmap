package ru.bmstu.tp.nmapclient.Fragments.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import ru.bmstu.tp.nmapclient.Fragments.DomainFragment;
import ru.bmstu.tp.nmapclient.Fragments.IPAdressFragment;


public class MyFragmentPagerAdapter extends SmartFragmentStatePagerAdapter {

    static final int PAGE_COUNT = 2;
    public static String[] inSet = {"domain", "ip adresses"};
    static Context ctx;

    public MyFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        ctx = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return DomainFragment.newInstance(position, ctx);
            case 1:
                return IPAdressFragment.newInstance(position, ctx);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return inSet[position];
    }
}

