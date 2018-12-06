package com.lee.halu.du_an_1_mob;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lee.halu.du_an_1_mob.Model.UserModel;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    private AutoCompleteTextView email;
    private EditText password;
    private Button emailSignInButton;
    private TextView signup;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    UserModel userModel;
    List<UserModel> userModels = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);
        Log.e("SBC","LOGIN");
        init();
        email.setText("adminhalu");
        password.setText("17122017");
        emailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = email.getText().toString();
                final String passwords = password.getText().toString();
                myRef = database.getReference("User");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.e("SBC","LOGIN");
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            userModel = snapshot.getValue(UserModel.class);
                            userModels.add(userModel);
                            for (int i = 0; i < userModels.size(); i++) {
                                if (username.equals(userModels.get(i).getUsername().toString())
                                        && passwords.equals(userModels.get(i).getPassword())) {
                                    startActivity(new Intent(LoginActivity.this, HelloActivity.class));
                                    finish();
                                    break;
                                } else {
                                    Toast.makeText(LoginActivity.this, "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        signup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, CreateUserActivity.class));
            }
        });
    }

    private void init() {
        signup = findViewById(R.id.txt_sign_up);
        email =  findViewById(R.id.email);
        password =  findViewById(R.id.password);
        emailSignInButton =  findViewById(R.id.email_sign_in_button);

    }

}