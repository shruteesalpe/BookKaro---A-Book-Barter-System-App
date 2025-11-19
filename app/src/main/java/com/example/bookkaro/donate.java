package com.example.bookkaro;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.io.ByteArrayOutputStream;

public class donate extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String c[]={"Select category","Fiction","Textbooks","Children's Book","Non-fiction"};
    private static final int CAMERA_REQUEST=1888;
    ImageButton imageButton;
    Button button3;
    DBHelper mydb;
    EditText name,quantity;
    String category;
    Bitmap bookImage;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_donate);
        mydb=new DBHelper(this);
        button3=findViewById(R.id.button3);
        name=findViewById(R.id.name);
        quantity=findViewById(R.id.quantity);
        Spinner spinner=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter aa=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,c);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);
        spinner.setOnItemSelectedListener(this);
        imageButton=findViewById(R.id.imageButton6);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,CAMERA_REQUEST);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//        button3.setOnClickListener(view -> {
//            // Log the values being passed to the database for debugging
//            Log.d("SELL_ACTIVITY", "Name: " + name.getText().toString());
//            Log.d("SELL_ACTIVITY", "Category: " + category);
//            Log.d("SELL_ACTIVITY", "Price: " + price.getText().toString());
//            Log.d("SELL_ACTIVITY", "Quantity: " + quantity.getText().toString());
//
//            if (category.equals("Select category")) {
//                Toast.makeText(sell.this, "Please select a valid category", Toast.LENGTH_SHORT).show();
//            } else {
//                boolean isInserted = mydb.sellData(name.getText().toString(), price.getText().toString(), quantity.getText().toString());
//                if (isInserted) {
//                    Toast.makeText(sell.this, "Book listed for selling", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(sell.this, "Book not listed for selling", Toast.LENGTH_SHORT).show();
//                }
//                Intent i1 = new Intent(sell.this, mainhomepage.class);
//                startActivity(i1);
//            }
//        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();
                Intent i1=new Intent(donate.this, mainhomepage.class);
                startActivity(i1);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        category=adapterView.getItemAtPosition(i).toString();
        Log.d("SELL_ACTIVITY", "Selected category: " + category);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    public  void insert(){
        byte[] imageInByte = getBitmapAsByteArray(bookImage);
        boolean isInserted=mydb.sellData(name.getText().toString(),category,"free",quantity.getText().toString(),imageInByte );
        if (isInserted)
            Toast.makeText(donate.this, "Book listed for selling", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(donate.this, "Book not listed for selling", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            bookImage = (Bitmap) data.getExtras().get("data");
            imageButton.setImageBitmap(bookImage);  // Display captured image
        }
    }

}