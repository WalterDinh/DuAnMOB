package com.lee.halu.du_an_1_mob;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lee.halu.du_an_1_mob.Adapter.NameAdapter;
import com.lee.halu.du_an_1_mob.Adapter.PayAdapter;
import com.lee.halu.du_an_1_mob.Model.Model;
import com.lee.halu.du_an_1_mob.Model.PayModel;
import com.lee.halu.du_an_1_mob.Model.PaysModel;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.lee.halu.du_an_1_mob.LoginActivity.username1;

public class ListPayFragment extends Fragment {
    List<PayModel> payModels = new ArrayList<>();
    PayAdapter payAdapter;
    ListView listView;
    String drinksname, tablename;
    int price;
    private BroadcastReceiver broadcastReceiver;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    Button btnPay, btnSave, btntablename;
    TextView textView;
    int pay = 0;
    DecimalFormat formatter = new DecimalFormat("###,###,###");

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //chuyen layout --> view
        View view = inflater.inflate(R.layout.list_pay_fragment,
                container, false);
        listView = view.findViewById(R.id.list);
        btntablename = view.findViewById(R.id.btn_table_name_in_pay);
        btnPay = view.findViewById(R.id.btn_pay);
        btnSave = view.findViewById(R.id.btn_save_pay);
        textView = view.findViewById(R.id.txt_amount);
        myRef = database.getReference("User").child(username1).child("pay");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot modelDataSnapshot :
                        dataSnapshot.getChildren()) {
                    PaysModel model = modelDataSnapshot.getValue(PaysModel.class);
                    textView.setText(model.getAmoung().toString());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                btntablename.setText(tablename);
                drinksname = intent.getStringExtra("drinksname");
                price = intent.getIntExtra("drinksprice", -1);
                payModels.add(new PayModel(drinksname, price, 1, price));
                payAdapter = new PayAdapter(payModels, getActivity());
                listView.setAdapter(payAdapter);
                payAdapter.notifyDataSetChanged();
                pay = pay + price;
                textView.setText(formatter.format(pay) + " VNĐ");
                listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        pay = pay - payModels.get(position).getPrice();
                        Log.e("pay", pay + "");
                        payModels.remove(position);
                        payAdapter.notifyDataSetChanged();
                        textView.setText(formatter.format(pay) + " VNĐ");
                        return true;
                    }
                });


            }

        };
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                System.out.println("Current time =&gt; "+c.getTime());

                SimpleDateFormat df = new SimpleDateFormat("HH:mm yyyy/MM/dd");
                String formattedDate = df.format(c.getTime());
                String tableids = myRef.push().getKey();
                PaysModel paysModel=new PaysModel(formatter.format(pay) + " VNĐ",formattedDate,1);
                myRef.child(tableids).setValue(paysModel);
                startActivity(new Intent(getActivity(),HomeActivity.class));

            }
        });
        IntentFilter intentFilter = new IntentFilter("PAY");
        getActivity().registerReceiver(broadcastReceiver, intentFilter);
        return view;
    }
}

