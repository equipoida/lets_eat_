package com.example.lets_eat;

import android.widget.Button;
import android.widget.RatingBar;

public class Userhelper {
    String menuname;
    String review;
    String star;

    public Userhelper() {
    }

    public Userhelper(String menuname, String review, String star) {
        this.menuname = menuname;
        this.review = review;
        this.star = star;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }


}
