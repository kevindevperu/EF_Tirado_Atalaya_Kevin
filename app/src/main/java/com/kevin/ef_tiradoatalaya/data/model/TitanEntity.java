package com.kevin.ef_tiradoatalaya.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "titan")
public class TitanEntity {

    @PrimaryKey(autoGenerate = true)
    private  int id;

    @ColumnInfo(name="titan_name")
    private String name;

    @ColumnInfo(name="image")
    private String img;

    @ColumnInfo(name="titan_height")
    private String height;

    @ColumnInfo(name="titan_allegiance")
    private String allegiance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getAllegiance() {
        return allegiance;
    }

    public void setAllegiance(String allegiance) {
        this.allegiance = allegiance;
    }
}
