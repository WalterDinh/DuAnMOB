package com.lee.halu.du_an_1_mob;

import android.content.Intent;
import android.renderscript.Sampler;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lee.halu.du_an_1_mob.Adapter.SpinnerAdapter;
import com.lee.halu.du_an_1_mob.Model.Model;

import java.util.ArrayList;
import java.util.List;

public class UpdateDrinksNameActivity extends AppCompatActivity {
    private Button btnUpdateDrinks2;
    private Button btnBackToUpdateDrinks2;
    private TextView txtIdDrinksName;
    private TextInputEditText edtUpdateDrinksName;
    private Spinner spinnerUpdateDrinks;
    private TextInputEditText edyUpdateDrinksPrice;
    final List<Model> models = new ArrayList<Model>();
    final List<Model> models1 = new ArrayList<Model>();
    int position;
    SpinnerAdapter nameAdapter;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef,myRefs;
    String zonename;
    int price,ii;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_drinks_name);
        init();
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundledrinksname");
        position = intent.getIntExtra("positiondrinksname", -1);
        txtIdDrinksName.setText(bundle.getString("iddrinksname"));
        edtUpdateDrinksName.setText(bundle.getString("drinksname"));
        price=bundle.getInt("drinksprice");
        edyUpdateDrinksPrice.setText(price+"");
        myRefs = database.getReference("User").child("adminhalu").child("loaiDoUong");
        myRefs.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot modelDataSnapshot :
                        dataSnapshot.getChildren()) {
                    Model model = modelDataSnapshot.getValue(Model.class);
                    models1.add(model);
                }
                nameAdapter=new SpinnerAdapter(models1,UpdateDrinksNameActivity.this);
                spinnerUpdateDrinks.setAdapter(nameAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnUpdateDrinks2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRefs = database.getReference("User").child("adminhalu").child("doUong");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot modelsDataSnapshot :
                                dataSnapshot.getChildren()) {
                            Model model = modelsDataSnapshot.getValue(Model.class);
                            models.add(model);
                            Log.e("model", models.size() + "");
                        }
                        update();
                        startActivityForResult(new Intent(UpdateDrinksNameActivity.this, UpdateDrinksActivity.class), 222);
                        finish();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
    }
    private void update() {

        Log.e("hhh", position + "");
        Log.e("mossss", models.size() + "");
        String yy=edyUpdateDrinksPrice.getText().toString();
        ii= Integer.parseInt(yy);
        zonename=models1.get(spinnerUpdateDrinks.getSelectedItemPosition()).getZonename().toString();
        Model model2 = new Model(models.get(position).getIdzone(), edtUpdateDrinksName.getText().toString(),zonename, ii);
        myRef.child(models.get(position).getIdzone()).setValue(model2);
        Intent intent1 = new Intent();
        intent1.putExtra("modeldrinksname2", model2);
        intent1.putExtra("positiondrinksname3", position);
        setResult(-1, intent1);
        finish();
    }

    private void init(){

        btnUpdateDrinks2 =  findViewById(R.id.btn_update_drinks2);
        btnBackToUpdateDrinks2 =  findViewById(R.id.btn_back_to_update_drinks2);
        txtIdDrinksName =  findViewById(R.id.txt_id_drinks_name);
        edtUpdateDrinksName =  findViewById(R.id.edt_update_drinks_name);
        spinnerUpdateDrinks =  findViewById(R.id.spinner_update_drinks);
        edyUpdateDrinksPrice =  findViewById(R.id.edy_update_drinks_price);

    }
}
