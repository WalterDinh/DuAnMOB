package com.lee.halu.du_an_1_mob;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lee.halu.du_an_1_mob.Model.Model;

public class CreateZoneActivity extends AppCompatActivity {
    private ImageView imageView3;
    private Button btnCreateZone;
    private Button btnCreateDiagram;
    private TextInputEditText edtCreateIdZone;
    private TextInputEditText edtCreateZoneName;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_zone2);
        init();
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateZoneActivity.this, HomeActivity.class));
                finish();
            }
        });
        btnCreateZone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String zoneid = edtCreateIdZone.getText().toString();
                final String zonename = edtCreateZoneName.getText().toString();
                myRef = database.getReference("User").child("adminhalu").child("khu");
                String zoneids = myRef.child(zoneid).getKey();
                Model model = new Model(zoneid, zonename);
                myRef.child(zoneids).setValue(model);
                startActivity(new Intent(CreateZoneActivity.this,UpdateDiagramActivity.class));
                finish();
            }
        });

    }

    private void init() {
        imageView3 = findViewById(R.id.image_create_zone);
        btnCreateZone = findViewById(R.id.btn_create_zone);
        btnCreateDiagram = findViewById(R.id.btn_back_to_create_diagram);
        edtCreateIdZone = findViewById(R.id.edt_create_id_zone);
        edtCreateZoneName = findViewById(R.id.edt_create_zone_name);

    }
}
