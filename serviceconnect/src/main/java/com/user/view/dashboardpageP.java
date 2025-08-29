package com.user.view;

import org.json.*;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import com.user.Controller.logincontroller;
import com.user.model.Rating;
import com.user.model.profiledata;

import javafx.geometry.*;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;



public class dashboardpageP {

    static final String PROJECT_ID="serviceconnect-eb8b6";
    static final String API_KEY="AIzaSyAlJon0EBJRYetQUsc2ySdnt3pm41U4KMU";
    private String userInput="Ram";

    // public dashboardpageP() {
    //     this.userInput = username;
    // }

    public void launchdashboard(Stage stage,String userInput){


        VBox sidebarContent = new VBox(20);
        sidebarContent.setPadding(new Insets(20));
        sidebarContent.setPrefHeight(795);
        sidebarContent.setAlignment(Pos.TOP_CENTER);

        ImageView profilePic = new ImageView();
        profilePic.setFitWidth(130);
        profilePic.setFitHeight(130);
        profilePic.setPreserveRatio(true);


        if (profiledata.imagePath != null) {
            profilePic.setImage(new Image(profiledata.imagePath));
        } else {
            profilePic.setImage(new Image("Assets\\Images\\oldpr.jpg")); // fallback
        }


        Label providerName = new Label(userInput);
        providerName.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        providerName.setTextFill(Color.web("#333"));

        // Label providerEmail = new Label("samarthgaikwad0709@gmail.com");
        // providerEmail.setFont(Font.font("Arial", 11));
        // providerEmail.setTextFill(Color.GRAY);

        VBox profileBox = new VBox(6, profilePic, providerName);
        profileBox.setAlignment(Pos.CENTER);

        VBox sidebarItems = new VBox(10);
        sidebarItems.setAlignment(Pos.TOP_LEFT);
        

        

        sidebarItems.getChildren().addAll(
            createSidebarItem("🏠 Dashboard Overview", stage),
            createSidebarItem("👤 My Profile", stage),
            // createSidebarItem("📄 My Posts", stage),
            createSidebarItem("📅 My Appointments", stage),
            createSidebarItem("⭐ My Ratings & Reviews",stage),
            createSidebarItem("📱 Complaints / Feedback", stage)
        );

        sidebarContent.getChildren().addAll(profileBox, sidebarItems);
        sidebarContent.setStyle("-fx-background-color: #97c5ebff");


        ScrollPane sidebarScroll = new ScrollPane(sidebarContent);
        sidebarScroll.setFitToWidth(true);
        sidebarScroll.setStyle("-fx-background-color: linear-gradient(to bottom, #fff, #ecd6dcff;");
        sidebarScroll.setPrefWidth(240);
        sidebarScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        getProviderProfileFromFirestore();
       Label welcomeLabel = new Label("👋 Welcome " + profiledata.name);

        welcomeLabel.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 24));
        welcomeLabel.setTextFill(Color.web("#333"));

        Button settingsBtn = new Button("⚙ Settings");
        settingsBtn.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        settingsBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: #2C3E50; -fx-cursor: hand;");

        settingsBtn.setStyle("""
    -fx-background-color: transparent;
    -fx-font-size: 18;
    -fx-cursor: hand;
""");

// Hover effect (on mouse enter)
settingsBtn.setOnMouseEntered(e -> {
    settingsBtn.setStyle("""
        -fx-background-color: #e0e0e0;
        -fx-font-size: 18;
        -fx-cursor: hand;
        -fx-background-radius: 10;
    """);
});

// Reset style (on mouse exit)
settingsBtn.setOnMouseExited(e -> {
    settingsBtn.setStyle("""
        -fx-background-color: transparent;
        -fx-font-size: 18;
        -fx-cursor: hand;
    """);
});

        Button notifBtn = new Button("🔔");
        notifBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        notifBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: #2C3E50; -fx-cursor: hand;");
        // Notification bell icon
// 🔔 Notification bell icon



        notifBtn.setOnAction(e -> {
        NotificationPageP notification = new NotificationPageP();
            try {
               notification.start((Stage) notifBtn.getScene().getWindow());
            } catch (Exception ex) {
                ex.printStackTrace();
        }
});

        notifBtn.setStyle("""
             -fx-background-color: transparent;
             -fx-font-size: 18;
             -fx-cursor: hand;
             """);

// Hover effect (on mouse enter)
        notifBtn.setOnMouseEntered(e -> {
        notifBtn.setStyle("""
             -fx-background-color: #e0e0e0;
             -fx-font-size: 18;
             -fx-cursor: hand;
             -fx-background-radius: 10;
              """);
});

// Reset style (on mouse exit)
            notifBtn.setOnMouseExited(e -> {
            notifBtn.setStyle("""
                -fx-background-color: transparent;
                -fx-font-size: 18;
               -fx-cursor: hand;
               """);
});




        Button logoutBtn = new Button("⏻ Logout");
        logoutBtn.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        logoutBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: #2C3E50; -fx-cursor: hand;");

         logoutBtn.setStyle("""
            -fx-background-color: transparent;
            -fx-font-size: 18;
           -fx-cursor: hand;
           """);

// Hover effect (on mouse enter)
          logoutBtn.setOnMouseEntered(e -> {
          logoutBtn.setStyle("""
            -fx-background-color: #e0e0e0;
            -fx-font-size: 18;
            -fx-cursor: hand;
            -fx-background-radius: 10;
            """);
});

// Reset style (on mouse exit)
           logoutBtn.setOnMouseExited(e -> {
           logoutBtn.setStyle("""
               -fx-background-color: transparent;
               -fx-font-size: 18;
              -fx-cursor: hand;
              """);
});


        settingsBtn.setOnAction(e -> {
            ProviderSettingsPageP settingsPage = new ProviderSettingsPageP();
            try {
                settingsPage.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        logoutBtn.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Logout Confirmation");
            alert.setHeaderText("Are you sure you want to logout?");
            alert.setContentText("Click Yes to logout, or No to stay on the dashboard.");

            ButtonType yesButton = new ButtonType("Yes");
            ButtonType noButton = new ButtonType("No");
            alert.getButtonTypes().setAll(yesButton, noButton);

            alert.showAndWait().ifPresent(type -> {
                if (type == yesButton) {
                   LandingPage lp=new LandingPage();
                        lp.start(stage);
                }
                // No action needed for "No", the alert closes automatically
            });
        });

        HBox topRightButtons = new HBox(15, notifBtn, settingsBtn, logoutBtn);
        topRightButtons.setAlignment(Pos.TOP_RIGHT);
        topRightButtons.setPadding(new Insets(15, 30, 0, 0));
        topRightButtons.setStyle("-fx-background-color: #97c7efff");
        BorderPane.setAlignment(topRightButtons, Pos.TOP_RIGHT);

        BorderPane headerContent = new BorderPane();
        headerContent.setLeft(welcomeLabel);
        headerContent.setPadding(new Insets(12));
       headerContent.setStyle("-fx-background-color:  #fffffff);");

        HBox quickActions = new HBox(16);
        quickActions.setPadding(new Insets(10, 0, 8, 0));
        quickActions.setAlignment(Pos.CENTER_LEFT);

        // Button addPostBtn = createActionButton("📤 Add Post");


        // Button ratingsBtn = createActionButton("📊 Ratings");
        Button servicesBtn = createActionButton("🛠 My Services");

        servicesBtn.setOnMouseEntered(e -> servicesBtn.setStyle("-fx-background-color: linear-gradient(to right, #1976d2, #42a5f5); -fx-text-fill: white; -fx-background-radius: 10; -fx-padding: 10 20; -fx-font-size: 13px; -fx-cursor: hand;"));
        servicesBtn.setOnMouseExited(e -> servicesBtn.setStyle("-fx-background-color: linear-gradient(to right, #2196f3, #64b5f6); -fx-text-fill: white; -fx-background-radius: 10; -fx-padding: 10 20; -fx-font-size: 13px; -fx-cursor: hand;"));

        servicesBtn.setOnAction(e -> initializeMyServicesPage((Stage) servicesBtn.getScene().getWindow()));

        quickActions.getChildren().addAll( servicesBtn);

        HBox analyticsBox = new HBox(16);
        analyticsBox.setPadding(new Insets(5));
        analyticsBox.getChildren().addAll(
            createAnalyticsCard("Total Services", "15",stage),
            createAnalyticsCard("Upcoming Appointments", "6",stage),
            createAnalyticsCard("Avg Rating", "4.8 ⭐", stage)
        );

        VBox postSection = new VBox(12);
        postSection.setPadding(new Insets(15));
        postSection.getChildren().addAll(
            createClickablePostCard("📅 Upcoming Appointment", "Miss.Pooja - Tomorrow 10AM"),
            createClickablePostCard("🌟 New Review Received", "You got 5⭐ from Asha"),
            createClickablePostCard("💼 Service Update", "Now available for elder physiotherapy on Wed 5PM"),
            createClickablePostCard("📌 Quick Tip", "Update your availability regularly for better visibility."),
            createClickablePostCard("💬 New Message", "Chat initiated by Seeker Prashant."),
            createClickablePostCard("🧾 Complaint Resolved", "Feedback from patient Reshma has been addressed."),
            createClickablePostCard("📆 Reminder", "Health camp scheduled on Sunday 11AM."),
            createClickablePostCard("🛠 Profile Update", "Don't forget to update your certifications.")
        );

        VBox mainContent = new VBox();
        mainContent.setPadding(new Insets(20));
        mainContent.setSpacing(10);
        mainContent.getChildren().addAll(headerContent, quickActions, analyticsBox, postSection);

        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: linear-gradient(to bottom, #fff, #bce4fcff);");

        BorderPane root = new BorderPane();
        root.setLeft(sidebarScroll);
        root.setCenter(scrollPane);
        root.setTop(topRightButtons);

        Scene scene = new Scene(root, 1540, 795);
        stage.setTitle("Provider Dashboard");
        stage.setScene(scene);
        stage.show();
    }
    

   private Label createSidebarItem(String text, Stage stage) {
    Label label = new Label(text);
    label.setFont(Font.font("Arial", 13));
    label.setStyle("-fx-text-fill: #333; -fx-padding: 10 20; -fx-background-radius: 10;-fx-stroke:black");

    label.setOnMouseEntered(e -> label.setStyle("-fx-text-fill: #333; -fx-padding: 10 20; -fx-background-color: #bce4fcff; -fx-background-radius: 10;"));
    label.setOnMouseExited(e -> label.setStyle("-fx-text-fill: #333; -fx-padding: 10 20; -fx-background-radius: 10;"));
    Label resultLabel = new Label();

    // ✅ Only ONE mouse click event with all conditions
    label.setOnMouseClicked(e -> {
        switch (text) {
                case "👤 My Profile" -> {
                    myprofileP profilePage = new myprofileP();
                   Map<String, String> profileData = getProviderProfileFromFirestore();
                     // 🔹 Call to Firestore
                    // resultLabel.setText(result);              // 🔸 But this is not shown on the profile page
                    try {
                        profilePage.start(stage);             // 🔸 No data is passed to this page
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            case "📱 Complaints / Feedback" -> initializeComplaintPage(stage);
            case "📊 Ratings" -> initializeRatingPage(stage);
            case "📅 My Appointments" -> initializeAppointmentPage(stage);
            case "⭐ My Ratings & Reviews" -> {
                myratingpageP ratingPage = new myratingpageP();
                try {
                    ratingPage.show(stage,"Ram");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            case "🏠 Dashboard Overview" -> {
                dashboardpageP dashboard = new dashboardpageP();
                dashboard.launchdashboard(stage, userInput); // Pass username or other info
            }
            case "💬 Messages (Chats)" -> {
                String sender = logincontroller.getCurrentUserEmail(); // Logged-in provider email
                String receiver = "seeker@example.com"; // Replace this with actual selected seeker's email

                new ChatWindow(sender, receiver); // Opens the chat window using constructor
            }
        }
    });

    return label;
}


    

    private void initializeComplaintPage(Stage stage) {
       
                try {
                    new complaintsubmitpageP().start(new Stage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

    private void initializeRatingPage(Stage stage) {
        myratingpageP ratingPage = new myratingpageP();
        ratingPage.show(stage,"Ram");
    }

    private void initializeAppointmentPage(Stage stage) {
        myappointmentP appointmentPage = new myappointmentP();
        appointmentPage.start(stage);
    }

    private void initializeUpcomingAppointmentPage(Stage stage) {
        upappointmentP appointment = new upappointmentP();
        appointment.start(stage);
    }

    private void initializeMyServicesPage(Stage stage) {
        new myservicesP().start(stage);
    }

    private Button createActionButton(String text) {
        Button btn = new Button(text);
        btn.setStyle("-fx-background-color: linear-gradient(to right, #2196f3, #64b5f6); -fx-text-fill: white; -fx-background-radius: 10; -fx-padding: 10 20; -fx-font-size: 13px;");
        btn.setOnAction(e -> System.out.println("Action: " + text));
        return btn;
    }

    private VBox createClickablePostCard(String title, String description) {
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        Label descLabel = new Label(description);
        descLabel.setWrapText(true);
        descLabel.setFont(Font.font("Arial", 13));

        VBox card = new VBox(5, titleLabel, descLabel);
        card.setPadding(new Insets(12));
        card.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 12; -fx-border-radius: 12; -fx-border-color: #ddd; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.06), 5, 0, 0, 1);");
        card.setOnMouseClicked((MouseEvent e) -> System.out.println("Clicked on post: " + title));
        return card;
    }

    private VBox createAnalyticsCard(String title, String value, Stage stage) {
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", 13));
        titleLabel.setTextFill(Color.GRAY);

        Label valueLabel = new Label(value);
        valueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        valueLabel.setTextFill(Color.web("#222"));

        VBox card = new VBox(4, titleLabel, valueLabel);
        card.setPadding(new Insets(12));
        card.setAlignment(Pos.CENTER_LEFT);
        card.setStyle("-fx-background-color: linear-gradient(to right, #bbdefb, #e3f2fd); -fx-background-radius: 10;");

        if (title.equals("My Services")) {
            card.setCursor(Cursor.HAND);
            card.setOnMouseClicked(e -> initializeMyServicesPage(stage));
        }

        if (title.equals("Upcoming Appointments")) {
            card.setCursor(Cursor.HAND);
            card.setOnMouseClicked(e -> initializeUpcomingAppointmentPage(stage));
        }

        return card;
    }

    //     //--------------Read users---------
    // static String readUserFromFirestore(){
    //     String endpoint= String.format("https://firestore.googleapis.com/v1/projects/%s/databases/(default)/documents/users?key=%s",PROJECT_ID,API_KEY);
    //     try{
    //         URL url = new URL(endpoint);
    //         HttpURLConnection conn = (HttpURLConnection)url.openConnection();
    //         conn.setRequestMethod("GET");
    //         conn.setRequestProperty("Content-Type", "application/json");
    //         InputStream is = conn.getInputStream();
    //         byte[] responseBytes = is.readAllBytes();
    //         String response = new String(responseBytes,StandardCharsets.UTF_8);
    //         is.close();
    //         conn.disconnect();;
    //         return "User in Firestore:\n"+response;
    //     }catch(Exception e){
    //         return "Error:"+e.getMessage();
    //     }
    // }

    static Map<String, String> getProviderProfileFromFirestore() {
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

        JSONObject jsonResponse = new JSONObject(response);
        JSONArray documents = jsonResponse.getJSONArray("documents");

        for (int i = 0; i < documents.length(); i++) {
            JSONObject doc = documents.getJSONObject(i);
            JSONObject fields = doc.getJSONObject("fields");

            String name = fields.getJSONObject("name").getString("stringValue");
            String email = fields.getJSONObject("email").getString("stringValue");

            // You can match the `name` or email to `userInput` (current user)
            if (email.equals(profiledata.email)) {
                profileData.put("name", name);
                profileData.put("email", email);
                profileData.put("age", fields.getJSONObject("age").getString("stringValue"));
                profileData.put("location", fields.getJSONObject("location").getString("stringValue"));
                profileData.put("bio", fields.getJSONObject("bio").getString("stringValue"));
                profileData.put("category", fields.getJSONObject("category").getString("stringValue"));
                profileData.put("description", fields.getJSONObject("description").getString("stringValue"));
                profileData.put("charges", fields.getJSONObject("charges").getString("stringValue"));
                profileData.put("availability", fields.getJSONObject("availability").getString("stringValue"));
                profileData.put("experience", fields.getJSONObject("experience").getString("stringValue"));
                profileData.put("rating", fields.getJSONObject("rating").getString("stringValue"));

                // ✅ Now update static profiledata variables
                profiledata.name = name;
                profiledata.email = email;
                profiledata.age = profileData.get("age");
                profiledata.location = profileData.get("location");
                profiledata.bio = profileData.get("bio");
                profiledata.category = profileData.get("category");
                profiledata.description = profileData.get("description");
                profiledata.charges = profileData.get("charges");
                profiledata.availability = profileData.get("availability");
                profiledata.experience = profileData.get("experience");
                profiledata.rating = profileData.get("rating");

                break;
            }

        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return profileData;
}
  
}
