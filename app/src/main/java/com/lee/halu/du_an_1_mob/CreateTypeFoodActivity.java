package com.lee.halu.du_an_1_mob;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_type_food);
        init();
        btnCreateTypeFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String idfood = edtTypeFoodId.getText().toString();
                final String foodname=edtFoodName.getText().toString();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("LoaiMon");
                String foodids = myRef.child(idfood).getKey();
                Model model = new Model(idfood, foodname);
                myRef.child(foodids).setValue(model);
                startActivity(new Intent(CreateTypeFoodActivity.this,UpdateFoodActivity.class));
            }
        });
btnBackToChosePubulum.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(CreateTypeFoodActivity.this,UpdateFoodActivity.class));
    }
});
    }
    public void init(){
        btnCreateTypeFood = (Button) findViewById(R.id.btn_create_type_food);
        btnBackToChosePubulum = (Button) findViewById(R.id.btn_back_to_chose_pubulum);
        edtTypeFoodId = (TextInputEditText) findViewById(R.id.edt_type_food_id);
        edtFoodName = (TextInputEditText) findViewById(R.id.edt_food_name);

    }
}
