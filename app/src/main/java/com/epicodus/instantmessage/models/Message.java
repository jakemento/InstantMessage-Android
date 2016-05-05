package com.epicodus.instantmessage.models;

import org.parceler.Parcel;

@Parcel
public class Message {
    String message;
    String author;

    public Message() {}

    public Message (String message, String author) {
        this.message = message;
        this.author = author;
    }

    public String getMessage() {
        return message;
    }
    public String getAuthor() {
        return author;
    }
}
