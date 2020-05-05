package com.example.tmdb.model;


import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel(Parcel.Serialization.BEAN)
public class Reviews {

    @SerializedName("id")
    private String id;

    @SerializedName("author")
    private String author;

    @SerializedName("content")
    private String content;

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
