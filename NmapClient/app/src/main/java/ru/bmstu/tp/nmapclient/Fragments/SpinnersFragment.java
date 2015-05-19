package ru.bmstu.tp.nmapclient.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import ru.bmstu.tp.nmapclient.Fragments.Interfaces.Spinners;
import ru.bmstu.tp.nmapclient.R;

public class SpinnersFragment extends Fragment implements Spinners {

    final String LOG_TAG = "myLogs";
    static Context ctx;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "Fragment1 onCreate");
        super.onCreate(savedInstanceState);
    }

    public static SpinnersFragment newInstance(Context context) {
        ctx = context;
        SpinnersFragment spinnersFragment;
        spinnersFragment = new SpinnersFragment();
        return spinnersFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        Log.w(LOG_TAG, "Fragment1 onAttach");
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        Log.w(LOG_TAG, "Fragment1 onDetach");
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.w(LOG_TAG, "Fragment1 onCreateView");

        View v = inflater.inflate(R.layout.fragment_spinners_main, null);

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
