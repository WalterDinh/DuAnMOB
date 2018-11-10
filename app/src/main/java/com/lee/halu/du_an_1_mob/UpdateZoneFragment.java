package com.lee.halu.du_an_1_mob;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UpdateZoneFragment extends Fragment {
    FloatingActionButton btn_insert_zone;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.update_zonef_ragment, container, false);
        btn_insert_zone=view.findViewById(R.id.btn_insert_zone);
btn_insert_zone.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(getActivity(),CreateZoneActivity.class));
    }
});
        return view;
    }
}
