package com.user.view;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class changepassP extends Application {

    @Override
    public void start(Stage stage) {
        VBox outerLayout = new VBox();
        outerLayout.setAlignment(Pos.CENTER);
        outerLayout.setPadding(new Insets(50));
        outerLayout.setStyle("-fx-background-color: linear-gradient(to right, #f8f9fa, #e8f0fe);");

        Label heading = new Label("Change Your Password");
        heading.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28));
        heading.setTextFill(Color.web("#1A237E"));

        VBox card = new VBox(25);
        card.setPadding(new Insets(40));
        card.setAlignment(Pos.CENTER);
        card.setMaxWidth(450);
        card.setStyle("-fx-background-color: white; -fx-background-radius: 12; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 15, 0.3, 0, 8);");

        Label instructions = new Label("Please enter your current and new password below.");
        instructions.setFont(Font.font("Segoe UI", 14));
        instructions.setTextFill(Color.web("#555"));

        VBox form = new VBox(18);
        form.setAlignment(Pos.CENTER);

        PasswordField current = createStyledPasswordField("Current Password");
        PasswordField newPwd = createStyledPasswordField("New Password");
        PasswordField confirmPwd = createStyledPasswordField("Confirm Password");

        Button submit = new Button("Update Password");
        submit.setStyle("-fx-background-color: #3949AB; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 6; -fx-padding: 10 20 10 20;");
        submit.setOnAction(e -> {
            // Navigate back to settings page
            ProviderSettingsPageP settingsPage = new ProviderSettingsPageP();
            try {
                settingsPage.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        form.getChildren().addAll(current, newPwd, confirmPwd, submit);

        card.getChildren().addAll(instructions, form);
        outerLayout.getChildren().addAll(heading, card);

        Scene scene = new Scene(outerLayout, 1540, 795);
        stage.setTitle("Change Password");
        stage.setScene(scene);
        stage.show();
    }

    private PasswordField createStyledPasswordField(String prompt) {
        PasswordField field = new PasswordField();
        field.setPromptText(prompt);
        field.setFont(Font.font("Segoe UI", 13));
        field.setPrefWidth(300);
        field.setStyle("-fx-background-color: #f0f0f0; -fx-border-radius: 5; -fx-background-radius: 5; -fx-padding: 8;");
        return field;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
