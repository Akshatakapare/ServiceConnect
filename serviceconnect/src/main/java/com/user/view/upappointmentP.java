package com.user.view;

import com.user.model.Booking;
import com.user.model.BookingStore;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class upappointmentP extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Manage My Appointments");

        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: white;");

        // Back Button
        Button backButton = new Button("⬅ Back to Dashboard");
        backButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold;");
        backButton.setOnAction(e -> {
            dashboardpageP dashboard = new dashboardpageP();
            dashboard.launchdashboard(primaryStage, "Ram");
        });

        Label upcoming = new Label("🔔 Upcoming Appointments");
        upcoming.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        upcoming.setPadding(new Insets(10, 0, 0, 0));

        VBox cardList = new VBox(16);
        cardList.setPadding(new Insets(10));

        // Add 6 cards
//        for (Booking b : BookingStore.upcomingBookings) {
//     String date = b.getDateTime().toString(); // or format into dd-MM HH:mm
//     cardList.getChildren().add(createCard(date, b.getSeekerName(), b.getServiceName()));
// }

        for (Booking b : BookingStore.upcomingBookings) {
    cardList.getChildren().add(createCard(b));
}
        ScrollPane scrollPane = new ScrollPane(cardList);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: white; -fx-background-color: white;");
        scrollPane.setPrefHeight(600);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        cardList.setStyle("-fx-background-color: white;");

        root.getChildren().addAll(backButton, upcoming, scrollPane);

        Scene scene = new Scene(root, 1540, 795);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createCard(Booking b) {
    VBox card = new VBox(8);
    card.setPadding(new Insets(12));
    card.setBackground(new Background(new BackgroundFill(Color.web("#E0F0FF"), new CornerRadii(10), Insets.EMPTY)));
    card.setStyle("-fx-border-color: #B0D6F7; -fx-border-radius: 10;");
    card.setPrefWidth(650);

    Label dateLabel = new Label("📅 " + b.getDateTime().toString());
    dateLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

    Label nameLabel = new Label("👤 Seeker: " + b.getSeekerName());
    nameLabel.setFont(Font.font("Arial", 13));

    Label addressLabel = new Label("🏠 Address: " + b.getSeekerAddress());
    addressLabel.setFont(Font.font("Arial", 13));

    Label noteLabel = new Label("📝 Note: " + b.getNote());
    noteLabel.setFont(Font.font("Arial", 13));

    Label timeLabel = new Label("🕒 Time: " + b.getTime());
    Label durationLabel = new Label("⏳ Duration: " + b.getDuration());

    Label serviceLabel = new Label("📄 Service: " + b.getServiceName());
    serviceLabel.setFont(Font.font("Arial", 13));

    card.getChildren().addAll(dateLabel, nameLabel, addressLabel, noteLabel, timeLabel, durationLabel, serviceLabel);
    return card;
}
}