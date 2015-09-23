package com.trydroid.activitytransition.model;

public class Item {
    private String url;
    private String text;

    public Item(String url, String text) {
        this.url = url;
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public String getText() {
        return text;
    }
}
