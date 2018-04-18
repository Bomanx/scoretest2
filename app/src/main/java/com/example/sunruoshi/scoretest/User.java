package com.example.sunruoshi.scoretest;

import java.io.Serializable;

public class User implements Serializable{
    public String email;
    public Integer score;

    public User(){};

    public User(String email, Integer score){
        this.email = email;
        this.score = score;
    }

}
