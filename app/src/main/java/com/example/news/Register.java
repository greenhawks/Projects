package com.example.news;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Register extends AppCompatActivity {
    TextView username,password,age,email,confirm_password,goback;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        DataBase db = new DataBase(getApplicationContext(),"user",null,1);
        username = (TextView)findViewById(R.id.UserName);
        age = (TextView)findViewById(R.id.Age);
        email=(TextView)findViewById(R.id.Email);
        password=(TextView)findViewById(R.id.password);
        confirm_password=(TextView)findViewById(R.id.confirmPassword);
        register = (Button)findViewById(R.id.register);
        goback=(TextView)findViewById(R.id.Goback);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = username.getText().toString();
                String Age1 = age.getText().toString();
                String Email = email.getText().toString();
                String Password = password.getText().toString();
                String CPass = confirm_password.getText().toString();
                if(uname.isEmpty() && Age1.isEmpty() && Email.isEmpty() && Password.isEmpty() && CPass.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Provide All The Details",Toast.LENGTH_LONG).show();
                }
                else
                {
                    int Age = Integer.parseInt(Age1);
                    if(checkPassword(Password,CPass))
                    {
                        db.add(uname,Age,Password,Email);
                        Toast.makeText(getApplicationContext(),"Successfully Registered",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Register.this,Home_Activity.class));
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Password Does'nt match",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, MainActivity.class));
            }
        });
    }
    public boolean checkPassword(String pass , String confirm)
    {
        if(pass.equals(confirm))
        {
            return true;
        }
        return false;
    }
}