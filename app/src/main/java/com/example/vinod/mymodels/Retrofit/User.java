package com.example.vinod.mymodels.Retrofit;

/**
 * Created by Vinod on 8/23/2018.
 */

public class User {

    private Integer id;
    private String name;
    private String email;
    private int age;
    private String[] topics;

    // we post only user body without id
    public User(String name, String email, int age, String[] topics) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.topics = topics;
    }

    // In response we get whole user with id
    public Integer getId() {
        return id;
    }
}
