package com.example.user.project_sqlite;

/**
 * Created by USER on 11/13/2017.
 */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends Activity {
    private EditText txtIdEdit,txtNameEdit, txtEmailEdit, txtYearEdit;
    private Button btnEdit;
    private int id;

    @SuppressLint("CutPasteId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        txtIdEdit = (EditText) findViewById(R.id.txtIdEdit);
        txtNameEdit = (EditText) findViewById(R.id.txtNameEdit);
        txtEmailEdit = (EditText) findViewById(R.id.txtEmailEdit);
        btnEdit = (Button) findViewById(R.id.btnEdit);

        this.id = getIntent().getExtras().getInt("keyId");
        txtIdEdit.setText(getIntent().getExtras().getString("key"));
        txtNameEdit.setText(getIntent().getExtras().getString("keyName"));
        txtEmailEdit.setText("" + getIntent().getExtras().getInt("keyEmail"));
        btnEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent();
                setResult(RESULT_OK, i);
                i.putExtra("keyId", id);
                i.putExtra("keyName", txtNameEdit.getText().toString());
                i.putExtra("keyEmail", txtEmailEdit.getText().toString());
                i.putExtra("keyYear", Integer.parseInt(txtYearEdit.getText().toString()));
                finish();
            }
        });
    }
}