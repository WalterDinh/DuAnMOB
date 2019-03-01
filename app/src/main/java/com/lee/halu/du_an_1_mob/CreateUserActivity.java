package com.lee.halu.du_an_1_mob;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lee.halu.du_an_1_mob.Model.Model;
import com.lee.halu.du_an_1_mob.Model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class CreateUserActivity extends AppCompatActivity {
    private Button btnChangepassword2;
    private Button btnBackchangepassword;
    private TextInputEditText edtCreateUser;
    private TextInputEditText edtCreatePassword;
    private TextInputEditText edtRepassword2;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    UserModel userModel;
    List<UserModel> userModels = new ArrayList<>();
    int dem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        init();
        myRef = database.getReference("User");
        btnBackchangepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateUserActivity.this, LoginActivity.class));
                finish();
            }
        });
        btnChangepassword2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = edtCreateUser.getText().toString();
                final String password = edtCreatePassword.getText().toString();
                final String repassword = edtRepassword2.getText().toString();

                myRef.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            userModel = snapshot.getValue(UserModel.class);
                            userModels.add(userModel);
                        }
                        if (username.length() < 6) {
                            edtCreateUser.setError("Tên đăng nhập phải trên 6 kí tự");
                        }
                        if (password.length() < 6) {
                            edtCreatePassword.setError("Mật khẩu phải trên 6 kí tự");

                        }
                            if (password.equals(repassword)) {
                                for (int i = 0; i < userModels.size(); i++) {
                                    dem = 0;
                                    if (username.equals(userModels.get(i).getUsername().toString())) {
                                        dem++;
                                        Log.e("dem", dem + "");
                                    }

                                }
                                if (dem == 0) {
                                    String user = myRef.child(username).getKey();
                                    UserModel model = new UserModel(username, password);
                                    myRef.child(user).setValue(model);
                                    Toast.makeText(CreateUserActivity.this, "Tạo thành công", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(CreateUserActivity.this, LoginActivity.class));
                                    finish();
                                } else {
                                    edtCreateUser.setError("Tên đăng nhập đã tồn tại");
                                    Toast.makeText(CreateUserActivity.this, "Tên đăng nhập đã tồn tại", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                edtRepassword2.setError("Mật khẩu không trùng khớp");
                                Toast.makeText(CreateUserActivity.this, "Tên đăng nhập đã tồn tại", Toast.LENGTH_SHORT).show();
                            }
                        }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
    }

    private void init() {
        btnChangepassword2 = findViewById(R.id.btn_create_user);
        btnBackchangepassword = findViewById(R.id.btn_back_login);
        edtCreateUser = findViewById(R.id.edt_create_user);
        edtCreatePassword = findViewById(R.id.edt_create_password);
        edtRepassword2 = findViewById(R.id.edt_repassword2);

    }
}
