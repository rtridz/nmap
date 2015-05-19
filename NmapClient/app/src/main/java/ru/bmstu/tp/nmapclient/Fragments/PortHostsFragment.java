package ru.bmstu.tp.nmapclient.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

import ru.bmstu.tp.nmapclient.Activities.PortHostsItem;
import ru.bmstu.tp.nmapclient.Fragments.Adapters.BoxAdapter;
import ru.bmstu.tp.nmapclient.R;

public class PortHostsFragment extends Fragment {
    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";

    int pageNumber;
    int backColor;
    static Context ctx;

    ArrayList<PortHostsItem> portHostsesItem = new ArrayList<PortHostsItem>();
    BoxAdapter boxAdapter;

    public static PortHostsFragment newInstance(int page, Context context) {
        ctx = context;
        PortHostsFragment pageFragment = new PortHostsFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);

        Random rnd = new Random();
        backColor = Color.argb(40, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

    }

    // генерируем данные для адаптера
    void fillData() {
        String[] domain = getResources().getStringArray(R.array.lvDomain);
        String[] port = getResources().getStringArray(R.array.lvPort);
        String[] server1 = getResources().getStringArray(R.array.lvServer1);
        String[] server2 = getResources().getStringArray(R.array.lvServer2);
        for (int i = 0; i <= 5; i++) {
            portHostsesItem.add(new PortHostsItem(domain[i], port[i], server1[i], server2[i]));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.fragment_port_hosts, container, false);

        fillData();
        boxAdapter = new BoxAdapter(ctx, portHostsesItem);

        // настраиваем список
        ListView lvMain = (ListView) baseView.findViewById(R.id.lvMain);
        lvMain.setAdapter(boxAdapter);

        baseView.setBackgroundColor(backColor);
        return baseView;
    }
}
