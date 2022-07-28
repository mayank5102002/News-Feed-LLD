package com.example.news_feed.Exceptions;

public class NoLoggedInUser extends Exception{
    public NoLoggedInUser(){
        super();
        System.out.println("No user is currently logged in");
    }
}
