package com.lee.halu.du_an_1_mob;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.lee.halu.du_an_1_mob.Adapter.PayAdapter;
import com.lee.halu.du_an_1_mob.Model.PayModel;

import java.util.ArrayList;
import java.util.List;

public class ListPayFragment extends Fragment {
    List<PayModel> payModels = new ArrayList<>();
    PayAdapter payAdapter;
    ListView listView;
    String demo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //chuyen layout --> view
        View view = inflater.inflate(R.layout.list_pay_fragment,
                container, false);
        Bundle bundle=this.getArguments();
        if(bundle!=null){
            demo=bundle.getString("typename");

        }
listView=view.findViewById(R.id.list);
payModels.add(new PayModel("caphe",20000,20000,1));
payAdapter=new PayAdapter(payModels,getActivity());
listView.setAdapter(payAdapter);
listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
});
        return view;
    }
}

