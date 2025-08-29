package com.user.view;

import com.user.model.Booking;
import com.user.model.BookingStore;
import com.user.model.NotificationStore;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class NotificationPageP extends Application {

    public static List<Booking> providerBookings = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: #f9f9f9;");

        Button backbtn = new Button("Back");
        backbtn.setStyle("-fx-font-size: 14px; -fx-padding: 8px 16px;");
        backbtn.setOnAction(e -> {
            dashboardpageP db = new dashboardpageP();
            db.launchdashboard(primaryStage, "Ram");
        });

        root.getChildren().add(backbtn);

        for (int i = 0; i < NotificationStore.providerNotifications.size(); i++) {
            String message = NotificationStore.providerNotifications.get(i).getMessage();
            Booking booking = providerBookings.get(i);

            VBox card = new VBox(15);
            card.setPadding(new Insets(20));
            card.setMaxWidth(800);
            card.setStyle("""
                    -fx-background-color: #bedaffff;
                    -fx-background-radius: 16;
                    -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 4);
                    """);

            Label msgLabel = new Label(message);
            msgLabel.setWrapText(true);
            msgLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #333;");

            Label seekerLabel = new Label("👤 Seeker: " + booking.getSeekerName());
            seekerLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");

            Button acceptBtn = new Button("Accept");
            acceptBtn.setStyle(
                    "-fx-background-color: #27ae60; -fx-text-fill:white; -fx-font-size:14px; -fx-background-radius:8; -fx-pref-width:90px; -fx-pref-height:35px;"
            );

            Button rejectBtn = new Button("Reject");
            rejectBtn.setStyle(
                    "-fx-background-color: #e74c3c; -fx-text-fill:white; -fx-font-size:14px; -fx-background-radius:8; -fx-pref-width:90px; -fx-pref-height:35px;"
            );

            acceptBtn.setOnAction(e -> {
                Booking b = new Booking(
                        booking.getSeekerName(),
                        booking.getProviderName(),
                        booking.getServiceName(),
                        booking.getDateTime(),
                        booking.getSeekerAddress(),
                        booking.getNote(),
                        booking.getTime(),
                        booking.getDuration()
                );

                BookingStore.acceptBooking(b);
                acceptBtn.setDisable(true);
                rejectBtn.setDisable(true);
                card.setStyle("-fx-background-color: #C8F7C5; -fx-background-radius:16;");

                String msg = "Your booking request for service \"" + booking.getServiceName() +
                        "\" with provider " + booking.getProviderName() +
                        " on " + booking.getDateTime() + " has been ACCEPTED.";
                NotificationStore.seekerNotifications.add(new com.user.model.Notification(msg));

                Alert al = new Alert(Alert.AlertType.INFORMATION, "Booking Accepted & seeker notified!");
                al.showAndWait();
            });

            rejectBtn.setOnAction(e -> {
                BookingStore.rejectBooking(booking);
                acceptBtn.setDisable(true);
                rejectBtn.setDisable(true);
                card.setStyle("-fx-background-color: #F6C2C2; -fx-background-radius:16;");

                String msg = "Your booking request for service \"" + booking.getServiceName() +
                        "\" with provider " + booking.getProviderName() +
                        " on " + booking.getDateTime() + " has been REJECTED.";
                NotificationStore.seekerNotifications.add(new com.user.model.Notification(msg));

                Alert al = new Alert(Alert.AlertType.INFORMATION, "Booking Rejected & seeker notified!");
                al.showAndWait();
            });

            HBox btnBox = new HBox(15, acceptBtn, rejectBtn);
            btnBox.setAlignment(Pos.CENTER_RIGHT);

            HBox headerBox = new HBox(20, seekerLabel);
            headerBox.setAlignment(Pos.CENTER_LEFT);

            card.getChildren().addAll(msgLabel, headerBox, btnBox);
            root.getChildren().add(card);
        }

        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background:transparent;");

        Scene scene = new Scene(scrollPane, 1540, 795);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Provider Notifications");
        primaryStage.show();
    }
}
