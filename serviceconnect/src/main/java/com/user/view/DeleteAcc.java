package com.user.view;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class DeleteAcc extends Application {

    @Override
    public void start(Stage stage) {
        VBox outerLayout = new VBox();
        outerLayout.setAlignment(Pos.CENTER);
        outerLayout.setPadding(new Insets(50));
        outerLayout.setStyle("-fx-background-color: linear-gradient(to right, #fff1f0, #ffe6e6);");

        Label heading = new Label("Delete Your Account");
        heading.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28));
        heading.setTextFill(Color.web("#B71C1C"));

        VBox card = new VBox(25);
        card.setPadding(new Insets(30));
        card.setAlignment(Pos.CENTER);
        card.setMaxWidth(500);
        card.setStyle("-fx-background-color: white; -fx-background-radius: 12; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 15, 0.3, 0, 8);");

        Label warning = new Label("Are you sure you want to permanently delete your account?\nThis action cannot be undone.");
        warning.setFont(Font.font("Segoe UI", 14));
        warning.setTextFill(Color.web("#d32f2f"));
        warning.setWrapText(true);
        warning.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        TextField reasonField = new TextField();
        reasonField.setPromptText("Reason for deleting your account");
        reasonField.setPrefWidth(300);
        reasonField.setStyle("-fx-background-color: #fbe9e7; -fx-border-radius: 5; -fx-background-radius: 5; -fx-padding: 8;");

        PasswordField confirmPassword = new PasswordField();
        confirmPassword.setPromptText("Enter your password to confirm");
        confirmPassword.setPrefWidth(300);
        confirmPassword.setStyle("-fx-background-color: #fbe9e7; -fx-border-radius: 5; -fx-background-radius: 5; -fx-padding: 8;");

        HBox buttons = new HBox(20);
        buttons.setAlignment(Pos.CENTER);

        Button yesBtn = new Button("Yes, Delete");
        yesBtn.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 6; -fx-padding: 10 20 10 20;");
        yesBtn.setOnAction(e -> {
    
        LandingPage landingPage = new LandingPage();
         try {
           landingPage.start(stage);
          } catch (Exception ex) {
            ex.printStackTrace();
    }
});


        Button noBtn = new Button("No, Go Back");
        noBtn.setStyle("-fx-background-color: #cfd8dc; -fx-text-fill: black; -fx-font-size: 14px; -fx-background-radius: 6; -fx-padding: 10 20 10 20;");

        buttons.getChildren().addAll(yesBtn, noBtn);

        card.getChildren().addAll(warning, reasonField, confirmPassword, buttons);
        outerLayout.getChildren().addAll(heading, card);

        Scene scene = new Scene(outerLayout, 1540, 795);
        stage.setTitle("Delete Account");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}