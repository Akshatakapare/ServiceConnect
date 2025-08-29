package com.user.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoaderUI {
    public static void showLoaderAndProceed(Stage stage, String providerName) {
        Stage loaderStage = new Stage(StageStyle.UNDECORATED);
        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: white; -fx-padding: 30;");

        try {
            Image gif = new Image(LoaderUI.class.getResourceAsStream("/Assets/Images/gpay.gif")); // Make sure this exists
            ImageView gifView = new ImageView(gif);
            gifView.setFitWidth(200);
            gifView.setPreserveRatio(true);
            root.getChildren().addAll(gifView, new Label(" payment successful..."));

            Scene scene = new Scene(root, 300, 250);
            loaderStage.setScene(scene);
            loaderStage.show();

            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                javafx.application.Platform.runLater(() -> {
                    loaderStage.close();
                    try {
                        new StarRatingUI().show(loaderStage);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
            }).start();

        } catch (Exception e) {
            System.out.println("GIF not found.");
            e.printStackTrace();
        }
    }
}
