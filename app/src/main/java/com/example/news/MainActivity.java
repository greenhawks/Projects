package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView uname,password,register;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataBase db = new DataBase(MainActivity.this,"user",null,1);
        uname = (TextView)findViewById(R.id.editTextText);
        password = (TextView)findViewById(R.id.editTextTextPassword);
        register = (TextView)findViewById(R.id.Register);
        login = (Button)findViewById(R.id.button);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = uname.getText().toString();
                String pass = password.getText().toString();
                if(username.isEmpty() && pass.isEmpty())
                {
                    Toast.makeText(MainActivity.this,"Fill Username / Password",Toast.LENGTH_LONG).show();
                }
                if(!db.check(username,pass) )
                {
                    Toast.makeText(getApplicationContext(),"Username and password Doesn't match",Toast.LENGTH_LONG).show();
                }
                else
                {
                    startActivity(new Intent(MainActivity.this,Home_Activity.class));
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Register.class);
                startActivity(i);
            }
        });

    }
}