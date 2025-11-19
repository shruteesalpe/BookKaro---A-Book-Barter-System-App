package com.example.bookkaro;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class mainhomepage extends AppCompatActivity {
ImageButton imageButton8,imageButton5;
    ImageButton imageButton2,imageButton9,imageButton4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mainhomepage);
        Intent i1=getIntent();
        imageButton8=findViewById(R.id.imageButton8);
        imageButton5=findViewById(R.id.imageButton5);
        imageButton4=findViewById(R.id.imageButton4);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i4=new Intent(mainhomepage.this,contactus.class);
                startActivity(i4);
            }
        });
        imageButton2=(ImageButton) findViewById(R.id.imageButton2);
        imageButton9=findViewById(R.id.imageButton9);
        imageButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(mainhomepage.this,donate.class);
                startActivity(i);
            }
        });
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2=new Intent(mainhomepage.this,account.class);
                startActivity(i2);
            }
        });
        imageButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3=new Intent(mainhomepage.this, sell.class);
                startActivity(i3);
            }
        });
        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i4=new Intent(mainhomepage.this,buy.class);
                startActivity(i4);
            }
        });

    }

}