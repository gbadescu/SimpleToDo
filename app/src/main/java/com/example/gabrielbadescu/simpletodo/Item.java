package com.example.gabrielbadescu.simpletodo;

import android.support.annotation.IntDef;


import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;


public class Item extends RealmObject {
    public long remoteId;
    public String name;
    public Date itemDate;
    public String itemNotes;

    public long getRemoteId() {
        return remoteId;
    }

    public void setRemoteId(long remoteId) {
        this.remoteId = remoteId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getItemDate() {
        return itemDate;
    }

    public void setItemDate(Date itemDate) {
        this.itemDate = itemDate;
    }

    public String getItemNotes() {
        return itemNotes;
    }

    public void setItemNotes(String itemNotes) {
        this.itemNotes = itemNotes;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @IntDef({LOW_PRIORITY, MEDIUM_PRIORITY, HIGH_PRIORITY})
    public @interface Priority {}



    public static final int LOW_PRIORITY = 0;
    public static final int MEDIUM_PRIORITY = 1;
    public static final int HIGH_PRIORITY = 2;


    @Priority
    public int priority;

    // Make sure to have a default constructor for every ActiveAndroid model
    public Item(){
        super();
    }

    public Item(int remoteId, String name, String priority, Date date){
        super();
        this.remoteId = remoteId;
        this.name = name;
        this.priority = LOW_PRIORITY;
        this.itemDate = date;
        this.itemNotes = "";
    }

}

