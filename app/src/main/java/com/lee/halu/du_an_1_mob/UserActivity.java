package com.lee.halu.du_an_1_mob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class UserActivity extends AppCompatActivity {
    private ImageView imageView3;
    private Button btnChangepassword;
    private Button btnCreate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        init();
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           startActivity(new Intent(UserActivity.this,HomeActivity.class));
            }
        });
        btnChangepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this,ChangePassword.class));
            }
        });
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this,ChoseCreateDataActivity.class));
            }
        });
    }
    private void init(){
        imageView3 =  findViewById(R.id.imageUser);
        btnChangepassword =  findViewById(R.id.btn_changepassword);
        btnCreate =  findViewById(R.id.btn_create);

    }
}
