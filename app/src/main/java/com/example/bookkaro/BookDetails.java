package com.example.bookkaro;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BookDetails extends AppCompatActivity {
    private TextView bookTitle, bookPrice, bookDescription;
    ImageView bookImage;
    Button button;
    private DBHelperlogin logindb;
    DBHelper mydb;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        mydb=new DBHelper(this);
        logindb = new DBHelperlogin(this);
        bookTitle = findViewById(R.id.bookTitle);
        bookPrice = findViewById(R.id.bookPrice);
        bookDescription = findViewById(R.id.bookDescription);
        bookImage = findViewById(R.id.bookImage);
        button=findViewById(R.id.buy);

        // Get book data from the intent
        String title = getIntent().getStringExtra("title");
        String price = getIntent().getStringExtra("price");
        byte[] imageBytes = getIntent().getByteArrayExtra("image");  // Get the image byte array

        // Set book data to views
        bookTitle.setText(title);
        bookPrice.setText(price);

        // Set the image if available

            Bitmap bookBmp = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            bookImage.setImageBitmap(bookBmp);

        displayUserData();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BookDetails.this, "order placed", Toast.LENGTH_SHORT).show();
                Intent i1=new Intent(BookDetails.this,buy.class);
                startActivity(i1);
                mydb.insertbuyer(title,price);
                mydb.deleteData(title);

            }
        });
    }
    private void displayUserData() {
        Cursor res = logindb.getAllData();  // Fetch data from DBHelperlogin

        // Check if user data is available
        if (res != null && res.moveToFirst()) {
            // Fetch name and email from the first row
            String name = res.getString(1);  // Assuming COL_2 is 'NAME'
            String email = res.getString(2);  // Assuming COL_3 is 'EMAIL'

            // Set user information in place of description
            bookDescription.setText("Seller: " + name + "\nEmail: " + email);
        } else {
            bookDescription.setText("No user data available");
        }

        // Close the cursor to prevent memory leaks
        if (res != null) {
            res.close();
        }
    }

}