package com.user.view;

import com.user.model.NotificationStore;
import com.user.model.Notification;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NotificationUI {

   public void showNotifications(Stage stage) {
    VBox root = new VBox(10);
    root.setPadding(new Insets(15));

    Button backbtn = new Button("Back");
    backbtn.setOnAction(e -> {
        DashboardView db = new DashboardView();
        db.launchDashboard(stage, "seeker");
    });

    root.getChildren().add(backbtn);

    // Dynamically load notifications from store
    for (Notification n : NotificationStore.seekerNotifications) {
        Label label = new Label(n.getMessage() + "    (" + n.getDateTime().toString() + ")");
        label.setStyle("-fx-background-color: #f4b9b9ff; -fx-padding:15; -fx-border-radius:5; -fx-background-radius:5;");
        root.getChildren().add(label); // only add labels inside loop
    }

    ScrollPane scrollPane = new ScrollPane(root);
    scrollPane.setFitToWidth(true);

    Scene scene = new Scene(scrollPane, 1540, 795);
    stage.setTitle("Seeker Notifications");
    stage.setScene(scene);
    stage.show();
}
}