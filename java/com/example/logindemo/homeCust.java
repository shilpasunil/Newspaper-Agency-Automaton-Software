package com.example.logindemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class homeCust extends AppCompatActivity {
    private Button Subscribe;
    private Button Bill;
    private Button Unsubscribe;
    private Button View;
    private TextView Home;
    public final static String MESSAGE_KEY ="shilpa.senddata.message_key";

    DBHelperAddArticle DB;
    DBart DB2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_cust);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MESSAGE_KEY);

        View =(Button)findViewById(R.id.btnView);
        Subscribe = (Button) findViewById(R.id.btnSubscribe);
        Bill = (Button) findViewById(R.id.btnBill);
        Unsubscribe = (Button) findViewById(R.id.btnUnsubscribe);
       Home =(TextView)findViewById(R.id.tvHo);
        DB = new DBHelperAddArticle(this);
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent = new Intent(homeCust.this,MainActivity.class);
                startActivity(intent);
            }
        });
       View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Cursor res = DB.getData();
                if (res.getCount() == 0) {

                    Toast.makeText(homeCust.this, "No entry exists so far", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("\nName \t:" + res.getString(0) + "\n");
                    buffer.append("\nType \t:" + res.getString(1) + "\n");
                    buffer.append("\n");

                }
                AlertDialog.Builder builder = new AlertDialog.Builder(homeCust.this);
                builder.setCancelable(true);
                builder.setTitle("Available Articles");
                builder.setMessage(buffer.toString());
                builder.show();


            }
        });

       Subscribe.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(android.view.View v) {
               Intent intent = new Intent(homeCust.this,subscription.class);
               intent.putExtra(MESSAGE_KEY,message);

               startActivity(intent);
           }
       });

        Bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Toast.makeText(homeCust.this, "Bill calculation action takes place", Toast.LENGTH_SHORT).show();


            }
        });

        Unsubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent = new Intent(homeCust.this,unsubscribe.class);
                intent.putExtra(MESSAGE_KEY,message);

                startActivity(intent);
            }
        });


    }
}