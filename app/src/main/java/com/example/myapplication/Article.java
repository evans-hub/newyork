package com.example.myapplication;

public class Article {
    private String title;
    private String abstractText;
    public Article(String title, String abstractText) {
        this.title = title;
        this.abstractText = abstractText;
    }

    public String getTitle() {
        return title;
    }

    public String getAbstract() {
        return abstractText;
    }
}
