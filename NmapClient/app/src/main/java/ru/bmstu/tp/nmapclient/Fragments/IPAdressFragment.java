package ru.bmstu.tp.nmapclient.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


import java.util.Random;

import ru.bmstu.tp.nmapclient.Fragments.Interfaces.IpParam;
import ru.bmstu.tp.nmapclient.R;

public class IPAdressFragment extends Fragment implements IpParam {
    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";

    int pageNumber;
    int backColor;
    static Context ctx;

    public static IPAdressFragment newInstance(int page, Context context) {
        ctx = context;
        IPAdressFragment ipAdressFragment = new IPAdressFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        ipAdressFragment.setArguments(arguments);
        return ipAdressFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);

        Random rnd = new Random();
        backColor = Color.argb(40, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ipadresses, null);

        view.setBackgroundColor(backColor);
        return view;
    }

    @Override
    public String getIpFrom() {
        View view;
        if ((view = getView()) != null){
            EditText editText = (EditText) view.findViewById(R.id.etIp1);
            return editText.getText().toString();
        }
        return null;
    }

    @Override
    public String getIpTo() {
        View view;
        if ((view = getView()) != null){
            EditText editText = (EditText) view.findViewById(R.id.etIp2);
            return editText.getText().toString();
        }
        return null;
    }

    @Override
    public String getPortFrom() {
        View view;
        if ((view = getView()) != null){
            EditText editText = (EditText) view.findViewById(R.id.etFrom);
            return editText.getText().toString();
        }
        return null;
    }

    @Override
    public String getPortTo() {
        View view;
        if ((view = getView()) != null){
            EditText editText = (EditText) view.findViewById(R.id.etTo);
            return editText.getText().toString();
        }
        return null;
    }
}
