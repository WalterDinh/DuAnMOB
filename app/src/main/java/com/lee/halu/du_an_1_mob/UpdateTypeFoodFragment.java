package com.lee.halu.du_an_1_mob;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.update_type_food_fragment, container, false);
        btn_insert_type_food = view.findViewById(R.id.btn_insert_type_food);
        listView = view.findViewById(R.id.list);
        myRef = database.getReference("User").child("adminhalu").child("loaiDoAn");
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


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showAlertDialog(position);
                return true;
            }
        });

        btn_insert_type_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent=new Intent(getActivity(), CreateTypeFoodActivity.class);
                startActivity(intent);
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
                final Intent intent=new Intent(getActivity(), UpdateTypeFoodActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("idtypefood",models.get(position).getIdzone());
                bundle.putString("typefoodname",models.get(position).getZonename());
                intent.putExtra("bundle",bundle);
                intent.putExtra("positiontypefood",position);
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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==333){
            if(resultCode==-1){
                Model model=data.getParcelableExtra("modeltypefood");
                int position=data.getIntExtra("positiontypefood2",-1);
                models.set(position,model);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
