package com.example.news_feed.Models;

import com.example.news_feed.Utils.UserIdGenerator;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int userId;
    private String name;
    private Long phone;
    private List<Integer> following = new ArrayList<>();
    private List<Integer> listOfPosts = new ArrayList<>();

    public User(String name, Long phone) {
        this.userId = UserIdGenerator.getId();
        this.name = name;
        this.phone = phone;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public List<Integer> getFollowing() {
        return following;
    }

    public void setFollowing(List<Integer> following) {
        this.following = following;
    }

    public List<Integer> getListOfPosts() {
        return listOfPosts;
    }

    public void setListOfPosts(List<Integer> listOfPosts) {
        this.listOfPosts = listOfPosts;
    }
}
