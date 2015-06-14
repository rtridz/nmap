package ru.bmstu.tp.nmapclient.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;

import ru.bmstu.tp.nmapclient.R;

/**
 * Created by aleksei on 20.05.15.
 */
public class ScanParamFragment extends Fragment {

    static Context ctx;

    public static ScanParamFragment newInstance(int page, Context context) {
        ctx = context;
        return new ScanParamFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_scan_params, null);
        //v.setBackgroundColor(backColor);
        return v;
    }
}
