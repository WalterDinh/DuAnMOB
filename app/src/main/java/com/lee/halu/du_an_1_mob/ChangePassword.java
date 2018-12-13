package com.lee.halu.du_an_1_mob;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
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
import com.lee.halu.du_an_1_mob.Model.UserModel;

import java.util.ArrayList;
import java.util.List;

import static com.lee.halu.du_an_1_mob.LoginActivity.username1;

public class ChangePassword extends AppCompatActivity {
    private ImageView imageView3;
    private Button btnChangepassword2;
    private Button btnBackchangepassword;
    private TextInputEditText edtOldPassword;
    private TextInputEditText edtNewPassword;
    private TextInputEditText edtRepassword;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    List<UserModel> models=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        init();
        btnChangepassword2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String oldpassword=edtOldPassword.getText().toString();
                final String newpassword=edtNewPassword.getText().toString();
                final String repassword=edtRepassword.getText().toString();
                myRef = database.getReference("User").child(username1);
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            UserModel model = snapshot.getValue(UserModel.class);
                            if ((oldpassword.equals(model.getPassword())&&(newpassword.equals(repassword)))){
                                myRef.child("password").setValue(newpassword);

                                }
                                else {
                                edtOldPassword.setError("Sai mật khẩu");
                            }
                            startActivity(new Intent(ChangePassword.this,UserActivity.class));

                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    private void init(){
        imageView3 =  findViewById(R.id.imageView3);
        btnChangepassword2 =  findViewById(R.id.btn_changepassword2);
        btnBackchangepassword =  findViewById(R.id.btn_backchangepassword);
        edtOldPassword =  findViewById(R.id.edt_old_password);
        edtNewPassword =  findViewById(R.id.edt_new_password);
        edtRepassword =  findViewById(R.id.edt_repassword);

    }
}
