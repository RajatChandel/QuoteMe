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

    @Ignore
    public Quote(String content, String category, Date date) {
        this.content = content;
        this.category = category;
        this.date = date;
    }

    public Quote(int id, String content, String category, Date date) {
        this.id = id;
        this.content = content;
        this.category = category;
        this.date = date;
    }
}
