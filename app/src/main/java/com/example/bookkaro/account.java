package com.example.bookkaro;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class account extends AppCompatActivity {
    ImageButton imageButton,imageButton4;
    DBHelperlogin logindb;
    TextView textView2,textView6,number,address;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_account);
        number=(TextView)findViewById(R.id.number);
        address=(TextView)findViewById(R.id.address);

        imageButton4=findViewById(R.id.imageButton4);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i4=new Intent(account.this,contactus.class);
                startActivity(i4);
            }
        });
        textView2=(TextView)findViewById(R.id.textView2);
        textView6=(TextView)findViewById(R.id.textView6);
        logindb=new DBHelperlogin(this);
        imageButton=(ImageButton)findViewById(R.id.imageButton);
        imageButton.setOnClickListener(v -> {
            Intent i=new Intent(account.this,mainhomepage.class);
            startActivity(i);
        });
        viewname();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void viewname() {
        Cursor res = logindb.getAllData();

        // Check if the cursor contains any data
        if (res != null && res.moveToFirst()) {
            // Access data from the first row
            textView2.setText(res.getString(1));
            textView6.setText(res.getString(2));
            number.setText(res.getString(3));
            address.setText(res.getString(4));
        } else {
            // Handle case when there's no data or cursor is empty
            textView2.setText("No data available");
            textView6.setText("No data available");
            number.setText("No data available");
            address.setText("No data available");
        }

        // Close the cursor after use to prevent memory leaks
        if (res != null) {
            res.close();
        }
    }

}