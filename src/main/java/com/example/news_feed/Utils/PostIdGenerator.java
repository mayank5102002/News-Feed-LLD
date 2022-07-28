package com.example.news_feed.Utils;

public class PostIdGenerator {
    private static int id = 0;
    public static int getId(){
        id++;
        return id;
    }
}
