package com.lee.halu.du_an_1_mob;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lee.halu.du_an_1_mob.Adapter.NameAdapter;
import com.lee.halu.du_an_1_mob.Model.Model;

import java.util.ArrayList;
import java.util.List;

import static com.lee.halu.du_an_1_mob.LoginActivity.username1;

public class ZoneFragment extends Fragment {
    NameAdapter nameAdapter;
    List<Model> modelss = new ArrayList<>();
    ListView listView;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myref;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_type_fragment, container, false);
        listView = view.findViewById(R.id.list);

        myref = database.getReference("User").child(username1).child("khu");
        myref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot modelDataSnapshot :
                        dataSnapshot.getChildren()) {
                    Model model = modelDataSnapshot.getValue(Model.class);
                    modelss.add(model);
                }
                nameAdapter = new NameAdapter(modelss, getActivity());
                listView.setAdapter(nameAdapter);
                nameAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = modelss.get(position).getZonename().toString();
                Intent intent = new Intent("zone");
                intent.putExtra("zonename", name);
                getActivity().sendBroadcast(intent);
            }
        });
        return view;

    }
}
