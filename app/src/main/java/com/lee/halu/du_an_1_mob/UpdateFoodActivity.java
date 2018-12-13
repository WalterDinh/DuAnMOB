package com.lee.halu.du_an_1_mob;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class UpdateFoodActivity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_food_fragment);
imageView=findViewById(R.id.img_back_to_chose);
imageView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(UpdateFoodActivity.this,ChoseCreatePubulumActivity.class));
        finish();
    }
});
    }
}
