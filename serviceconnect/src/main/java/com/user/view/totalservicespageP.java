package com.user.view;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.animation.ScaleTransition;
import javafx.util.Duration;

public class totalservicespageP extends Application {

    private final String[] services = {
        "🧘 Yoga & Meditation", "💆 Gentle Physiotherapy", "🌿 Ayurvedic Advice",
        "🕊 Reiki Healing", "📖 Language Tutoring", "👨‍🏫 Career Mentoring",
        "🧩 Storytelling Circles", "📚 History Guidance", "🥘 Home Cooking Class",
        "🧵 Traditional Crafts", "🎨 Handmade Gifts", "🌱 Gardening Advisory",
        "🙏 Puja Hosting", "📄 Document Help", "💌 Grief Listening Circle"
    };

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(25);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #fff, #b1e4dcff);");

        // 🔙 Back Button
        Button backBtn = new Button("← Back");
        backBtn.setStyle("-fx-background-color: #09676dff;; -fx-text-fill: white; -fx-font-weight: bold;");
        backBtn.setOnMouseEntered(ev -> backBtn.setStyle("-fx-background-color: #09676dff;-fx-text-fill: white; -fx-font-weight: bold;"));
        backBtn.setOnMouseExited(ev -> backBtn.setStyle("-fx-background-color: #09676dff; -fx-text-fill: white; -fx-font-weight: bold;"));
        backBtn.setOnAction(e -> new dashboardpageP().launchdashboard(stage,"Ram")); // ✅ Navigation

        // 💼 Page Title
        Label title = new Label("💼 My Services");
        title.setFont(Font.font("Poppins", FontWeight.BOLD, 30));
        title.setTextFill(Color.web("#021603ff"));
        title.setEffect(new DropShadow(2, Color.LIGHTGRAY));

        // 🧩 Services Grid
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(20));
        grid.setAlignment(Pos.CENTER);

        int column = 0, row = 0;
        for (String service : services) {
            VBox card = createServiceCard(service);
            grid.add(card, column, row);
            column++;
            if (column == 3) {
                column = 0;
                row++;
            }
        }

        ScrollPane scrollPane = new ScrollPane(grid);
scrollPane.setFitToWidth(true);

// ✅ Remove the gray background — make it white
scrollPane.setStyle("-fx-background: white; -fx-background-color: linear-gradient(to bottom, #fff, #b1e4dcff);");
grid.setStyle("-fx-background-color: linear-gradient(to bottom, #fff, #b1e4dcff);");

scrollPane.setPadding(new Insets(10));

        root.getChildren().addAll(backBtn, title, scrollPane);

        Scene scene = new Scene(root, 1540, 795);
        stage.setScene(scene);
        stage.setTitle("Total Services Completed");
        stage.show();
    }

    private VBox createServiceCard(String name) {
        Label serviceName = new Label(name);
        serviceName.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        serviceName.setTextFill(Color.web("#2e7d32"));

        Label status = new Label("✔ Completed");
        status.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 13));

        status.setStyle("-fx-background-color: #714ce0ff; -fx-padding: 4 10; -fx-background-radius: 15;");

        VBox box = new VBox(8, serviceName, status);
        box.setPadding(new Insets(18));
        box.setAlignment(Pos.CENTER_LEFT);
        box.setPrefSize(260, 90);
        box.setStyle("""
            -fx-background-color: linear-gradient(to right, #db6389ff, #f1f8e9);
            -fx-border-color: #c8e6c9;
            -fx-border-radius: 15;
            -fx-background-radius: 15;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 6, 0, 0, 1);
        """);

        // ✨ Hover Effects
        box.setOnMouseEntered(e -> {
            box.setStyle("""
                -fx-background-color: linear-gradient(to right, #bf3f68ff, #f1e9eaff);
                -fx-border-color: #c1e9ecff;
                -fx-border-radius: 15;
                -fx-background-radius: 15;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 2);
            """);
            ScaleTransition st = new ScaleTransition(Duration.millis(150), box);
            st.setToX(1.03);
            st.setToY(1.03);
            st.play();
        });

        box.setOnMouseExited(e -> {
            box.setStyle("""
                -fx-background-color: linear-gradient(to right, #db6389ff, #f1f8e9);
                -fx-border-color: #c8e6c9;
                -fx-border-radius: 15;
                -fx-background-radius: 15;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 6, 0, 0, 1);
            """);
            ScaleTransition st = new ScaleTransition(Duration.millis(150), box);
            st.setToX(1.0);
            st.setToY(1.0);
            st.play();
        });

        return box;
    }
}