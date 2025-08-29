package com.user.view;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import com.user.model.Provider;

public class ServiceListView {

    
   
    private final List<Provider> providers = new ArrayList<>();
    private boolean sidebarVisible=true;
    
    public void showServiceList(Stage stage, String serviceName) {
        BorderPane rootLayout = new BorderPane();


        // // Sidebar (with toggle)
        // VBox sidebar = createSidebar(stage, rootLayout);
        // rootLayout.setLeft(sidebar);

        VBox sidebar = createSidebar(stage, rootLayout);

// Toggle Button VBox (just like dashboard)
Button toggleBtn = new Button("☰");
toggleBtn.setFont(Font.font(16));
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
);

// Hover Effects
toggleBtn.setOnMouseEntered(e -> toggleBtn.setStyle(
    "-fx-background-color: #F5F5F5;" +
    "-fx-text-fill: #4B2E2E;" +
    "-fx-font-weight: bold;" +
    "-fx-background-radius: 8;" +
    "-fx-cursor: hand;" +
    "-fx-padding: 6 14;" +
    "-fx-border-color: #4B2E2E;" +
    "-fx-border-radius: 8;" +
    "-fx-border-width: 1;"
));

toggleBtn.setOnMouseExited(e -> toggleBtn.setStyle(
    "-fx-background-color: white;" +
    "-fx-text-fill: #4B2E2E;" +
    "-fx-font-weight: bold;" +
    "-fx-background-radius: 8;" +
    "-fx-cursor: hand;" +
    "-fx-padding: 6 14;" +
    "-fx-border-color: #4B2E2E;" +
    "-fx-border-radius: 8;" +
    "-fx-border-width: 1;"
));

toggleBtn.setOnAction(e -> {
    boolean isVisible = sidebar.isVisible();
    sidebar.setVisible(!isVisible);
    sidebar.setManaged(!isVisible);
});

// Toggle VBox
VBox toggleBox = new VBox(toggleBtn);
toggleBox.setPadding(new Insets(10));
toggleBox.setAlignment(Pos.TOP_CENTER);
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

// Wrap toggle + sidebar in HBox
HBox leftPanel = new HBox(toggleBox, sidebar);
rootLayout.setLeft(leftPanel);

        // Center content
        VBox content = new VBox(20);
        content.setPadding(new Insets(20));
        content.setAlignment(Pos.TOP_CENTER);

        Label heading = new Label("Providers for: " + serviceName);
        heading.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        content.getChildren().add(heading);

        TextField searchBar = new TextField();
        searchBar.setPromptText("Search by  name or Location");
        searchBar.setPrefWidth(400);

        ComboBox<String> sortBox=new ComboBox<>();
        sortBox.getItems().addAll("Sort by Location","Sort by Availability","Sort by Rating");
        sortBox.setPromptText("Sort By");

        HBox searchSortBox = new HBox(10,searchBar,sortBox);
        searchSortBox.setAlignment(Pos.CENTER);
        content.getChildren().add(searchSortBox);

        VBox providerListBox = new VBox(15);
        providerListBox.setPadding(new Insets(10));

        if(providers.isEmpty()){
          providers.addAll(List.of(
    new Provider(serviceName + " Ram", "3 years experience", "Skilled in " + serviceName, "Contact: +91 9000112345", "Pune", "Available", 4, "/Assets/Images/provider1.jpeg"),
    new Provider(serviceName + " Sham", "5 years experience", "Expert in " + serviceName, "Contact: +91 9000212345", "Mumbai", "Busy", 5, "/Assets/Images/provider2.jpeg"),
    new Provider(serviceName + " Sheela", "4 years experience", "Experienced in " + serviceName, "Contact: +91 9000312345", "Pune", "Available", 3, "/Assets/Images/provider3.jpeg"),
    new Provider(serviceName + " Dilip", "2 years experience", "Talented in " + serviceName, "Contact: +91 9000412345", "Nagpur", "Available", 2, "/Assets/Images/provider4.jpeg"),
    new Provider(serviceName + " Shita", "6 years experience", "Highly rated for " + serviceName, "Contact: +91 9000512345", "Pune", "Busy", 5, "/Assets/Images/provider5.jpeg")
));
        }

        renderProviders(stage, serviceName, providerListBox, providers);

        content.getChildren().add(providerListBox);
        ScrollPane sc = new ScrollPane(content);
        

        searchBar.textProperty().addListener((obs, oldVal, newVal) -> {
            String keyword = newVal.toLowerCase();
            List<Provider> filtered = providers.stream()
                    .filter(p -> p.getName().toLowerCase().contains(keyword) || p.getLocation().toLowerCase().contains(keyword))
                    .collect(Collectors.toList());
            renderProviders(stage, serviceName, providerListBox, filtered);
        });

        sortBox.setOnAction(e -> {
            String option = sortBox.getValue();
            List<Provider> sortedList = new ArrayList<>(providers);

            switch (option) {
                case "Sort by Location" -> sortedList.sort(Comparator.comparing(Provider::getLocation));
                case "Sort by Availability" -> sortedList.sort(Comparator.comparing(Provider::getAvailability));
                case "Sort by Rating" -> sortedList.sort(Comparator.comparingDouble(Provider::getRating).reversed());
            }

            renderProviders(stage, serviceName, providerListBox, sortedList);
        });

        rootLayout.setCenter(sc);
        rootLayout.setStyle("-fx-background-color: #b5a7a5ff");
        sc.setFitToWidth(true);
sc.setPrefViewportHeight(500);
//sc.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
sc.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
sc.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        Scene scene = new Scene(rootLayout, 1540, 795);
        stage.setScene(scene);
    }


    private void renderProviders(Stage stage, String serviceName, VBox container, List<Provider> list) {
    container.getChildren().clear();

    for (Provider p : list) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(15));
        card.setSpacing(8);
        card.setAlignment(Pos.TOP_LEFT);
        card.setStyle("""
            -fx-background-color: #ffffff;
            -fx-background-radius: 12;
            -fx-border-radius: 12;
            -fx-border-color: #e0e0e0;
            -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 6, 0, 0, 2);
        """);

        // Labels with modern font styling
        Label nameLabel = createStyledLabel("👤 " + p.getName(), true);
        Label expLabel = createStyledLabel("📅 Experience: " + p.getExperience(), false);
        Label skillsLabel = createStyledLabel("🛠 Skills: " + p.getSkills(), false);
        Label locationLabel = createStyledLabel("📍 Location: " + p.getLocation(), false);
        Label availabilityLabel = createStyledLabel("📆 Availability: " + p.getAvailability(), false);
        Label ratingLabel = createStyledLabel("⭐ Rating: " + p.getRating(), false);
        Label contactLabel = createStyledLabel("📞 " + p.getContact(), false);

        // for (int i = 1; i <= 3; i++) {
        //     String providerName = serviceName + " Provider " + i;
        //     String experience = i + 2 + " years experience";
        //     String skills = "Skilled in " + serviceName;
        //     String contact = "Contact: +91 9000" + i + "12345";

        //     VBox card = new VBox(10);
        //     card.setPadding(new Insets(15));
        //     card.setStyle("-fx-border-color: #ccc; -fx-border-radius: 10; -fx-background-color: #f9f9f9;");
        //     card.setAlignment(Pos.CENTER_LEFT);

        //     Label nameLabel = new Label("Name: " + providerName);
        //     Label expLabel = new Label("Experience: " + experience);
        //     Label skillsLabel = new Label("Skills: " + skills);
        //     Label contactLabel = new Label(contact);

//            Button viewDetailsBtn = new Button("View Details");
// viewDetailsBtn.setOnAction(event -> {
//     ProviderDetailView detailView = new ProviderDetailView(serviceName);
//     detailView.showProviderDetail(stage, serviceName, providerName, experience, skills, contact);
// });



//             card.getChildren().addAll(nameLabel, expLabel, skillsLabel, contactLabel, viewDetailsBtn);
//             content.getChildren().add(card);
//         }

//         rootLayout.setCenter(content);

//         Scene scene = new Scene(rootLayout, 1540, 795);
//         stage.setScene(scene);
//     }

//     private static VBox createSidebar(Stage stage, BorderPane rootLayout) {
//         String[] menuItems = {"Dashboard", "Profile", "My Bookings", "Calendar", "Messages", "Reviews", "Video Call", "Complaint", "Help", "Logout"};

//         VBox sidebar = new VBox(20);
//         sidebar.setPadding(new Insets(20));
//         sidebar.setStyle("-fx-background-color: #f4f4f4;");
//         sidebar.setPrefWidth(180);
//         sidebar.setMinWidth(180);
//         sidebar.setMaxWidth(180);

//         // Toggle Button
//         Button toggleBtn = new Button("☰");
//         toggleBtn.setFont(Font.font(16));
//         toggleBtn.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
//         toggleBtn.setOnAction(e -> {
//             boolean isVisible = sidebar.isVisible();
//             sidebar.setVisible(!isVisible);
//             sidebar.setManaged(!isVisible);
//         });

//         HBox toggleContainer = new HBox(toggleBtn);
//         toggleContainer.setAlignment(Pos.TOP_LEFT);
//         toggleContainer.setPadding(new Insets(5));

//         // Profile Image
//         try {
//             Image profileImg = new Image(ServiceListView.class.getResourceAsStream("/Assets/Images/user.jpg"));
//             ImageView profileImgView = new ImageView(profileImg);
//             profileImgView.setFitWidth(80);
//             profileImgView.setFitHeight(80);
//             profileImgView.setClip(new Circle(40, 40, 40));
//             VBox profileBox = new VBox(profileImgView);
//             profileBox.setAlignment(Pos.CENTER);
//             sidebar.getChildren().addAll(toggleContainer, profileBox);
//         } catch (Exception e) {
//             System.out.println("Profile image not found!");
//         }

//         // Menu Items
//         for (String item : menuItems) {
//             Label label = new Label(item);
//             label.setFont(Font.font("Arial", FontWeight.MEDIUM, 14));
//             label.setTextFill(Color.web("#333"));
//             label.setStyle("-fx-cursor: hand;");

//             label.setOnMouseEntered(e -> label.setTextFill(Color.web("#007ACC")));
//             label.setOnMouseExited(e -> label.setTextFill(Color.web("#333")));
// String username = "Guest"; 
//             label.setOnMouseClicked(event -> {
//                 switch (item) {
//                    case "Dashboard":
//     DashboardView dashboard = new DashboardView();
//     dashboard.showDashboard(stage, username); // ✅ no error
//     break;

//                     case "Profile":
//                         // new ProfileView().start(stage);
//                         break;
//                     case "Logout":
//                         LandingPage lp=new LandingPage();
//                         lp.start(stage);
//                         break;
//                     default:
//                         System.out.println(item + " clicked");
//                 }
//             });

//             sidebar.getChildren().add(label);
//         }

//         return sidebar;
//     }
// }

Button viewDetailsBtn = new Button("View Details");
        viewDetailsBtn.setStyle("""
            -fx-background-color: #007ACC;
            -fx-text-fill: white;
            -fx-padding: 6 12 6 12;
            -fx-background-radius: 6;
            -fx-font-size: 13px;
        """);
        viewDetailsBtn.setOnMouseEntered(e -> viewDetailsBtn.setStyle(
            "-fx-background-color: #005fa3; -fx-text-fill: white; -fx-padding: 6 12 6 12; -fx-background-radius: 6;"
        ));
        viewDetailsBtn.setOnMouseExited(e -> viewDetailsBtn.setStyle(
            "-fx-background-color: #007ACC; -fx-text-fill: white; -fx-padding: 6 12 6 12; -fx-background-radius: 6;"
        ));

        viewDetailsBtn.setOnAction(event -> {
            ProviderDetailView detailView = new ProviderDetailView(serviceName);
            detailView.showProviderDetail(stage, serviceName, Provider.getEmail(),p.getName(), p.getExperience(), p.getSkills(), p.getContact());
        });


       ImageView providerImgView;
try {
    String imgPath = p.getImage2(); // Make sure this is something like "/Assets/Images/provider1.jpeg"
    System.out.println("Loading image: " + imgPath);

    InputStream imgStream = ServiceListView.class.getResourceAsStream(imgPath);
    if (imgStream != null) {
        Image img = new Image(imgStream);
        providerImgView = new ImageView(img);
        providerImgView.setFitWidth(80);
        providerImgView.setFitHeight(80);
        providerImgView.setClip(new Circle(40, 40, 40));
    } else {
        throw new Exception("Image stream is null. Path: " + imgPath);
    }
} catch (Exception ex) {
    providerImgView = new ImageView();
    System.out.println("Error loading image for: " + p.getName() + " | Reason: " + ex.getMessage());
}

        VBox textSection = new VBox(4, providerImgView,nameLabel, expLabel, skillsLabel, locationLabel, availabilityLabel, ratingLabel, contactLabel);
        textSection.setAlignment(Pos.TOP_LEFT);

        card.getChildren().addAll(textSection, viewDetailsBtn);
        container.getChildren().add(card);
    }
}
    private Label createStyledLabel(String text, boolean isBold) {
    Label label = new Label(text);
    label.setFont(Font.font("Segoe UI", isBold ? FontWeight.SEMI_BOLD : FontWeight.NORMAL, 13));
    label.setTextFill(Color.web("#333"));
    return label;
}


//     private VBox createSidebar(Stage stage, BorderPane rootLayout) {
//         String[] menuItems = {"Dashboard", "Profile", "My Bookings", "Calendar", "Messages", "Reviews", "Video Call", "Complaint", "Help", "Logout"};
//         VBox sidebar = new VBox(20);
//         sidebar.setPadding(new Insets(20));
//         sidebar.setStyle("-fx-background-color: #f4f4f4;");
//         sidebar.setPrefWidth(180);
//         sidebar.setTranslateX(0);

//         Button toggleBtn = new Button("☰");
//         toggleBtn.setFont(Font.font(16));
//         toggleBtn.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");

//         toggleBtn.setOnAction(e -> {
//             TranslateTransition slide = new TranslateTransition(Duration.millis(250), sidebar);
//             if (sidebarVisible) {
//                 slide.setToX(-200);
// slide.setOnFinished(ev -> sidebar.setVisible(false));

//             } else {
//                sidebar.setVisible(true);
// slide.setToX(0);

//             }
//             sidebarVisible = !sidebarVisible;
//             slide.play();
//         });

//         HBox toggleContainer = new HBox(toggleBtn);
//         toggleContainer.setAlignment(Pos.TOP_LEFT);
//         toggleContainer.setPadding(new Insets(5));

//         // Profile
//         try {
//             Image profileImg = new Image(ServiceListView.class.getResourceAsStream("/Assets/Images/user.jpg"));
//             ImageView profileImgView = new ImageView(profileImg);
//             profileImgView.setFitWidth(80);
//             profileImgView.setFitHeight(80);
//             profileImgView.setClip(new Circle(40, 40, 40));

//             VBox profileBox = new VBox(profileImgView);
//             profileBox.setAlignment(Pos.CENTER);
//             sidebar.getChildren().addAll(toggleContainer, profileBox);
//         } catch (Exception e) {
//             System.out.println("Profile image not found!");
//         }

//         for (String item : menuItems) {
//             Label label = new Label(item);
//             label.setFont(Font.font("Arial", FontWeight.MEDIUM, 14));
//             label.setTextFill(Color.web("#333"));
//             label.setStyle("-fx-cursor: hand;");

//             label.setOnMouseEntered(e -> label.setTextFill(Color.web("#007ACC")));
//             label.setOnMouseExited(e -> label.setTextFill(Color.web("#333")));

//             String username = "Guest";
//             label.setOnMouseClicked(event -> {
//                 switch (item) {
//                     case "Dashboard" -> new DashboardView().showDashboard(stage, username);
//                     case "Logout" -> new LandingPage().start(stage);
//                     default -> System.out.println(item + " clicked");
//                 }
//             });

//             sidebar.getChildren().add(label);
//         }

//         return sidebar;
//     }
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
