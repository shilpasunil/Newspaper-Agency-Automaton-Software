package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class addArticle extends AppCompatActivity {
    private EditText name;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button Add;
    private EditText Cost;
    DBHelperAddArticle DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);
        name = (EditText)findViewById(R.id.etArticleName);
        radioGroup =(RadioGroup)findViewById(R.id.radioGroup);
        Add =(Button)findViewById(R.id.btnAdd);
        Cost=(EditText)findViewById(R.id.etPrice);

        DB=new DBHelperAddArticle(this);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameA = name.getText().toString();
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton)findViewById(radioId);
                String type = radioButton.getText().toString();
                String temp= Cost.getText().toString();


                if (nameA.equals(" ") || type.equals(" ")|| temp.equals(" "))
                    Toast.makeText(addArticle.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean check = DB.checkarticle(nameA);
                    int cost = Integer.parseInt(temp);
                    if (check == true) {
                        Toast.makeText(addArticle.this, "Article already added", Toast.LENGTH_SHORT).show();
                    } else {
                        Boolean insert = DB.insertData(nameA,type,cost);
                        if (insert == true){
                            Toast.makeText(addArticle.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(addArticle.this,homeNews.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(addArticle.this, "It is not added", Toast.LENGTH_SHORT).show();
                        }
                    }


                }
            }
        });

    }


}