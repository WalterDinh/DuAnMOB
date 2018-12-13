package com.lee.halu.du_an_1_mob.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lee.halu.du_an_1_mob.DiagramFragment;
import com.lee.halu.du_an_1_mob.Model.DiagramModel;
import com.lee.halu.du_an_1_mob.Model.Model;
import com.lee.halu.du_an_1_mob.Model.OrderModel;
import com.lee.halu.du_an_1_mob.R;

import java.util.List;

public class DiagramAdpater extends BaseAdapter {
    List<Model> diagramModels;
    Context context;
    DiagramFragment diagramFragment;

    public DiagramAdpater(List<Model> diagramModels, Context context) {
        this.diagramModels = diagramModels;
        this.context = context;
    }

    @Override
    public int getCount() {
        return diagramModels.size();
    }

    @Override
    public Object getItem(int position) {
        return diagramModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_gridview_table, null);
        diagramFragment = new DiagramFragment();
        final TextView tablename = view.findViewById(R.id.btn_name);
        final LinearLayout constraintLayout = view.findViewById(R.id.container3);
        tablename.setText(diagramModels.get(position).getZonename());

        return view;
    }
}