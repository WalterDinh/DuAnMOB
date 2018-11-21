package com.lee.halu.du_an_1_mob;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lee.halu.du_an_1_mob.Model.Model;

public class CreateZoneActivity extends AppCompatActivity {
    private ImageView imageView3;
    private Button btnCreateZone;
    private Button btnCreateDiagram;
    private TextInputEditText edtCreateIdZone;
    private TextInputEditText edtCreateZoneName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_zone2);
        init();
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateZoneActivity.this,HomeActivity.class));
            }
        });
        btnCreateDiagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateZoneActivity.this,CreateDiagramActivity.class));
                }
        });
        btnCreateZone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String zoneid = edtCreateIdZone.getText().toString();
                final String zonename=edtCreateZoneName.getText().toString();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Khu");
                String zoneids = myRef.child(zoneid).getKey();
                Model model = new Model(zoneid, zonename);
                myRef.child(zoneids).setValue(model);
                startActivity(new Intent(CreateZoneActivity.this,UpdateDiagramActivity.class));

            }
        });

    }
    private void init(){
        imageView3 = (ImageView) findViewById(R.id.image_create_zone);
        btnCreateZone = (Button) findViewById(R.id.btn_create_zone);
        btnCreateDiagram = (Button) findViewById(R.id.btn_back_to_create_diagram);
        edtCreateIdZone = (TextInputEditText) findViewById(R.id.edt_create_id_zone);
        edtCreateZoneName = (TextInputEditText) findViewById(R.id.edt_create_zone_name);

    }
}
