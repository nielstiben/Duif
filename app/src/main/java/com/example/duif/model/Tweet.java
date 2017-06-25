package com.example.duif.model;

public class Tweet {
    public String createdAt;
    public String id;
    public String text;
    public boolean truncated;
    public Entity[] entities;
    public Entity[] extendedEntities;
    public String source;
    public Tweet inReplyTo;
    public User user;
    public long retweetCount;
    public long favoritedCount;
    public boolean retweeted;
    public boolean favorited;
    public boolean possiblySensitive;
    public String lang;


    // Bare minimum constructor
    public Tweet(String createdAt, String text) {
        this.createdAt = createdAt;
        this.text = text;
    }

    // Full constructor
    public Tweet(String createdAt, String id, String text, boolean truncated, Entity[] entities, Entity[] extendedEntities, String source, Tweet inReplyTo, User user, long retweetCount, long favoritedCount, boolean retweeted, boolean favorited, boolean possiblySensitive, String lang) {
        this.createdAt = createdAt;
        this.id = id;
        this.text = text;
        this.truncated = truncated;
        this.entities = entities;
        this.extendedEntities = extendedEntities;
        this.source = source;
        this.inReplyTo = inReplyTo;
        this.user = user;
        this.retweetCount = retweetCount;
        this.favoritedCount = favoritedCount;
        this.retweeted = retweeted;
        this.favorited = favorited;
        this.possiblySensitive = possiblySensitive;
        this.lang = lang;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public boolean isTruncated() {
        return truncated;
    }

    public Entity[] getEntities() {
        return entities;
    }

    public Entity[] getExtendedEntities() {
        return extendedEntities;
    }

    public String getSource() {
        return source;
    }

    public Tweet getInReplyTo() {
        return inReplyTo;
    }

    public User getUser() {
        return user;
    }

    public long getRetweetCount() {
        return retweetCount;
    }

    public long getFavoritedCount() {
        return favoritedCount;
    }

    public boolean isRetweeted() {
        return retweeted;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public boolean isPossiblySensitive() {
        return possiblySensitive;
    }

    public String getLang() {
        return lang;
    }
}