package com.lee.halu.du_an_1_mob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ChoseCreatePubulumActivity extends AppCompatActivity {
    private ImageView imgLogoChosePubulum;
    private Button btnCreateFood;
    private Button btnCreateDrinks;
    private Button btnBackChossseCreateData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_create_pubulum);
        init();
        imgLogoChosePubulum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoseCreatePubulumActivity.this,HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        btnCreateFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChoseCreatePubulumActivity.this,UpdateFoodActivity.class));

            }
        });
        btnCreateDrinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChoseCreatePubulumActivity.this,UpdateDrinksActivity.class));

            }
        });
        btnBackChossseCreateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChoseCreatePubulumActivity.this,ChoseCreateDataActivity.class));

            }
        });
    }
    private void init(){
        imgLogoChosePubulum = (ImageView) findViewById(R.id.img_logo_chose_pubulum);
        btnCreateFood = (Button) findViewById(R.id.btn_create_food);
        btnCreateDrinks = (Button) findViewById(R.id.btn_create_drinks);
        btnBackChossseCreateData = (Button) findViewById(R.id.btn_back_chossse_create_data);

    }
}
