package com.example.news_feed.Exceptions;

public class NoUserFoundException extends Exception{
    public NoUserFoundException(Long phone){
        super();
        System.out.println("No User Found logged in for phone number " + phone);
    }
}
