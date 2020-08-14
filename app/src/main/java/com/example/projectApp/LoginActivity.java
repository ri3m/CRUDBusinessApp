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

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    Button login;
    TextView textView;
    DBAdapter adapter;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context=this;

        adapter = new DBAdapter(this);
        email = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        textView=findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupB=new Intent(LoginActivity.this, Signup.class);
                startActivity(signupB);
            }
        });

        login=findViewById(R.id.login);

    }

    public void login(View view){
        String emailInput=email.getText().toString();
        String passwordInput=password.getText().toString();

        if(emailInput.equals("")||passwordInput.equals("")){
            if(emailInput.equals(""))
                email.setError("Please enter your email");
            if(passwordInput.equals(""))
                password.setError("Please enter your password");
        }
        else{
            boolean checkEmailAndPassword=adapter.emailNPasswordCheck(emailInput,passwordInput);
            if(checkEmailAndPassword==true){
                Intent login=new Intent(LoginActivity.this, MainActivity.class);
                login.putExtra("email", emailInput);
                startActivity(login);
            } else{
                Toast.makeText(context,"Wrong email or password",Toast.LENGTH_LONG).show();

            }

        }
    }
}
