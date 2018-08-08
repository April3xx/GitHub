package com.example.user.project_sqlite;

/**
 * Created by USER on 11/13/2017.
 */

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Button;

public class AdapterListview extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context context;
    private MainActivity control;

    private ArrayList<ProductData> listData = new ArrayList<ProductData>();

    public AdapterListview(MainActivity control, ArrayList<ProductData> listData) {
        this.control = control;
        this.context = control.getBaseContext();
        this.mInflater = LayoutInflater.from(context);
        this.listData = listData;
    }

    public int getCount() {
        return listData.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {         // TODO Auto-generated method stub
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        HolderListAdapter holderListAdapter; //เกบ็ส่วนประกอบของ List แต่ละอัน
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.adapter_listview, null);
            holderListAdapter = new HolderListAdapter();
            holderListAdapter.txtName = (TextView) convertView.findViewById(R.id.txtName);
            holderListAdapter.txtEmail = (TextView) convertView.findViewById(R.id.txtEmail);
            holderListAdapter.txtYear = (TextView) convertView.findViewById(R.id.txtYear);
            holderListAdapter.btnEdit = (Button) convertView.findViewById(R.id.btnEdit);
            holderListAdapter.btnDelete = (Button) convertView.findViewById(R.id.btnDelete);
            convertView.setTag(holderListAdapter);
        } else {
            holderListAdapter = (HolderListAdapter)
                    convertView.getTag();
        }
        final int id = listData.get(position).getId();
        final String name = listData.get(position).getProduct();
        final String email = listData.get(position).getDetail();
        final int year = listData.get(position).getPrice();
        holderListAdapter.txtName.setText("Product : " + name);
        holderListAdapter.txtEmail.setText("Detail : " + email);
        holderListAdapter.txtYear.setText("Price : " + year);
        holderListAdapter.btnDelete.setOnClickListener(new View.OnClickListener()

        {
            public void onClick (View v){
            control.deleteProduct(id);
        }
        });

        holderListAdapter.btnEdit.setOnClickListener(new View.OnClickListener()

        {
            public void onClick(View v) {
                control.showEdit(id, name, email, year);
            }
        });
        return convertView;
    }
}