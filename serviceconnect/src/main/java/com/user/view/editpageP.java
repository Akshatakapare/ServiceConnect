package com.user.view;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.user.model.profiledata;
import com.user.view.profilepageP;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class editpageP extends Application {
    

    static final String PROJECT_ID="serviceconnect-eb8b6";
    static final String API_KEY="AIzaSyAlJon0EBJRYetQUsc2ySdnt3pm41U4KMU";
    private static class UserProfile {
        public String name, email, age, location, bio;
        public String category, description, charges, availability;
        public String experience, rating;
        public boolean[] skills = new boolean[10];
        public String otherSkill;

        public UserProfile(String name, String email, String age, String location, String bio,
                           String category, String description, String charges, String availability,
                           String experience, String rating, boolean[] skills, String otherSkill) {
            this.name = name;
            this.email = email;
            this.age = age;
            this.location = location;
            this.bio = bio;
            this.category = category;
            this.description = description;
            this.charges = charges;
            this.availability = availability;
            this.experience = experience;
            this.rating = rating;
            this.skills = skills;
            this.otherSkill = otherSkill;
        }
    }

    @Override
    public void start(Stage myStage) {

        System.out.println("📥 editpageP received docId = " + profiledata.docId);


    UserProfile existingUser = new UserProfile(
    profiledata.name, profiledata.email, profiledata.age, profiledata.location,profiledata.bio,
    profiledata.category, profiledata.description, profiledata.charges, profiledata.availability,
    profiledata.experience,profiledata.rating,
    new boolean[]{false,false,false,false,false,false,false,false,false,false},
    ""
);


        //final boolean[] editMode = {false};

        Text title = new Text("\uD83D\uDC75 Profile Page for Elders");
        title.setFont(Font.font("Georgia", 32));
        title.setStyle("-fx-font-weight: bold; -fx-fill: #333;");

        ImageView imageView = new ImageView();
        imageView.setFitHeight(200);
        imageView.setFitWidth(150);
if (profiledata.imagePath != null && !profiledata.imagePath.isEmpty()) {
    imageView.setImage(new Image(profiledata.imagePath));
} else {
    imageView.setImage(new Image("Assets\\Images\\oldpr.jpg"));

}



        TextField nameField = new TextField(existingUser.name);
        TextField emailField = new TextField(existingUser.email);
        TextField ageField = new TextField(existingUser.age);
        TextField locationField = new TextField(existingUser.location);
        TextField bioField = new TextField(existingUser.bio);
        TextField categoryField = new TextField(existingUser.category);
        TextField descriptionField = new TextField(existingUser.description);
        TextField chargesField = new TextField(existingUser.charges);
        TextField availabilityField = new TextField(existingUser.availability);
        TextField experienceField = new TextField(existingUser.experience);
        TextField ratingField = new TextField(existingUser.rating);

        TextField[] allFields = {
            nameField, emailField, ageField, locationField, bioField,
            categoryField, descriptionField, chargesField,
            availabilityField, experienceField, ratingField
        };

        for (TextField tf : allFields) {
            tf.setMaxWidth(300);
            tf.setPrefHeight(35);
            tf.setStyle("-fx-background-radius: 12; -fx-border-radius: 12; -fx-border-color: #bbb; -fx-padding: 6; -fx-font-size: 13px;");
            
        }

        VBox profileBox = new VBox(10,
            fixedLabel("Full Name:"), nameField,
            fixedLabel("Email:"), emailField,
            fixedLabel("Age:"), ageField,
            fixedLabel("Location:"), locationField,
            fixedLabel("Short Bio:"), bioField
        );
        profileBox.setAlignment(Pos.CENTER);

        Text serviceTitle = fixedTitle("\uD83D\uDEE0 Service Offerings");

        VBox serviceBox = new VBox(10,
            serviceTitle,
            fixedLabel("Service Category:"), categoryField,
            fixedLabel("Description:"), descriptionField,
            fixedLabel("Charges:"), chargesField,
            fixedLabel("Availability:"), availabilityField
        );
        serviceBox.setAlignment(Pos.CENTER);

        Text skillTitle = fixedTitle("\uD83C\uDF93 Skills");

        VBox skillBox = new VBox(10, skillTitle);
        skillBox.setAlignment(Pos.CENTER);

        CheckBox cb1 = new CheckBox("Cooking");
        CheckBox cb2 = new CheckBox("Knitting / Embroidery");
        CheckBox cb3 = new CheckBox("Tutoring");
        CheckBox cb4 = new CheckBox("Storytelling");
        CheckBox cb5 = new CheckBox("Gardening");
        CheckBox cb6 = new CheckBox("Spiritual Guidance");
        CheckBox cb7 = new CheckBox("Cultural Teachings");
        CheckBox cb8 = new CheckBox("Child Care");
        CheckBox cb9 = new CheckBox("Life Advice / Mentoring");
        CheckBox cb10 = new CheckBox("Music / Singing");

        CheckBox[] checkBoxes = {cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8, cb9, cb10};
        for (int i = 0; i < checkBoxes.length; i++) {
            checkBoxes[i].setSelected(existingUser.skills[i]);
            
        }

        RadioButton otherSkillRadio = new RadioButton("Other");
        TextField otherSkillField = new TextField(existingUser.otherSkill);
        otherSkillField.setPromptText("Please specify...");
        otherSkillField.setMaxWidth(250);
        otherSkillField.setPrefHeight(35);
        otherSkillField.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #ccc; -fx-padding: 5;");
        otherSkillField.setVisible(existingUser.otherSkill != null && !existingUser.otherSkill.isEmpty());
        otherSkillField.setDisable(true);
        otherSkillRadio.setSelected(otherSkillField.isVisible());
       

        otherSkillRadio.setOnAction(e -> {
            otherSkillField.setVisible(otherSkillRadio.isSelected());
        });

        VBox skillListBox = new VBox(8);
        skillListBox.setAlignment(Pos.CENTER_LEFT);
        skillListBox.getChildren().addAll(checkBoxes);
        skillListBox.getChildren().addAll(otherSkillRadio, otherSkillField);

        skillBox.getChildren().addAll(fixedLabel("Select Skills:"), skillListBox);

        Button saveButton = new Button("\uD83D\uDCBE Save Profile");
        

        saveButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20; -fx-background-radius: 8;");
        

        

        Label resultLabel= new Label();
        //   saveButton.setOnAction(e -> {
        //         // ✅ 1. Save updated data to profiledata
        //         profiledata.name = nameField.getText();
        //         profiledata.email = emailField.getText();
        //         profiledata.age = ageField.getText();
        //         profiledata.location = locationField.getText();
        //         profiledata.bio = bioField.getText();
        //         profiledata.category = categoryField.getText();
        //         profiledata.description = descriptionField.getText();
        //         profiledata.charges = chargesField.getText();
        //         profiledata.availability = availabilityField.getText();
        //         profiledata.experience = experienceField.getText();
        //         profiledata.rating = ratingField.getText();

        //         String name=profiledata.name;
        //         String email =profiledata.email;
        //         String age= profiledata.age;
        //         String location=profiledata.location;
        //         String bio=profiledata.bio;
        //         String category=profiledata.category;
        //         String description=profiledata.description;
        //         String charges=profiledata.charges;
        //         String availability=profiledata.availability;
        //         String experience=profiledata.experience;
        //         String rating=profiledata.rating;
        //         String docId=profiledata.docId;

        //         String result = updateUserInFirestore(docId,name,email,age,location,bio,category,description,charges,availability,experience,rating);
        //         resultLabel.setText(result);

        //         // Save skills and otherSkill if any
        //         for (int i = 0; i < checkBoxes.length; i++) {
        //             profiledata.skills[i] = checkBoxes[i].isSelected();
        //         }

        //         if (otherSkillRadio.isSelected()) {
        //             profiledata.otherSkill = otherSkillField.getText();
        //         } else {
        //             profiledata.otherSkill = "";
        //         }

        //         // ✅ 2. Navigate directly to dashboard
               
        //         // dashboardpageP dashboard = new dashboardpageP();
        //         // try {
        //         //     dashboard.launchdashboard((Stage) saveButton.getScene().getWindow(), profiledata.name);
        //         // } catch (Exception ex) {
        //         //     ex.printStackTrace();
        //         // }

        //                     if (result.startsWith("✅")) {
        //         System.out.println("✅ Update successful, navigating to dashboard...");
        //         try {
        //             dashboardpageP dashboard = new dashboardpageP();
        //             dashboard.launchdashboard((Stage) saveButton.getScene().getWindow(), profiledata.name);
        //         } catch (Exception ex) {
        //             System.out.println("❌ Error navigating to dashboard:");
        //             ex.printStackTrace();
        //         }
        //     } else {
        //         System.out.println("❌ Update failed, staying on page.");
        //     }


        //         System.out.println("🔁 Save button clicked.");
        //         System.out.println("Document ID: " + profiledata.docId);
        //     });

        saveButton.setOnAction(e -> {
    profiledata.name = nameField.getText();
    profiledata.email = emailField.getText();
    profiledata.age = ageField.getText();
    profiledata.location = locationField.getText();
    profiledata.bio = bioField.getText();
    profiledata.category = categoryField.getText();
    profiledata.description = descriptionField.getText();
    profiledata.charges = chargesField.getText();
    profiledata.availability = availabilityField.getText();
    profiledata.experience = experienceField.getText();
    profiledata.rating = ratingField.getText();

    String name = profiledata.name;
    String email = profiledata.email;
    String age = profiledata.age;
    String location = profiledata.location;
    String bio = profiledata.bio;
    String category = profiledata.category;
    String description = profiledata.description;
    String charges = profiledata.charges;
    String availability = profiledata.availability;
    String experience = profiledata.experience;
    String rating = profiledata.rating;

    String result;

    if (profiledata.docId == null || profiledata.docId.isEmpty()) {
        // New user - add to Firestore
        result = profilepageP.addUserToFirestore(name, email, age, location, bio, category, description, charges, availability, experience, rating);
    } else {
        // Existing user - update Firestore document by docId
        System.out.println("Document ID: " + profiledata.docId);
        result = updateUserInFirestore(profiledata.docId, name, email, age, location, bio, category, description, charges, availability, experience, rating);
    }

    resultLabel.setText(result);

    dashboardpageP dashboard = new dashboardpageP();
    try {
        dashboard.launchdashboard((Stage) saveButton.getScene().getWindow(), profiledata.name);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
});



        HBox buttonBox = new HBox(20, saveButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox contentBox = new VBox(20, title, imageView, profileBox, serviceBox, skillBox, buttonBox);
        contentBox.setPadding(new Insets(30));
        contentBox.setAlignment(Pos.TOP_CENTER);
        contentBox.setMaxWidth(520);
        contentBox.setStyle("""
            -fx-background-color: white;
            -fx-border-color: #e0e0e0;
            -fx-border-radius: 15;
            -fx-background-radius: 15;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0.4, 0, 3);
        """);

        StackPane centerPane = new StackPane(contentBox);
        centerPane.setPadding(new Insets(40));
        centerPane.setAlignment(Pos.TOP_CENTER);

        ScrollPane scrollPane = new ScrollPane(centerPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: linear-gradient(to bottom, #fff, #bce4fcff);");

        Scene scene = new Scene(scrollPane, 1540, 795);
        myStage.setScene(scene);
        myStage.setTitle("\uD83E\uDDD3 Elder Service Provider Profile Page");
        myStage.show();
    }


    
    private Text fixedLabel(String content) {
        Text label = new Text(content);
        label.setFont(Font.font("Georgia", 16));
        label.setStyle("-fx-fill: #444;");
        return label;
    }

    private Text fixedTitle(String content) {
        Text title = new Text(content);
        title.setFont(Font.font("Georgia", 24));
        title.setStyle("-fx-font-weight: bold; -fx-fill: #222;");
        return title;
    }



    static String updateUserInFirestore(String docId, String name, String email, String age, String location, String bio, String category, String description, String charges, String availability, String experience, String rating) {
    if (docId == null || docId.isEmpty()) {
        return "Error: Document ID is missing.";
    }
    String endpoint = String.format(
        "https://firestore.googleapis.com/v1/projects/%s/databases/(default)/documents/provider/%s?key=%s" + 
        PROJECT_ID, docId, API_KEY
    );


    String payload = String.format(
        "{ \"fields\": {" +
            "\"name\": {\"stringValue\": \"%s\"}," +
            "\"email\": {\"stringValue\": \"%s\"}," +
            "\"age\": {\"stringValue\": \"%s\"}," +
            "\"location\": {\"stringValue\": \"%s\"}," +
            "\"bio\": {\"stringValue\": \"%s\"}," +
            "\"category\": {\"stringValue\": \"%s\"}," +
            "\"description\": {\"stringValue\": \"%s\"}," +
            "\"charges\": {\"stringValue\": \"%s\"}," +
            "\"availability\": {\"stringValue\": \"%s\"}," +
            "\"experience\": {\"stringValue\": \"%s\"}," +
            "\"rating\": {\"stringValue\": \"%s\"}" +
        "} }",
        name, email, age, location, bio, category, description, charges, availability, experience, rating
    );

    try {
        URL url = new URL(endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PATCH");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(payload.getBytes(StandardCharsets.UTF_8));
        }

        InputStream is = conn.getInputStream();
        byte[] responseBytes = is.readAllBytes();
        String response = new String(responseBytes, StandardCharsets.UTF_8);
        is.close();
        conn.disconnect();

        return "Update successful! Firestore says:\n" + response;
    } catch (Exception e) {
        return "Update failed: " + e.getMessage();
    }
}


            private static String safeString(String value) {
            return value == null ? "" : value.replace("\"", "\\\"");
        }
    
}
