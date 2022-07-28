package com.example.news_feed.Models;

import com.example.news_feed.Utils.PostIdGenerator;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Comment {
    private int commentId;
    private int postId;
    private int userId;
    private String comment;
    private String timeStamp;
    private List<Integer> listOfFollowingComments = new ArrayList<>();
    private int upvotes;
    private int downvotes;

    public Comment(int postId, int userId, String comment) {
        this.commentId = PostIdGenerator.getId();
        this.postId = postId;
        this.userId = userId;
        this.comment = comment;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH:mm:ss");
        this.timeStamp = sdf.format(new Date());
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTimestamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timestamp) {
        this.timeStamp = timestamp;
    }

    public List<Integer> getListOfFollowingComments() {
        return listOfFollowingComments;
    }

    public void setListOfFollowingComments(List<Integer> listOfFollowingComments) {
        this.listOfFollowingComments = listOfFollowingComments;
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
}
