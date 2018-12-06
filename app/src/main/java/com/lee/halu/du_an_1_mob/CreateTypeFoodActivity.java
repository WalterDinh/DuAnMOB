package com.lee.halu.du_an_1_mob;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lee.halu.du_an_1_mob.Model.Model;

public class CreateTypeFoodActivity extends AppCompatActivity {
    private Button btnCreateTypeFood;
    private Button btnBackToChosePubulum;
    private TextInputEditText edtTypeFoodId;
    private TextInputEditText edtFoodName;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_type_food);
        init();

        btnCreateTypeFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String idfood = edtTypeFoodId.getText().toString();
                final String foodname = edtFoodName.getText().toString();
                myRef = database.getReference("User").child("adminhalu").child("loaiDoAn");
                String foodids = myRef.child(idfood).getKey();
                Model model = new Model(idfood, foodname);
                myRef.child(foodids).setValue(model);
                startActivity(new Intent(CreateTypeFoodActivity.this, UpdateFoodActivity.class));
                finish();

            }
        });
        btnBackToChosePubulum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateTypeFoodActivity.this, UpdateFoodActivity.class));
                finish();
            }
        });
    }

    public void init() {
        btnCreateTypeFood = findViewById(R.id.btn_create_type_food);
        btnBackToChosePubulum = findViewById(R.id.btn_back_to_chose_pubulum);
        edtTypeFoodId = findViewById(R.id.edt_type_food_id);
        edtFoodName = findViewById(R.id.edt_food_name);

    }
}
