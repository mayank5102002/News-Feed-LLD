package com.example.news_feed.Exceptions;

public class CommentNotFound extends Exception{
    public CommentNotFound(int commentId) {
        super();
        System.out.println("No comment found for Comment Id " + commentId);
    }
}
