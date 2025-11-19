package com.example.bookkaro;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText username;
    EditText password;
    DBHelperlogin logindb;
    String checkemail;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        logindb=new DBHelperlogin(this);
        button=(Button)findViewById(R.id.login_button) ;
        username=(EditText)findViewById(R.id.email) ;
        password=(EditText)findViewById(R.id.password) ;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Cursor res = logindb.getAllData();
//                res.moveToLast();
//                checkemail=res.getString(2);
//                if(username.getText().toString().equals(checkemail)){
//                    Intent i1=new Intent(MainActivity.this,mainhomepage.class);
//                    startActivity(i);
//                }
//                else{
//                    Intent i=new Intent(MainActivity.this, userdetails.class);
//                    i.putExtra("email",username.getText().toString());
//                    startActivity(i);
//                }
                Intent i=new Intent(MainActivity.this, userdetails.class);
                i.putExtra("email",username.getText().toString());
                startActivity(i);

            }
        });

    }

}