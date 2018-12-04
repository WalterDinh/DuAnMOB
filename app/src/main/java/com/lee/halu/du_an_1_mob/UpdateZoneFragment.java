package com.lee.halu.du_an_1_mob;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
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

public class UpdateZoneFragment extends Fragment {
    FloatingActionButton btn_insert_zone;
    NameAdapter adapter;
    ListView listView;
    List<Model> models = new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    String a;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.update_zonef_ragment, container, false);
        btn_insert_zone = view.findViewById(R.id.btn_insert_zone);
        listView = view.findViewById(R.id.list);
        myRef = database.getReference("User").child("adminhalu").child("khu");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot modelDataSnapshot :
                        dataSnapshot.getChildren()) {
                    Model model = modelDataSnapshot.getValue(Model.class);
                    models.add(model);
                }
                adapter = new NameAdapter(models, getActivity());
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btn_insert_zone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CreateZoneActivity.class));
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((UpdateDiagramActivity) getActivity()).b = models.get(position).getZonename().toString();
                ((UpdateDiagramActivity) getActivity()).d = false;
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showAlertDialog(position);
                return true;
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
                models.clear();
                adapter = new NameAdapter(models, getActivity());
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();


            }
        });
        builder.setNegativeButton("Sửa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final Intent intent = new Intent(getActivity(), UpdateZoneActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("idzonefood", models.get(position).getIdzone());
                bundle.putString("zonename", models.get(position).getZonename());
                intent.putExtra("bundlezone", bundle);
                intent.putExtra("positionzone", position);
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

    private void delete(int i) {
        myRef.child(models.get(i).getIdzone()).removeValue();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 333) {
            if (resultCode == -1) {
                Model model = data.getParcelableExtra("modelzone");
                int position = data.getIntExtra("positionzone2", -1);
                models.set(position, model);
                adapter.notifyDataSetChanged();
            }
        }
    }
}