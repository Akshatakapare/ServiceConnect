package com.user.model;

import javafx.scene.image.Image;

public class Provider {
    private String name;
    private String experience;
    private String skills;
    private String contact;
    private String location;
    private String availability;
    private int rating;
    private Image image; // Optional
    private String imagePath;
     private static String email;


    public static String getEmail() {
        return email;
    }

     public void setEmail(String email) {
         this.email = email;
     }

    public Provider(String name, String experience, String skills, String contact,
                    String location, String availability, int rating, Image image) {
        this.name = name;
        this.experience = experience;
        this.skills = skills;
        this.contact = contact;
        this.location = location;
        this.availability = availability;
        this.rating = rating;
        this.image = image;
        this.email=email;
    }

    public Provider(String name, String experience, String skills, String contact,
                String location, String availability, int rating, String imagePath) {
    this.name = name;
    this.experience = experience;
    this.skills = skills;
    this.contact = contact;
    this.location = location;
    this.availability = availability;
    this.rating = rating;
    this.imagePath = imagePath;
}


    // Add a constructor without image if needed
    

   
  
    public String getName() { return name; }
    public String getExperience() { return experience; }
    public String getSkills() { return skills; }
    public String getContact() { return contact; }
    public String getLocation() { return location; }
    public String getAvailability() { return availability; }
    public int getRating() { return rating; }
    public Image getImage() { return image; }
    public String getImage2() {
    return imagePath;
}

    public void setImage(Image image2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setImage'");
    }


}