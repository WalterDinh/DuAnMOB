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
import com.lee.halu.du_an_1_mob.Model.Model;

import java.util.ArrayList;
import java.util.List;

public class ListDrinkFragment extends Fragment {
    ItemOrderAdapter itemOrderAdapter;
    List<Model> models=new ArrayList<>();
    GridView gridView;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //chuyen layout --> view
        View view = inflater.inflate(R.layout.list_drinks_fragment,
                container, false);
        gridView=view.findViewById(R.id.gridviewdrinks);

        Log.e("ssaa","hien");
        myRef = database.getReference("TenDoUong");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot modelDataSnapshot :
                        dataSnapshot.getChildren()) {
                    Model model = modelDataSnapshot.getValue(Model.class);
                    models.add(model);
                    Log.e("models",models.size()+"");
                    }
                itemOrderAdapter=new ItemOrderAdapter(models,getActivity());
                gridView.setAdapter(itemOrderAdapter);
                }
                @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListPayFragment fragment=new ListPayFragment();
                Bundle bundle=new Bundle();
                String typename=models.get(position).getIdzone().toString();
                bundle.putString("typename",typename);
                Log.e("hoo","hoho");
                fragment.setArguments(bundle);
            }
        });
        return view;
    }
}
