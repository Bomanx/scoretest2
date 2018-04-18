package com.example.sunruoshi.scoretest;

import java.io.Serializable;

/**
 * Created by ruochen on 4/18/18.
 */

public class Players implements Serializable {
    public String email;
    public int score;

    public Players(){}

    public Players(String email, int score){
        this.email= email;
        this.score= score;
    }
}
