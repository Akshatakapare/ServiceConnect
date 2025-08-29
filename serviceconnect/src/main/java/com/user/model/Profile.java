package com.user.model;

import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Profile {
    private String name;
    private String email;
    private String age;
    private String gender;
    private String location;
    private String phone;
    private String service;
    private String skills;

    public void setEmail(String email) {
        this.email = email;
    }

    private Image profileImage;
    private String preferredLanguage;
    private String imagePath;
    private String expectations;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setExpectations(String expectations) {

        this.expectations = expectations;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public void setProfileImage(Image profileImage) {
        this.profileImage = profileImage;
    }

    public Profile(String name, String email, String age, String gender, String location,
            String phone, String service, String skills,
            String language, Image profileImage) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.location = location;
        this.phone = phone;
        this.service = service;
        this.skills = skills;
        this.profileImage = profileImage;
    }

    public Profile() {
        // TODO Auto-generated constructor stub
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getLocation() {
        return location;
    }

    public String getPhone() {
        return phone;
    }

    public String getService() {
        return service;
    }

    public String getSkills() {
        return skills;
    }

    public Image getProfileImage() {
        return profileImage;
    }
    // ✅ class variable

    public String getExpectations() {
        return expectations; // ✅ Corrected: don't call method again
    }

    public static Scene getScene() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getScene'");
    }

}
