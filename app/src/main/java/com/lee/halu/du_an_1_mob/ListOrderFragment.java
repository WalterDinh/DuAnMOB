package com.lee.halu.du_an_1_mob;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lee.halu.du_an_1_mob.Adapter.ItemOrderAdapter;
import com.lee.halu.du_an_1_mob.Adapter.OrderAdapter;
import com.lee.halu.du_an_1_mob.Model.Model;
import com.lee.halu.du_an_1_mob.Model.OrderModel;
import com.lee.halu.du_an_1_mob.Model.PaysModel;

import java.util.ArrayList;
import java.util.List;

import static com.lee.halu.du_an_1_mob.LoginActivity.username1;

public class ListOrderFragment extends Fragment {
    OrderAdapter orderAdapter;
    List<OrderModel> orderModels=new ArrayList<>();
    GridView gridView;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
List<PaysModel>paysModels=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //chuyen layout --> view
        View view = inflater.inflate(R.layout.list_order_fragmnet,
                container, false);
        gridView=view.findViewById(R.id.gridview);
        myRef = database.getReference("User").child(username1).child("pay");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot modelDataSnapshot :
                        dataSnapshot.getChildren()) {
                    PaysModel model = modelDataSnapshot.getValue(PaysModel.class);
                    paysModels.add(model);
                }
                orderAdapter = new OrderAdapter(paysModels, getActivity());
                gridView.setAdapter(orderAdapter);
                orderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), PayActivity.class));


            }
        });
        return view;
    }
}
