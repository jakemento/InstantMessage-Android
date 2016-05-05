package com.epicodus.instantmessage.models;

/**
 * Created by Guest on 5/5/16.
 */
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
