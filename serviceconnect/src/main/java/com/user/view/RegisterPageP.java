package com.user.view;

import com.user.Controller.FirebaseAuthenticationController;
import com.user.model.NotificationStore;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class RegisterPageP {

    FirebaseAuthenticationController authController = new FirebaseAuthenticationController();
    Stage rStage;
    Scene rScene;

    public void setrStage(Stage rStage) {
        this.rStage = rStage;
    }

    public void setrScene(Scene rScene) {
        this.rScene = rScene;
    }

    public VBox createsignupScene(Runnable prv) {
        // Logo at the top of form
        ImageView logo = new ImageView(new Image("Assets\\Images\\oldpr.jpg"));
        logo.setFitWidth(100);
        logo.setFitHeight(100);

        // Form Heading
        Label signupLabel = new Label("Get Started Now");
        signupLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        signupLabel.setTextFill(Color.web("#2C3E50"));

        

        // Input Fields
        TextField nameField = new TextField();
        nameField.setPromptText("Username");
        nameField.setMaxWidth(280);
        nameField.setStyle(
    "-fx-background-color: #FAFAFA;" +
    "-fx-border-color: #ccc;" +
    "-fx-border-radius: 6px;" +
    "-fx-background-radius: 6px;" +
    "-fx-padding: 8 12;" +
    "-fx-font-size: 13px;"
);


        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.setMaxWidth(280);
        emailField.setStyle(
    "-fx-background-color: #FAFAFA;" +
    "-fx-border-color: #ccc;" +
    "-fx-border-radius: 6px;" +
    "-fx-background-radius: 6px;" +
    "-fx-padding: 8 12;" +
    "-fx-font-size: 13px;"
);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(280);
        passwordField.setStyle(
    "-fx-background-color: #FAFAFA;" +
    "-fx-border-color: #ccc;" +
    "-fx-border-radius: 6px;" +
    "-fx-background-radius: 6px;" +
    "-fx-padding: 8 12;" +
    "-fx-font-size: 13px;"
);

        PasswordField confirmField = new PasswordField();
        confirmField.setPromptText("Confirm Password");
        confirmField.setMaxWidth(280);
        confirmField.setStyle(
    "-fx-background-color: #FAFAFA;" +
    "-fx-border-color: #ccc;" +
    "-fx-border-radius: 6px;" +
    "-fx-background-radius: 6px;" +
    "-fx-padding: 8 12;" +
    "-fx-font-size: 13px;"
);
      Hyperlink forgotLink = new Hyperlink("Forgot Password?");
forgotLink.setStyle("-fx-text-fill: #4CAF50; -fx-underline: true;");
forgotLink.setBorder(Border.EMPTY);
forgotLink.setPadding(Insets.EMPTY);
forgotLink.setOnAction(event -> {
    // Add your logic here
    System.out.println("Forgot Password clicked");
});
        Label message = new Label();

        // Register Button
        Button createBtn = new Button("CREATE ACCOUNT");
        
        createBtn.setMinWidth(250);
        createBtn.setFont(Font.font(14));
        createBtn.setStyle(
    "-fx-background-color: linear-gradient(to right, #4CAF50, #388E3C);" +
    "-fx-text-fill: white;" +
    "-fx-font-weight: bold;" +
    "-fx-background-radius: 8px;" +
    "-fx-padding: 10 0;" +
    "-fx-cursor: hand;"
    );
    Label resultLabel = new Label();
    

        
        // Button Logic
        createBtn.setOnAction(e -> {
            String user = nameField.getText();
            String email = emailField.getText();
            String pass = passwordField.getText();
            String confirm = confirmField.getText();

            if (user.isEmpty() || email.isEmpty() || pass.isEmpty() || confirm.isEmpty()) {
                message.setText("All fields are required.");
                message.setTextFill(Color.RED);
            } else if (!pass.equals(confirm)) {
                message.setText("Passwords do not match.");
                message.setTextFill(Color.RED);
            } else {
                message.setText("Registration successful!");
                message.setTextFill(Color.GREEN);

                NotificationStore.addSeekerNotification(user);

                profilepageP pg = new profilepageP(user, email);
                try{
                    pg.start(rStage);

                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
            boolean success = authController.signUpWithEmailAndPassword(email,pass);
            resultLabel.setText(success?"SignUp successful!!":"signUp failed!!");
        });

        // Back to login
        Label signinPrompt = new Label("Have an account?");
        Hyperlink backLink = new Hyperlink("Sign in");
        backLink.setTextFill(Color.GRAY);
        backLink.setStyle("-fx-text-fill: #4CAF50; -fx-underline: true;");
        backLink.setBorder(Border.EMPTY);
        backLink.setPadding(Insets.EMPTY);
        backLink.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                prv.run();
            }
        });
        HBox signinBox = new HBox(5, signinPrompt, backLink);
        signinBox.setAlignment(Pos.CENTER);


        // Form box with shadow
        VBox formBox = new VBox(10, logo, signupLabel, nameField, emailField, passwordField, confirmField,forgotLink, createBtn, message, signinBox);
        formBox.setAlignment(Pos.CENTER);
    // formBox.setPadding(new Insets(40));
    formBox.setPrefHeight(200);
    formBox.setPrefWidth(500);
    formBox.setStyle(
    "-fx-background-color: white;" +
    "-fx-background-radius: 15px 0 0 15px;" +
    "-fx-border-radius: 15px 0 0 15px;" +
    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 10, 0, 0, 2);"
);
        
        

        // Side image (tall, matching form)
        ImageView sideImage = new ImageView(new Image("Assets\\Images\\sideimage.jpg"));
        sideImage.setFitHeight(500);
        sideImage.setFitHeight(480);
    sideImage.setFitWidth(400);
    sideImage.setSmooth(true);

    StackPane imagePane = new StackPane(sideImage);
    imagePane.setStyle(
    "-fx-background-color: #FFF6F1;" +
    "-fx-border-color: #eee;" +
    "-fx-border-width: 0 0 0 1;" +
    "-fx-border-style: solid;"
);

    // imagePane.setPrefSize(400, 500);   // Same size as image
    imagePane.setMaxSize(400, 500);

        // Main HBox for side-by-side
        HBox hboxContent = new HBox(formBox, imagePane);
    hboxContent.setStyle(
        "-fx-background-radius: 20px; " +
        "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.23), 12, 0.5, 0, 2);"
    );
    hboxContent.setMaxHeight(400);
    hboxContent.setMaxWidth(800);

       
       


        // Background with image
        VBox outer = new VBox(hboxContent);
        outer.setAlignment(Pos.CENTER);   
        outer.setStyle(
            "-fx-background-image: url('/Assets/Images/1.png');" +
            "-fx-background-size: cover;" +
            "-fx-background-position: center center;"
        );

        return outer;
    }
}
