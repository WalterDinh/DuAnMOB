package com.lee.halu.du_an_1_mob;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lee.halu.du_an_1_mob.Adapter.SpinnerAdapter;
import com.lee.halu.du_an_1_mob.Model.Model;

import java.util.ArrayList;
import java.util.List;

public class CreateFoodActivity extends AppCompatActivity {
    private Button btnCreateFood;
    private Button btnBackToCreateDiagram2;
    private TextInputEditText edtIdFood;
    private TextInputEditText edtFoodName;
    private Spinner spinnerFood;
    private TextInputEditText edtFoodPrice;
    SpinnerAdapter nameAdapter;
    List<Model> models = new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_food);
        init();
        myRef = database.getReference("User").child("adminhalu").child("loaiDoAn");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot modelDataSnapshot :
                        dataSnapshot.getChildren()) {
                    Model model = modelDataSnapshot.getValue(Model.class);
                    models.add(model);
                }
                nameAdapter = new SpinnerAdapter(models, CreateFoodActivity.this);
                spinnerFood.setAdapter(nameAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnCreateFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String idfood = edtIdFood.getText().toString();
                final String foodname = edtFoodName.getText().toString();
                final String price = edtFoodPrice.getText().toString();
                final String typefoodname = models.get(spinnerFood.getSelectedItemPosition()).getZonename().toString();
                DatabaseReference myRef1 = database.getReference("User").child("adminhalu").child("doAn");

                String foodsids = myRef1.child(idfood).getKey();
                Model model = new Model(idfood, foodname, typefoodname, Integer.parseInt(price));
                myRef1.child(foodsids).setValue(model);
                startActivity(new Intent(CreateFoodActivity.this, UpdateFoodActivity.class));

            }
        });
    }

    private void init() {
        btnCreateFood =  findViewById(R.id.btn_create_food);
        btnBackToCreateDiagram2 =  findViewById(R.id.btn_back_to_create_diagram2);
        edtIdFood =  findViewById(R.id.edt_id_food);
        edtFoodName =  findViewById(R.id.edt_food_name);
        spinnerFood =  findViewById(R.id.spinner_food);
        edtFoodPrice =  findViewById(R.id.edt_food_frice);

    }
}
