package com.user.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import com.user.Controller.logincontroller;
import com.user.model.Provider;
import com.user.model.loginUser;

public class ProviderDetailView {
     private String serviceName;

    public ProviderDetailView(String serviceName) {
        this.serviceName = serviceName;
    }

    public void showProviderDetail(Stage stage, String serviceName, String email,String providerName, String experience, String skills, String contact) {
        BorderPane rootLayout = new BorderPane();

        // Sidebar with toggle
        // VBox sidebar = createSidebar(stage, rootLayout, serviceName);
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


        

        // Provider Details Content
        VBox content = new VBox(15);
        content.setPadding(new Insets(30));
        content.setAlignment(Pos.TOP_CENTER);

        Label heading = new Label("Provider Details");
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Label nameLabel = new Label("Name: " + providerName);
        Label emailLabel = new Label("Email: " + email);
        Label expLabel = new Label("Experience: " + experience);
        Label skillsLabel = new Label("Skills: " + skills);
        Label contactLabel = new Label("Contact: " + contact);

        nameLabel.setFont(Font.font(14));
        emailLabel.setFont(Font.font(14));
        expLabel.setFont(Font.font(14));
        skillsLabel.setFont(Font.font(14));
        contactLabel.setFont(Font.font(14));

        // Button chatButton = new Button("Chat Now");
// chatButton.setOnAction(e -> {
//     String senderId = loginUser.getUserEmail();
//     String receiverId = Provider.getEmail();

//     System.out.println("Sender: " + senderId);
//     System.out.println("Receiver: " + receiverId);

//     if (senderId != null && receiverId != null && !senderId.equals(receiverId)) {
//         ChatWindow chatWindow = new ChatWindow(senderId, receiverId);
//         System.out.println("Opening chat window...");
//         chatWindow.show();
//     } else {
//         System.out.println("Chat cannot be opened. Invalid sender or receiver.");
//     }
// });
// chatButton.setOnAction(e -> {
//     try {
//         System.out.println("chat btn clicked");
//         String seekerId = logincontroller.getCurrentUserEmail();
//         String providerId = Provider.getEmail();
//         System.out.println("Seeker: " + seekerId);
//         System.out.println("Provider: " + providerId);

//         ChatWindow chatWindow = new ChatWindow(seekerId, providerId);
//         chatWindow.show();
//     } catch (Exception ex) {
//         ex.printStackTrace(); // see exception in console
//     }
// });








        Button bookNowBtn = new Button("Book Now");
        bookNowBtn.setStyle("-fx-background-color: #007ACC; -fx-text-fill: white;");
        bookNowBtn.setOnAction(e -> {
            Booking_Scheduling.show(stage, providerName, skills); // pass values
        });
        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> {
    ServiceListView listView = new ServiceListView();
    listView.showServiceList(stage, serviceName);  // ✅ Works properly
});


        VBox box = new VBox(10, nameLabel, emailLabel, expLabel, skillsLabel, contactLabel,bookNowBtn, backBtn);
        box.setAlignment(Pos.CENTER_LEFT);
        box.setStyle("-fx-border-color: #ccc; -fx-border-radius: 10; -fx-background-color: #f9f9f9;");
        box.setPadding(new Insets(20));

        content.getChildren().addAll(heading, box);
         content.setStyle("-fx-background-color: #b5a7a5ff");
        rootLayout.setCenter(content);

        Scene scene = new Scene(rootLayout, 1540, 795);
        stage.setScene(scene);
    }

//     private static VBox createSidebar(Stage stage, BorderPane rootLayout, String serviceName) {
//         String[] menuItems = {"Dashboard", "Profile", "My Bookings", "Calendar", "Messages", "Video Call", "Complaint", "Help", "Logout"};

//         VBox sidebar = new VBox(15);
//         sidebar.setPadding(new Insets(20));
//         sidebar.setStyle("-fx-background-color: #f4f4f4;");
//         sidebar.setPrefWidth(180);

//         // Toggle Button
//         Button toggleBtn = new Button("☰");
//         toggleBtn.setFont(Font.font(16));
//         toggleBtn.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");

//         // Container for toggle and profile image
//         HBox toggleContainer = new HBox(toggleBtn);
//         toggleContainer.setAlignment(Pos.TOP_LEFT);
//         toggleContainer.setPadding(new Insets(5));

//         // Profile Image
//         VBox profileBox = new VBox();
//         profileBox.setAlignment(Pos.CENTER);
//         try {
//             Image profileImg = new Image(ProviderDetailView.class.getResourceAsStream("/Assets/Images/user.jpg"));
//             ImageView profileImgView = new ImageView(profileImg);
//             profileImgView.setFitWidth(80);
//             profileImgView.setFitHeight(80);
//             profileImgView.setClip(new Circle(40, 40, 40));
//             profileBox.getChildren().add(profileImgView);
//         } catch (Exception e) {
//             System.out.println("Profile image not found!");
//         }

//         VBox menuContainer = new VBox(10);
//         menuContainer.setPadding(new Insets(10, 0, 0, 0));
//         List<Label> menuLabels = new ArrayList<>();

//         for (String item : menuItems) {
//             Label label = new Label(item);
//             label.setFont(Font.font("Arial", FontWeight.MEDIUM, 14));
//             label.setTextFill(Color.web("#333"));
//             label.setStyle("-fx-cursor: hand;");

//             label.setOnMouseEntered(e -> label.setTextFill(Color.web("#007ACC")));
//             label.setOnMouseExited(e -> label.setTextFill(Color.web("#333")));

//             label.setOnMouseClicked(event -> {
//                 switch (item) {
//                     case "Dashboard":
//                         new DashboardView().showDashboard(stage, "Guest");

//                         break;
//                     case "Profile":
//                         //showAlert("Profile", "Profile page not yet implemented.");
//                         break;
//                     case "My Bookings":
//                         // Booking_Scheduling.show(stage, providerName, skills);
//                         break;
//                     case "Calendar":
//                         //showAlert("Calendar", "Calendar functionality not implemented.");
//                         break;
//                     case "Messages":
//                         //showAlert("Messages", "Message center not implemented.");
//                         break;
                    
//                     case "Video Call":
//                         //showAlert("Video Call", "Video calling not available yet.");
//                         break;
//                     case "Complaint":
//                         new SubmitComplaintPage().show(stage);
//                         break;
//                     case "Help":
//                         new DisputeResolutionUI().start(stage);
//                         break;
//                     case "Logout":
//                         LandingPage lp=new LandingPage();
//                         lp.start(stage);
//                         break;
//                     default:
//                         System.out.println(item + " clicked");
//                 }
//             });

//             menuLabels.add(label);
//             menuContainer.getChildren().add(label);
//         }

//         toggleBtn.setOnAction(e -> {
//             boolean currentlyVisible = menuContainer.isVisible();
//             menuContainer.setVisible(!currentlyVisible);
//             menuContainer.setManaged(!currentlyVisible);
//         });

//         sidebar.getChildren().addAll(toggleContainer, profileBox, menuContainer);
//         return sidebar;
//     }
// 
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

// package com.seeker.View;

// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.*;
// import javafx.scene.paint.Color;
// import javafx.scene.shape.Circle;
// import javafx.scene.text.Font;
// import javafx.scene.text.FontWeight;
// import javafx.stage.Stage;

// import java.util.ArrayList;
// import java.util.List;

// public class ProviderDetailView {

 
// private String username;
//     private String serviceName;

//     // ✅ Constructor
//     public ProviderDetailView(String username) {
//         this.username = username;
//     }


    
//     public static void showProviderDetail(Stage stage, String serviceName, String providerName, String experience, String skills, String contact) {
//         BorderPane rootLayout = new BorderPane();

//         // Sidebar with toggle
//         VBox sidebar = createSidebar(stage, rootLayout, serviceName);
//         rootLayout.setLeft(sidebar);

//         // Provider Details Content
//         VBox content = new VBox(15);
//         content.setPadding(new Insets(30));
//         content.setAlignment(Pos.TOP_CENTER);

//         Label heading = new Label("Provider Details");
//         heading.setFont(Font.font("Arial", FontWeight.BOLD, 20));

//         Label nameLabel = new Label("Name: " + providerName);
//         Label expLabel = new Label("Experience: " + experience);
//         Label skillsLabel = new Label("Skills: " + skills);
//         Label contactLabel = new Label(contact);

//         nameLabel.setFont(Font.font(14));
//         expLabel.setFont(Font.font(14));
//         skillsLabel.setFont(Font.font(14));
//         contactLabel.setFont(Font.font(14));

//         Button bookNowBtn = new Button("Book Now");
//         bookNowBtn.setStyle("-fx-background-color: #007ACC; -fx-text-fill: white;");
//         bookNowBtn.setOnAction(e -> {
//             System.out.println("Booking for " + providerName);
//             // You can replace with booking form popup later
//         });

//        Button backBtn = new Button("← Back to Providers");
//        backBtn.setOnAction(e -> {
//     ServiceListView listView = new ServiceListView(username);
//     listView.showServiceList(stage, serviceName);
// });




//         VBox box = new VBox(10, nameLabel, expLabel, skillsLabel, contactLabel, bookNowBtn, backBtn);
//         box.setAlignment(Pos.CENTER_LEFT);
//         box.setStyle("-fx-border-color: #ccc; -fx-border-radius: 10; -fx-background-color: #f9f9f9;");
//         box.setPadding(new Insets(20));

//         content.getChildren().addAll(heading, box);
//         rootLayout.setCenter(content);

//         Scene scene = new Scene(rootLayout, 1000, 600);
//         stage.setScene(scene);
//     }

//     private static VBox createSidebar(Stage stage, BorderPane rootLayout, String serviceName) {
//         String[] menuItems = {"Dashboard", "Profile", "My Bookings", "Calendar", "Messages", "Reviews", "Video Call", "Complaint", "Help", "Logout"};

//         VBox sidebar = new VBox(15);
//         sidebar.setPadding(new Insets(20));
//         sidebar.setStyle("-fx-background-color: #f4f4f4;");
//         sidebar.setPrefWidth(180);

//         // Toggle Button
//         Button toggleBtn = new Button("☰");
//         toggleBtn.setFont(Font.font(16));
//         toggleBtn.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");

//         // Container for toggle and profile image
//         HBox toggleContainer = new HBox(toggleBtn);
//         toggleContainer.setAlignment(Pos.TOP_LEFT);
//         toggleContainer.setPadding(new Insets(5));

//         // Profile Image
//         VBox profileBox = new VBox();
//         profileBox.setAlignment(Pos.CENTER);
//         try {
//             Image profileImg = new Image(ProviderDetailView.class.getResourceAsStream("/Assets/Images/user.jpg"));
//             ImageView profileImgView = new ImageView(profileImg);
//             profileImgView.setFitWidth(80);
//             profileImgView.setFitHeight(80);
//             profileImgView.setClip(new Circle(40, 40, 40));
//             profileBox.getChildren().add(profileImgView);
//         } catch (Exception e) {
//             System.out.println("Profile image not found!");
//         }

//         // Menu Container (will be toggled)
//         VBox menuContainer = new VBox(10);
//         menuContainer.setPadding(new Insets(10, 0, 0, 0));
//         List<Label> menuLabels = new ArrayList<>();

//         for (String item : menuItems) {
//             Label label = new Label(item);
//             label.setFont(Font.font("Arial", FontWeight.MEDIUM, 14));
//             label.setTextFill(Color.web("#333"));
//             label.setStyle("-fx-cursor: hand;");

//             label.setOnMouseEntered(e -> label.setTextFill(Color.web("#007ACC")));
//             label.setOnMouseExited(e -> label.setTextFill(Color.web("#333")));

//             label.setOnMouseClicked(event -> {
//                 switch (item) {
//                    case "Dashboard":
//                     DashboardView dashboard = new DashboardView(username); // ✅ use parameter
//                     dashboard.launchDashboard(stage, username);    
   
//                         break;
//                     case "Profile":
//                         // new ProfileView().start(stage);
//                         break;
//                     case "Logout":
//                         stage.close();
//                         break;
//                     default:
//                         System.out.println(item + " clicked");
//                 }
//             });

//             menuLabels.add(label);
//             menuContainer.getChildren().add(label);
//         }

//         toggleBtn.setOnAction(e -> {
//             boolean currentlyVisible = menuContainer.isVisible();
//             menuContainer.setVisible(!currentlyVisible);
//             menuContainer.setManaged(!currentlyVisible);
//         });

//         sidebar.getChildren().addAll(toggleContainer, profileBox, menuContainer);
//         return sidebar;
//     }
// }
