package com.user.model;

public class Service {
    private String name;
    private String emoji;

    public Service(String name, String emoji) {
        this.name = name;
        this.emoji = emoji;
    }

    public String getName() {
        return name;
    }

    public String getEmoji() {
        return emoji;
    }
}
