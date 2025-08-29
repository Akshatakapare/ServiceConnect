package com.user.view;

import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.effect.DropShadow;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import com.user.model.Profile;
import com.user.model.profiledata;

public class ProfilePage  {
    static final String PROJECT_ID="serviceconnect-eb8b6";
    static final String API_KEY="AIzaSyAlJon0EBJRYetQUsc2ySdnt3pm41U4KMU";
    private String username;
    private String email;

    private Map<String, String> profileData;

    public ProfilePage(Map<String, String> profileData) {
        this.profileData = profileData;
    }


    //     public void Profile(String name, String age, String gender, String location,
    //                String phone, String service, String expectations,
    //                String language, Image image) {
    //     this.name = name;
    //     this.age = age;
    //     this.gender = gender;
    //     this.location = location;
    //     this.phone = phone;
    //     this.service = service;
    //     this.expectations = expectations;
    //     this.language = language;
    //     this.image = image;
    // }

    
   public ProfilePage(String user,String pass) {
      
    }

    public void start(Stage stage) {
    start(stage, null); // Pass null or use default profile
}

   private Image selectedImage;

  
    public void start(Stage myStage, Profile profile) {
        myStage.setTitle("Seeker Profile Page");

        // Title
        Label title = new Label("Create Your Profile");
        title.setFont(Font.font("Arial", 28));
        title.setTextFill(Color.web("#333"));

        // Profile Image
        ImageView imageView = new ImageView();
        imageView.setFitWidth(120);
        imageView.setFitHeight(120);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 0, 1);");

        Image defaultImage = new Image("/Assets//Images//akshata.png");
        imageView.setImage(defaultImage);

        // Choose Image Button
        Button chooseImageBtn = new Button("Choose Image");
        chooseImageBtn.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Profile Image");
            fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
            );
            File selectedFile = fileChooser.showOpenDialog(myStage);
            if (selectedFile != null) {
                selectedImage = new Image(selectedFile.toURI().toString());
                imageView.setImage(selectedImage);
            }
        });

        // Profile Fields
        TextField nameField = new TextField();
        nameField.setPromptText("Enter your name");

        TextField emailField = new TextField();
        emailField.setPromptText("Enter your email");

        TextField ageField = new TextField();
        ageField.setPromptText("Enter your age");

        TextField bioField = new TextField();
        bioField.setPromptText("Enter your bio");

        // Gender using RadioButtons
        Label genderLabel = new Label("Select Gender:");
        RadioButton maleRadio = new RadioButton("Male");
        RadioButton femaleRadio = new RadioButton("Female");
        RadioButton otherRadio = new RadioButton("Other");

        ToggleGroup genderGroup = new ToggleGroup();
        maleRadio.setToggleGroup(genderGroup);
        femaleRadio.setToggleGroup(genderGroup);
        otherRadio.setToggleGroup(genderGroup);
        

        HBox genderBox = new HBox(15, maleRadio, femaleRadio, otherRadio);
        genderBox.setAlignment(Pos.CENTER_LEFT);
        VBox genderSection = new VBox(5, genderLabel, genderBox);

        TextField locationField = new TextField();
        locationField.setPromptText("Enter your location");

        TextField phoneField = new TextField();
        phoneField.setPromptText("Enter your phone number");

        Label seekingLabel = new Label("What are you seeking?");
        ComboBox<String> seekingComboBox = new ComboBox<>();
seekingComboBox.getItems().addAll(
    "Tutoring", "Storytelling", "Singing", "Cooking Help", "Child Care", "Gardening", "Other"
);
seekingComboBox.setPromptText("Choose service");
        HBox lbseek = new HBox(6 ,seekingLabel,seekingComboBox);

        TextArea expectationsArea = new TextArea();
        expectationsArea.setPromptText("Tell us what you expect...");
        expectationsArea.setPrefRowCount(4);

        Label languageLabel = new Label("Preferred Language:");
        ComboBox<String> languageComboBox = new ComboBox<>();
        languageComboBox.getItems().addAll(
    "English", "Hindi", "Marathi", "Gujarati", "Tamil", "Telugu", "Kannada", "Bengali", "Other"
);
languageComboBox.setPromptText("Select language");

        HBox lblang = new HBox(5,languageLabel,languageComboBox);
        

        System.out.println("Fetching profile for: " + profiledata.email);


        // Message Label for validation
        Label messageLabel = new Label();
        messageLabel.setTextFill(Color.RED);
        messageLabel.setFont(Font.font("Arial", 14));
        Label resultLabel = new Label();

        // Save Button
        Button saveButton = new Button("Save Profile");
        saveButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        saveButton.setOnAction(e -> {

            String name = nameField.getText();
            String age = ageField.getText();
            String gender = ((RadioButton) genderGroup.getSelectedToggle()).getText();
            String location = locationField.getText();
            String phone = phoneField.getText();
            String service = seekingComboBox.getValue();
            String expectations = expectationsArea.getText();
            String language = languageComboBox.getValue();

            Image imageToPass = selectedImage != null ? selectedImage : defaultImage;



            String result = addUserToFirestore(name, email,age, gender, location, phone, service, expectations,language,imageToPass);
                resultLabel.setText(result);
            // Validate fields
            boolean isValid = !nameField.getText().trim().isEmpty()
                    && !ageField.getText().trim().isEmpty()
                    && genderGroup.getSelectedToggle() != null
                    && !locationField.getText().trim().isEmpty()
                    && !phoneField.getText().trim().isEmpty()
                    && seekingComboBox.getValue() != null
                    && !expectationsArea.getText().trim().isEmpty()
                    && languageComboBox.getValue() != null;


            if (isValid) {
        // String name = nameField.getText();
        // int age = Integer.parseInt(ageField.getText());
        // String gender = ((RadioButton) genderGroup.getSelectedToggle()).getText();
        // String location = locationField.getText();
        // String phone = phoneField.getText();
        // String service = seekingComboBox.getValue();
        // String expectations = expectationsArea.getText();
        // String language = languageComboBox.getValue();




            // Create profile model with all details
            Profile updatedprofile = new Profile(name,email, age, gender, location, phone, service, expectations,language, imageToPass);

            // Pass profile to dashboard
            DashboardView dashboard = new DashboardView(updatedprofile.getName());
            dashboard.setProfile(updatedprofile);

            try {
            dashboard.launchDashboard(new Stage(), updatedprofile.getName());
            myStage.close();
            } catch (Exception ex) {
            ex.printStackTrace();
            }
        }         
        });


        VBox imageBox = new VBox(8, imageView, chooseImageBtn);
        imageBox.setAlignment(Pos.CENTER);

        VBox contentBox = new VBox(15,
            title,
            imageBox,
            nameField,
            ageField,
            genderSection,
            locationField,
            phoneField,
            lbseek,
            expectationsArea,
            lblang,
            messageLabel,     // Message shown here
            saveButton
        );
        contentBox.setPadding(new Insets(30,30,30,30));
        contentBox.setAlignment(Pos.TOP_CENTER);
        contentBox.setStyle("-fx-background-color: #FFF6F1; -fx-border-radius: 12; -fx-background-radius: 12;");
        contentBox.setMaxWidth(400);
        contentBox.setMaxHeight(400);
        ImageView backgroundImage = new ImageView(
        new Image("Assets/Images/Untitled design (3).png") // Replace with your background image path
    );
    backgroundImage.setFitWidth(1400); // match your scene size
    backgroundImage.setFitHeight(750);
    backgroundImage.setPreserveRatio(false);
    backgroundImage.setOpacity(0.9); // light overlay
    backgroundImage.setSmooth(true);


        StackPane shadowPane = new StackPane();
        shadowPane.getChildren().addAll(backgroundImage, contentBox);
       
        shadowPane.setStyle("-fx-background-color: #e0e0e0;");
        shadowPane.setEffect(new DropShadow());

        Scene scene = new Scene(shadowPane, 1540, 795);
        myStage.setScene(scene);
        myStage.show();
    }

    //-----------------add user to firestore-------
    static String addUserToFirestore(String name,String email, String age, String gender, String location, String phone, String service, String expectations,String language, Image imageToPass) {
        if(name.isEmpty()||age.isEmpty()){
            return"Please enter both name and age";
        }
        String endpoint= String.format("https://firestore.googleapis.com/v1/projects/%s/databases/(default)/documents/seeker?key=%s",PROJECT_ID,API_KEY);
        // String payload = String.format(
        //                                 "{ \"fields\": {"
        //                                     + "\"name\": {\"stringValue\": \"%s\"},"
        //                                     + "\"age\": {\"stringValue\": \"%s\"},"
        //                                     + "\"gender\": {\"stringValue\": \"%s\"},"
        //                                     + "\"location\": {\"stringValue\": \"%s\"},"
        //                                     + "\"phone\": {\"stringValue\": \"%s\"},"
        //                                     + "\"service\": {\"stringValue\": \"%s\"},"
        //                                     + "\"expectations\": {\"stringValue\": \"%s\"},"
        //                                     +"\"language\": {\"stringValue\": \"%s\"},"
        //                                     +"} }",
        //                                 name, age, gender, location, phone, service, expectations,language,imageToPass
        //                             );

        String payload = String.format(
                "{ \"fields\": {" +
                    "\"name\": {\"stringValue\": \"%s\"}," +
                    "\"age\": {\"stringValue\": \"%s\"}," +
                    "\"gender\": {\"stringValue\": \"%s\"}," +
                    "\"location\": {\"stringValue\": \"%s\"}," +
                    "\"phone\": {\"stringValue\": \"%s\"}," +
                    "\"service\": {\"stringValue\": \"%s\"}," +
                    "\"expectations\": {\"stringValue\": \"%s\"}," +
                    "\"language\": {\"stringValue\": \"%s\"}," +
                    "\"imageUrl\": {\"stringValue\": \"%s\"}" +
                "} }",
                name, age, gender, location, phone, service, expectations, language, imageToPass
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
            conn.disconnect();;
            return "Added! Firestore says:\n"+response;
        }catch(Exception e){
            return "Error:"+e.getMessage();
        }
    }

    //==============get data from firestore
    private Map<String, String> getseekerProfileFromFirestoreByEmail(String emailToFind) {
    String endpoint = String.format(
        "https://firestore.googleapis.com/v1/projects/%s/databases/(default)/documents/seeker?key=%s",
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

            if (email.equals(emailToFind)) {
                profileData.put("name", fields.getJSONObject("name").getString("stringValue"));
                profileData.put("email", email);
                profileData.put("age", fields.getJSONObject("age").getString("stringValue"));
                profileData.put("gender", fields.getJSONObject("gender").getString("stringValue"));
                profileData.put("location", fields.getJSONObject("location").getString("stringValue"));
                profileData.put("phone", fields.getJSONObject("phone").getString("stringValue"));
                profileData.put("service", fields.getJSONObject("service").getString("stringValue"));
                profileData.put("expectations", fields.getJSONObject("expectations").getString("stringValue"));
                profileData.put("language", fields.getJSONObject("language").getString("stringValue"));
                profileData.put("imageToPass", fields.getJSONObject("imageToPass").getString("stringValue"));
                break;
            }
        }
        // name, age, gender, location, phone, service, expectations,language,imageToPass

    } catch (Exception e) {
        e.printStackTrace();
    }

    return profileData;
}

}  