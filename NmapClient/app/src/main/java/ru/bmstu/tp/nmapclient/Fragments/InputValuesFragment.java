package ru.bmstu.tp.nmapclient.Fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Random;

import ru.bmstu.tp.nmapclient.Fragments.Interfaces.PortParam;
import ru.bmstu.tp.nmapclient.Fragments.Interfaces.Spinners;
import ru.bmstu.tp.nmapclient.Helper.SpinnerParameters;
import ru.bmstu.tp.nmapclient.R;

public class InputValuesFragment extends Fragment implements Spinners, View.OnClickListener, PortParam {

    final String LOG_TAG = "myLogs";
    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";

    private Listener activity;

    int pageNumber;
    int backColor;
    static Context ctx;

    public interface Listener {
        void scanAgain();
    }

    public static InputValuesFragment newInstance(int page, Context context) {
        ctx = context;
        InputValuesFragment pageFragment = new InputValuesFragment();
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

        View v = inflater.inflate(R.layout.fragment_input_values, null);
        v.setBackgroundColor(backColor);

        Button translate = (Button) v.findViewById(R.id.btnScanAgain);
        translate.setOnClickListener(this);

        String[] mode = getResources().getStringArray(R.array.buttom_mode);
        String[] load = getResources().getStringArray(R.array.buttom_load);
        String[] scan = getResources().getStringArray(R.array.buttom_scan);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(ctx, R.layout.spinner_layout, mode);
        adapter1.setDropDownViewResource(R.layout.spinner_layout);
        Spinner spinner1 = (Spinner) v.findViewById(R.id.mode);
        spinner1.setAdapter(adapter1);
        spinner1.setSelection(0);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(ctx, R.layout.spinner_layout, load);
        adapter2.setDropDownViewResource(R.layout.spinner_layout);
        Spinner spinner2 = (Spinner) v.findViewById(R.id.load);
        spinner2.setAdapter(adapter2);
        spinner2.setSelection(0);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(ctx, R.layout.spinner_layout, scan);
        adapter3.setDropDownViewResource(R.layout.spinner_layout);
        Spinner spinner3 = (Spinner) v.findViewById(R.id.scan);
        spinner3.setAdapter(adapter3);
        spinner3.setSelection(0);

        return v;
    }

    @Override
    public void onClick(View v) {
        View view;
        switch (v.getId()) {
            case R.id.btnScanAgain :
                if ((view = getView()) != null){
                    SpinnerParameters spinnerParameters = new SpinnerParameters(getSpinner1(), getSpinner2(),getSpinner3());
                    activity.scanAgain();
                }
                break;
        }
    }

    @Override
    public String getSpinner1() {
        View view;
        if ((view = getView()) != null){
            Spinner spinner = (Spinner) view.findViewById(R.id.mode);
            return spinner.getSelectedItem().toString();
        }
        return null;
    }

    @Override
    public String getSpinner2() {
        View view;
        if ((view = getView()) != null){
            Spinner spinner = (Spinner) view.findViewById(R.id.load);
            return spinner.getSelectedItem().toString();
        }
        return null;
    }

    @Override
    public String getSpinner3() {
        View view;
        if ((view = getView()) != null){
            Spinner spinner = (Spinner) view.findViewById(R.id.scan);
            return spinner.getSelectedItem().toString();
        }
        return null;
    }

    @Override
    public String getPortTo() {
        return null;
    }

    @Override
    public String getPortFrom() {
        return null;
    }

    @Override
    public int setPortTo() {
        return 0;
    }

    @Override
    public int setPortFrom() {
        return 0;
    }

    @Override
    public void onAttach(Activity activity) {
        Log.e(LOG_TAG, "onAttach");
        super.onAttach(activity);
        if (activity instanceof Listener) {
            this.activity = (Listener) activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " must implement InputValueListener.Listener");
        }
    }



    public void onStart() {
        super.onStart();
        Log.w(LOG_TAG, "Fragment1 onStart");
    }

    public void onResume() {
        super.onResume();
        Log.w(LOG_TAG, "Fragment1 onResume");
    }

    public void onPause() {
        super.onPause();
        Log.w(LOG_TAG, "Fragment1 onPause");
    }

    public void onStop() {
        super.onStop();
        Log.w(LOG_TAG, "Fragment1 onStop");
    }

    public void onDestroyView() {
        super.onDestroyView();
        Log.w(LOG_TAG, "Fragment1 onDestroyView");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.w(LOG_TAG, "Fragment1 onDestroy");
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.w(LOG_TAG, "Fragment1 onActivityCreated");
    }
}
