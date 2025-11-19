package com.example.bookkaro;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class buy extends AppCompatActivity {
    public TextView heading;
    public Button fictionBtn, nonfictionBtn, textbooksBtn, childrenbooksBtn;
    public SearchView search;
    public GridView grid;
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buy);
        mydb = new DBHelper(this);  // Initialize database helper
        heading = findViewById(R.id.tv);
        fictionBtn = findViewById(R.id.b1);
        nonfictionBtn = findViewById(R.id.b2);
        textbooksBtn = findViewById(R.id.b3);
        childrenbooksBtn = findViewById(R.id.b4);
        search = findViewById(R.id.search);
        grid = findViewById(R.id.grid);

        // Display all books by default
        showBooks(getBooksByCategory("All"));

        // Button click listeners for filtering by category
        fictionBtn.setOnClickListener(v -> showBooks(getBooksByCategory("Fiction")));
        nonfictionBtn.setOnClickListener(v -> showBooks(getBooksByCategory("Non-fiction")));
        textbooksBtn.setOnClickListener(v -> showBooks(getBooksByCategory("Textbooks")));
        childrenbooksBtn.setOnClickListener(v -> showBooks(getBooksByCategory("Children's Book")));
    }

    // Method to display books on the GridView
    private void showBooks(ArrayList<Book> books) {
        BookAdapter adapter = new BookAdapter(this, books);
        grid.setAdapter(adapter);
    }

    // Method to fetch books by category from the database
    private ArrayList<Book> getBooksByCategory(String category) {
        ArrayList<Book> booksByCategory = new ArrayList<>();
        Cursor res = mydb.getBooksByCategory(category);  // Fetch books by category

        // Check if data is available
        if (res == null ||res.getCount() == 0) {
            return booksByCategory;  // return empty list if no data
        }

        // Loop through cursor to extract data
        while (res.moveToNext()) {
            String title = res.getString(1); // Assuming COL_2 is 'NAME'
            String price = res.getString(3); // Assuming COL_4 is 'PRICE'
            byte[] image = res.getBlob(5);   // Assuming COL_6 is 'IMAGE'

            // Create book object with title, price, and image and add to the list
            booksByCategory.add(new Book(title, price, image));
        }
        res.close();
        return booksByCategory;
    }


}