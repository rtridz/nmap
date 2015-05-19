package ru.bmstu.tp.nmapclient.Fragments;

import java.util.Random;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import ru.bmstu.tp.nmapclient.Fragments.Interfaces.DomainParam;
import ru.bmstu.tp.nmapclient.R;

public class DomainFragment extends Fragment implements DomainParam {

    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";

    int pageNumber;
    int backColor;
    static Context ctx;

    public static DomainFragment newInstance(int page, Context context) {
        ctx = context;
        DomainFragment pageFragment = new DomainFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.fragment_domain, container, false);

        baseView.setBackgroundColor(backColor);
        return baseView;
    }

    @Override
    public String getDomain() {
        View view;
        if ((view = getView()) != null){
            EditText editText = (EditText) view.findViewById(R.id.etIp1);
            return editText.getText().toString();
        }
        return null;
    }

    @Override
    public String getFrom() {
        View view;
        if ((view = getView()) != null){
            EditText editText = (EditText) view.findViewById(R.id.etFrom);
            return editText.getText().toString();
        }
        return null;
    }

    @Override
    public String getTo() {
        View view;
        if ((view = getView()) != null){
            EditText editText = (EditText) view.findViewById(R.id.etTo);
            return editText.getText().toString();
        }
        return null;
    }
}
