package com.lee.halu.du_an_1_mob;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class UpdateTypeFoodFragment extends Fragment {
    FloatingActionButton btn_insert_type_food;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.update_type_food_fragment, container, false);
        btn_insert_type_food=view.findViewById(R.id.btn_insert_type_food);
        btn_insert_type_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),CreateTypeFoodActivity.class));
            }
        });
        return view;
    }
}
