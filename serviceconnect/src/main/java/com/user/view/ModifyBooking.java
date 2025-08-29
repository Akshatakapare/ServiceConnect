package com.user.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ModifyBooking {

     public static void show(Stage stage, String providerName, String skills) {
        BorderPane rootLayout = new BorderPane();

        ImageView backgroundImage = new ImageView(
        new Image("Assets\\Images\\yoga.jpg") // Replace with your background image path
    );
    backgroundImage.setFitWidth(1540); // match your scene size
    backgroundImage.setFitHeight(795);
    backgroundImage.setPreserveRatio(false);
    backgroundImage.setOpacity(0.9); // light overlay
    backgroundImage.setSmooth(true);
    backgroundImage.setEffect(new BoxBlur(8, 5, 9));

     // === App Bar ===
        // ImageView logoView = new ImageView(new Image("Assets\\Images\\Logo.png"));
        // logoView.setFitWidth(40);
        // logoView.setPreserveRatio(true);

        // Label appName = new Label("ServiceConnect");
        // appName.setFont(Font.font("Poppins", FontWeight.BOLD, 24));
        // appName.setTextFill(Color.WHITE);

        // HBox topBar = new HBox(10, logoView, appName);
        // topBar.setPadding(new Insets(10));
        // topBar.setAlignment(Pos.CENTER_LEFT);
        // topBar.setStyle("-fx-background-color: #1087a4ff;");
        // // Sidebar with toggle
        // VBox sidebar = createSidebar(stage, skills, providerName);
        // rootLayout.setLeft(sidebar);
        // rootLayout.setTop(topBar);


        VBox sidebar = createSidebar(stage, rootLayout);

        Button toggleBtn = new Button("☰");
        toggleBtn.setFont(Font.font(16));
        toggleBtn.setStyle("-fx-background-color: white; -fx-text-fill: #4B2E2E; -fx-font-weight: bold; -fx-background-radius: 8; -fx-cursor: hand; -fx-padding: 6 14; -fx-border-color: #4B2E2E; -fx-border-radius: 8; -fx-border-width: 1;");

        toggleBtn.setOnMouseEntered(e -> toggleBtn.setStyle("-fx-background-color: #F5F5F5; -fx-text-fill: #4B2E2E; -fx-font-weight: bold; -fx-background-radius: 8; -fx-cursor: hand; -fx-padding: 6 14; -fx-border-color: #4B2E2E; -fx-border-radius: 8; -fx-border-width: 1;"));
        toggleBtn.setOnMouseExited(e -> toggleBtn.setStyle("-fx-background-color: white; -fx-text-fill: #4B2E2E; -fx-font-weight: bold; -fx-background-radius: 8; -fx-cursor: hand; -fx-padding: 6 14; -fx-border-color: #4B2E2E; -fx-border-radius: 8; -fx-border-width: 1;"));

        toggleBtn.setOnAction(e -> {
            boolean isVisible = sidebar.isVisible();
            sidebar.setVisible(!isVisible);
            sidebar.setManaged(!isVisible);
        });

        VBox toggleBox = new VBox(toggleBtn);
        toggleBox.setPadding(new Insets(10));
        toggleBox.setAlignment(Pos.TOP_CENTER);
        toggleBox.setStyle("-fx-background-color: linear-gradient(to bottom, rgba(161,136,127,0.85), rgba(93,64,55,0.85)); -fx-border-color: rgba(62,39,35,0.8); -fx-border-width: 0 1 0 0;");

        HBox leftPanel = new HBox(toggleBox, sidebar);
        rootLayout.setLeft(leftPanel);

        Button backbtn = new Button("Back");
        backbtn.setFocusTraversable(false);
        backbtn.setStyle("-fx-background-color:#4a90e2ff;"+" -fx-text-fill: white;");
        backbtn.setOnAction(e -> {
              Booking_Scheduling.show(stage, providerName, skills);
        });

        Label title = new Label("Modify Your Booking");
        title.setFont(new Font(40));
        title.setStyle("-fx-text-fill:#333333;");
        title.setAlignment(Pos.CENTER);

        Label providerTitle = new Label("Provider Details");
        providerTitle.setFont(new Font(20));
        Label providerNameLabel = new Label("Name: Mrs. Meena Sharma");
        providerNameLabel.setFont(new Font(15));
        Label providerSkill = new Label("Skill: Home Cooking");
        providerSkill.setFont(new Font(15));
        Label providerRating = new Label("Rating: 4.8 ");
        providerRating.setFont(new Font(15));

        Label addressLabel = new Label("Change Address:");
        addressLabel.setFont(new Font(15));
        TextField seekerAddress = new TextField();
        seekerAddress.setPromptText("Enter full address");
        seekerAddress.setFocusTraversable(false);
        seekerAddress.setMaxSize(300,200);

        Label dateLabel = new Label("Change date for meeting:");
        dateLabel.setFont(new Font(15));
        DatePicker calender = new DatePicker(LocalDate.now());

    
        Label timeLabel = new Label("Change time from available:");
        timeLabel.setFont(new Font(15));
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("10:00 AM", "12:00 PM", "3:00 PM", "5:00 PM");

    
        Label durationLabel = new Label("Change Duration:");
        durationLabel.setFont(new Font(15));
        ComboBox<String> durationBox = new ComboBox<>();
        durationBox.getItems().addAll("30 minutes", "1 hour", "1.5 hours", "2 hours");

        Label noteLabel = new Label("Additional Note for provider:");
        noteLabel.setFont(new Font(15));
        TextField note = new TextField();
        note.setPromptText("Add note..");
        note.setMaxSize(300,200);

        Button savebtn = new Button("Save Changes");
        savebtn.setOnAction(e -> {
            PaymentUI paymentUI = new PaymentUI(providerName, skills);
            paymentUI.start(stage);
        });

        Button cancelbtn = new Button("Cancel");
        cancelbtn.setOnAction(e->{
            seekerAddress.clear();
            calender.cancelEdit();
            comboBox.setValue(null);
            durationBox.setValue(null);
            note.clear();
            
        });
        savebtn.setStyle("-fx-background-color:#4A90E2; -fx-text-fill: white;");
        cancelbtn.setStyle("-fx-background-color:#BDBDBD; -fx-text-fill: white;");
        HBox buttonBox = new HBox(10, savebtn, cancelbtn,backbtn);
        buttonBox.setAlignment(Pos.CENTER);

        VBox prBox=new VBox(providerTitle,providerNameLabel,providerSkill,providerRating);
        //prBox.setStyle("-fx-border-color:black;"+"-fx-border-width:2" +"-fx-border-radius: 15;"+"-fx-font-weight: bold;");
        prBox.setAlignment(Pos.CENTER);
        prBox.setMaxSize(250,150);

    
        VBox bookingBox = new VBox(20);
        bookingBox.getChildren().addAll(
        prBox,addressLabel,seekerAddress,
        dateLabel, calender,
        timeLabel, comboBox,
        durationLabel,durationBox,
        noteLabel, note,
        buttonBox
        );
        bookingBox.setAlignment(Pos.CENTER);
        bookingBox.setPadding(new Insets(25));
        bookingBox.setPrefWidth(500);
        bookingBox.setPrefHeight(800);
        bookingBox.setStyle(
                        "-fx-background-radius: 20; -fx-background-color: rgba(255,255,255,0.3);"+
                        "-fx-border-width: 2;" +
                        "-fx-border-radius: 15;" +
                        "-fx-background-radius: 15;");
    //    VBox vb=new VBox(20,title,bookingBox);
    

         ScrollPane scrollPane = new ScrollPane();
         scrollPane.setContent(bookingBox);         // formBox is your main VBox
       
         scrollPane.setPannable(true);           // enable drag scroll
         scrollPane.setStyle("-fx-background-radius: 20; -fx-background-color: rgba(255,255,255,0.3);");
         scrollPane.setMaxSize(500,800);
         scrollPane.setPadding(new Insets(40));
         scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // no horizontal bar
         scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // no horizontal bar
        rootLayout.setCenter(scrollPane);
       rootLayout.setStyle("-fx-background-radius: 20; -fx-background-color: rgba(255,255,255,0.3);");

        StackPane root = new StackPane();
    
    root.getChildren().addAll(backgroundImage, rootLayout);
    StackPane.setAlignment(rootLayout, Pos.CENTER);
    backgroundImage.fitWidthProperty().bind(root.widthProperty());
    backgroundImage.fitHeightProperty().bind(root.heightProperty());

    
        Scene scene = new Scene(root, 1540, 795);
        stage.setScene(scene);
        stage.setTitle("Service Modifying");
        stage.show();

    

    }
    // ---------- Sidebar for Booking Page ----------
    private static VBox createSidebar(Stage primaryStage, BorderPane rootLayout ) {
    String[] menuItems = {"Dashboard", "Profile","Complaint", "Help", "Logout"};
    VBox sidebar = new VBox(20);
    sidebar.setPadding(new Insets(20));
    sidebar.setStyle("""
        -fx-background-color: linear-gradient(to bottom, rgba(161,136,127,0.85), rgba(93,64,55,0.85));
        -fx-border-color: rgba(62,39,35,0.8);
        -fx-border-width: 0 1 0 0;
        -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0.3, 2, 2);
    """);
    sidebar.setPrefWidth(180);

  

    // Profile Image
    ImageView profileImgView;
    try {
        Image profileImg = new Image(ServiceListView.class.getResourceAsStream("/Assets//Images//akshata.png"));
        profileImgView = new ImageView(profileImg);
    } catch (Exception e) {
        profileImgView = new ImageView();
        System.out.println("Profile image not found!");
    }
    profileImgView.setFitWidth(80);
    profileImgView.setFitHeight(80);
    profileImgView.setClip(new Circle(40, 40, 40));

    VBox profileBox = new VBox(profileImgView);
    profileBox.setAlignment(Pos.CENTER);

sidebar.getChildren().add(profileBox);

    for (String item : menuItems) {
        Label label = new Label(item);
        label.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        label.setTextFill(Color.WHITE);
        label.setStyle("""
            -fx-background-color: transparent;
            -fx-padding: 8 16;
            -fx-background-radius: 10;
            -fx-cursor: hand;
        """);

        // Hover effects
        label.setOnMouseEntered(e -> label.setStyle("""
            -fx-background-color: rgba(255, 255, 255, 0.25);
            -fx-text-fill: #3E2723;
            -fx-background-radius: 10;
            -fx-padding: 8 16;
            -fx-cursor: hand;
            -fx-font-weight: bold;
        """));

        label.setOnMouseExited(e -> label.setStyle("""
            -fx-background-color: transparent;
            -fx-text-fill: white;
            -fx-background-radius: 10;
            -fx-padding: 8 16;
            -fx-cursor: hand;
        """));

        //String username = "Guest";
        label.setOnMouseClicked(event -> {
            switch (item) {
                case "Dashboard" -> new DashboardView().showDashboard(primaryStage,"seeker");
                case "Profile" -> {
                    // Optional: showProfileInfo() or EditProfile
                }
                case "Complaint"->{
                        new complaintsubmitpage().start(primaryStage);
                    }
                    case "Help"->{
                        new helppageS().launchHelp(primaryStage);
                    }
                case "Logout" -> new LandingPage().start(primaryStage);
                default -> System.out.println(item + " clicked");
            }
        });

        sidebar.getChildren().add(label);
    }

    return sidebar;
}
}