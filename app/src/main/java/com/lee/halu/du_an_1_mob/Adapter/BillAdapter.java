package com.lee.halu.du_an_1_mob.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lee.halu.du_an_1_mob.Model.BillModel;
import com.lee.halu.du_an_1_mob.R;

import java.util.Date;
import java.util.List;

public class BillAdapter extends BaseAdapter {
    List<BillModel> billModels;
    Context context;

    public BillAdapter(List<BillModel> billModels, Context context) {
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
        View view=layoutInflater.inflate(R.layout.list_item_bill,null);
        TextView idbill=view.findViewById(R.id.txt_bill_item_idbill);
        TextView tablename=view.findViewById(R.id.txt_bill_item_tablename);
        TextView paytime=view.findViewById(R.id.txt_bill_item_paytime);
        TextView pay=view.findViewById(R.id.txt_bill_item_pay);
idbill.setText(billModels.get(position).getIdbill());
        pay.setText(billModels.get(position).getPay()+" ");
        tablename.setText(billModels.get(position).getTablename());
        paytime.setText(billModels.get(position).getTimefinish());
        return view;
    }
}
