package ru.bmstu.tp.nmapclient.Fragments.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ru.bmstu.tp.nmapclient.Activities.PortHostsItem;
import ru.bmstu.tp.nmapclient.R;

public class BoxAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<PortHostsItem> objects;

    public BoxAdapter(Context context, ArrayList<PortHostsItem> portHostseItems) {
        ctx = context;
        objects = portHostseItems;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.port_hosts_item, parent, false);
        }

        PortHostsItem p = getProduct(position);

        // заполняем View в пункте списка данными из ответа: домен, порт
        // и сервера
        ((TextView) view.findViewById(R.id.lvDomain)).setText(p.domain);
        ((TextView) view.findViewById(R.id.lvPort)).setText(p.port + "");
        ((TextView) view.findViewById(R.id.lvServer1)).setText(p.server1);
        ((TextView) view.findViewById(R.id.lvServer2)).setText(p.server2);

        return view;
    }

    // товар по позиции
    PortHostsItem getProduct(int position) {
        return ((PortHostsItem) getItem(position));
    }
}
