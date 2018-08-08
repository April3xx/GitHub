package com.example.priva.test2;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Button;

public class AdapterListViewData extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context context;
    private MainActivity control;

    private ArrayList<ProductData> listData = new ArrayList<ProductData>();

    public AdapterListViewData(MainActivity control, ArrayList<ProductData> listData) {
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
            holderListAdapter.txtnumber = convertView.findViewById(R.id.txtnumber);
            holderListAdapter.txtProduct = (TextView) convertView.findViewById(R.id.txtProduct);
            holderListAdapter.txtDetail = (TextView) convertView.findViewById(R.id.txtDetail);
            holderListAdapter.txtPrice = (TextView) convertView.findViewById(R.id.txtPrice);
            holderListAdapter.btnEdit = (Button) convertView.findViewById(R.id.btnEdit);
            holderListAdapter.btnDelete = (Button) convertView.findViewById(R.id.btnDelete);
            convertView.setTag(holderListAdapter);
        } else {
            holderListAdapter = (HolderListAdapter)
                    convertView.getTag();
        }

        final int id = listData.get(position).getId();

        final String product = listData.get(position).getProduct();
        final String detail = listData.get(position).getDetail();
        final int price = listData.get(position).getPrice();
        final int number =listData.get(position).getNumber();

        holderListAdapter.txtProduct.setText("Product : " + product);
        holderListAdapter.txtDetail.setText("Detail : " + detail);
        holderListAdapter.txtPrice.setText("Price : " + price);
        holderListAdapter.txtnumber.setText("id : " + number);
        holderListAdapter.btnDelete.setOnClickListener(new View.OnClickListener()

        {
            public void onClick(View v) {
                control.deleteProduct(id);
            }
        });

        holderListAdapter.btnEdit.setOnClickListener(new View.OnClickListener()

        {
            public void onClick(View v) {
                control.showEdit(id, product, detail, price, number);
            }
        });
        return convertView;
    }
}