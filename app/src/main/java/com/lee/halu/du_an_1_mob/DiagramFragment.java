package com.lee.halu.du_an_1_mob;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lee.halu.du_an_1_mob.Adapter.DiagramAdpater;
import com.lee.halu.du_an_1_mob.Model.DiagramModel;

import java.util.ArrayList;
import java.util.List;

public class DiagramFragment extends Fragment {
    DiagramAdpater adpater;
    List<DiagramModel> models=new ArrayList<>();
    GridView gridViewl;
 final FirebaseDatabase database=FirebaseDatabase.getInstance();
  DatabaseReference myref;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //chuyen layout --> view
        View view = inflater.inflate(R.layout.diagram_fragment, container, false);
        gridViewl=view.findViewById(R.id.gridview2);
        models.add(new DiagramModel("A4"));
        models.add(new DiagramModel("A2"));
        models.add(new DiagramModel("A6"));
        models.add(new DiagramModel("A1"));
        models.add(new DiagramModel("A9"));

        adpater=new DiagramAdpater(models,getActivity());
        gridViewl.setAdapter(adpater);
        return view;
    }
}
