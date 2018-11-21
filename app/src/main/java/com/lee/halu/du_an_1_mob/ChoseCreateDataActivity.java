package com.lee.halu.du_an_1_mob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChoseCreateDataActivity extends AppCompatActivity {
    private ImageView imgLogoCreateData;
    private Button btnCreateDiagram;
    private Button btnCreateData;
    private Button btnBackUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_create_data);
        init();
        btnBackUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChoseCreateDataActivity.this,UserActivity.class));
            }
        });
        btnCreateDiagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ChoseCreateDataActivity.this,UpdateDiagramActivity.class));

            }
        });
        btnCreateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChoseCreateDataActivity.this,ChoseCreatePubulumActivity.class));

            }
        });
    }
    private void init(){
        imgLogoCreateData = (ImageView) findViewById(R.id.img_logo_create_date);
        btnCreateDiagram = (Button) findViewById(R.id.btn_create_diagram);
        btnCreateData = (Button) findViewById(R.id.btn_create_date);
        btnBackUser = (Button) findViewById(R.id.btn_back_user);

    }
}
