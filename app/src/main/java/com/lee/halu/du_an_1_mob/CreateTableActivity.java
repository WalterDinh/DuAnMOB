package com.lee.halu.du_an_1_mob;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

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

public class CreateTableActivity extends AppCompatActivity {
    private Button btnCreateNewTable;
    private Button btnBackToCreateZone;
    private TextInputEditText edtIdTable;
    private TextInputEditText edtTableName;
    private Spinner spinnerTable;
    SpinnerAdapter nameAdapter;
    List<Model> models = new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_table);
        init();
        myRef = database.getReference("User").child("adminhalu").child("khu");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot modelDataSnapshot :
                        dataSnapshot.getChildren()) {
                    Model model = modelDataSnapshot.getValue(Model.class);
                    models.add(model);
                }
                nameAdapter = new SpinnerAdapter(models, CreateTableActivity.this);
                spinnerTable.setAdapter(nameAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnCreateNewTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String idtable = edtIdTable.getText().toString();
                final String tablename = edtTableName.getText().toString();
                final String zonename = models.get(spinnerTable.getSelectedItemPosition()).getZonename().toString();
                DatabaseReference myRef1 = database.getReference("User").child("adminhalu").child("ban");
                String tableids = myRef1.child(idtable).getKey();
                Model model = new Model(idtable, tablename, zonename);
                myRef1.child(tableids).setValue(model);
                startActivity(new Intent(CreateTableActivity.this, UpdateDiagramActivity.class));

            }
        });
    }

    private void init() {
        btnCreateNewTable = (Button) findViewById(R.id.btn_create_new_table);
        btnBackToCreateZone = (Button) findViewById(R.id.btn_back_to_create_zone);
        edtIdTable = (TextInputEditText) findViewById(R.id.edt_id_table);
        edtTableName = (TextInputEditText) findViewById(R.id.edt_table_name);
        spinnerTable = (Spinner) findViewById(R.id.spinner_table);

    }
}
