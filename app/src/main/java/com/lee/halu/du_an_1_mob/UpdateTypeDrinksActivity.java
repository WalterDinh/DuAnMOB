package com.lee.halu.du_an_1_mob;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lee.halu.du_an_1_mob.Model.Model;

import java.util.ArrayList;
import java.util.List;

public class UpdateTypeDrinksActivity extends AppCompatActivity {
    private Button btnUpdateTypeDrinks;
    private Button btnUpdateDrinks;
    private TextInputEditText edtUpdateTypeDrinksId;
    private TextInputEditText edtUpdateTypeDrinksName;
    final List<Model> models = new ArrayList<Model>();
    int position;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_type_drinks);
        init();
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        edtUpdateTypeDrinksId.setText(bundle.getString("idtypedrinks"));
        edtUpdateTypeDrinksName.setText(bundle.getString("typedrinksname"));
        myRef = database.getReference("LoaiDoUong");
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
        btnUpdateDrinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
                startActivityForResult(new Intent(UpdateTypeDrinksActivity.this, UpdateDrinksActivity.class), 333);
                finish();
            }
        });
    }
    private void update() {
        Intent intent = getIntent();
        position = intent.getIntExtra("positiontypefood", -1);
        Log.e("hhh", position + "");
        Log.e("mossss", models.size() + "");
        Model model = new Model(models.get(position).getIdzone(), edtUpdateTypeDrinksName.getText().toString());
        myRef.child(models.get(position).getIdzone()).setValue(model);
        Intent intent1 = new Intent();
        intent1.putExtra("modeltypedrinks", model);
        intent1.putExtra("positiontypedrinks2", position);
        setResult(-1, intent1);
        finish();
    }
    private void init(){
        btnUpdateTypeDrinks = (Button) findViewById(R.id.btn_update_type_drinks);
        btnUpdateDrinks = (Button) findViewById(R.id.btn_update_drinks);
        edtUpdateTypeDrinksId = (TextInputEditText) findViewById(R.id.edt_update_type_drinks_id);
        edtUpdateTypeDrinksName = (TextInputEditText) findViewById(R.id.edt_update_type_drinks_name);

    }
}
