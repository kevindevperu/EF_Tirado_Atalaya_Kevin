package com.kevin.ef_tiradoatalaya.data.model;

import java.util.List;

public class Titan {
    private String name;
    private String img;
    private List<String> abilities;

    public Titan(String name, String img, List<String> abilities) {
        this.name = name;
        this.img = img;
        this.abilities = abilities;
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
}
