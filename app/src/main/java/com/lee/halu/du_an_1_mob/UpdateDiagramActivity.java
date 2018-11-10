package com.lee.halu.du_an_1_mob;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UpdateDiagramActivity extends AppCompatActivity {
private UpdateTableFragment  updateTableFragment;
private UpdateZoneFragment updateZoneFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_diagram);
        updateTableFragment=new UpdateTableFragment();
        updateZoneFragment=new UpdateZoneFragment();
        android.support.v4.app.FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.show(updateTableFragment);
        ft.show(updateZoneFragment);
    }
}