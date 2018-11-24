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

public class UpdateZoneActivity extends AppCompatActivity {
    private Button btnUpdateZone;
    private Button btnBackUpdateDiagram;
    private TextInputEditText edtUpdateZoneId;
    private TextInputEditText edtUpdateZoneName;
    final List<Model> models = new ArrayList<Model>();
    int position;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_zone);
        init();
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundlezone");
        edtUpdateZoneId.setText(bundle.getString("idzonefood"));
        edtUpdateZoneName.setText(bundle.getString("zonename"));
        myRef = database.getReference("Khu");
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
        btnUpdateZone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
                startActivityForResult(new Intent(UpdateZoneActivity.this, UpdateDiagramActivity.class), 333);
                finish();
            }
        });
    }
    private void update() {
        Intent intent = getIntent();
        position = intent.getIntExtra("positionzone", -1);
        Log.e("hhh", position + "");
        Log.e("mossss", models.size() + "");
        Model model = new Model(models.get(position).getIdzone(), edtUpdateZoneName.getText().toString());
        myRef.child(models.get(position).getIdzone()).setValue(model);
        Intent intent1 = new Intent();
        intent1.putExtra("modelzone", model);
        intent1.putExtra("positionzone2", position);
        setResult(-1, intent1);
        finish();
    }
    private void init(){
        btnUpdateZone = (Button) findViewById(R.id.btn_update_zone);
        btnBackUpdateDiagram = (Button) findViewById(R.id.btn_back_update_diagram);
        edtUpdateZoneId = (TextInputEditText) findViewById(R.id.edt_update_zone_id);
        edtUpdateZoneName = (TextInputEditText) findViewById(R.id.edt_update_zone_name);
        }
}
