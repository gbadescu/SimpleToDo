package com.example.gabrielbadescu.simpletodo;

import android.support.annotation.IntDef;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Items")
public class Item extends Model {
    // This is the unique id given by the server
    @Column(name = "remote_id", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public long remoteId;
    // This is a regular field
    @Column(name = "Name")
    public String name;

    @IntDef({LOW_PRIORITY, MEDIUM_PRIORITY, HIGH_PRIORITY})
    public static final int LOW_PRIORITY;
    public static final int MEDIUM_PRIORITY;
    public static final int HIGH_PRIORITY;
    // This is an association to another activeandroid model
    @Column(name = "Category", onUpdate = ForeignKeyAction.CASCADE, onDelete = ForeignKeyAction.CASCADE)


    // Make sure to have a default constructor for every ActiveAndroid model
    public Item(){
        super();
    }

    public Item(int remoteId, String name, Category category){
        super();
        this.remoteId = remoteId;
        this.name = name;
        this.category = category;
    }
}

