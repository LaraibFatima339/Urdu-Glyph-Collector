package com.example.registration_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername;
    EditText etPassword;
    Button btnLogin;
    TextView tvRegister;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db= new DatabaseHelper(this) ;
        etUsername = findViewById(R.id.etUsername ) ;
        etPassword  = findViewById(R.id.etPassword ) ;
        btnLogin  = findViewById(R.id.btnLogin  ) ;
        tvRegister  = findViewById(R.id.tvRegister ) ;

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent=new Intent(LoginActivity.this, RegistrationActivity.class ) ;
                startActivity(registerIntent ) ;
            }
        }) ;

        btnLogin .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String user = etUsername .getText().toString().trim();
                String pwd = etPassword  .getText().toString().trim();
                Boolean res = db.checkLogin(user, pwd) ;
                if(res==true)
                {
                    Toast.makeText(LoginActivity.this, "Successfully Logged In", Toast.LENGTH_SHORT ).show();
                   Intent LoginScreen = new Intent(LoginActivity.this,MainActivity.class);
                   startActivity(LoginScreen) ;
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Login Error", Toast.LENGTH_SHORT ).show();
                }

            }
        }) ;
    }
}
