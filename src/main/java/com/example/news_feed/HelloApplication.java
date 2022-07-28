package com.example.news_feed;

import com.example.news_feed.Models.SortBy;
import com.example.news_feed.Models.VoteActionType;
import com.example.news_feed.Services.FollowService;
import com.example.news_feed.Services.NewsFeedService;
import com.example.news_feed.Services.PostService;
import com.example.news_feed.Services.UserService;

public class HelloApplication{
    public static void main(String[] args) {
        UserService userService = UserService.getInstance();
        FollowService followService = FollowService.getInstance();
        PostService postService = PostService.getInstance();
        NewsFeedService newsFeedService = NewsFeedService.getInstance();
        userService.registerUser("Mayank", 5L);
        userService.registerUser("Warlord", 6L);
        userService.loginUser(5L);
        followService.followUser(6L);

        postService.postFeed("My first post");
        userService.loginUser(6L);
        postService.postFeed("Warlord post");
        postService.actionOnFeed(1, VoteActionType.upvoted);

        userService.loginUser(5L);
        postService.actionOnFeed(2, VoteActionType.upvoted);
        postService.actionOnFeed(2, VoteActionType.upvoted);
        postService.comment(2, "Follow warlord");

        userService.registerUser("Warling", 7L);
        userService.loginUser(7L);
        postService.actionOnFeed(2, VoteActionType.downvoted);
        postService.actionOnFeed(2, VoteActionType.downvoted);
        newsFeedService.fetchNewsFeed(SortBy.score);
    }
}