package com.example.news_feed.DAO;

import com.example.news_feed.Exceptions.NoLoggedInUser;
import com.example.news_feed.Exceptions.NoUserFoundException;
import com.example.news_feed.Exceptions.PostNotFound;
import com.example.news_feed.Exceptions.UserAlreadyExists;
import com.example.news_feed.Models.*;
import javafx.geometry.Pos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserDao {
    private static UserDao instance = null;
    public UserDao(){}
    public static UserDao getInstance(){
        if (instance == null){
            synchronized (UserDao.class){
                if (instance == null){
                    instance = new UserDao();
                }
            }
        }
        return instance;
    }

    private User user = null;

    private HashMap<Long, Integer> phoneToUserMap = new HashMap<>();
    private HashMap<Integer, User> listOfUsers = new HashMap<>();
    private HashMap<Integer, Post> listOfPosts = new HashMap<>();
    private HashMap<Integer, Comment> listOfComments = new HashMap<>();

    public User registerUser(String name, Long phone) throws UserAlreadyExists{
        //Throw error if the user already exists
        if(phoneToUserMap.containsKey(phone)){
            throw new UserAlreadyExists(phone);
        }

        //If user doesn't exist create a new user and input it in the data
        User user = new User(name, phone);
        phoneToUserMap.put(phone, user.getUserId());
        listOfUsers.put(user.getUserId(), user);
        return user;
    }

    public User logInUser(Long phone) throws NoUserFoundException {
        //Throw error if the user doesn't exist
        if(!phoneToUserMap.containsKey(phone)){
            throw new NoUserFoundException(phone);
        }

        User user = listOfUsers.get(phoneToUserMap.get(phone));
        this.user = user;
        return user;
    }

    public Boolean followUser(Long phone) throws NoUserFoundException, NoLoggedInUser {
        //Throw if no user is currently logged in
        if(this.user == null){
            throw new NoLoggedInUser();
        }

        //Throw error if the user doesn't exist
        if(!phoneToUserMap.containsKey(phone)){
            throw new NoUserFoundException(phone);
        }

        //Add the user which we have to follow to the current logged in user
        User followUser = listOfUsers.get(phoneToUserMap.get(phone));
        user.getFollowing().add(followUser.getUserId());

        System.out.println(user.getName() + " followed " + followUser.getName());
        return true;
    }

    public Post postFeed(String postContent) throws NoLoggedInUser{
        //Throw if no user is currently logged in
        if(this.user == null){
            throw new NoLoggedInUser();
        }

        //Create a post object and put it in the users data who created the post
        // and in the overall data of posts
        Post post = new Post(this.user.getUserId(), postContent);
        this.user.getListOfPosts().add(post.getPostId());
        listOfPosts.put(post.getPostId(), post);

        System.out.println("Feed posted by " + user.getName());

        return post;
    }

    public Boolean actionOnFeed(Integer postId, VoteActionType action) throws NoLoggedInUser, PostNotFound{
        //Throw if no user is currently logged in
        if(this.user == null){
            throw new NoLoggedInUser();
        }

        //Throw if no post with provided post id is found
        if(!listOfPosts.containsKey(postId)){
            throw new PostNotFound(postId);
        }

        //Get post and do the appropriate action on it
        Post post = listOfPosts.get(postId);
        if(action == VoteActionType.upvoted){
            post.setUpvotes(post.getUpvotes()+1);
        } else {
            post.setDownvotes(post.getDownvotes()+1);
        }

        System.out.println("Post " + post.getPostId() + " has been " + action + " by user " + this.user.getName());
        return true;
    }

    public Comment comment(Integer feedId, String commentString) throws NoLoggedInUser, PostNotFound{
        //Throw if no user is currently logged in
        if(this.user == null){
            throw new NoLoggedInUser();
        }

        //Throw if no post with provided post id is found
        if(!listOfPosts.containsKey(feedId)){
            throw new PostNotFound(feedId);
        }

        Comment comment = new Comment(feedId, this.user.getUserId(), commentString);
        Post post = listOfPosts.get(feedId);
        post.getListOfComments().add(comment.getCommentId());
        listOfComments.put(comment.getCommentId(), comment);
        System.out.println("Comment posted by " + user.getName());
        return comment;
    }

    public void fetchNewsFeed(SortBy sortBy) throws NoLoggedInUser{
        //Throw if no user is currently logged in
        if(this.user == null){
            throw new NoLoggedInUser();
        }

        List<Post> postOfFollowing = new ArrayList<>();
        List<Post> postOfNonFollowing = new ArrayList<>();

        for(Integer id : this.user.getFollowing()){
            User user1 = listOfUsers.get(id);
            for (Integer postId : user.getListOfPosts()){
                postOfFollowing.add(listOfPosts.get(postId));
            }
        }

        for(Post p : listOfPosts.values()){
            if(!postOfFollowing.contains(p)){
                postOfNonFollowing.add(p);
            }
        }

        if(postOfFollowing.isEmpty() && postOfNonFollowing.isEmpty()){
            System.out.println("No Posts exists yet");
            return;
        }

        if (sortBy.equals(SortBy.score)){
            Collections.sort(postOfFollowing, new sortByScore());
            Collections.sort(postOfNonFollowing, new sortByScore());
        } else if(sortBy.equals(SortBy.comments)){
            Collections.sort(postOfFollowing, new sortByComments());
            Collections.sort(postOfNonFollowing, new sortByComments());
        } else {
            Collections.sort(postOfFollowing, new sortByTime());
            Collections.sort(postOfNonFollowing, new sortByTime());
        }

        System.out.println();
        System.out.println("News Feed for User " + user.getName());
        for(Post p : postOfFollowing){
            System.out.println("Post Id : " + p.getPostId());
            System.out.println("(" + p.getUpvotes() + " upvotes, " + p.getDownvotes() + " downvotes)");
            System.out.println(listOfUsers.get(p.getUserId()).getName());
            System.out.println(p.getPostContent());
            System.out.println(p.getTimeStamp());
            System.out.println();

            for (Integer commentId : p.getListOfComments()){
                Comment comment = listOfComments.get(commentId);
                System.out.println("\tComment Id : " + comment.getCommentId());
                System.out.println("\t(" + comment.getUpvotes() + " upvotes, " + comment.getDownvotes() + " downvotes)");
                System.out.println("\t"+listOfUsers.get(comment.getUserId()).getName());
                System.out.println("\t"+comment.getComment());
                System.out.println("\t"+comment.getTimestamp());
                System.out.println();
            }
        }

        for(Post p : postOfNonFollowing){
            System.out.println("Post Id : " + p.getPostId());
            System.out.println("(" + p.getUpvotes() + " upvotes, " + p.getDownvotes() + " downvotes)");
            System.out.println(listOfUsers.get(p.getUserId()).getName());
            System.out.println(p.getPostContent());
            System.out.println(p.getTimeStamp());
            System.out.println();

            for (Integer commentId : p.getListOfComments()){
                Comment comment = listOfComments.get(commentId);
                System.out.println("\tComment Id : " + comment.getCommentId());
                System.out.println("\t(" + comment.getUpvotes() + " upvotes, " + comment.getDownvotes() + " downvotes)");
                System.out.println("\t"+listOfUsers.get(comment.getUserId()).getName());
                System.out.println("\t"+comment.getComment());
                System.out.println("\t"+comment.getTimestamp());
                System.out.println();
            }
        }

    }

    class sortByScore implements Comparator<Post>{
        @Override
        public int compare(Post o1, Post o2) {
            int score1 = o1.getUpvotes() - o1.getDownvotes();
            int score2 = o2.getUpvotes() - o2.getDownvotes();
            return score2 - score1;
        }
    }

    class sortByComments implements Comparator<Post>{
        @Override
        public int compare(Post o1, Post o2) {
            return o2.getListOfComments().size() - o1.getListOfComments().size();
        }
    }

    class sortByTime implements Comparator<Post>{
        @Override
        public int compare(Post o1, Post o2) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH:mm:ss");
            try {
                Date date1 = sdf.parse(o1.getTimeStamp());
                Date date2 = sdf.parse(o2.getTimeStamp());
                return (int) (date2.getTime() - date1.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return 0;
        }
    }
}
