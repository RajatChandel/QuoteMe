package com.RajatChandel.quoteme.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(tableName = "quotes")
public class Quote {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String content;
    private String category;
    @ColumnInfo(name = "date")
    private Date date;
    private String author;
    private boolean isFavorite;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Ignore
    public Quote(String content, String category, Date date, String author, boolean isFavorite) {
        this.content = content;
        this.category = category;
        this.date = date;
        this.author = author;
        this.isFavorite = false;
    }

    public Quote(int id, String content, String category, Date date) {
        this.id = id;
        this.content = content;
        this.category = category;
        this.date = date;
        this.author = author;
        this.isFavorite = false;
    }
}
