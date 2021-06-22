package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class unsubscribe extends AppCompatActivity {
    private Button Unsub;
    private EditText Article;
    DBart DB;
    public final static String MESSAGE_KEY ="shilpa.senddata.message_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unsubscribe);
        DB = new DBart(this);
        Unsub =(Button)findViewById(R.id.btnUnsub);
        Article =(EditText)findViewById(R.id.etUnsub);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MESSAGE_KEY);

        Unsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String article = Article.getText().toString();
                if(article.equals(" "))
                    Toast.makeText(unsubscribe.this, "Please provide article info", Toast.LENGTH_SHORT).show();
                else {
                    Boolean check = DB.checkarticle(article);
                    if (!check) {
                        Toast.makeText(unsubscribe.this, "Not Subscribed to unsubscribe", Toast.LENGTH_SHORT).show();
                    } else {
                        Boolean delete = DB.delete(message, article);
                        if (delete) {
                            Toast.makeText(unsubscribe.this, "Unsubscribed Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(unsubscribe.this, homeCust.class);
                            intent.putExtra(MESSAGE_KEY,message);

                            startActivity(intent);

                        } else {
                            Toast.makeText(unsubscribe.this, "Unsubscription failed", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
            }
        });
    }
}