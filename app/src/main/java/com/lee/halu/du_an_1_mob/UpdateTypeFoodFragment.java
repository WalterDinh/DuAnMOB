package com.lee.halu.du_an_1_mob;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lee.halu.du_an_1_mob.Adapter.NameAdapter;
import com.lee.halu.du_an_1_mob.Model.Model;

import java.util.ArrayList;
import java.util.List;

public class UpdateTypeFoodFragment extends Fragment {
    FloatingActionButton btn_insert_type_food;
    NameAdapter adapter;
    ListView listView;
    List<Model> models = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.update_type_food_fragment, container, false);
        btn_insert_type_food=view.findViewById(R.id.btn_insert_type_food);
        listView = view.findViewById(R.id.list);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("LoaiMon");
        Log.e("aaaaa", "nhan du lieu");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("aaaaa", "nhan du lieu");

                for (DataSnapshot modelDataSnapshot :
                        dataSnapshot.getChildren()) {
                    Model model = modelDataSnapshot.getValue(Model.class);
                    Log.e("aaaaa", "nhan du lieu");
                    models.add(model);
                    Log.e("bbbbb", "add list");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        adapter = new NameAdapter(models, getActivity());
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        Toast.makeText(getActivity(), "asdasdas", Toast.LENGTH_SHORT).show();
        btn_insert_type_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),CreateTypeFoodActivity.class));
            }
        });
        return view;
    }
}
