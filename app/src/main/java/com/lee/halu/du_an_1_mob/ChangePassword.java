package com.lee.halu.du_an_1_mob;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ChangePassword extends AppCompatActivity {
    private ImageView imageView3;
    private Button btnChangepassword2;
    private Button btnBackchangepassword;
    private TextInputEditText edtOldPassword;
    private TextInputEditText edtNewPassword;
    private TextInputEditText edtRepassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        init();
        btnBackchangepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChangePassword.this,UserActivity.class));
            }
        });
    }
    private void init(){
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        btnChangepassword2 = (Button) findViewById(R.id.btn_changepassword2);
        btnBackchangepassword = (Button) findViewById(R.id.btn_backchangepassword);
        edtOldPassword = (TextInputEditText) findViewById(R.id.edt_old_password);
        edtNewPassword = (TextInputEditText) findViewById(R.id.edt_new_password);
        edtRepassword = (TextInputEditText) findViewById(R.id.edt_repassword);

    }
}
