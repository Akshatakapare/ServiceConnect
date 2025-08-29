// package com.user.view;

// import javafx.application.Application;
// import javafx.geometry.*;
// import javafx.scene.Scene;
// import javafx.scene.control.*;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.*;
// import javafx.scene.paint.Color;
// import javafx.scene.shape.Circle;
// import javafx.scene.text.*;
// import javafx.stage.Stage;

// public class HelpS extends Application {
//     @Override
//     public void start(Stage stage) {
//         BorderPane root = new BorderPane();
//         root.setStyle("-fx-background-color: #fce5d4;");

//         VBox profileBox = new VBox();
//         profileBox.setAlignment(Pos.CENTER);
//         try {
//             Image profileImg = new Image(StarRatingUI.class.getResourceAsStream("/Assets/Images/user.jpg"));
//             ImageView profileImgView = new ImageView(profileImg);
//             profileImgView.setFitWidth(80);
//             profileImgView.setFitHeight(80);
//             profileImgView.setClip(new Circle(40, 40, 40));
//             profileBox.getChildren().add(profileImgView);
//         } catch (Exception e) {
//             System.out.println("Profile image not found!");
//         }

//         Button backBtn = new Button("Back");
//         backBtn.setStyle("-fx-background-color: #639af5; -fx-text-fill: white;");
//         BorderPane.setAlignment(backBtn, Pos.TOP_LEFT);
//         BorderPane.setMargin(backBtn, new Insets(15));
//         root.setTop(backBtn);

//         VBox centerBox = new VBox(15);
//         centerBox.setPadding(new Insets(20));
//         centerBox.setAlignment(Pos.TOP_CENTER);

//         Label title = new Label("Dispute Resolution Page");
//         title.setFont(Font.font("Poppins", FontWeight.BOLD, 32));
//         title.setTextFill(Color.web("#333"));

//         VBox formBox = new VBox(10);
//         formBox.setPadding(new Insets(20));
//         formBox.setStyle("-fx-background-color: white; -fx-background-radius: 10;");
//         formBox.setMaxWidth(500);
//         formBox.setMaxHeight(500);

//         // Service Info (Dynamic later)
//         Label info = new Label("Booking Details:\nSeeker: Vaishnavi Patil\nProvider: Mrs. Meena Sharma\nService: Home Cooking\nDate: 20/07/2025\nTime: 4:00 PM – 5:00 PM");
//         info.setFont(Font.font("Poppins", 13));

//         // Complaint Type
//         Label typeLabel = new Label("Complaint Category:");
//         typeLabel.setFont(Font.font("Poppins", FontWeight.BOLD, 13));
//         ComboBox<String> complaintType = new ComboBox<>();
//         complaintType.getItems().addAll("Payment Issue", "No Show", "Miscommunication", "Unprofessional Behavior", "Other");
//         complaintType.setPromptText("Select Complaint Type");

//         // Description Box
//         Label descLabel = new Label("Describe the issue:");
//         descLabel.setFont(Font.font("Poppins", FontWeight.BOLD, 13));
//         TextArea descArea = new TextArea();
//         descArea.setPromptText("Write the issue in detail...");
//         descArea.setPrefRowCount(5);

//         // Date of Incident
//         Label dateLabel = new Label("Date of Incident:");
//         dateLabel.setFont(Font.font("Poppins", FontWeight.BOLD, 13));
//         DatePicker datePicker = new DatePicker();

//         // Optional file attach field (simulated)
//         Label fileLabel = new Label("Attach Evidence (optional):");
//         fileLabel.setFont(Font.font("Poppins", FontWeight.BOLD, 13));
//         TextField fileField = new TextField();
//         fileField.setPromptText("Upload file name (screenshot, receipt, etc.)");

//         // Buttons
//         HBox btnBox = new HBox(10);
//         btnBox.setAlignment(Pos.CENTER_RIGHT);
//         Button submit = new Button("Submit");
//         submit.setStyle("-fx-background-color: #f26b6b; -fx-text-fill: white;");
//         Button cancel = new Button("Cancel");
//         cancel.setStyle("-fx-background-color: #999; -fx-text-fill: white;");

//         // Functionalities
//         submit.setOnAction(e -> {
//             Alert alert = new Alert(Alert.AlertType.INFORMATION);
//             alert.setTitle("Submitted");
//             alert.setHeaderText(null);
//             alert.setContentText("✅ Dispute submitted successfully!");
//             alert.showAndWait();
//             new DashboardView().showDashboard(stage,"");
//         });

//         cancel.setOnAction(e -> {
//             complaintType.setValue(null);
//             descArea.clear();
//             fileField.clear();
//             datePicker.setValue(null);
//         });

//         btnBox.getChildren().addAll(cancel, submit);

//         formBox.getChildren().addAll(info, typeLabel, complaintType, descLabel, descArea, dateLabel, datePicker, fileLabel, fileField, btnBox);

        
//         root.setCenter(formBox);

//         VBox vb=new VBox(20,title,formBox);
//         vb.setAlignment(Pos.CENTER);

//         Scene scene = new Scene(vb, 1540, 795);
//         stage.setTitle("ServiceConnect - Dispute Resolution");
//         stage.setScene(scene);
//         stage.show();
//     }

// }

package com.user.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class helppageS  {

    public void launchHelp(Stage stage) {
        Label title = fixedLabel("❓ Help & Support", 28);
        Label intro = new Label("Welcome to the Help Center! Here you'll find answers to common questions and ways to get support.");
        intro.setWrapText(true);
        intro.setFont(Font.font("Segoe UI", 14));
        intro.setTextFill(Color.web("#34495e"));

        TitledPane faq1 = createFAQ("How do I book a service?", "To book a service, go to the provider list, select a service provider, and click on 'Book Now'.");
        TitledPane faq2 = createFAQ("How do I cancel a booking?", "Open your bookings section and click on 'Cancel' next to the booking you wish to cancel.");
        TitledPane faq3 = createFAQ("How do I raise a complaint?", "Use the 'Complaint' section in the sidebar to submit any issues with a provider.");
        TitledPane faq4 = createFAQ("How can I edit my profile?", "Click on 'Profile' in the sidebar, then click 'Edit Profile'.");

        VBox faqBox = new VBox(10, faq1, faq2, faq3, faq4);
        faqBox.setPadding(new Insets(10));

        Label contactTitle = fixedLabel("📞 Contact Us", 20);
        Label contactInfo = new Label("""
                If you still need help, contact us:
                ✉ Email: support@serviceconnect.com
                ☎ Phone: +91-9876543210
                📅 Mon to Sat - 9:00 AM to 6:00 PM
                """);
        contactInfo.setFont(Font.font("Segoe UI", 14));
        contactInfo.setTextFill(Color.web("#2c3e50"));
        contactInfo.setWrapText(true);

        Button backBtn = new Button("🔙 Back to Dashboard");
        backBtn.setStyle(buttonStyle("#3498db"));
        backBtn.setOnAction(e -> {
            DashboardView dashboard = new DashboardView(); // replace with your actual dashboard class
            Stage current = (Stage) backBtn.getScene().getWindow();
            dashboard.showDashboard(current, "seeker");
        });

        VBox mainContent = new VBox(20,
                title, intro,
                new Separator(), faqBox,
                new Separator(), contactTitle, contactInfo,
                new Separator(), backBtn
        );
        mainContent.setAlignment(Pos.CENTER);
        mainContent.setPadding(new Insets(30));
        mainContent.setMaxWidth(700);
        mainContent.setStyle("""
            -fx-background-color: rgba(255, 255, 255, 0.88);
            -fx-background-radius: 18;
            -fx-border-radius: 18;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 12, 0.2, 0, 6);
        """);

        ScrollPane scroll = new ScrollPane(mainContent);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background-color: transparent;");

        // ImageView bgImage = new ImageView(new Image("/Assets//Images//images.jpg")); // use appropriate background
        // bgImage.setFitWidth(1366);
        // bgImage.setFitHeight(768);
        // bgImage.setPreserveRatio(false);
        // bgImage.setEffect(new BoxBlur(4, 8, 3));

        StackPane stack = new StackPane(scroll);
        stack.setPadding(new Insets(20));
        stack.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(stack);
        borderPane.setStyle("-fx-background-color: #f0f0f0;"); // optional

        Scene scene = new Scene(borderPane, 1540, 795);
        stage.setScene(scene);
        stage.setTitle("Help - Seeker");
        stage.show();
    }

    private Label fixedLabel(String text, int size) {
        Label lbl = new Label(text);
        lbl.setFont(Font.font("Segoe UI", size));
        lbl.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        return lbl;
    }

    private TitledPane createFAQ(String question, String answer) {
        Label content = new Label(answer);
        content.setWrapText(true);
        content.setFont(Font.font("Segoe UI", 13));
        content.setTextFill(Color.DARKSLATEGRAY);
        TitledPane tp = new TitledPane(question, content);
        tp.setExpanded(false);
        tp.setStyle("-fx-font-weight: bold;");
        return tp;
    }

    private String buttonStyle(String color) {
        return "-fx-background-color: " + color + ";" +
               "-fx-text-fill: white;" +
               "-fx-font-weight: bold;" +
               "-fx-padding: 10 20;" +
               "-fx-background-radius: 10;" +
               "-fx-cursor: hand;";
    }



}
