package com.example.news_feed.Services;

import com.example.news_feed.DAO.UserDao;
import com.example.news_feed.Exceptions.NoUserFoundException;
import com.example.news_feed.Exceptions.UserAlreadyExists;
import com.example.news_feed.Models.User;

public class UserService {
    private static UserService instance = null;
    public UserService(){}
    public static UserService getInstance(){
        if (instance == null){
            synchronized (UserService.class){
                if (instance == null){
                    instance = new UserService();
                }
            }
        }
        return instance;
    }

    UserDao userDao = UserDao.getInstance();

    public Boolean registerUser(String name, Long phone){
        if(name.isEmpty() || phone<=0){
            System.out.println("Invalid credentials");
            return false;
        }

        try{
           userDao.registerUser(name, phone);
        }catch (UserAlreadyExists e){
            return false;
        }
        System.out.println("User " + name + " registered");
        return true;
    }

    public Boolean loginUser(Long phone){
        if(phone <= 0){
            System.out.println("Invalid credentials");
            return false;
        }
        User user;
        try{
            user = userDao.logInUser(phone);
        }catch (NoUserFoundException e){
            return false;
        }
        System.out.println("User " + user.getName() + " logged in");
        return true;
    }

}
