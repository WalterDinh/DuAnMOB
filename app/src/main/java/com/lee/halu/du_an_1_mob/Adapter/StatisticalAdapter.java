package com.lee.halu.du_an_1_mob.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lee.halu.du_an_1_mob.Model.StatisticalModel;
import com.lee.halu.du_an_1_mob.R;

import java.util.Date;
import java.util.List;

public class StatisticalAdapter extends BaseAdapter{
    List<StatisticalModel> statisticalModels;
    Context context;

    public StatisticalAdapter(List<StatisticalModel> statisticalModels, Context context) {
        this.statisticalModels = statisticalModels;
        this.context = context;
    }

    @Override
    public int getCount() {
        return statisticalModels.size();
    }

    @Override
    public Object getItem(int position) {
        return statisticalModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.list_item_statistical,null);
        TextView foodname=view.findViewById(R.id.txt_item_namefood);
        TextView count=view.findViewById(R.id.txt_item_count);
        TextView pay=view.findViewById(R.id.txt_item_pay);
        foodname.setText(statisticalModels.get(position).getFoodname());
        pay.setText(statisticalModels.get(position).getPay()+" ");
        count.setText(statisticalModels.get(position).getCount()+" ");
        return view;
    }
}
