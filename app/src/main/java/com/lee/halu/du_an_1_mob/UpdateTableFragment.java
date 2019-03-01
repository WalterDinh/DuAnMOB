package com.lee.halu.du_an_1_mob;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
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
import com.lee.halu.du_an_1_mob.Adapter.DiagramAdpater;
import com.lee.halu.du_an_1_mob.Adapter.NameAdapter;
import com.lee.halu.du_an_1_mob.Model.Model;

import java.util.ArrayList;
import java.util.List;

import static com.lee.halu.du_an_1_mob.LoginActivity.username1;

public class UpdateTableFragment extends Fragment {
    FloatingActionButton btn_insert_table;
    NameAdapter adapter;
    ListView listView;
    List<Model> models = new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef, getMyRef;
    String zone;
    private BroadcastReceiver broadcastReceiver;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.update_table_fragment, container, false);
        btn_insert_table = view.findViewById(R.id.btn_insert_table);
        listView = view.findViewById(R.id.list);

        myRef = database.getReference("User").child(username1).child("ban");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot modelDataSnapshot :
                        dataSnapshot.getChildren()) {
                    Model model = modelDataSnapshot.getValue(Model.class);
                    Log.e("ssss", model.getName2());
                    Log.e("ssss", model.getZonename());
                    models.add(model);
                    Log.e("ssss", models.size() + "");
                }
                adapter = new NameAdapter(models, getActivity());
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                zone = intent.getStringExtra("zonename");
                Log.e("type", zone);
                models.clear();
                getMyRef = database.getReference("User").child(username1).child("ban");
                getMyRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot modelDataSnapshot :
                                dataSnapshot.getChildren()) {
                            Model model = modelDataSnapshot.getValue(Model.class);
                            Log.e("models", model.getName2());
                            if (model.getName2().equalsIgnoreCase(zone))
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

            }
        };
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
                startActivity(new Intent(getActivity(), CreateTableActivity.class));
            }
        });
        IntentFilter intentFilter = new IntentFilter("zone1");
        getActivity().registerReceiver(broadcastReceiver, intentFilter);
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
                adapter.notifyDataSetChanged();

            }
        });
        builder.setNegativeButton("Sửa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final Intent intent = new Intent(getActivity(), UpdateTableActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("idtable", models.get(position).getIdzone());
                bundle.putString("tablename", models.get(position).getZonename());
                bundle.putString("zonename", models.get(position).getName2());
                intent.putExtra("bundletable", bundle);
                intent.putExtra("positiontable", position);
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
        if (requestCode == 444) {
            if (resultCode == -1) {
                Model model = data.getParcelableExtra("modeltable");
                int position = data.getIntExtra("positiontable2", -1);
                models.set(position, model);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
