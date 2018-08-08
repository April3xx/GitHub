package com.example.user.project_sqlite;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private EditText txtId, txtName, txtEmail, txtYear;
    private Button btnAdd;
    private ListView listProduct;
    private ArrayList<ProductData> listData = new ArrayList<ProductData>();
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtId = (EditText) findViewById(R.id.txtId);
        txtName = (EditText) findViewById(R.id.txtName);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtYear = (EditText) findViewById(R.id.txtYear);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        listProduct = (ListView) findViewById(R.id.listProduct);
        dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        showList();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addProduct();
            }
        });


    }

    private void showList() {
        getProduct();

        listProduct.setAdapter(new AdapterListview(this, listData));
    }

    private void getProduct() {
        Cursor mCursor = database.query(true, "show", new String[]{"id", "name", "email", "year"}
                , null, null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();

            listData.clear();
            if (mCursor.getCount() > 0) {
                do {
                    int id = mCursor.getInt(mCursor.getColumnIndex("id"));
                    String product = mCursor.getString(mCursor.getColumnIndex("product"));
                    String detail = mCursor.getString(mCursor.getColumnIndex("detail"));
                    int price = mCursor.getInt(mCursor.getColumnIndex("price"));

                    listData.add(new ProductData(id, product, detail, price));
                } while (mCursor.moveToNext());
            }
        }
    }

    public void editProduct(int id, String name, String email, int year) {
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("name", name);
        values.put("email", email);
        values.put("year", year);

        database.update("show", values, "id = ?", new String[]{"" + id});

        showList();
    }

    public void deleteProduct(int id) {
        database.delete("show", "id = " + id, null);
        Toast.makeText(this, "Delete Data Id " + id + " Complete", Toast.LENGTH_SHORT).show();

        showList();
    }


    private void addProduct() {         // TODO Auto-generated method stub
        if (txtId.length() > 0 && txtName.length() > 0 && txtEmail.length() > 0 && txtYear.length() >0) {
            ContentValues values = new ContentValues();
            values.put("id", txtId.getText().toString());
            values.put("name", txtName.getText().toString());
            values.put("email", txtEmail.getText().toString());
            values.put("year", txtYear.getText().toString());


            database.insert("show", null, values);

            Toast.makeText(this, "Add Data Complete", Toast.LENGTH_SHORT).show();

            txtId.setText("");
            txtName.setText("");
            txtEmail.setText("");
            txtYear.setText("");

            showList();
        } else

        {
            Toast.makeText(this, "Please Input Data", Toast.LENGTH_SHORT).show();
        }
    }


    public void showEdit(int id, String name, String email, int year) {
        Intent i = new Intent(this, EditActivity.class);
        i.putExtra("keyId", id);
        i.putExtra("keyName", name);
        i.putExtra("keyEmail", email);
        i.putExtra("keyYear", year);

        startActivityForResult(i, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            int id = intent.getExtras().getInt("keyId");
            String NameEdit = intent.getExtras().getString("keyName");
            String EmailEdit = intent.getExtras().getString("keyEmail");
            int YearEdit = intent.getExtras().getInt("keyYear");
            editProduct(id, NameEdit, EmailEdit, YearEdit);
        }
    }
}

