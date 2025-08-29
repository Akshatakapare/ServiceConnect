package com.user.view;

import com.user.model.Profile;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class EditProfile {
    private Profile profile;
    private String username;


  private DashboardView dashboard;

    public EditProfile(Profile profile, DashboardView dashboard) {
    this.profile = profile;
    this.dashboard = dashboard;
}

    public void start(Stage stage) {
        Label title = new Label("✏️ Edit Profile");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");
        
        // Pre-filled input fields
    TextField nameField = new TextField(profile.getName());
    TextField ageField = new TextField(String.valueOf(profile.getAge()));
    TextField genderField = new TextField(profile.getGender());
    TextField locationField = new TextField(profile.getLocation());
    TextField phoneField = new TextField(profile.getPhone());
    TextField serviceField = new TextField(profile.getService());
    TextArea expectationsArea = new TextArea(profile.getExpectations());
    expectationsArea.setPrefRowCount(3);

TextField languageField = new TextField(profile.getPreferredLanguage());

        VBox form = new VBox(10,
            title,
            new Label("Name:"), nameField,
            new Label("Age:"), ageField,
            new Label("Gender:"), genderField,
            new Label("Location:"), locationField,
            new Label("Phone:"), phoneField,
            new Label("Service:"), serviceField,
            new Label("Expectations:"), expectationsArea,
            new Label("Language Preferred:") ,languageField

        );
        form.setPadding(new Insets(20));
        form.setAlignment(Pos.CENTER_LEFT);

        // Save button
        Button saveBtn = new Button("💾 Save Changes");
        saveBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        saveBtn.setOnAction(e -> {
    try {
        // Update profile data
        profile.setName(nameField.getText());
        profile.setAge(ageField.getText());
        profile.setGender(genderField.getText());
        profile.setLocation(locationField.getText());
        profile.setPhone(phoneField.getText());
        profile.setService(serviceField.getText());
        profile.setExpectations(expectationsArea.getText());
        profile.setPreferredLanguage(languageField.getText());


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Profile Updated");
        alert.setContentText("Changes have been saved successfully!");
        alert.showAndWait();

        dashboard.setProfile(profile); // If such method exists or add it
      
        dashboard.launchDashboard(stage, username); // ✅ Custom method you define

        
    } catch (NumberFormatException ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Invalid Input");
        alert.setContentText("Please enter a valid age (number).");
        alert.showAndWait();
    }
});

        VBox root = new VBox(20, form, saveBtn);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));

        Scene scene = new Scene(root, 1540,795);
        stage.setScene(scene);
        stage.setTitle("Edit Profile");
        stage.show();
    }
}
