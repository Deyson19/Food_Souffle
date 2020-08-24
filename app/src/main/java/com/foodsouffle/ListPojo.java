package com.foodsouffle;

public class ListPojo {
    private String title;
    private int description;
    private String value;
    private int images;

    public ListPojo(String title,int description, String value, int images){
        this.title = title;
        this.description = description;
        this.value = value;
        this.images = images;
    }
    public String getTitle(){
        return title;
    }

    public int getDescription(){
        return description;
    }

    public int getImages(){
        return images;
    }

    public String getValue() {
        return value;
    }
}
