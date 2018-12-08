package com.lee.halu.du_an_1_mob;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lee.halu.du_an_1_mob.Adapter.NameAdapter;
import com.lee.halu.du_an_1_mob.Adapter.SpinnerAdapter;
import com.lee.halu.du_an_1_mob.Model.Model;

import java.util.ArrayList;
import java.util.List;

public class UpdateTableActivity extends AppCompatActivity {
    private Button btnUpdateTable;
    private Button btnBackToCreateZones;
    private TextView edtUpdateIdTable;
    private TextInputEditText edtUpdateTableName;
    private Spinner spinnerUpdateTable;
    final List<Model> models = new ArrayList<Model>();
    final List<Model> models1 = new ArrayList<Model>();

    int position;
    SpinnerAdapter nameAdapter;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef,myRefs;
    String zonename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_table);
        init();
        Intent intent = getIntent();
        position = intent.getIntExtra("positiontable", -1);

        Bundle bundle = intent.getBundleExtra("bundletable");
        edtUpdateIdTable.setText(bundle.getString("idtable"));
        edtUpdateTableName.setText(bundle.getString("tablename"));
        myRef = database.getReference("User").child("adminhalu").child("khu");
        myRefs.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot modelDataSnapshot :
                        dataSnapshot.getChildren()) {
                    Model model = modelDataSnapshot.getValue(Model.class);
                    models1.add(model);
                }
                nameAdapter=new SpinnerAdapter(models1,UpdateTableActivity.this);
                spinnerUpdateTable.setAdapter(nameAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        myRef = database.getReference("User").child("adminhalu").child("ban");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot modelsDataSnapshot :
                        dataSnapshot.getChildren()) {
                    Model model = modelsDataSnapshot.getValue(Model.class);
                    models.add(model);
                    Log.e("model", models.size() + "");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        btnUpdateTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                update();
                startActivityForResult(new Intent(UpdateTableActivity.this, UpdateDiagramActivity.class), 444);
                finish();
            }
        });
    }
    private void update() {

        Log.e("hhh", position + "");
        Log.e("mossss", models.size() + "");
        zonename=models1.get(spinnerUpdateTable.getSelectedItemPosition()).getZonename().toString();
        Model model = new Model(models.get(position).getIdzone(), edtUpdateTableName.getText().toString(),zonename);
        myRef.child(models.get(position).getIdzone()).setValue(model);
        Intent intent1 = new Intent();
        intent1.putExtra("modeltable", model);
        intent1.putExtra("positiontable2", position);
        setResult(-1, intent1);
        finish();

    }

    private void  init(){
        btnUpdateTable =  findViewById(R.id.btn_update_table);
        btnBackToCreateZones =  findViewById(R.id.btn_back_to_create_zones);
        edtUpdateIdTable =  findViewById(R.id.edt_update_id_table);
        edtUpdateTableName =  findViewById(R.id.edt_update_table_name);
        spinnerUpdateTable =  findViewById(R.id.spinner_update_table);

    }
}
