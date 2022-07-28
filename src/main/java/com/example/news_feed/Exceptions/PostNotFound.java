package com.example.news_feed.Exceptions;

public class PostNotFound extends Exception{
    public PostNotFound(int postId){
        super();
        System.out.println("No post found for Post Id " + postId);
    }
}
