package com.lee.halu.du_an_1_mob;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.lee.halu.du_an_1_mob.Adapter.OrderAdapter;
import com.lee.halu.du_an_1_mob.Model.OrderModel;

import java.util.ArrayList;
import java.util.List;

public class ListOrderFragment extends Fragment {
    OrderAdapter orderAdapter;
    List<OrderModel> orderModels=new ArrayList<>();
    GridView gridView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //chuyen layout --> view
        View view = inflater.inflate(R.layout.list_order_fragmnet,
                container, false);
        gridView=view.findViewById(R.id.gridview);
        orderModels.add(new OrderModel("A4","666666","1000000000"));
        orderModels.add(new OrderModel("A4","666666","1000000000"));

        orderModels.add(new OrderModel("A4","666666","1000000000"));

        orderAdapter=new OrderAdapter(orderModels,getActivity());
        gridView.setAdapter(orderAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), PayActivity.class));
            }
        });
        return view;
    }
}
