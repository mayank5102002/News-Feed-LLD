package com.example.news_feed.Exceptions;

public class UserAlreadyExists extends Exception{
    public UserAlreadyExists(Long phone){
        super();
        System.out.println("User already exists for phone number " + phone);
    }
}
