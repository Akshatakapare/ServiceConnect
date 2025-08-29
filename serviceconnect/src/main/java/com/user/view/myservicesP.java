package com.user.view;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.animation.*;
import javafx.util.Duration;

public class myservicesP extends Application {

    private final String[] services = {
        "🧘 Yoga & Meditation","🌿 Ayurvedic Advice",
        "👨‍🏫 Career Mentoring",
        "🧩 Storytelling Circles", "📚 History Guidance",
        "🌱 Gardening Advisory"
    };

    @Override
    public void start(Stage stage) {

        // 🎨 Background Image Setup
        ImageView bgImage = new ImageView(new Image("Assets\\Images\\service2.jpg"));
        bgImage.setFitWidth(1366);
        bgImage.setFitHeight(768);
        bgImage.setPreserveRatio(false);
        bgImage.setEffect(new BoxBlur(8, 5, 9));

        // 🖙 Back Button
        Button backBtn = new Button("\u2190 Back");
        backBtn.setStyle("-fx-background-color: #09676d; -fx-text-fill: white; -fx-font-weight: bold;");
        backBtn.setOnAction(e -> new dashboardpageP().launchdashboard(stage, "Ram"));
        VBox btn=new VBox(10,backBtn);
        backBtn.setAlignment(Pos.TOP_RIGHT);

        // 💼 Page Title
        Label title = new Label("💼 My Services");
        title.setFont(Font.font("Poppins", FontWeight.BOLD, 32));
        title.setTextFill(Color.BLACK);
        title.setEffect(new DropShadow(3, Color.DARKSLATEBLUE));

        // 🧩 Services Grid
        GridPane grid = new GridPane();
        grid.setHgap(25);
        grid.setVgap(25);
        grid.setPadding(new Insets(30));
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-background-color: transparent;");

        int column = 0, row = 0;
        for (String service : services) {
            VBox card = createServiceCard(service);
            grid.add(card, column, row);

            // ✨ Animation for Each Card
            card.setOpacity(0);
            card.setTranslateY(40);

            FadeTransition fadeIn = new FadeTransition(Duration.millis(600), card);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);

            TranslateTransition slideUp = new TranslateTransition(Duration.millis(600), card);
            slideUp.setFromY(40);
            slideUp.setToY(0);
            slideUp.setInterpolator(Interpolator.EASE_OUT);

            ScaleTransition bounce = new ScaleTransition(Duration.millis(250), card);
            bounce.setFromX(0.95);
            bounce.setFromY(0.95);
            bounce.setToX(1.0);
            bounce.setToY(1.0);
            bounce.setInterpolator(Interpolator.EASE_OUT);

            SequentialTransition animation = new SequentialTransition(
                new ParallelTransition(fadeIn, slideUp),
                bounce
            );
            animation.setDelay(Duration.millis((row * 2 + column) * 200));
            animation.play();

            column++;
            if (column == 2) {
                column = 0;
                row++;
            }
        }

        // 📜 ScrollPane Setup
        ScrollPane scrollPane = new ScrollPane(grid);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent;");
        scrollPane.setPadding(new Insets(10));
        

        // 📆 Main Layout
        VBox content = new VBox(25,btn, title, scrollPane);
        content.setPadding(new Insets(30));
        content.setAlignment(Pos.TOP_CENTER);
        content.setStyle("-fx-background-color: transparent;");

        // 🧱 StackPane for layering
        StackPane root = new StackPane(bgImage, content);

        Scene scene = new Scene(root, 1540, 795);
        stage.setScene(scene);
        stage.setTitle("Total Services Completed");
        stage.show();
    }

    private VBox createServiceCard(String name) {
        Label serviceName = new Label(name);
        serviceName.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        serviceName.setTextFill(Color.web("#1f0303ff"));

        Label status = new Label("\u2714 Completed");
        status.setFont(Font.font("Arial", FontWeight.MEDIUM, 14));
        status.setStyle("-fx-background-color: #4ce065ff; -fx-text-fill: white; -fx-padding: 4 10; -fx-background-radius: 15;");

        VBox box = new VBox(10, serviceName, status);
        box.setPadding(new Insets(20));
        box.setAlignment(Pos.CENTER_LEFT);
        box.setPrefSize(300, 120);
        box.setStyle("""
            -fx-background-color: linear-gradient(to right, #f475e9ff, #8d18b7ff);
            -fx-border-color: #ffffff44;
            -fx-border-width: 1.5;
            -fx-border-radius: 18;
            -fx-background-radius: 18;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 6, 0.1, 0, 2);
        """);

        // Hover animation on mouse enter
box.setOnMouseEntered(e -> {
    box.setStyle("""
        -fx-background-color: linear-gradient(to right, #cfecf4ff, #f9c6eeff);
        -fx-border-color: #ffffff44;
        -fx-border-radius: 15;
        -fx-background-radius: 15;
        -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 12, 0.4, 0, 4);
    """);

    ScaleTransition st = new ScaleTransition(Duration.millis(200), box);
    st.setToX(1.05);
    st.setToY(1.05);
    st.setInterpolator(Interpolator.EASE_OUT);
    st.play();
});
box.setOnMouseExited(e -> {
    box.setStyle("""
        -fx-background-color: linear-gradient(to right,  #cfecf4ff, #f9c6eeff);
        -fx-border-color: #c8e6c9;
        -fx-border-radius: 15;
        -fx-background-radius: 15;
        -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 6, 0, 0, 1);
    """);

    ScaleTransition st = new ScaleTransition(Duration.millis(200), box);
    st.setToX(1.0);
    st.setToY(1.0);
    st.setInterpolator(Interpolator.EASE_IN);
    st.play();
});





        return box;
    }
}