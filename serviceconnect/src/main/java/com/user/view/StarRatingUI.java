package com.user.view;

import com.user.model.Rating;
import com.user.model.RatingStore;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class StarRatingUI {

    public static void show(Stage stage) {
        BorderPane rootLayout = new BorderPane();

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

        // ---------- Top Heading ----------
        Label heading = new Label("Rate a Provider");
        heading.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28));
        heading.setTextFill(Color.web("#3E2723"));

        HBox topbar = new HBox(heading);
        topbar.setAlignment(Pos.CENTER);
        topbar.setPadding(new Insets(30, 0, 10, 0));

        // ---------- Form Design ----------
        VBox form = new VBox(15);
        form.setPadding(new Insets(30));
        form.setAlignment(Pos.CENTER_LEFT);
        form.setStyle("""
            -fx-background-color: white;
            -fx-background-radius: 12;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 12, 0.4, 4, 4);
        """);

        TextField seekerField = new TextField();
        seekerField.setPromptText("Your Name");
        seekerField.setPrefWidth(300);

        TextField providerField = new TextField();
        providerField.setPromptText("Provider Name");
        providerField.setPrefWidth(300);

        TextField serviceField = new TextField();
        serviceField.setPromptText("Service Name");
        serviceField.setPrefWidth(300);

        Spinner<Integer> stars = new Spinner<>(1, 5, 5);
        stars.setPrefWidth(100);

        TextArea feedback = new TextArea();
        feedback.setPromptText("Your Feedback");
        feedback.setPrefRowCount(4);
        feedback.setPrefWidth(300);

        Button submit = new Button("Submit Rating");
        submit.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8 16; -fx-background-radius: 6;");

        submit.setOnAction(e -> {
            String seeker = seekerField.getText().trim();
            String provider = providerField.getText().trim();
            String service = serviceField.getText().trim();
            String feed = feedback.getText().trim();
            int starVal = stars.getValue();

            if (seeker.isEmpty() || provider.isEmpty() || service.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please fill all fields.").show();
                return;
            }

            Rating rating = new Rating(seeker, provider, service, starVal, feed);
            RatingStore.allRatings.add(rating);

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Rating Submitted Successfully!");
            alert.showAndWait();

            new DashboardView().showDashboard(stage, feed);
        });

        form.getChildren().addAll(
                new Label("Your Name:"), seekerField,
                new Label("Provider Name:"), providerField,
                new Label("Service Name:"), serviceField,
                new Label("Stars (1-5):"), stars,
                new Label("Feedback:"), feedback,
                submit
        );

        VBox centerBox = new VBox(20, topbar, form);
        centerBox.setAlignment(Pos.TOP_CENTER);
        centerBox.setPadding(new Insets(40, 0, 0, 0));

        rootLayout.setCenter(centerBox);

        Scene scene = new Scene(rootLayout, 1540, 795);
        stage.setScene(scene);
        stage.setTitle("Seeker Rating Page");
        stage.show();
    }

    // ---------- Sidebar Method (unchanged) ----------
    private static VBox createSidebar(Stage primaryStage, BorderPane rootLayout) {
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
                    case "Dashboard" -> new DashboardView().showDashboard(primaryStage, "seeker");
                    case "Profile" -> {
                        // Add profile navigation if needed
                    }
                    case "Complaint" -> {
                        try {
                            new complaintsubmitpageP().start(primaryStage);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    case "Help" -> new helppageS().launchHelp(primaryStage);
                    case "Logout" -> new LandingPage().start(primaryStage);
                    default -> System.out.println(item + " clicked");
                }
            });

            sidebar.getChildren().add(label);
        }

        return sidebar;
    }
}
