package com.user.view;

import com.user.Controller.FirebaseAuthenticationController;
import com.user.model.loginUser;
import com.user.model.profiledata;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class loginPageP extends Application {

    public static String email;
    public static String password1;
    
    

    FirebaseAuthenticationController authController = new FirebaseAuthenticationController();
    Stage primaryStage;
    Scene regScene, loginScene;

 @Override
public void start(Stage mystage) {
    String adminemail = "admin123@gmail.com";
    String adminpass = "1234";

    // ---------- Background Image ----------
    ImageView backgroundImage = new ImageView(
        new Image("/Assets/Images/1.png") // Replace with your background image path
    );
    backgroundImage.setFitWidth(1000); // match your scene size
    backgroundImage.setFitHeight(750);
    backgroundImage.setPreserveRatio(false);
    backgroundImage.setOpacity(0.9); // light overlay
    backgroundImage.setSmooth(true);

    // ---------- Logo ----------
    ImageView logo = new ImageView(new Image("Assets\\Images\\oldpr.jpg"));
    logo.setFitWidth(80);
    logo.setFitHeight(80);

    // ---------- Labels and Fields ----------
    Label loginLabel = new Label("Welcome Back!");
    loginLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));
    loginLabel.setTextFill(Color.web("#2C3E50"));

    TextField emailField = new TextField();
    emailField.setPromptText("Username");
    emailField.setMaxWidth(250);
    emailField.setStyle(
    "-fx-background-color: #FAFAFA" +
    "-fx-border-color: #ccc" +
    "-fx-border-radius: 6px" +
    "-fx-background-radius: 6px" +
    "-fx-padding: 8 12" +
    "-fx-font-size: 13px"
);

    PasswordField passwordField = new PasswordField();
    passwordField.setPromptText("Password");
    passwordField.setMaxWidth(250);
    passwordField.setStyle(
    "-fx-background-color: #FAFAFA;" +
    "-fx-border-color: #ccc;" +
    "-fx-border-radius: 6px;" +
    "-fx-background-radius: 6px;" +
    "-fx-padding: 8 12;" +
    "-fx-font-size: 13px;"
);


    // CheckBox remember = new CheckBox("Remember me");

    Label messageLabel = new Label("");
    messageLabel.setTextFill(Color.RED);

    // ---------- Login Button ----------
    Button loginBtn = new Button("LOGIN");
    loginBtn.setMinWidth(250);
    loginBtn.setFont(Font.font(14));
    loginBtn.setStyle(
    "-fx-background-color: linear-gradient(to right, #4CAF50, #388E3C);" +
    "-fx-text-fill: white;" +
    "-fx-font-weight: bold;" +
    "-fx-background-radius: 8px;" +
    "-fx-padding: 10 0;" +
    "-fx-cursor: hand;"
    );
    Label resultLabel= new Label();
    
loginBtn.setOnAction(event ->{

        String email=emailField.getText();
        String password=passwordField.getText();
        boolean success=authController.signInWithEmailAndPassword(email,password);
        resultLabel.setText(success ? "Login successful!" : "Login failed");

        email = emailField.getText();
        password1 = passwordField.getText();


            if (success) {
                messageLabel.setText("");
                loginPageP.email = email; 
                profiledata.email = email;  // or loginPageP.email if that's where it's stored

                try {
                    new dashboardpageP().launchdashboard(primaryStage,loginUser.userName);
                } catch (Exception ex) {
                ex.printStackTrace();
                }
            } else {
                messageLabel.setText("Invalid Username or Password.");
            }
            profiledata.email = email;
        
    });


    // ---------- Signup Link ----------
    Label signupPrompt = new Label("Don't have an account?");
    Hyperlink signupLink = new Hyperlink("Register Now");
    signupPrompt.setTextFill(Color.GRAY);
    signupLink.setStyle("-fx-text-fill: #4CAF50; -fx-underline: true;");
    signupLink.setBorder(Border.EMPTY);
    signupLink.setOnAction((ActionEvent arg0) -> {
        initializesignup();
        primaryStage.setScene(regScene);
    });

    HBox signupBox = new HBox(signupPrompt, signupLink);
    signupBox.setAlignment(Pos.CENTER);
    signupBox.setSpacing(5);

    // ---------- Form Container ----------
    VBox formBox = new VBox(10, logo, loginLabel, emailField, passwordField, loginBtn, messageLabel, signupBox,resultLabel);
    formBox.setAlignment(Pos.CENTER);
    // formBox.setPadding(new Insets(40));
    formBox.setPrefHeight(400);
    formBox.setPrefWidth(300);
    formBox.setStyle(
    "-fx-background-color: white;" +
    "-fx-background-radius: 15px 0 0 15px;" +
    "-fx-border-radius: 15px 0 0 15px;" +
    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 10, 0, 0, 2);"
);


    // ---------- Right Image ----------
    ImageView sideImage = new ImageView(
        new Image("Assets\\Images\\sideimage.jpg"));
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
    // dropshadow effect

    DropShadow shadow = new DropShadow();
    shadow.setRadius(10);
    shadow.setOffsetX(0);
    shadow.setOffsetY(4);
    shadow.setColor(Color.rgb(0, 0, 0, 0.2));  // soft shadow

     

    // ---------- HBox for Form + Image ----------
    HBox hboxContent = new HBox(formBox, imagePane);
    hboxContent.setStyle(
        "-fx-background-radius: 20px; " +
        "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.23), 12, 0.5, 0, 2);"
    );
    
    hboxContent.setEffect(shadow);
    hboxContent.setMaxHeight(400);
    hboxContent.setMaxWidth(800);
    hboxContent.setAlignment(Pos.CENTER);
    HBox.setHgrow(formBox, Priority.ALWAYS);
    HBox.setHgrow(imagePane, Priority.ALWAYS);

    // ---------- StackPane with background image ----------
    StackPane root = new StackPane();
    
    root.getChildren().addAll(backgroundImage, hboxContent);
    StackPane.setAlignment(hboxContent, Pos.CENTER);
    backgroundImage.fitWidthProperty().bind(root.widthProperty());
    backgroundImage.fitHeightProperty().bind(root.heightProperty());

    Scene myScene = new Scene(root, 1540, 795);
    mystage.setScene(myScene);
    mystage.setTitle("Login Page");
    primaryStage = mystage;
    loginScene = myScene;
    mystage.show();
}

    private void initializesignup() {
        RegisterPageP res2 = new RegisterPageP();
        res2.setrStage(primaryStage);
        regScene = new Scene(res2.createsignupScene(this::handleBackButton), 1540,795);
        res2.setrScene(regScene);
    }

    private void handleBackButton() {
        primaryStage.setScene(loginScene);
    }
} 




