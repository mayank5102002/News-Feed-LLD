package com.example.news_feed.Services;

import com.example.news_feed.DAO.UserDao;
import com.example.news_feed.Models.SortBy;

public class NewsFeedService {
    private static NewsFeedService instance = null;
    public NewsFeedService(){}
    public static NewsFeedService getInstance(){
        if (instance == null){
            synchronized (NewsFeedService.class){
                if (instance == null){
                    instance = new NewsFeedService();
                }
            }
        }
        return instance;
    }

    UserDao userDao = UserDao.getInstance();

    public Boolean fetchNewsFeed(SortBy sortBy){
        try {
            userDao.fetchNewsFeed(sortBy);
        } catch (Exception e){
            return false;
        }
        return true;
    }
}
