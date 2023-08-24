package com.kevin.ef_tiradoatalaya.data.model;

import java.util.List;

public class Titan {

    private  int id;
    private String name;
    private String img;
    private List<String> abilities;

    private String height;

    private String allegiance;

    public Titan(int id, String name, String img, List<String> abilities, String height, String allegiance) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.abilities = abilities;
        this.height = height;
        this.allegiance = allegiance;
    }

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

    public List<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<String> abilities) {
        this.abilities = abilities;
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
