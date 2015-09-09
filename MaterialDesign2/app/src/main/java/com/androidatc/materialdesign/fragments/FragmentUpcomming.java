package com.androidatc.materialdesign.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidatc.materialdesign.R;
import com.androidatc.materialdesign.extras.SortListener;
import com.androidatc.materialdesign.loggin.L;

/**
 * Created by jorgecasariego on 1/9/15.
 */
public class FragmentUpcomming extends Fragment implements SortListener{
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

    @Override
    public void onSortByName() {
        L.t(getActivity(), "Sort name UPCOMMING was clicked");
    }

    @Override
    public void onSortByDate() {
        L.t(getActivity(), "Sort date UPCOMMING was clicked");
    }

    @Override
    public void onSortByRating() {
        L.t(getActivity(), "Sort rating UPCOMMING was clicked");
    }
}
