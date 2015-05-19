package ru.bmstu.tp.nmapclient.Fragments.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ru.bmstu.tp.nmapclient.Fragments.DomainFragment;
import ru.bmstu.tp.nmapclient.Fragments.InputValuesFragment;
import ru.bmstu.tp.nmapclient.Fragments.PortHostsFragment;
import ru.bmstu.tp.nmapclient.Fragments.TopologyFragment;


public class TwoFragmentPagerAdapter extends FragmentPagerAdapter {

    static final int PAGE_COUNT = 3;
    public static String[] inSet = {"input values", "port/hosts", "topology"};
    static Context ctx;


    public TwoFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        ctx = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return InputValuesFragment.newInstance(position, ctx);
            case 1:
                return PortHostsFragment.newInstance(position, ctx);
            case 2:
                return TopologyFragment.newInstance(position, ctx);
        }
        return DomainFragment.newInstance(position, ctx);
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
