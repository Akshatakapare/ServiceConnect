package com.user.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import com.user.model.profiledata;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class profilepageP extends Application {


    static final String PROJECT_ID="serviceconnect-eb8b6";
    static final String API_KEY="AIzaSyAlJon0EBJRYetQUsc2ySdnt3pm41U4KMU";

        private Map<String, String> profileData;

    public profilepageP(Map<String, String> profileData) {
        this.profileData = profileData;
    }

    public profilepageP(String user,String pass) {
      
    }
    private Image selectedImage;

    @Override
    public void start(Stage myStage) {

        Text profileTitle = fixedTitle("\uD83D\uDC64 My Profile");
     

        ImageView imageView = new ImageView();
imageView.setFitWidth(120);
imageView.setPreserveRatio(true);
imageView.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 8, 0.3, 0, 2);");

Image defaultImage = new Image("file:/Assets/Images/image.png");
        imageView.setImage(defaultImage);

// Button to choose image
Button uploadBtn = new Button("Upload Image");
uploadBtn.setStyle("-fx-background-color: #007BFF; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 6px;");

uploadBtn.setOnAction(e -> {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choose Profile Image");
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
    );

    File selectedFile = fileChooser.showOpenDialog(myStage);
    if (selectedFile != null) {
        Image selectedImage = new Image(selectedFile.toURI().toString());
        imageView.setImage(selectedImage);
        profiledata.imagePath = selectedFile.toURI().toString(); // Save for dashboard
    }
});

        TextField nameField = new TextField();
        nameField.setPromptText("Enter Your Full Name");

        TextField emailField = new TextField();
        emailField.setPromptText("Enter Your Email");

        TextField ageField = new TextField();
        ageField.setPromptText("Enter Your Age");

        TextField locationField = new TextField();
        locationField.setPromptText("Enter Your Location");

        TextField bioField = new TextField();
        bioField.setPromptText("Enter Your Bio");

        TextField categoryField = new TextField();
        categoryField.setPromptText("e.g., Cooking, Tutoring...");

        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Briefly explain your service");

        TextField chargesField = new TextField();
        chargesField.setPromptText("Per hour / Per task / Negotiable");

        TextField availabilityField = new TextField();
        availabilityField.setPromptText("Available Days & Time Slots");

        TextField experienceField = new TextField();
        experienceField.setPromptText("e.g., 5 years");

        TextField ratingField = new TextField();
        ratingField.setPromptText("e.g., 4.8 / 5");

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
        // 🔽 Fetch user profile from Firestore using email saved in profiledata
        System.out.println("Fetching profile for: " + profiledata.email);

        Map<String, String> fetchedData = getProviderProfileFromFirestoreByEmail(profiledata.email);

                if (!fetchedData.isEmpty()) {
                    // Save to static fields
                    profiledata.name = fetchedData.getOrDefault("name", "");
                    profiledata.email = fetchedData.getOrDefault("email", "");
                    profiledata.age = fetchedData.getOrDefault("age", "");
                    profiledata.location = fetchedData.getOrDefault("location", "");
                    profiledata.bio = fetchedData.getOrDefault("bio", "");
                    profiledata.category = fetchedData.getOrDefault("category", "");
                    profiledata.description = fetchedData.getOrDefault("description", "");
                    profiledata.charges = fetchedData.getOrDefault("charges", "");
                    profiledata.availability = fetchedData.getOrDefault("availability", "");
                    profiledata.experience = fetchedData.getOrDefault("experience", "");
                    profiledata.rating = fetchedData.getOrDefault("rating", "");

                    // Then update UI
                    nameField.setText(profiledata.name);
                    emailField.setText(profiledata.email);
                    ageField.setText(profiledata.age);
                    locationField.setText(profiledata.location);
                    bioField.setText(profiledata.bio);
                    categoryField.setText(profiledata.category);
                    descriptionField.setText(profiledata.description);
                    chargesField.setText(profiledata.charges);
                    availabilityField.setText(profiledata.availability);
                    experienceField.setText(profiledata.experience);
                    ratingField.setText(profiledata.rating);
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

        RadioButton otherSkillRadio = new RadioButton("Other");
        TextField otherSkillField = new TextField();
        otherSkillField.setPromptText("Please specify...");
        otherSkillField.setMaxWidth(250);
        otherSkillField.setPrefHeight(35);
        otherSkillField.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #ccc; -fx-padding: 5;");
        otherSkillField.setVisible(false);

        otherSkillRadio.setOnAction(e -> {
            otherSkillField.setVisible(otherSkillRadio.isSelected());
        });

        VBox skillListBox = new VBox(8,
            cb1, cb2, cb3, cb4, cb5,
            cb6, cb7, cb8, cb9, cb10,
            otherSkillRadio, otherSkillField
        );
        skillListBox.setAlignment(Pos.CENTER_LEFT);

        skillBox.getChildren().addAll(fixedLabel("Select Skills:"), skillListBox);

        Button saveButton = new Button("\uD83D\uDCBE Save Profile");
        //Button editButton = new Button("\u270F\uFE0F Edit Profile");
        
        Label resultLabel = new Label();
// ...

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

    String name =profiledata.name;
    String email = profiledata.email;
    String age=profiledata.age;
    String location=profiledata.location;
    String bio=profiledata.bio;
    String category=profiledata.category;
    String description=profiledata.description;
    String charges =profiledata.charges;
    String availability =profiledata.availability;
    String experience =profiledata.experience;
    String rating =profiledata.rating;

    Image imageToPass = selectedImage != null ? selectedImage : defaultImage;

    String result = addUserToFirestore(name, email,age,location,bio,category,description,charges,availability,experience,rating);
    resultLabel.setText(result);

    dashboardpageP dashboard = new dashboardpageP();
    try {
        dashboard.launchdashboard((Stage) saveButton.getScene().getWindow(), profiledata.name);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
});




        saveButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20; -fx-background-radius: 8;");
        

        HBox buttonBox = new HBox(20, saveButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox contentBox = new VBox(20, profileTitle, imageView,uploadBtn, profileBox, skillBox,serviceBox, buttonBox);
        contentBox.setPadding(new Insets(30));
        contentBox.setAlignment(Pos.TOP_CENTER);
        contentBox.setMaxWidth(520);
        contentBox.setStyle("""
            -fx-background-color: #ffffff;
            -fx-border-color: #e0e0e0;
            -fx-border-radius: 15;
            -fx-background-radius: 15;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0.4, 0, 3);
        """);

        StackPane centerPane = new StackPane(contentBox);
        centerPane.setPadding(new Insets(40));
        centerPane.setAlignment(Pos.TOP_CENTER);
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #ffe5b4);" +
              "-fx-background-size: cover;" +
              "-fx-background-position: center center;");

        ScrollPane scrollPane = new ScrollPane(centerPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: linear-gradient(to bottom, #fff, #bce4fcff);");

        Scene scene = new Scene(scrollPane, 1540, 795);
        myStage.setScene(scene);
        myStage.setTitle("\uD83E\uDDD3 Service Provider Profile Page");
        myStage.show();
    }

    private Text fixedLabel(String content) {
        Text label = new Text(content);
        label.setFont(Font.font("Arial", 16));
        label.setStyle("-fx-fill: #444;");
        return label;
    }

    private Text fixedTitle(String content) {
        Text title = new Text(content);
        title.setFont(Font.font("Arial", 24));
        title.setStyle(" -fx-fill: #222;");
        return title;
    }

     //-----------------add user to firestore-------
    public static String addUserToFirestore(String name, String email, String age, String location, String bio, String category, String description, String charges, String availability, String experience, String rating) {
        if(name.isEmpty()||age.isEmpty()){
            return"Please enter both name and age";
        }

        String endpoint= String.format("https://firestore.googleapis.com/v1/projects/%s/databases/(default)/documents/provider?key=%s",PROJECT_ID,API_KEY);

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
                    "\"rating\": {\"stringValue\": \"%s\"}," +
                "} }",
                name, email,age,location,bio,category,description,charges,availability,experience,rating
            );



        // String payload = String.format("{\"fields\":{\"name\":{\"stringValue\":\"%s\"},\"age\":{\"integerValue\":\"%s\"},{\"gender\":{\"stringValue\":\"%s\"},{\"location\":{\"stringValue\":\"%s\"},{\"phone\":{\"stringValue\":\"%s\"},{\"service\":{\"stringValue\":\"%s\"}}}", name,age,gender,location,phone,service);
        try{
            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            try(OutputStream os = conn.getOutputStream()){
                os.write(payload.getBytes(StandardCharsets.UTF_8));
            }
            InputStream is = conn.getInputStream();
            byte[] responseBytes = is.readAllBytes();
            String response = new String(responseBytes,StandardCharsets.UTF_8);
            is.close();
            conn.disconnect();
            return "Added! Firestore says:\n"+response;
        }catch(Exception e){
            return "Error:"+e.getMessage();
        }
    }

    //==============get data from firestore
    private Map<String, String> getProviderProfileFromFirestoreByEmail(String emailToFind) {
    String endpoint = String.format(
        "https://firestore.googleapis.com/v1/projects/%s/databases/(default)/documents/provider?key=%s",
        PROJECT_ID, API_KEY
    );
    Map<String, String> profileData = new HashMap<>();

    try {
        URL url = new URL(endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");
        InputStream is = conn.getInputStream();
        String response = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        is.close();

        org.json.JSONObject jsonResponse = new org.json.JSONObject(response);
        org.json.JSONArray documents = jsonResponse.getJSONArray("documents");

        for (int i = 0; i < documents.length(); i++) {
            org.json.JSONObject doc = documents.getJSONObject(i);
            org.json.JSONObject fields = doc.getJSONObject("fields");

            String email = fields.getJSONObject("email").getString("stringValue");

            // if (email.equals(emailToFind)) {
            //     String docName = doc.getString("name"); // e.g., "projects/your-project-id/databases/(default)/documents/provider/p7PlZLGHKbYVSeTVukYd"
            //     String[] parts = docName.split("/");
            //     String docId = parts[parts.length - 1]; // Extract "p7PlZLGHKbYVSeTVukYd"
                
            //     profiledata.docId = docId; // Store it for later use
            //     System.out.println(docId);
            //     System.out.println("✅ profilepageP: Stored docId = " + profiledata.docId);

            //     //                 String documentName = document.get("name").getAsString(); // e.g., "projects/.../documents/provider/abc123"
            //     // String[] parts = documentName.split("/");
            //     // profiledata.docId = parts[parts.length - 1]; // This sets profiledata.docId to "abc123"
            //     // System.out.println("✅ Fetched docId: " + profiledata.docId);



            //     profileData.put("name", fields.getJSONObject("name").getString("stringValue"));
            //     profileData.put("email", email);
            //     profileData.put("age", fields.getJSONObject("age").getString("stringValue"));
            //     profileData.put("location", fields.getJSONObject("location").getString("stringValue"));
            //     profileData.put("bio", fields.getJSONObject("bio").getString("stringValue"));
            //     profileData.put("category", fields.getJSONObject("category").getString("stringValue"));
            //     profileData.put("description", fields.getJSONObject("description").getString("stringValue"));
            //     profileData.put("charges", fields.getJSONObject("charges").getString("stringValue"));
            //     profileData.put("availability", fields.getJSONObject("availability").getString("stringValue"));
            //     profileData.put("experience", fields.getJSONObject("experience").getString("stringValue"));
            //     profileData.put("rating", fields.getJSONObject("rating").getString("stringValue"));
            //     break;

            // }
            System.out.println("📌 Checking document with email: " + email);

if (email != null && email.trim().equalsIgnoreCase(emailToFind.trim())) {
    String docName = doc.getString("name");
    String[] parts = docName.split("/");
    String docId = parts[parts.length - 1];
    profiledata.docId = docId;
    System.out.println("✅ profilepageP: Stored docId = " + profiledata.docId);
}

        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return profileData;
}
    
}

