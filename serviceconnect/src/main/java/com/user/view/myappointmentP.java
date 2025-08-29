package com.user.view;

import com.user.model.Booking;
import com.user.model.BookingStore;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class myappointmentP extends Application {

    private Label slotStatusLabel = new Label(); // Status after saving slot

    @Override
    public void start(Stage stage) {
        VBox content = new VBox(30);
        content.setPadding(new Insets(40));
        content.setAlignment(Pos.TOP_CENTER);

        Button backbtn=new Button("Back");
        backbtn.setOnAction(e->{
            dashboardpageP dp=new dashboardpageP();
            dp.launchdashboard(stage,"Ram");
        });

        Label title = new Label("📅  My Appointments");
        title.setFont(Font.font("Poppins", FontWeight.BOLD, 32));
        title.setTextFill(Color.web("#222"));
        title.setEffect(new DropShadow(4, Color.LIGHTGRAY));

       // Upcoming appointments section
VBox upcomingSection = createSection("🔮 Upcoming Appointments");

// Loop through accepted bookings
for (Booking b : BookingStore.upcomingBookings) {
    String dateTime = b.getDateTime().toString();  // format as needed
    String seeker =  b.getSeekerName();
    String service = b.getServiceName();
    

    VBox appointmentCard = createAppointmentCard(dateTime, seeker, service);
    upcomingSection.getChildren().add(appointmentCard);
}


        // VBox requests = createSection("📥 Appointment Requests",
        //     createRequestCard("25 July, 2PM", "Seeker: Sneha", "Counselling"),
        //     createRequestCard("26 July, 1:30PM", "Seeker: Nikhil", "Cooking")
        // );

        // VBox availability = createSection("🗓 Set Your Availability");
        // ComboBox<String> dayBox = new ComboBox<>();
        // dayBox.getItems().addAll("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun");
        // dayBox.setPromptText("Select Day");

        // TextField timeBox = new TextField();
        // timeBox.setPromptText("e.g. 10AM - 2PM");

        // Button save = new Button("✔ Save Slot");
        // save.setStyle("-fx-background-color: #00c853; -fx-text-fill: white; -fx-font-weight: bold;");
        // save.setOnAction(e -> {
        //     showAlertWithNavigation("Saved", "Your availability has been saved.", stage);
        //     slotStatusLabel.setText("✅ Slot Confirmed");
        //     slotStatusLabel.setTextFill(Color.GREEN);
        //     slotStatusLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        // });

        // HBox slotEntry = new HBox(15, dayBox, timeBox, save);
        // slotEntry.setAlignment(Pos.CENTER_LEFT);
        // availability.getChildren().addAll(slotEntry, slotStatusLabel);

        content.getChildren().addAll(backbtn,title, upcomingSection);


        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
         
        BorderPane root = new BorderPane(scrollPane);
        root.setStyle("-fx-background-color: linear-gradient(to right, #ffffff, #f5f9fd);");

        Scene scene = new Scene(root, 1540, 795);
        stage.setScene(scene);
        stage.setTitle("ServiceConnect - My Appointments");
        stage.show();
    }

    private void showAlertWithNavigation(String title, String message, Stage stage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.setOnHidden(event -> {
            dashboardpageP dashboard = new dashboardpageP();
            dashboard.launchdashboard(stage,"Ram");
        });

        alert.show();
    }

    private VBox createSection(String title, Node... items) {
        Label heading = new Label(title);
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        heading.setTextFill(Color.web("#333"));

        VBox box = new VBox(15);
        box.setPadding(new Insets(20));
        box.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 12; -fx-border-radius: 12; -fx-border-color: #ddd;");
        box.getChildren().add(heading);
        box.getChildren().addAll(items);
        return box;
    }

    private VBox createAppointmentCard(String date, String name, String service) {
        Label dateLabel = new Label("📅 " + date);
        dateLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Label nameLabel = new Label("👤 " + name);
        Label serviceLabel = new Label("💼 Service: " + service);

        VBox card = new VBox(5, dateLabel, nameLabel, serviceLabel);
        card.setPadding(new Insets(14));
        card.setStyle("-fx-background-color: #e3f2fd; -fx-border-color: #90caf9; -fx-border-radius: 8; -fx-background-radius: 8;");
        return card;
    }

    private VBox createRequestCard(String date, String name, String type) {
        Label dateLabel = new Label("📅 " + date);
        dateLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Label nameLabel = new Label("👤 " + name);
        Label typeLabel = new Label("📌 " + type);
        Label statusLabel = new Label();

        Button approve = new Button("✔ Approve");
        Button reject = new Button("❌ Reject");

        approve.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-weight: bold;");
        reject.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-font-weight: bold;");

        HBox actions = new HBox(12, approve, reject);
        actions.setAlignment(Pos.CENTER_LEFT);

        approve.setOnAction(e -> {
            showAlert("Appointment Accepted", name + " has been successfully booked.");
            statusLabel.setText("✔ Accepted");
            statusLabel.setTextFill(Color.GREEN);
            statusLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            actions.getChildren().remove(approve);
        });

        reject.setOnAction(e -> {
            showAlert("Appointment Rejected", name + " was declined.");
            statusLabel.setText("❌ Rejected");
            statusLabel.setTextFill(Color.RED);
            statusLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            actions.getChildren().remove(reject);
        });

        VBox card = new VBox(6, dateLabel, nameLabel, typeLabel, actions, statusLabel);
        card.setPadding(new Insets(14));
        card.setStyle("-fx-background-color: #fce4ec; -fx-border-color: #f8bbd0; -fx-border-radius: 10; -fx-background-radius: 10;");
        return card;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
}