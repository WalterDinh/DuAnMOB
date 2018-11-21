package com.lee.halu.du_an_1_mob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.lee.halu.du_an_1_mob.Adapter.BillAdapter;
import com.lee.halu.du_an_1_mob.Model.BillModel;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ListBillActivity extends AppCompatActivity {
List<BillModel> billModels=new ArrayList<>();
BillAdapter billAdapter;
ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bill);
        date();
        listView=findViewById(R.id.list);
        billAdapter=new BillAdapter(billModels,this);
        listView.setAdapter(billAdapter);
    }
    private void date(){
        Calendar c = Calendar.getInstance();
        System.out.println("Current time =&gt; "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("HH:mm yyyy/MM/dd");
        String formattedDate = df.format(c.getTime());
        BillModel billModel=new BillModel();
        billModel.setIdbill("123");
        billModel.setTablename("A4");
        billModel.setPay(1000000);
        billModel.setTimefinish(formattedDate);
        billModels.add(billModel);
        BillModel billModel1=new BillModel();
        billModel1.setIdbill("13");
        billModel1.setTablename("A4");
        billModel1.setPay(10000000);
        billModel1.setTimefinish(formattedDate);
        billModels.add(billModel1);
        BillModel billModel2=new BillModel();
        billModel2.setIdbill("13");
        billModel2.setTablename("C4");
        billModel2.setPay(1000000000);
        billModel2.setTimefinish(formattedDate);
        billModels.add(billModel2);
    }
}
