package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class newsagent extends AppCompatActivity {
    private EditText Name;
    private EditText Pass;
    private TextView Info;
    private TextView Register;
    private Button Login;
    private Button SignUp;
    DBHelperNews DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsagent);
        Name =(EditText)findViewById(R.id.etName);
        Pass =(EditText)findViewById(R.id.etPass);
        Info = (TextView)findViewById(R.id.tvInfo);
        Register = (TextView)findViewById(R.id.tvRegister);
        Login =(Button)findViewById(R.id.btnLogin);
        SignUp=(Button)findViewById(R.id.btnSignup);
        DB = new DBHelperNews(this);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = Name.getText().toString();
                String password =Pass.getText().toString();

                if (user.equals("") || password.equals(""))
                    Toast.makeText(newsagent.this,"Please enter all the fields",Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkUser = DB.checkusernamepassword(user,password);
                    if (checkUser == false)
                        Toast.makeText(newsagent.this, "Either Username or Password is incorrect ", Toast.LENGTH_SHORT).show();
                    else {

                        Intent intent = new Intent(newsagent.this, homeNews.class);
                        startActivity(intent);
                    }
                }
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(newsagent.this,signUpnews.class);
                startActivity(intent);

            }
        });


    }
}