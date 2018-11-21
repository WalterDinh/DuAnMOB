package com.lee.halu.du_an_1_mob;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UpdateDrinksNameFragment extends Fragment {
    private Toolbar toolbar;
    private FloatingActionButton btnInsertDrinks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //chuyen layout --> view
        View view = inflater.inflate(R.layout.update_drinks_name_fragment, container, false);
        btnInsertDrinks = view.findViewById(R.id.btn_insert_drinks);
        btnInsertDrinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CreateDrinksActivity.class));
            }
        });
        return view;
    }

}
