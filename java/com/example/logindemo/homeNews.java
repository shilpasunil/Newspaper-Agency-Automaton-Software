package com.example.logindemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class homeNews extends AppCompatActivity {
    private Button Add;
    private Button View;
    private TextView Home;
    DBHelper DB;
    DBart DB2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_news);
        Add = (Button)findViewById(R.id.btnAddArticle);
        View =(Button)findViewById(R.id.btnViewCust);
        DB = new DBHelper(this);
        DB2 = new DBart(this);
        Home =(TextView)findViewById(R.id.tvHome);
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent = new Intent(homeNews.this,MainActivity.class);
                startActivity(intent);
            }
        });
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent = new Intent(homeNews.this, addArticle.class);
                startActivity(intent);
            }
        });

        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Cursor res = DB2.getData();
                if ( res.getCount() == 0){

                    Toast.makeText(homeNews.this, "No entry exists so far", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                        buffer.append("\nCustomer Name \t:"+res.getString(0)+"\n");
                    buffer.append("\nArticle Name \t:"+res.getString(1)+"\n");
                    buffer.append("\n");


                }
                AlertDialog.Builder  builder= new AlertDialog.Builder(homeNews.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();



            }
        });


    }
}