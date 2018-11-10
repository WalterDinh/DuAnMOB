package com.lee.halu.du_an_1_mob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CreateDiagramActivity extends AppCompatActivity {
    private ImageView imgLogoCreateZone;
    private Button btnCreateZone;
    private Button btnCreateTable;
    private Button btnBackUser2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_zone);
        init();
        imgLogoCreateZone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateDiagramActivity.this, ChoseCreateDataActivity.class));
            }
        });
        btnCreateZone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateDiagramActivity.this, CreateZoneActivity.class));
            }
        });
        btnCreateTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateDiagramActivity.this, CreateTableActivity.class));

            }
        });
        btnBackUser2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateDiagramActivity.this, ChoseCreateDataActivity.class));

            }
        });
    }

    private void init() {
        imgLogoCreateZone = (ImageView) findViewById(R.id.img_logo_create_zone);
        btnCreateZone = (Button) findViewById(R.id.btn_create_zone);
        btnCreateTable = (Button) findViewById(R.id.btn_create_table);
        btnBackUser2 = (Button) findViewById(R.id.btn_back_user2);

    }
}
