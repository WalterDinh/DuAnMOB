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

public class SpinnerAdapter extends BaseAdapter {
    List<Model> billModels;
    Context context;

    public SpinnerAdapter(List<Model> billModels, Context context) {
        this.billModels = billModels;
        this.context = context;
    }
    @Override
    public int getCount() {
        return billModels.size();
    }

    @Override
    public Object getItem(int position) {
        return billModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.item_spinner,null);
        TextView name=view.findViewById(R.id.txt_name1);
        name.setText(billModels.get(position).getZonename());
        return view;
    }
}
