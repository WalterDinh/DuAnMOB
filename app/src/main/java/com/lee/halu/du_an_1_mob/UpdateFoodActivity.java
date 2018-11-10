package com.lee.halu.du_an_1_mob;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UpdateFoodActivity extends AppCompatActivity {
private UpdateFoodNameFragment updateFoodNameFragment;
private UpdateTypeFoodFragment updateTypeFoodFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_food_fragment);
        updateFoodNameFragment=new UpdateFoodNameFragment();
        updateTypeFoodFragment=new UpdateTypeFoodFragment();
      /*  android.support.v4.app.FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.show(updateFoodNameFragment);
        ft.show(updateTypeFoodFragment);*/
    }
}
