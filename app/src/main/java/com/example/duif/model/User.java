package com.example.duif.model;

/**
 * Created by raffe on 10-5-2017.
 */

public class User {
    private int id;
    private String id_str;
    private String name;
    private String screenName;
    private String location;
    private String description;
    private String url;
    private Entity entity;
    private boolean isProtected;
    private String icon;

    public int getId() {
        return id;
    }

    public String getIcon() {
        return icon;
    }

    public String getId_str() {
        return id_str;
    }

    public String getName() {
        return name;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public Entity getEntity() {
        return entity;
    }

    public boolean isProtected() {
        return isProtected;
    }
}
