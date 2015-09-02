package com.androidatc.materialdesign;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jorgecasariego on 1/9/15.
 */
public class FragmentUpcomming extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String param1;
    private String param2;


    public static FragmentUpcomming newInstance(String param1, String param2){
        FragmentUpcomming fragmentSearch = new FragmentUpcomming();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);

        fragmentSearch.setArguments(args);

        return fragmentSearch;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_upcoming, container, false);

        return layout;
    }
}
