package com.lee.halu.du_an_1_mob.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lee.halu.du_an_1_mob.Model.Model;
import com.lee.halu.du_an_1_mob.R;

import java.util.List;

public class ItemOrderAdapter extends BaseAdapter {
    List<Model> models;
    Context context;

    public ItemOrderAdapter(List<Model> models, Context context) {
        this.models = models;
        this.context = context;
    }
    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public Object getItem(int position) {
        return models.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.item_list_food,null);
        TextView name=view.findViewById(R.id.txt_item_name);
        TextView prince=view.findViewById(R.id.txt_item_prince);
        name.setText(models.get(position).getZonename());
        prince.setText(models.get(position).getPrice()+"");
        return view;
    }
}
