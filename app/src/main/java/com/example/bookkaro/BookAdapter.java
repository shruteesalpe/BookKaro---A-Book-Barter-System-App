package com.example.bookkaro;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BookAdapter extends BaseAdapter {
    private Button buyBTn;
    private Context context;
    private ArrayList<Book> books;

    public BookAdapter(Context context, ArrayList<Book> books) {
        this.context = context;
        this.books = books;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int position) {
        return books.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.gridview, parent, false);
        }

        // Get references to the views
        ImageView bookImage = view.findViewById(R.id.bookimage);
        TextView bookTitle = view.findViewById(R.id.booktitle);
        TextView bookPrice = view.findViewById(R.id.bookprice);
        Button buy = view.findViewById(R.id.buy);

        // Get the current book object
        Book book = books.get(position);

        // Set the book title and price
        bookTitle.setText(book.getTitle());
        bookPrice.setText(book.getPrice());

        // Convert the byte array (image data) to Bitmap and set it in the ImageView
        byte[] imageBytes = book.getImage();
            Bitmap bookBmp = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            bookImage.setImageBitmap(bookBmp);


        // Handle item clicks to show book details
        view.setOnClickListener(v -> {
            Intent intent = new Intent(context, BookDetails.class);
            intent.putExtra("title", book.getTitle());
            intent.putExtra("price", book.getPrice());
            intent.putExtra("image", book.getImage());  // Add this line to pass the image
            intent.putExtra("description", "Description for " + book.getTitle());
            context.startActivity(intent);
        });

        // Handle buy button click (modify as needed)
        buyBTn = buy.findViewById(R.id.buy);
        buyBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent to the transaction page or other logic
            }
        });

        return view;
    }
}

