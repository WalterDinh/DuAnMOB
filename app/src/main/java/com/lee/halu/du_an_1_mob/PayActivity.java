package com.lee.halu.du_an_1_mob;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PayActivity extends AppCompatActivity {
    private ListTypeFoodFragment listTypeFoodFragment;
    private ListDrinkFragment listDrinkFragment;
    private ListFoodFragment listFoodFragment;
    boolean a = true;
int position;
String typename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        listDrinkFragment = new ListDrinkFragment();
        listFoodFragment = new ListFoodFragment();
        listTypeFoodFragment = new ListTypeFoodFragment();
        hienthidoan();
    }

    public void drink(View view) {
        hienthidouong();
    }

    private void hienthidouong() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();

        if (listDrinkFragment.isAdded()) { //neu co chi hien thi
            ft.show(listDrinkFragment);
        } else { //khong co --> them vao
            ft.add(R.id.container1, listDrinkFragment);
        }

        if (listFoodFragment.isAdded()) { //neu dang hien thi --> an no di
            ft.hide(listFoodFragment);
        }
if(listTypeFoodFragment.isAdded()){
            ft.hide(listTypeFoodFragment);
}
        //sau khi thay doi fragment--> phai xac thuc
        ft.commit();
    }

    public void food(View view) {
        hienthidoan();
    }

    public void typed(View view) {
        hienthuloaido();
    }

    private void hienthuloaido() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        if (a == true) {
            if (listTypeFoodFragment.isAdded()) { //neu co chi hien thi
                ft.show(listTypeFoodFragment);
                a = false;
            } else { //khong co --> them vao
                ft.add(R.id.container1, listTypeFoodFragment);
            }
        } else {
            if (listTypeFoodFragment.isAdded()) { //neu dang hien thi --> an no di
                ft.hide(listTypeFoodFragment);
                a = true;
            }
        }
        ft.commit();

    }

    private void hienthidoan() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();

        if (listFoodFragment.isAdded()) { //neu co chi hien thi
            ft.show(listFoodFragment);
        } else { //khong co --> them vao
            ft.add(R.id.container1, listFoodFragment);
        }

        if (listDrinkFragment.isAdded()) { //neu dang hien thi --> an no di
            ft.hide(listDrinkFragment);
        }
        if(listTypeFoodFragment.isAdded()){
            ft.hide(listTypeFoodFragment);
        }
        //sau khi thay doi fragment--> phai xac thuc
        ft.commit();
    }
}
