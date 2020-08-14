package com.example.projectApp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class Signup extends AppCompatActivity {
    EditText name, username, password, repassword;
    Button signup;
    DBAdapter adapter;
    TextView loginText;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        context=this;

        name = (EditText) findViewById(R.id.name);
        username = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.spassword);
        repassword = (EditText) findViewById(R.id.repassword);
        signup=(Button) findViewById(R.id.signup);
        adapter = new DBAdapter(this);

        loginText=findViewById(R.id.textView5);
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login=new Intent(Signup.this, LoginActivity.class);
                startActivity(login);
            }
        });
    }
    public void insert(View view) {
        String nameInput = name.getText().toString();
        String usernameInput = username.getText().toString();
        String passwordInput = password.getText().toString();
        String repasswordInput = repassword.getText().toString();
        if (nameInput.equals("") || usernameInput.equals("") || passwordInput.equals("") || repasswordInput.equals("")) {
            if (nameInput.equals("")) {
                name.setError("this field is required");
            } if (usernameInput.equals("")) {
                username.setError("this field is required");
            }  if (passwordInput.equals("")) {
                password.setError("this field is required");
            } if (repasswordInput.equals("")) {
                repassword.setError("this field is required");
            }
        } else {
            if (!passwordInput.equals(repasswordInput)) {
                repassword.setError("Passwords have to match");
            } else {
                boolean checkEmail=adapter.checkEmail(usernameInput); //check if email already registered
                if(checkEmail==true){ //email not registered
                    boolean insert = adapter.insertData(nameInput, usernameInput, passwordInput,0);
                    if (insert==true) {
                        Toast.makeText(context,"Sign up successfully!",Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(context,"Sign up unsuccessfully",Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(context,"Email already registered",Toast.LENGTH_LONG).show();

                }

            }
        }
    }
}