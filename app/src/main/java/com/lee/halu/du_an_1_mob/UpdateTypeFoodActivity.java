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

public class UpdateTypeFoodActivity extends AppCompatActivity {
    private Button btnUpdateTypeFood;
    private Button btnUbackToChosePubulum;
    private TextInputEditText edtUpdateTypeFoodId;
    private TextInputEditText edtUpdateFoodName;
    final List<Model> models = new ArrayList<Model>();
    int position;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("LoaiMon");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_type_food);
        init();
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        edtUpdateTypeFoodId.setText(bundle.getString("idtypefood"));
        edtUpdateFoodName.setText(bundle.getString("typefoodname"));
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
        btnUpdateTypeFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
                startActivityForResult(new Intent(UpdateTypeFoodActivity.this, UpdateFoodActivity.class), 333);
                finish();
            }
        });
    }

    private void update() {
        Intent intent = getIntent();
        position = intent.getIntExtra("positiontypefood", -1);
        Log.e("hhh", position + "");
        Log.e("mossss", models.size() + "");
        Model model = new Model(models.get(position).getIdzone(), edtUpdateFoodName.getText().toString());
        myRef.child(models.get(position).getIdzone()).setValue(model);
        Intent intent1 = new Intent();
        intent1.putExtra("modeltypefood", model);
        intent1.putExtra("positiontypefood2", position);
        setResult(-1, intent1);
        finish();
    }

    private void init() {
        btnUpdateTypeFood = (Button) findViewById(R.id.btn_update_type_food);
        btnUbackToChosePubulum = (Button) findViewById(R.id.btn_uback_to_chose_pubulum);
        edtUpdateTypeFoodId = (TextInputEditText) findViewById(R.id.edt_update_type_food_id);
        edtUpdateFoodName = (TextInputEditText) findViewById(R.id.edt_update_food_name);

    }
}
