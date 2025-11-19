package com.example.bookkaro;

import android.content.Intent;
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

public class userdetails extends AppCompatActivity {
Button button3;
EditText name,phoneno,address;
DBHelperlogin dblogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_userdetails);
        dblogin=new DBHelperlogin(this);
        button3=findViewById(R.id.button3);
        name=findViewById(R.id.name);
        phoneno=findViewById(R.id.phoneno);
        address=findViewById(R.id.address);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(userdetails.this,selectcat.class);
                startActivity(i);
                insert();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void insert(){
        boolean isinserted=dblogin.loginData(name.getText().toString(),getIntent().getStringExtra("email"),phoneno.getText().toString(),address.getText().toString());
        if (isinserted)
            Toast.makeText(this, "details saved", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "details not saved", Toast.LENGTH_SHORT).show();

    }
}