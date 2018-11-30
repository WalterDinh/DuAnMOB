package com.lee.halu.du_an_1_mob.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lee.halu.du_an_1_mob.Model.Model;
import com.lee.halu.du_an_1_mob.Model.PayModel;
import com.lee.halu.du_an_1_mob.R;

import java.util.ArrayList;
import java.util.List;

public class PayAdapter extends BaseAdapter {
    List<PayModel> payModels;
    Context context;

    public PayAdapter(List<PayModel> payModels, Context context) {
        this.payModels = payModels;
        this.context = context;
    }

    @Override
    public int getCount() {
        return payModels.size();
    }

    @Override
    public Object getItem(int position) {
        return payModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_list_pay, null);
        TextView name = view.findViewById(R.id.txt_item_pay_name);
        TextView price = view.findViewById(R.id.txt_item_pay_price);
        TextView amount = view.findViewById(R.id.txt_item_pay_amount);
        TextView tong = view.findViewById(R.id.txt_item_pay_sta);
        name.setText(payModels.get(position).getName());
        price.setText(payModels.get(position).getPrice() + "");
        amount.setText(payModels.get(position).getAmount() + "");
        double tong1=payModels.get(position).getPrice()*payModels.get(position).getAmount();
        tong.setText(tong1+"");
        return view;
    }
}
