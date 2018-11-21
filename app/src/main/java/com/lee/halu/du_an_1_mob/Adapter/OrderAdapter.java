package com.lee.halu.du_an_1_mob.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lee.halu.du_an_1_mob.Model.OrderModel;
import com.lee.halu.du_an_1_mob.R;

import java.util.List;

public class OrderAdapter extends BaseAdapter {
    List<OrderModel> orderModels;
    Context context;

    public OrderAdapter(List<OrderModel> orderModels, Context context) {
        this.orderModels = orderModels;
        this.context = context;
    }

    @Override
    public int getCount() {
        return orderModels.size();
    }

    @Override
    public Object getItem(int position) {
        return orderModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.item_list_order_recycleview,null);

        TextView tablename=view.findViewById(R.id.txt_table_name_in_order);
        TextView timeuse=view.findViewById(R.id.txt_use_time_in_order);
        TextView pay1=view.findViewById(R.id.txt_pay_in_order);

        tablename.setText(orderModels.get(position).getTablename());
        timeuse.setText(orderModels.get(position).getTimeuse());
        pay1.setText(orderModels.get(position).getPay1());
        return view;
    }
}
