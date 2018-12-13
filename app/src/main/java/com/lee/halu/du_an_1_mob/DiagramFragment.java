package com.lee.halu.du_an_1_mob;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lee.halu.du_an_1_mob.Adapter.DiagramAdpater;
import com.lee.halu.du_an_1_mob.Adapter.NameAdapter;
import com.lee.halu.du_an_1_mob.Model.DiagramModel;
import com.lee.halu.du_an_1_mob.Model.Model;

import java.util.ArrayList;
import java.util.List;

import static com.lee.halu.du_an_1_mob.LoginActivity.username1;

public class DiagramFragment extends Fragment {
    DiagramAdpater adpater;
    List<Model> models = new ArrayList<>();
    GridView gridViewl;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    String type;
    private BroadcastReceiver broadcastReceiver;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //chuyen layout --> view
        View view = inflater.inflate(R.layout.diagram_fragment, container, false);
        gridViewl = view.findViewById(R.id.gridview2);

        myRef = database.getReference("User").child(username1).child("ban");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot modelDataSnapshot :
                        dataSnapshot.getChildren()) {
                    Model model = modelDataSnapshot.getValue(Model.class);
                    models.add(model);
                }
                adpater = new DiagramAdpater(models, getActivity());
                gridViewl.setAdapter(adpater);
                gridViewl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String name = models.get(position).getZonename().toString();
                      
                        startActivity(new Intent(getActivity(),PayActivity.class));
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                type = intent.getStringExtra("zonename");
                Log.e("type", type);
                models.clear();
                myRef = database.getReference("User").child(username1).child("ban");
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot modelDataSnapshot :
                                dataSnapshot.getChildren()) {
                            Model model = modelDataSnapshot.getValue(Model.class);
                            Log.e("models",model.getName2());
                            if(model.getName2().equalsIgnoreCase(type))
                            models.add(model);

                        }
                        adpater = new DiagramAdpater(models, getActivity());
                        gridViewl.setAdapter(adpater);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        };

        IntentFilter intentFilter = new IntentFilter("zone");
        getActivity().registerReceiver(broadcastReceiver, intentFilter);

        return view;
    }

}
