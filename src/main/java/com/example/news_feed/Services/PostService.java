package com.example.news_feed.Services;

import com.example.news_feed.DAO.UserDao;
import com.example.news_feed.Models.VoteActionType;

public class PostService {
    private static PostService instance = null;
    public PostService(){}
    public static PostService getInstance(){
        if (instance == null){
            synchronized (PostService.class){
                if (instance == null){
                    instance = new PostService();
                }
            }
        }
        return instance;
    }

    UserDao userDao = UserDao.getInstance();

    public Boolean postFeed(String postContent){
        if (postContent.isEmpty()){
            System.out.println("Invalid credentials");
            return false;
        }

        try{
            userDao.postFeed(postContent);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    public Boolean actionOnFeed(Integer postId, VoteActionType actionType){
        try{
            userDao.actionOnFeed(postId, actionType);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    public Boolean comment(Integer feedId, String commentString){
        if(commentString.isEmpty()){
            System.out.println("Invalid credentials");
            return false;
        }
        try{
            userDao.comment(feedId, commentString);
        } catch (Exception e){
            return false;
        }
        return true;
    }
}
