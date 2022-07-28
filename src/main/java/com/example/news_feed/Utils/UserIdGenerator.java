package com.example.news_feed.Utils;

public class UserIdGenerator {
    private static int id = 0;
    public static int getId(){
        id++;
        return id;
    }
}
