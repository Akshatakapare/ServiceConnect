package com.user.view;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.user.Controller.DashboardController;
import com.user.model.Appointment;
import com.user.model.Booking;
import com.user.model.BookingStore;
import com.user.model.Provider;
import com.user.model.Service;
import com.user.model.profiledata;
import com.user.model.Profile;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DashboardView  {

    static final String PROJECT_ID="serviceconnect-eb8b6";
    static final String API_KEY="AIzaSyAlJon0EBJRYetQUsc2ySdnt3pm41U4KMU";


    private final DashboardController controller = new DashboardController();
    private String userInput = "seeker";
    Stage primaryStage;
    private Image profileImage;
    private Profile currentProfile;
    private VBox profileSection;
    private ImageView cornerImage;
    private VBox centerContent;
   



    public DashboardView(String username) {
        this.userInput=username;
        
    }
    public DashboardView() {
      
        
    }


    public void setProfileImage(Image image) {
        this.profileImage = image;
    }

    public void setProfile(Profile profile) {
        this.currentProfile = profile;
        if (cornerImage != null && profile.getProfileImage() != null) {
            cornerImage.setImage(profile.getProfileImage());
        }
        if (profileImage == null && profile.getProfileImage() != null) {
            this.profileImage = profile.getProfileImage();
        }
        if (profileSection != null) {
            profileSection.getChildren().clear();
            Label name = new Label("Name: " + profile.getName());
            Label email = new Label("email: " + profile.getEmail());
            Label age = new Label("Age: " + profile.getAge());
            Label gender = new Label("Gender: " + profile.getGender());
            Label location = new Label("Location: " + profile.getLocation());
            Label phone = new Label("Phone: " + profile.getPhone());
            Label service = new Label("Service: " + profile.getService());
            Label expectations = new Label("Expectations: " + profile.getExpectations());
            VBox details = new VBox(10, name, email,age, gender, location, phone, service, expectations);
            profileSection.getChildren().add(details);
        }
    }

    


    public void showDashboard(Stage stage,String username) {
    this.primaryStage = stage;        // Save reference to use later
    this.userInput = "seeker";

    BorderPane dashboardLayout = getView();  // ✅ Use your full UI layout
    Scene scene = new Scene(dashboardLayout, 1540, 795);

    stage.setScene(scene);
    stage.setTitle("Seeker Dashboard");
    stage.show();
}

    
    public BorderPane getView() {
        VBox sidebar = createSidebar();
        sidebar.setId("sidebar");

        Button toggleBtn = new Button("☰");
        toggleBtn.setFont(Font.font(16));
        toggleBtn.setStyle(
            "-fx-background-color: white;" +                // White background
            "-fx-text-fill: #4B2E2E;" +                     // Brown text
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;" +
            "-fx-padding: 6 14;" +
            "-fx-border-color: #4B2E2E;" +                  // Brown border
            "-fx-border-radius: 8;" +
            "-fx-border-width: 1;"
        );

        toggleBtn.setOnMouseEntered(e ->
            toggleBtn.setStyle(
                "-fx-background-color: #F5F5F5;" +           // Slightly greyish hover
                "-fx-text-fill: #4B2E2E;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;" +
                "-fx-padding: 6 14;" +
                "-fx-border-color: #4B2E2E;" +
                "-fx-border-radius: 8;" +
                "-fx-border-width: 1;"
            )
        );

            toggleBtn.setOnMouseExited(e ->
                toggleBtn.setStyle(
                    "-fx-background-color: white;" +
                    "-fx-text-fill: #4B2E2E;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 8;" +
                    "-fx-cursor: hand;" +
                    "-fx-padding: 6 14;" +
                    "-fx-border-color: #4B2E2E;" +
                    "-fx-border-radius: 8;" +
                    "-fx-border-width: 1;"
                )
            );



        toggleBtn.setOnAction(e -> {
            boolean isVisible = sidebar.isVisible();
            sidebar.setVisible(!isVisible);
            sidebar.setManaged(!isVisible);
        });

        VBox toggleBox = new VBox(toggleBtn);
        toggleBox.setPadding(new Insets(10));
        toggleBox.setStyle(
        "-fx-background-color: linear-gradient(to bottom, rgba(161,136,127,0.85), rgba(93,64,55,0.85));" +
        "-fx-text-fill: white;" +
        "-fx-font-weight: bold;" +
        "-fx-background-radius: 8;" +
        "-fx-cursor: hand;" +
        "-fx-padding: 6 14;" +
        "-fx-border-color: rgba(62,39,35,0.8);" +
        "-fx-border-radius: 8;" +
        "-fx-border-width: 1;"
        );

 

        toggleBox.setAlignment(Pos.TOP_CENTER);

        HBox leftPanel = new HBox(toggleBox, sidebar);

        centerContent = new VBox(20);
        centerContent.setPadding(new Insets(20));
        centerContent.setStyle("-fx-background-color: #006400;");


        ScrollPane scrollPane = new ScrollPane(centerContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(10));
        scrollPane.setStyle("-fx-background-color: transparent;");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        BorderPane root = new BorderPane();
        root.setLeft(leftPanel);
        root.setCenter(scrollPane);

        showDashboardContent();

        return root;
        }

        private VBox createSidebar() {
        String[] menuItems = {"Dashboard", "Profile" ,"Complaint", "Help", "Logout"};

        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(20));
      
        sidebar.setStyle(
        "-fx-background-color: linear-gradient(to bottom, rgba(161,136,127,0.85), rgba(93,64,55,0.85));" +  // Warm brown gradient
        "-fx-border-color: rgba(62,39,35,0.8);" +  // Deep brown border
        "-fx-border-width: 0 1 0 0;" +
        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0.3, 2, 2);"
        );

        sidebar.setPrefWidth(180);
        

        try {
            Image img = (profileImage != null)
                    ? profileImage
                    : new Image(getClass().getResourceAsStream("/Assets//Images//akshata.png"));
            cornerImage = new ImageView(img);
            cornerImage.setFitWidth(80);
            cornerImage.setFitHeight(80);
            cornerImage.setClip(new Circle(40, 40, 40));
            VBox profileBox = new VBox(cornerImage);
            profileBox.setAlignment(Pos.CENTER);
            sidebar.getChildren().add(profileBox);
        } catch (Exception e) {
            System.out.println("Failed to load profile image.");
        }

        for (String item : menuItems) {
            Label label = new Label(item);
            label.setFont(Font.font("Arial", FontWeight.MEDIUM, 14));
            label.setTextFill(Color.WHITE);

            // label.setOnMouseEntered(e -> label.setTextFill(Color.web("#5e4b3c"))); // warm brown
            // label.setOnMouseExited(e -> label.setTextFill(Color.web("#333")));

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

            label.setOnMouseClicked(event -> {
                switch (item) {
                    case "Dashboard" -> showDashboardContent();
                    // case "Profile" -> showProfileInfo();
                    case "Profile" ->{
                            // Profile profile = getProviderProfileFromFirestoreByEmail();
                            Profile profile= new Profile();
                             //Map<String, String> profileData = getseekerProfileFromFirestore();
                            if (profile != null) {
                                setProfile(profile);     // Store the profile in currentProfile
                                showProfileInfo();       // Show profile in centerContent
                            } else {
                                Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to load profile.");
                                alert.showAndWait();
                            }
                    }
//                     case "My Bookings" -> {
//     if (!BookingStore.upcomingBookings.isEmpty()) {
//         Booking latest = BookingStore.upcomingBookings.get(BookingStore.upcomingBookings.size() - 1);

//         Alert alert = new Alert(Alert.AlertType.INFORMATION);
//         alert.setTitle("Appointment Accepted");
//         alert.setHeaderText("Booking Confirmed");

//         alert.setContentText(
//             "Service: " + latest.getServiceName() +
//             "\nProvider: " + latest.getProviderName() +
//             "\nDate: " + latest.getDateTime().toLocalDate() +
//             "\nTime: " + latest.getTime() +
//             "\nStatus: ACCEPTED"
//         );
//         alert.showAndWait();
//     } else {
//         Alert alert = new Alert(Alert.AlertType.INFORMATION);
//         alert.setTitle("No Bookings");
//         alert.setHeaderText(null);
//         alert.setContentText("You don't have any upcoming bookings at the moment.");
//         alert.showAndWait();
//     }
// }

                    case "Complaint" -> {
                         try {
                    new complaintsubmitpage().start(new Stage());
                } catch (Exception ex) {
                    ex.printStackTrace();

                }
                    }
                    case "Help"->{
                        // new helppageS().launchHelp(primaryStage);
                        new helppageS().launchHelp(primaryStage);
                        
                    }

                    case "Logout" -> {LandingPage lp=new LandingPage();
                        lp.start(primaryStage);


                    }
                    default -> System.out.println(item + " clicked");
                }
            });
            sidebar.getChildren().add(label);
        }

        return sidebar;
        }

        private void showDashboardContent() {
        VBox headerLayout = getHeaderView("seeker");

        
        Label recommendedLabel = new Label("Explore Services");
        recommendedLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        recommendedLabel.setTextFill(Color.WHITE);


        FlowPane servicesPane = new FlowPane(20, 20);
        servicesPane.setPadding(new Insets(10));
        servicesPane.setPrefWrapLength(800);

        for (Service s : controller.getAllServices()) {
            VBox serviceTile = new VBox(10);
                serviceTile.setAlignment(Pos.CENTER);
                serviceTile.setPadding(new Insets(15));
                serviceTile.setPrefSize(200, 200);  // Set fixed box size (optional)
                serviceTile.setStyle(
                    "-fx-background-color: linear-gradient(to bottom, #ffffff, #fef6f0);" +  // Soft gradient
                    "-fx-border-radius: 16;" +
                    "-fx-background-radius: 16;" +
                    "-fx-border-color: #e0cfcf;" +
                    "-fx-border-width: 1;" +
                    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 6, 0.3, 0, 2);" +
                    "-fx-cursor: hand;"
                );

            serviceTile.setOnMouseClicked(e -> openServiceDetailPage(s.getName()));
            // Hover Effect
                    serviceTile.setOnMouseEntered((MouseEvent e) -> {
                serviceTile.setStyle(
                    "-fx-background-color: #fff3e0;" +
                    "-fx-border-radius: 16;" +
                    "-fx-background-radius: 16;" +
                    "-fx-border-color: #ffcc80;" +
                    "-fx-border-width: 1.5;" +
                    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.18), 10, 0.4, 0, 4);" +
                    "-fx-cursor: hand;"
                );

                TranslateTransition bounceUp = new TranslateTransition(Duration.millis(150), serviceTile);
                bounceUp.setByY(-8);  // move up 8px
                bounceUp.play();
            });

            // Exit effect: revert style + move back
            serviceTile.setOnMouseExited((MouseEvent e) -> {
                serviceTile.setStyle(
                    "-fx-background-color: linear-gradient(to bottom, #ffffff, #fef6f0);" +
                    "-fx-border-radius: 16;" +
                    "-fx-background-radius: 16;" +
                    "-fx-border-color: #e0cfcf;" +
                    "-fx-border-width: 1;" +
                    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 6, 0.3, 0, 2);" +
                    "-fx-cursor: hand;"
                );

                TranslateTransition bounceDown = new TranslateTransition(Duration.millis(150), serviceTile);
                bounceDown.setByY(8);  // move back down
                bounceDown.play();
            });
           
    

            Image image = getImageForService(s.getName());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(170);
            imageView.setFitHeight(140);
            imageView.setStyle("-fx-cursor: hand;");
            imageView.setPreserveRatio(true);



            imageView.setOnMouseClicked(e -> {
        TranslateTransition bounce = new TranslateTransition(Duration.millis(200), imageView);
        bounce.setByY(-25);
        bounce.setCycleCount(2);
        bounce.setAutoReverse(true);
        bounce.play();
        });


        imageView.setOnMouseEntered(e -> {
            imageView.setStyle(
                "-fx-effect: dropshadow(gaussian, rgba(121,85,72,0.5), 8, 0.3, 2, 2);" +
                "-fx-cursor: hand;"
            );
        });

        imageView.setOnMouseExited(e -> {
            imageView.setStyle("-fx-effect: none; -fx-cursor: hand;");
        });


            

            Label label = new Label(s.getName());
            label.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));
            label.setTextFill(Color.web("#4b3832"));  // Warm brown or dark gray



            serviceTile.getChildren().addAll( imageView,label);
            // Slide-in effect
            FadeTransition ft = new FadeTransition(Duration.millis(800), serviceTile);
           ft.setFromValue(0);
            ft.setToValue(1);
            ft.play();

            servicesPane.getChildren().add(serviceTile);
            }

            Label featuredLabel = new Label("Featured Providers");
            featuredLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            featuredLabel.setTextFill(Color.WHITE);



       List<Provider> featuredProviders = controller.getFeaturedProviders();
        HBox featuredBox = new HBox(20);
        featuredBox.setPadding(new Insets(10));
        featuredBox.setAlignment(Pos.TOP_LEFT);

        for (Provider provider : featuredProviders) {
            VBox providerTile = new VBox(8);
   
            providerTile.setPadding(new Insets(10));
            providerTile.setStyle(
                "-fx-background-color: #fffaf5;" +
                "-fx-border-radius: 14;" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: #e0cfcf;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 5, 0.2, 0, 2);"
            );

                ImageView providerImage = new ImageView(provider.getImage());
                providerImage.setFitWidth(100);
                providerImage.setFitHeight(100);
                //  Rounded image

                Label nameLabel = new Label(provider.getName());
                nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                nameLabel.setTextFill(Color.WHITE);


                Label ratingLabel = new Label("⭐ " + provider.getRating());
                ratingLabel.setFont(Font.font("Arial", 12));
                ratingLabel.setTextFill(Color.BLACK);

                providerTile.getChildren().addAll( providerImage,nameLabel, ratingLabel);
                featuredBox.getChildren().add(providerTile);
            }


        

                Label heading = new Label("📅 Upcoming Appointments");
                heading.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                heading.setTextFill(Color.WHITE);

                // heading.setTextFill(Color.web("#300b6f")); // Violet color to match your theme

                VBox appBox = new VBox(8); // Slight spacing between appointments
                appBox.getChildren().add(heading); // Add the heading on top

                for (Appointment a : controller.getAppointments()) {
                    Label label = new Label(a.getTitle() + " - " + a.getDateTime());
                    label.setFont(Font.font("Segoe UI", 14));
                

                    label.setTextFill(Color.DARKBLUE);
                    appBox.getChildren().add(label);
                }

                // Apply card-like background style
                appBox.setStyle("-fx-background-color: rgba(255,255,255,0.7);"
                            + "-fx-background-radius: 12;"
                            + "-fx-padding: 15;");
                appBox.setAlignment(Pos.TOP_LEFT);


                        centerContent.getChildren().setAll(
                                headerLayout,
                    recommendedLabel, servicesPane,
                    featuredLabel, featuredBox,
                    appBox // Styled appointment section
                        );

URL bgUrl = getClass().getResource("/Assets/Images/main.jpeg");
if (bgUrl != null) {
    String bgPath = bgUrl.toExternalForm();
    centerContent.setStyle(
        "-fx-background-image: url('" + bgPath + "');" +
        "-fx-background-repeat: no-repeat;" +
        "-fx-background-size: cover;" +
        "-fx-background-position: center center;" +
        "-fx-background-color: linear-gradient(to bottom right, rgba(255, 255, 255, 0.85), rgba(255, 245, 235, 0.9));"
    );
} else {
    //System.out.println("❌ Dashboard image not found: /Assets/Images/main.jpeg");
}
}

                    public VBox getHeaderView(String userInput) {
                    // Welcome Label
                    Label nameLabel = new Label("👋 Welcome, " + userInput);
                    nameLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 22));
                    nameLabel.setTextFill(Color.WHITE);
                // Use Color.web() for hex codes


                    // Spacer
                    Region spacer = new Region();
                    HBox.setHgrow(spacer, Priority.ALWAYS);

    // Top-right action labels
    Button notifBtn = new Button("🔔");
        notifBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        notifBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: #2C3E50; -fx-cursor: hand;");
        // Notification bell icon
// 🔔 Notification bell icon



        notifBtn.setOnAction(e -> {
        NotificationUI notification = new NotificationUI();
            try {
               notification.showNotifications((Stage) notifBtn.getScene().getWindow());
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
settingsBtn.setOnAction(e -> {
            SettingsPageS settingsPage = new SettingsPageS();
            try {
                settingsPage.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


    // Right Controls HBox
    HBox rightControls = new HBox(30, notifBtn,settingsBtn);
    rightControls.setAlignment(Pos.CENTER_RIGHT);

    // Main Header Layout
    HBox header = new HBox(30, nameLabel, spacer, rightControls);
    header.setAlignment(Pos.CENTER_LEFT);
    header.setPadding(new Insets(18, 35, 18, 35));
    
    // Optional separator line
    Separator separator = new Separator();
    separator.setStyle("-fx-background-color: #dcdde1;");
    Separator line = new Separator();
    line.setStyle("-fx-background-color: #dcdcdc;");


    return new VBox(header, separator);
}



    private String buttonStyle() {
   return "-fx-background-color: linear-gradient(to bottom, #ffecd2, #fcb69f);" +  // Peach-beige tone
       "-fx-background-radius: 18;" +
       "-fx-text-fill: #4b3832;" +  // Brown text
       "-fx-font-size: 15px;" +
       "-fx-font-family: 'Segoe UI';" +
       "-fx-padding: 10 18;" +
       "-fx-cursor: hand;" +
       "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 4, 0.1, 0, 2);";
    }

    private Image getImageForService(String serviceName) {
        String path = switch (serviceName) {
            case "Cooking" -> "/Assets/Images/cooking.jpg";
            case "Tutoring" -> "/Assets/Images/gettyimages-1426606693-640x640.jpg";
            case "Storytelling" -> "/Assets/Images/cheerful-grandfather-and-grandson-reading-book-together-ai-generated-photo.jpg";
            case "Spiritual Guidance" -> "/Assets/Images/130949567-group-of-old-people-meditating-in-lotus-position-outdoors.jpg";
            case "Gardening" -> "/Assets/Images/images (1).jpg";
            case "Child Care" -> "/Assets/Images/download.jpg";
            case "Music/Singing" -> "/Assets/Images/download (1).jpg";
            case "Language / Dialect Teaching" -> "/Assets/Images/download (3).jpg";
            default -> "/Assets//Images//default.jpg";
        };
        try {
            return new Image(getClass().getResourceAsStream(path));
        } catch (Exception e) {
            System.out.println("Failed to load image for " + serviceName + " from path: " + path);
            return new Image(getClass().getResourceAsStream("/Assets//Images//akshata.png"));
        }
    }

    protected void openServiceDetailPage(String serviceName) {
        ServiceListView serviceListView = new ServiceListView();
        serviceListView.showServiceList(primaryStage, serviceName);
    }

    private void showAllServices() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("All Services");
        alert.setHeaderText("Full List of Services");
        alert.setContentText("(Coming soon!)");
        alert.showAndWait();
    }

    private void showProfileInfo() {
        if (currentProfile == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("No profile available. Please create your profile first.");
            alert.show();
            return;
        }
        // Title
Label title = new Label("👤 Profile Information");
title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
title.setTextFill(Color.DARKBLUE);
title.setPadding(new Insets(10, 0, 20, 0));

// Styled Labels
// Label name = new Label("👤 Name: " + currentProfile.getName());
// Label age = new Label("🎂 Age: " + currentProfile.getAge());
// Label email = new Label("🎂 email: " + currentProfile.getEmail());
// Label gender = new Label("⚧ Gender: " + currentProfile.getGender());
// Label location = new Label("📍 Location: " + currentProfile.getLocation());
// Label phone = new Label("📞 Phone: " + currentProfile.getPhone());
// Label service = new Label("💼 Service: " + currentProfile.getService());
// Label expectations = new Label("💬 Expectations: " + currentProfile.getExpectations());
// Label language = new Label("🌐 Preferred Language: " + currentProfile.getPreferredLanguage());

Label name = new Label("👤 Name: " + "seeker");
Label age = new Label("🎂 Age: " + "21");
Label email = new Label("🎂 email: " + "seeker@gmail.com");
Label gender = new Label("⚧ Gender: " + "Female");
Label location = new Label("📍 Location: " + "Pune");
Label phone = new Label("📞 Phone: " + "1234567890");
Label service = new Label("💼 Service: " + "cooking");
Label expectations = new Label("💬 Expectations: " + "None");
Label language = new Label("🌐 Preferred Language: " + "Marathi");


List<Label> labels = Arrays.asList(name, age, gender, location, phone, service, expectations,language);
for (Label lbl : labels) {
    lbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 16));
    lbl.setTextFill(Color.BLACK);
    lbl.setPadding(new Insets(5));
}

// Edit Button
Button editButton = new Button("✏️ Edit Profile");
editButton.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
editButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-background-radius: 8;");
editButton.setPadding(new Insets(8, 15, 8, 15));

// Add Action (you can modify to open your edit page)
editButton.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent arg0) {
        EditProfile editProfile = new EditProfile(currentProfile, DashboardView.this); // ✅ FIXED
        try {
            editProfile.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
});



// VBox container
VBox profileBox = new VBox(10);
profileBox.getChildren().addAll(title);
profileBox.getChildren().addAll(labels);
profileBox.getChildren().add(editButton);
profileBox.setAlignment(Pos.TOP_LEFT);
profileBox.setPadding(new Insets(30));
profileBox.setStyle("-fx-background-color: #f0f8ff; -fx-border-color: #cccccc; -fx-border-radius: 10; -fx-background-radius: 10;");

// Display in center content
centerContent.getChildren().setAll(profileBox);
    }

    public void launchDashboard(Stage stage, String username) {
    showDashboard(stage, username);
}



}
