package com.example.news_feed.Services;

import com.example.news_feed.DAO.UserDao;

public class FollowService {
    private static FollowService instance = null;
    public FollowService(){}
    public static FollowService getInstance(){
        if (instance == null){
            synchronized (FollowService.class){
                if (instance == null){
                    instance = new FollowService();
                }
            }
        }
        return instance;
    }

    UserDao userDao = UserDao.getInstance();

    public Boolean followUser(Long phone){
        if(phone <= 0){
            System.out.println("Invalid credentials");
            return false;
        }

        try{
            userDao.followUser(phone);
        } catch (Exception e){
            return false;
        }
        return true;
    }
}
