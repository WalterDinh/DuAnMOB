package com.lee.halu.du_an_1_mob;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

public class UpdateTableFragment extends Fragment {
    FloatingActionButton btn_insert_table;
    NameAdapter adapter;
    ListView listView;
    List<Model> models = new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Ban");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //chuyen layout --> view
        View view = inflater.inflate(R.layout.update_table_fragment, container, false);
btn_insert_table=view.findViewById(R.id.btn_insert_table);
listView=view.findViewById(R.id.list);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot modelDataSnapshot :
                        dataSnapshot.getChildren()) {
                    Model model = modelDataSnapshot.getValue(Model.class);
                    models.add(model);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        adapter = new NameAdapter(models, getActivity());
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showAlertDialog(position);
                return true;
            }
        });

btn_insert_table.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(getActivity(),CreateTableActivity.class));
    }
});
        return view;
    }
    public void showAlertDialog(final int position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Thay đổi dữ liệu");
        builder.setMessage("Bạn có muốn sửa hay xóa dữ liệu?");
        builder.setCancelable(false);

        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity(), "Đã xóa", Toast.LENGTH_SHORT).show();
                delete(position);
                models.remove(position);
                adapter = new NameAdapter(models, getActivity());
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        });
        builder.setNegativeButton("Sửa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final Intent intent=new Intent(getActivity(), UpdateZoneActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("idzonefood",models.get(position).getIdzone());
                bundle.putString("zonename",models.get(position).getZonename());
                intent.putExtra("bundlezone",bundle);
                intent.putExtra("positionzone",position);
                startActivity(intent);
            }
        });
        builder.setNeutralButton("thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
    private void delete(int i){
        myRef.child(models.get(i).getIdzone()).removeValue();

    }
}
