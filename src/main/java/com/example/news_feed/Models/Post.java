package com.example.news_feed.Models;

import com.example.news_feed.Utils.PostIdGenerator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post {
    private int postId;
    private int userId;
    private String postContent;
    private int upvotes;
    private int downvotes;
    private List<Integer> listOfComments = new ArrayList<>();
    private String timeStamp;

    public Post(int userId, String postContent) {
        this.postId = PostIdGenerator.getId();
        this.userId = userId;
        this.postContent = postContent;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH:mm:ss");
        this.timeStamp = sdf.format(new Date());
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }

    public List<Integer> getListOfComments() {
        return listOfComments;
    }

    public void setListOfComments(List<Integer> listOfComments) {
        this.listOfComments = listOfComments;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
