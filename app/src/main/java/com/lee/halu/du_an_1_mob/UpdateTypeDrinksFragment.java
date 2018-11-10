package com.lee.halu.du_an_1_mob;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UpdateTypeDrinksFragment extends Fragment{
    FloatingActionButton btn_insert_type_drinks;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //chuyen layout --> view
        View view = inflater.inflate(R.layout.update_type_drinks_fragment, container, false);
btn_insert_type_drinks=view.findViewById(R.id.btn_insert_type_drinks);
btn_insert_type_drinks.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(getActivity(),CreateTypeDrinksActivity.class));
        }
});
        return view;
    }
}
