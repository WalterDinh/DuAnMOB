package com.lee.halu.du_an_1_mob;

import android.content.Intent;
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

public class UpdateFoodNameActivity extends AppCompatActivity {
    private Button btnUpdateFood2;
    private Button btnBackToUpdateFood2;
    private TextView txtIdFoodName;
    private TextInputEditText edtUpdateFoodName;
    private Spinner spinnerUpdateFoodname;
    private TextInputEditText edyUpdateFoodPrice;
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
        setContentView(R.layout.activity_update_food_name);
        init();
        Intent intent = getIntent();
        position = intent.getIntExtra("positionfoodname", -1);
        Bundle bundle = intent.getBundleExtra("bundlefoodname");
        txtIdFoodName.setText(bundle.getString("idfoodname"));
        edtUpdateFoodName.setText(bundle.getString("foodname"));
        price=bundle.getInt("foodprice");
        edyUpdateFoodPrice.setText(price+"");
        myRef = database.getReference("User").child("adminhalu").child("loaiDoAn");
        myRefs.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot modelDataSnapshot :
                        dataSnapshot.getChildren()) {
                    Model model = modelDataSnapshot.getValue(Model.class);
                    models1.add(model);
                }
                nameAdapter=new SpinnerAdapter(models1,UpdateFoodNameActivity.this);
                spinnerUpdateFoodname.setAdapter(nameAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        myRef = database.getReference("Mon");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot modelsDataSnapshot :
                        dataSnapshot.getChildren()) {
                    Model model = modelsDataSnapshot.getValue(Model.class);
                    models.add(model);
                    Log.e("model", models.size() + "");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
        btnUpdateFood2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                update();
                startActivityForResult(new Intent(UpdateFoodNameActivity.this, UpdateFoodActivity.class), 222);
                finish();
            }
        });
    }
    private void update() {

        Log.e("hhh", position + "");
        Log.e("mossss", models.size() + "");
        String yy=edyUpdateFoodPrice.getText().toString();
        ii= Integer.parseInt(yy);
        zonename=models1.get(spinnerUpdateFoodname.getSelectedItemPosition()).getZonename().toString();
        Model model2 = new Model(models.get(position).getIdzone(), edtUpdateFoodName.getText().toString(),zonename, ii);
        myRef.child(models.get(position).getIdzone()).setValue(model2);
        Intent intent1 = new Intent();
        intent1.putExtra("modelfoodname2", model2);
        intent1.putExtra("positionfoodname3", position);
        setResult(-1, intent1);
        finish();
    }

    private void init(){

        btnUpdateFood2 = (Button) findViewById(R.id.btn_update_food2);
        btnBackToUpdateFood2 = (Button) findViewById(R.id.btn_back_to_update_food2);
        txtIdFoodName = (TextView) findViewById(R.id.txt_id_food_name2);
        edtUpdateFoodName = (TextInputEditText) findViewById(R.id.edt_update_food_name);
        spinnerUpdateFoodname = (Spinner) findViewById(R.id.spinner_update_foodname);
        edyUpdateFoodPrice = (TextInputEditText) findViewById(R.id.edy_update_food_price);

    }
}
