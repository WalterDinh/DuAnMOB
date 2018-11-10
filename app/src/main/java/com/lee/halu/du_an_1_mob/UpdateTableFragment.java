package com.lee.halu.du_an_1_mob;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UpdateTableFragment extends Fragment {
    FloatingActionButton btn_insert_table;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //chuyen layout --> view
        View view = inflater.inflate(R.layout.update_table_fragment, container, false);
btn_insert_table=view.findViewById(R.id.btn_insert_table);
btn_insert_table.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(getActivity(),CreateTableActivity.class));
    }
});
        return view;
    }
}
