package com.lee.halu.du_an_1_mob;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class CreateDrinksActivity extends AppCompatActivity {
    private Button btnCreateDrinks;
    private Button btnBackToUpdateDrinks2;
    private TextInputEditText edtCreateIdDrinks;
    private TextInputEditText edtCreateDrinksName;
    private TextInputEditText edtCreateDrinksPrice;

    private Spinner spinnerCreateDrinks;
    SpinnerAdapter nameAdapter;
    List<Model> models = new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_drinks);
        init();
        myRef = database.getReference("User").child("adminhalu").child("loaiDoUong");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot modelDataSnapshot :
                        dataSnapshot.getChildren()) {
                    Model model = modelDataSnapshot.getValue(Model.class);
                    models.add(model);
                }
                nameAdapter = new SpinnerAdapter(models, CreateDrinksActivity.this);
                spinnerCreateDrinks.setAdapter(nameAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnCreateDrinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String idfood = edtCreateIdDrinks.getText().toString();
                final String foodname = edtCreateDrinksName.getText().toString();
                final String price = edtCreateDrinksPrice.getText().toString();
                final String typefoodname = models.get(spinnerCreateDrinks.getSelectedItemPosition()).getZonename().toString();
                DatabaseReference myRef1 = database.getReference("User").child("adminhalu").child("doUong");
                String foodsids = myRef1.child(idfood).getKey();
                Model model = new Model(idfood, foodname, typefoodname, Integer.parseInt(price));
                myRef1.child(foodsids).setValue(model);
                startActivity(new Intent(CreateDrinksActivity.this, UpdateDrinksActivity.class));

            }
        });
    }

    private void init() {
        edtCreateDrinksPrice =  findViewById(R.id.edt_create_drinks_price);
        btnCreateDrinks =  findViewById(R.id.btn_create_drinks);
        btnBackToUpdateDrinks2 =  findViewById(R.id.btn_back_to_update_drinks2);
        edtCreateIdDrinks =  findViewById(R.id.edt_create_id_drinks);
        edtCreateDrinksName =  findViewById(R.id.edt_create_drinks_name);
        spinnerCreateDrinks =  findViewById(R.id.spinner_create_drinks);

    }
}
