

package com.user.view;

import com.user.model.Rating;
import com.user.model.RatingStore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;
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

public class myratingpageP {

    public static void show(Stage stage, String providerName) {

        BorderPane rootLayout = new BorderPane();
        

        Label heading = new Label("Your Ratings");
        heading.setStyle("-fx-font-size: 24px; -fx-text-fill: #333;");

        TableView<Rating> table = new TableView<>();
        table.setPrefWidth(600);

        TableColumn<Rating, String> reviewerCol = new TableColumn<>("Seeker Name");
        reviewerCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getReviewerName()));

        TableColumn<Rating, String> serviceCol = new TableColumn<>("Service");
        serviceCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getServiceName()));

        TableColumn<Rating, Integer> starCol = new TableColumn<>("Stars");
        starCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getStars()).asObject());

        TableColumn<Rating, String> feedbackCol = new TableColumn<>("Feedback");
        feedbackCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getFeedback()));

        table.getColumns().addAll(reviewerCol, serviceCol, starCol, feedbackCol);

        ObservableList<Rating> providerRatings = FXCollections.observableArrayList();
        for (Rating r : RatingStore.allRatings) {
            if (r.getRevieweeName().equalsIgnoreCase(providerName)) {
                providerRatings.add(r);
            }
        }

        table.setItems(providerRatings);

        Button back = new Button("Back to Dashboard");
        back.setOnAction(e -> {
            dashboardpageP db = new dashboardpageP();
            db.launchdashboard(stage, providerName);
        });

        
        
        VBox center = new VBox(15, heading, table, back);
        center.setAlignment(Pos.TOP_CENTER);
        rootLayout.setCenter(center);
       
        Scene scene = new Scene(rootLayout, 1540,795);
        stage.setScene(scene);
        stage.setTitle("Provider Ratings");
        stage.show();
    }
}

