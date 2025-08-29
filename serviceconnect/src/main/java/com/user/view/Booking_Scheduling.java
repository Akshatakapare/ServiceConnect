package com.user.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.user.model.Booking;
import com.user.model.NotificationStore;

import javafx.beans.InvalidationListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Booking_Scheduling {

     private static final String PROJECT_ID = "serviceconnect-eb8b6";
     private static final String API_KEY="AIzaSyAlJon0EBJRYetQUsc2ySdnt3pm41U4KMU";

    public static void show(Stage stage, String providerName, String skills) {
        BorderPane rootLayout = new BorderPane();
        

        //Button backBtn = new Button("← Back to Providers");
        //backBtn.setOnAction(e -> ProviderDetailView.showProviderDetail(stage, providerName));
        ImageView backgroundImage = new ImageView(
        new Image("Assets\\Images\\yoga.jpg") // Replace with your background image path
    );
    backgroundImage.setFitWidth(1540); // match your scene size
    backgroundImage.setFitHeight(795);
    backgroundImage.setPreserveRatio(false);
    backgroundImage.setOpacity(0.9); // light overlay
    backgroundImage.setSmooth(true);
    backgroundImage.setEffect(new BoxBlur(8, 5, 9));
    // ImageView logoView = new ImageView(new Image("Assets\\Images\\Logo.png"));
    //     logoView.setFitWidth(40);
    //     logoView.setPreserveRatio(true);

    //     Label appName = new Label("ServiceConnect");
    //     appName.setFont(Font.font("Poppins", FontWeight.BOLD, 24));
    //     appName.setTextFill(Color.WHITE);

    //     HBox topBar = new HBox(10, logoView, appName);
    //     topBar.setPadding(new Insets(10));
    //     topBar.setAlignment(Pos.CENTER_LEFT);
    //     topBar.setStyle("-fx-background-color: #1087a4ff;");
    //     rootLayout.setTop(topBar);

        // Sidebar with toggle
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

       
        rootLayout.setLeft(leftPanel);
       
        // ---------- Booking Form Section ----------
        Label title = new Label("Booking & Scheduling");
        title.setFont(new Font(32));
        title.setStyle("-fx-text-fill:#333333;");
        title.setAlignment(Pos.CENTER);

        Label providerTitle = new Label("Provider Details");
        providerTitle.setFont(new Font(20));
        Label providerNameLabel = new Label("Name: " + providerName);
        providerNameLabel.setFont(new Font(20));
        Label providerSkillLabel = new Label("Skill: " + skills);
        providerSkillLabel.setFont(new Font(20));
        Label providerRatingLabel = new Label("Rating: 4.8");
        providerRatingLabel.setFont(new Font(20));
        //================================================

        Label seekerNameLabel = new Label("Seeker's Name:");
        seekerNameLabel.setFont(new Font(15));
        TextField seekerNameField = new TextField();
        seekerNameField.setPromptText("Enter your name");
        seekerNameField.setFocusTraversable(false);
        seekerNameField.setMaxSize(300, 200);
//=======================================================

        Label addressLabel = new Label("Seeker's Address:");
        addressLabel.setFont(new Font(15));
        TextField seekerAddress = new TextField();
        seekerAddress.setPromptText("Enter full address");
        seekerAddress.setFocusTraversable(false);
        seekerAddress.setMaxSize(300, 200);

        Label dateLabel = new Label("Select date for meeting:");
        dateLabel.setFont(new Font(15));

        DatePicker calendar = new DatePicker(LocalDate.now());

        Label timeLabel = new Label("Select time from available:");
        timeLabel.setFont(new Font(15));

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("10:00 AM", "12:00 PM", "3:00 PM", "5:00 PM");

        Label durationLabel = new Label("Select Duration:");
        durationLabel.setFont(new Font(15));

        ComboBox<String> durationBox = new ComboBox<>();
        durationBox.getItems().addAll("30 minutes", "1 hour", "1.5 hours", "2 hours");

        Label noteLabel = new Label("Add Note for provider:");
        noteLabel.setFont(new Font(15));

        TextField note = new TextField();
        note.setPromptText("Add note..");
        note.setMaxSize(300, 200);

        Label summaryTitle = new Label("Booking Summary:");
        summaryTitle.setFont(new Font(15));

        Label summaryLabel = new Label();
        summaryLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333;");

        Button confirmbtn = new Button("Confirm");
//============================================================
// confirmbtn.setOnAction(e -> {
//     String address = seekerAddress.getText();
//     String selectedDate = calendar.getValue().toString();
//     String selectedTime = comboBox.getValue();
//     String selectedDuration = durationBox.getValue();
//     String noteText = note.getText();

//     // Optional summary display before saving
//     summaryLabel.setText(
//         "Provider: " + providerName + "\n" +
//         "Skill: " + skills + "\n" +
//         "Date: " + selectedDate + "\n" +
//         "Time: " + selectedTime + "\n" +
//         "Duration: " + selectedDuration + "\n" +
//         "Note: " + noteText
//     );
//     String seekerName = seekerNameField.getText();

//     // Call the method to save booking
//     saveBookingToFirestore(providerName, skills, seekerName, address, selectedDate,
//                        selectedTime, selectedDuration, noteText, summaryLabel);


//             PaymentUI paymentUI = new PaymentUI(providerName, skills);
//             paymentUI.start(stage);
// });
confirmbtn.setOnAction(e -> {
    String address = seekerAddress.getText();
    String selectedDate = calendar.getValue().toString();
    String selectedTime = comboBox.getValue();
    String selectedDuration = durationBox.getValue();
    String noteText = note.getText();
    String seekerName = seekerNameField.getText();

    // Optional summary display
    summaryLabel.setText(
        "Provider: " + providerName + "\n" +
        "Skill: " + skills + "\n" +
        "Date: " + selectedDate + "\n" +
        "Time: " + selectedTime + "\n" +
        "Duration: " + selectedDuration + "\n" +
        "Note: " + noteText
    );

    // Save to Firestore and then notify
    saveBookingToFirestore(providerName, skills, seekerName, address, selectedDate,
        selectedTime, selectedDuration, noteText, summaryLabel);

    // -------------- FIRESTORE SAVE CALLBACK or INSIDE saveBookingToFirestore ----------------
    // After Firestore save success (you can place this logic inside callback if async)

    // Create the notification message
    String fullMessage = "Booking Request:\nSeeker: " + seekerName +
                         "\nSeeker Address: " + address +
                         "\nSkill: " + skills +
                         "\nDate: " + selectedDate +
                         "\nTime: " + selectedTime +
                         "\nDuration: " + selectedDuration +
                         "\nNote: " + noteText;

    // Add the notification to in-app store
    NotificationStore.providerNotifications.add(new com.user.model.Notification(fullMessage));

    // Optional: Also add booking to pending list for provider UI
    Booking booking = new Booking(
        seekerName,          // seeker name
        providerName,        // provider name
        skills,              // service
        LocalDateTime.now(), // booking creation time
        address,
        noteText,
        selectedTime,
        selectedDuration
    );
    booking.setStatus("pending");

    NotificationPageP.providerBookings.add(booking);

    // Popup for confirmation
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Booking Request Sent");
    alert.setHeaderText(null);
    alert.setContentText("Your booking request has been sent to provider for approval.");
    alert.showAndWait();

    // Navigate to payment UI
    PaymentUI paymentUI = new PaymentUI(providerName, skills);
    paymentUI.start(stage);
});

confirmbtn.setStyle("-fx-background-color:#4A90E2; -fx-text-fill: white;");

//============================================================

        
        Button cancelbtn = new Button("Cancel");
        cancelbtn.setOnAction(e->{
            seekerAddress.clear();
            calendar.cancelEdit();
            comboBox.setValue(null);
            durationBox.setValue(null);
            note.clear();
        });
        // confirmbtn.setStyle("-fx-background-color:#4A90E2; -fx-text-fill: white;");
        // confirmbtn.setOnAction(e -> {
        //     PaymentUI paymentUI = new PaymentUI(providerName, skills);
        //     paymentUI.start(stage);
        // });

        cancelbtn.setStyle("-fx-background-color:#BDBDBD; -fx-text-fill: white;");
        Button modifyBtn = new Button("Modify Booking");
        modifyBtn.setStyle("-fx-background-color: #0f1d26ff; -fx-text-fill: white;");
        modifyBtn.setOnAction(e -> {
            ModifyBooking.show(stage, providerName, skills); // pass values
        });
        HBox buttonBox = new HBox(10, confirmbtn, cancelbtn,modifyBtn);
        buttonBox.setAlignment(Pos.CENTER);

        // Dynamic Summary Updater
        InvalidationListener summaryUpdater = obs -> {
            String date = calendar.getValue() != null ? calendar.getValue().toString() : "Not selected";
            String time = comboBox.getValue() != null ? comboBox.getValue() : "Not selected";
            String duration = durationBox.getValue() != null ? durationBox.getValue() : "Not selected";
            String noteField = note.getText();
            String address = seekerAddress.getText();
            String total = "N/A";

            switch (duration) {
                case "30 minutes": total = "₹150"; break;
                case "1 hour": total = "₹300"; break;
                case "1.5 hours": total = "₹450"; break;
                case "2 hours": total = "₹600"; break;
            }

            summaryLabel.setText("Provider: " + providerName +
                    "\nDate: " + date +
                    "\nTime: " + time +
                    "\nDuration: " + duration +
                    "\nNote: " + (noteField.isEmpty() ? "None" : noteField) +
                    "\nAddress: " + (address.isEmpty() ? "Not provided" : address) +
                    "\nTotal: " + total);
        };

        calendar.valueProperty().addListener(summaryUpdater);
        comboBox.valueProperty().addListener(summaryUpdater);
        durationBox.valueProperty().addListener(summaryUpdater);
        note.textProperty().addListener(summaryUpdater);
        seekerAddress.textProperty().addListener(summaryUpdater);

        VBox prBox = new VBox(10,providerTitle, providerNameLabel, providerSkillLabel, providerRatingLabel);
        prBox.setAlignment(Pos.CENTER);
        prBox.setMaxSize(350, 250);

        VBox bookingBox = new VBox(20);
        bookingBox.getChildren().addAll(
                prBox, seekerNameLabel, seekerNameField,
                addressLabel, seekerAddress,
                dateLabel, calendar,
                timeLabel, comboBox,
                durationLabel, durationBox,
                noteLabel, note,
                summaryTitle, summaryLabel,
                buttonBox
        );
        //bookingBox.setAlignment(Pos.BOTTOM_CENTER);
        bookingBox.setPadding(new Insets(50));
        // bookingBox.setPrefWidth(500);
        // bookingBox.setPrefHeight(800);
        bookingBox.setFillWidth(true);
        bookingBox.setStyle(
                        "-fx-background-radius: 20; -fx-background-color: rgba(255,255,255,0.3);"+
                        "-fx-border-width: 2;" +
                        "-fx-border-radius: 15;" +
                        "-fx-background-radius: 15;");

        //VBox vb=new VBox(20,title,bookingBox);

        ScrollPane scrollPane = new ScrollPane(bookingBox);
        scrollPane.setMaxSize(500,800);
         scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);
        scrollPane.setPadding(new Insets(40));
        scrollPane.setStyle("-fx-background-radius: 20; -fx-background-color: rgba(255,255,255,0.3);");
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
    }

    // ---------- Sidebar for Booking Page ----------
    private static VBox createSidebar(Stage primaryStage, BorderPane rootLayout ) {
    String[] menuItems = {"Dashboard", "Profile", "Complaint", "Help", "Logout"};
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
                        
                         try {
                    // new complaintsubmitpageP().start(new Stage());
                    new complaintsubmitpageP().start(primaryStage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
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

public static void saveBookingToFirestore(String providerName, String skills, String seekerName, String seekerAddress,
                                          String date, String time, String duration, String noteText,
                                          Label summaryLabel)

 {
    try {
        // 1. Build JSON structure for Firestore
        JsonObject fields = new JsonObject();

        JsonObject providerNameObj = new JsonObject();
        providerNameObj.addProperty("stringValue", providerName);
        fields.add("providerName", providerNameObj);

        JsonObject skillsObj = new JsonObject();
        skillsObj.addProperty("stringValue", skills);
        fields.add("skills", skillsObj);

//         JsonObject ratingObj = new JsonObject();
// ratingObj.addProperty("numberValue", 4.8);  // Firestore expects "numberValue" for numbers
// fields.add("rating", ratingObj);


        JsonObject seekerAddressObj = new JsonObject();
        seekerAddressObj.addProperty("stringValue", seekerAddress);
        fields.add("seekerAddress", seekerAddressObj);

        JsonObject dateObj = new JsonObject();
        dateObj.addProperty("stringValue", date);
        fields.add("date", dateObj);

        JsonObject timeObj = new JsonObject();
        timeObj.addProperty("stringValue", time);
        fields.add("time", timeObj);

        JsonObject durationObj = new JsonObject();
        durationObj.addProperty("stringValue", duration);
        fields.add("duration", durationObj);

        JsonObject noteObj = new JsonObject();
        noteObj.addProperty("stringValue", noteText);
        fields.add("note", noteObj);

        JsonObject seekerNameObj = new JsonObject();
        seekerNameObj.addProperty("stringValue", seekerName);
        fields.add("seekerName", seekerNameObj);


        JsonObject booking = new JsonObject();
        booking.add("fields", fields);

        // 2. Set endpoint (use your API key and project ID as constants)
        String endpoint = String.format(
            "https://firestore.googleapis.com/v1/projects/%s/databases/(default)/documents/bookings?key=%s",
            PROJECT_ID, API_KEY);

        // 3. Create connection
        URL url = new URL(endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

        // 4. Send data
        try (OutputStream os = conn.getOutputStream()) {
            os.write(booking.toString().getBytes(StandardCharsets.UTF_8));
        }

        // 5. Handle response
        int responseCode = conn.getResponseCode();
        if (responseCode == 200 || responseCode == 201) {
            summaryLabel.setText("✅ Booking confirmed and saved successfully!");
        } else {
            InputStream errorStream = conn.getErrorStream();
            String errorResponse = new String(errorStream.readAllBytes(), StandardCharsets.UTF_8);
            summaryLabel.setText("❌ Firestore Error (HTTP " + responseCode + "): " + errorResponse);
        }

        conn.disconnect();

    } catch (Exception e) {
        e.printStackTrace();
        summaryLabel.setText("❌ Error: " + e.getMessage());
    }
}


}