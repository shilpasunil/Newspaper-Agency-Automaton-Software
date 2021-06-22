package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signupCust extends AppCompatActivity {
    private EditText Username;
    private EditText Password;
    private EditText Email;
    private Button Register;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_cust);
        Username = (EditText)findViewById(R.id.etuser);
        Password = (EditText)findViewById(R.id.etPassword);

        Register =(Button)findViewById(R.id.btnRegister);

        DB = new DBHelper(this);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = Username.getText().toString();
                String password =Password.getText().toString();


                if (user.equals("") || password.equals(""))
                    Toast.makeText(signupCust.this,"Please enter all the fields",Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkUser = DB.checkusername(user);
                    if (checkUser == true)
                        Toast.makeText(signupCust.this, "Email already in use", Toast.LENGTH_SHORT).show();
                    else
                    {
                        Boolean insert = DB.insertData(user, password);
                        if (insert == true) {
                                Toast.makeText(signupCust.this, "Registered succesfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(signupCust.this, customer.class);
                                startActivity(intent);
                        }
                        else
                                Toast.makeText(signupCust.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

        });




    }
}