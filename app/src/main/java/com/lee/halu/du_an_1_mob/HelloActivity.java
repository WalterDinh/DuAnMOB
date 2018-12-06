package com.lee.halu.du_an_1_mob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HelloActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        Thread thread = new Thread();
        thread.start();
        try {
            Thread.sleep(8000);
            startActivity(new Intent(this, HomeActivity.class));
    } catch(
    InterruptedException e)

    {
        e.printStackTrace();
    }
}

}
