package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class subscription extends AppCompatActivity {
    private Button Sub;
    private EditText art;
    private EditText name;
    public final static String MESSAGE_KEY ="shilpa.senddata.message_key";

    DBHelperAddArticle DB;
    DBart DB2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MESSAGE_KEY);

        Sub = (Button)findViewById(R.id.btnSub);
        art =(EditText)findViewById(R.id.etArtt);


        DB = new DBHelperAddArticle(this);
        DB2 = new DBart(this);

        Sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String article = art.getText().toString();
                if(article.equals(" "))
                    Toast.makeText(subscription.this, "Please provide article info", Toast.LENGTH_SHORT).show();
                else{
                    Boolean check = DB.checkarticle(article);
                    if(!check){
                        Toast.makeText(subscription.this, "Article is not available", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Cursor c = DB.getCost(article);
                        int cost=0;
                        if(c.moveToFirst()){
                            String temp=c.getString(0);
                            cost = Integer.parseInt(temp);
                        }



                        Boolean insert = DB2.insertArticle(message,article,cost);
                        if(insert){

                            Toast.makeText(subscription.this, "article added successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(subscription.this,homeCust.class);
                            intent.putExtra(MESSAGE_KEY,message);

                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(subscription.this, "failed to add article", Toast.LENGTH_SHORT).show();

                        }
                    }
                }

            }
        });
    }
}