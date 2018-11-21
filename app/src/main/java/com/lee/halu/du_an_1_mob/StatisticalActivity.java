package com.lee.halu.du_an_1_mob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.lee.halu.du_an_1_mob.Adapter.StatisticalAdapter;
import com.lee.halu.du_an_1_mob.Model.BillModel;
import com.lee.halu.du_an_1_mob.Model.StatisticalModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class StatisticalActivity extends AppCompatActivity {
List<StatisticalModel> statisticalModels=new ArrayList<>();
StatisticalAdapter statisticalAdapter;
ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistical);
        date();
        listView=findViewById(R.id.list);
        statisticalAdapter=new StatisticalAdapter(statisticalModels,this);
        listView.setAdapter(statisticalAdapter);

    }
    private void date(){
    StatisticalModel model=new StatisticalModel();
    model.setFoodname("Cà phê sữa");
    model.setCount(5);
    model.setPay(100000);
    statisticalModels.add(model);
        StatisticalModel model1=new StatisticalModel();
        model1.setFoodname("Cà phê đen");
        model1.setCount(3);
        model1.setPay(60000);
        statisticalModels.add(model1);
        StatisticalModel model2=new StatisticalModel();
        model2.setFoodname("Bạc sỉu");
        model2.setCount(2);
        model2.setPay(50000);
        statisticalModels.add(model2);
    }
}
