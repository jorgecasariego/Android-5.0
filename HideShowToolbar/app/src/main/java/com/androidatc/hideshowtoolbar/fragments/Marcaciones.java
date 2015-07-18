package com.androidatc.hideshowtoolbar.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidatc.hideshowtoolbar.R;
import com.androidatc.hideshowtoolbar.adapter.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Marcaciones extends Fragment {

    public static final String CANTIDAD_ITEMS_KEY = "MarcacionesFragment$CantidadItems";

    public static Marcaciones crearInstancia(int cantidadItems){
        Marcaciones marcacionFragment = new Marcaciones();
        Bundle bundle = new Bundle();
        bundle.putInt(CANTIDAD_ITEMS_KEY, cantidadItems);
        marcacionFragment.setArguments(bundle);

        return marcacionFragment;
    }

    public Marcaciones() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_marcaciones, container, false);
        setupRecyclerView(recyclerView);

        return recyclerView;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(createItemList());
        recyclerView.setAdapter(recyclerAdapter);
    }

    private List<String> createItemList(){
        List<String> itemList = new ArrayList<>();
        Bundle bundle = getArguments();
        if(bundle != null){
            int itemsCount = bundle.getInt(CANTIDAD_ITEMS_KEY);
            for (int i = 0; i<itemsCount; i++){
                itemList.add("Item " + i);
            }
        }

        return itemList;
    }


}
