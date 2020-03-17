package com.example.registration_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText etUsername;
    EditText etPassword;
    EditText etCnfPassword;
    EditText etFirstName;
    EditText etLastName;
    EditText etEmail;
    EditText etPhone;
    Button btnRegister;
    TextView tvLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        db = new DatabaseHelper(this) ;
        etUsername = findViewById(R.id.etUsername ) ;
        etFirstName  = findViewById(R.id.etFirstName  ) ;
        etLastName  = findViewById(R.id.etLastname  ) ;
        etEmail  = findViewById(R.id.etEmail  ) ;
        etPhone  = findViewById(R.id.etPhone  ) ;
        etPassword  = findViewById(R.id.etPassword ) ;
        etCnfPassword  = findViewById(R.id.etCnfPassword ) ;
        btnRegister   = findViewById(R.id.btnRegister  ) ;
        tvLogin   = findViewById(R.id.tvLogin  ) ;

        tvLogin .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(RegistrationActivity.this, LoginActivity.class) ;
                startActivity(LoginIntent ) ;
            }
        }) ;

        btnRegister .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etUsername .getText() .toString().trim() ;
                String firstName = etFirstName  .getText() .toString().trim() ;
                String lastName = etLastName  .getText() .toString().trim() ;
                String email = etEmail  .getText() .toString().trim() ;
                String phone = etPhone  .getText() .toString().trim() ;
                String pwd = etPassword  .getText() .toString().trim() ;
                String cnf_pwd = etCnfPassword .getText() .toString().trim() ;

        if(user.equals("") || pwd.equals("") || cnf_pwd.equals("")||firstName .equals("") ||lastName .equals("") ||email.equals("") ||phone .equals(""))
        {
            Toast.makeText(RegistrationActivity.this, "Fields Required", Toast.LENGTH_SHORT).show();
        }
        else
            {
            if(pwd.equals(cnf_pwd)){
                Boolean checkUsername = db.CheckUsername(user);
                if(checkUsername == true){
                    long insert = db.addUser(user, pwd,firstName, lastName ,email, phone) ;
                    if(insert >0){
                        Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_SHORT).show();
                       etUsername .setText("");
                        etPassword .setText("");
                        etCnfPassword .setText("");
                        etFirstName  .setText("");
                        etLastName  .setText("");
                        etEmail  .setText("");
                        etPhone  .setText("");
                        Intent moveToLogin = new Intent(RegistrationActivity.this, LoginActivity.class);
                       startActivity(moveToLogin );
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Username already taken", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_SHORT).show();
            }
        }
    }
});
    }
}
