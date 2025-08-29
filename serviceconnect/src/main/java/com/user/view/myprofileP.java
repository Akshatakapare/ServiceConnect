package com.user.view;

import com.user.model.profiledata;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class myprofileP extends Application {

    @Override
    public void start(Stage stage) {
        Label title = new Label("My Profile");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        VBox profileBox = new VBox(15);
        profileBox.setPadding(new Insets(20));
        profileBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-radius: 10; -fx-background-radius: 10;");
        profileBox.setMaxWidth(500);
        profileBox.setAlignment(Pos.TOP_LEFT);

        profileBox.getChildren().addAll(
            createLabel("Name", profiledata.name),
            createLabel("Email", profiledata.email),
            createLabel("Age", profiledata.age),
            createLabel("Location", profiledata.location),
            createLabel("Bio", profiledata.bio),
            createLabel("Category", profiledata.category),
            createLabel("Description", profiledata.description),
            createLabel("Charges", profiledata.charges),
            createLabel("Availability", profiledata.availability),
            createLabel("Experience", profiledata.experience),
            createLabel("Rating", profiledata.rating)
        );

        Button editButton = new Button("✏️ Edit Profile");
        editButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        editButton.setOnAction(e -> {
    System.out.println("📤 Launching editpageP with docId = " + profiledata.docId); // ✅ Debug print

    editpageP editPage = new editpageP();
    try {
        editPage.start((Stage) editButton.getScene().getWindow());
    } catch (Exception ex) {
        ex.printStackTrace();
    }
});

      Button backButton = new Button("← Back to Dashboard");
backButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

backButton.setOnAction(e -> {
    try {
        dashboardpageP dashboard = new dashboardpageP();
        dashboard.launchdashboard(stage, profiledata.name);  // assuming you want to pass the name
    } catch (Exception ex) {
        ex.printStackTrace();
    }
});


        

        VBox buttons = new VBox(10, editButton,backButton);

        buttons.setAlignment(Pos.CENTER);

        VBox content = new VBox(30, title, profileBox, buttons);
        content.setAlignment(Pos.TOP_CENTER);
        content.setStyle("-fx-background: linear-gradient(to bottom, #fff,#bce4fcff);");
        content.setPadding(new Insets(30));

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("fx-background: linear-gradient(to bottom, #fff, #bce4fcff);");

        Scene scene = new Scene(scrollPane, 1540, 795);
        stage.setTitle("My Profile");
        stage.setScene(scene);
        stage.show();
    }

    private VBox createLabel(String title, String value) {
        Label labelTitle = new Label(title + ":");
        labelTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        Label labelValue = new Label(value != null && !value.isEmpty() ? value : "N/A");
        labelValue.setStyle("-fx-font-size: 14px;");

        VBox box = new VBox(2, labelTitle, labelValue);
        return box;
    }

    
}
