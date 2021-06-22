package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signUpnews extends AppCompatActivity {
    private EditText Username;
    private EditText Password;
    private Button Register;
    DBHelperNews DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_upnews);
        Username = (EditText)findViewById(R.id.etUserNews);
        Password = (EditText)findViewById(R.id.etPassNews);

        Register =(Button)findViewById(R.id.btnRegisterNews);

        DB = new DBHelperNews(this);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = Username.getText().toString();
                String password =Password.getText().toString();


                if (user.equals("") || password.equals(""))
                    Toast.makeText(signUpnews.this,"Please enter all the fields",Toast.LENGTH_SHORT).show();
                else {
                         Boolean checkUser = DB.checkusername(user);
                         if (checkUser == true)
                            Toast.makeText(signUpnews.this, "Account already exists", Toast.LENGTH_SHORT).show();
                        else {
                            Boolean insert = DB.insertData(user, password);
                            if (insert == true) {
                                Toast.makeText(signUpnews.this, "Registered succesfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(signUpnews.this, newsagent.class);
                                startActivity(intent);
                            } else
                                Toast.makeText(signUpnews.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }

            }

        });



    }
}