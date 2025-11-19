package com.example.bookkaro;

public class Book {
    private String title;
    private String price;
    private byte[] image;
//    private int imageResId; // Resource ID for the book image

//    public Book(String title, String price, int imageResId) {
//        this.title = title;
//        this.price = price;
//        this.imageResId = imageResId;
//    }

//    public Book(String title, String price) {
//        this.title = title;
//        this.price = price;
//    }

    public Book(String title, String price, byte[] image) {
        this.title = title;
        this.price = price;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public byte[] getImage() {
        return image;
    }

//    public int getImageResId() {
//        return imageResId;
//    }
}
