package com.user.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import com.google.gson.*;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.kordamp.ikonli.javafx.FontIcon;

public class Dashboard_Admin {

    static String PROJECT_ID = "serviceconnect-eb8b6";

    public void show(Stage myStage) throws Exception {
        int totalSeekers = getCollectionCount("seeker");
        int totalProviders = getCollectionCount("provider");
        int totalServices = getCollectionCount("services");
        int activeProviders = getActiveProvidersCount();
        int totalComplaints = getCollectionCount("Complaints");
        int totalBookings = getCollectionCount("bookings");

        // ---------- Topbar ----------
        Label appTitle = new Label("SERVICE CONNECT");
        appTitle.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: white;");
        FontIcon userIcon = new FontIcon("fas-user");
        userIcon.setIconColor(Color.WHITE);
        userIcon.setIconSize(18);

        Button adminProfilebtn = new Button("Admin Profile", userIcon);
        adminProfilebtn.setStyle("-fx-background-color: #3a3a55; -fx-background-radius: 20; -fx-text-fill: white;");
        adminProfilebtn.setOnMouseEntered(e -> adminProfilebtn.setStyle("-fx-background-color: #5c5c77; -fx-text-fill: white; -fx-background-radius: 20;"));
        adminProfilebtn.setOnMouseExited(e -> adminProfilebtn.setStyle("-fx-background-color: #3a3a55; -fx-background-radius: 20; -fx-text-fill: white;"));

        BorderPane topBar = new BorderPane();
        topBar.setLeft(adminProfilebtn);
        topBar.setCenter(appTitle);
        BorderPane.setAlignment(appTitle, Pos.CENTER);
        topBar.setStyle("-fx-background-color: #1e1e2f; -fx-padding: 10;");
        topBar.setPrefHeight(60);

        // ---------- Sidebar ----------
        VBox sidebar = new VBox(15); // Increased spacing for better separation
        sidebar.setStyle("-fx-background-color: #34495E; -fx-padding: 20;");
        sidebar.setPrefWidth(220); // Slightly wider for comfortable button size
        sidebar.setAlignment(Pos.TOP_CENTER);  // Align children top center for neat layout

        // Optional: Add a sidebar title or logo label at the top
        Label sidebarTitle = new Label("MENU");
        sidebarTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white;");
        sidebarTitle.setPadding(new Insets(0, 0, 20, 0));

        // Create buttons with full width and consistent margin
        Button[] navButtons = {
            createSidebarButton("Dashboard", "fas-tachometer-alt", null),
            createSidebarButton("User Management", "fas-users", e -> new UserManagementPage_Admin(myStage)),
            createSidebarButton("Service Management", "fas-tools", e -> new ServiceManagementPage_Admin(myStage)),
            createSidebarButton("Bookings", "fas-calendar-check", e -> {
                try { new Bookings_Admin(myStage); } catch (Exception e1) { e1.printStackTrace(); }
            }),
            // createSidebarButton("Complaints", "fas-exclamation-circle", e -> {
            //     try { new Complaints_Admin(); } catch (Exception e1) { e1.printStackTrace(); }
            // }),
            createSidebarButton("Complaints", "fas-exclamation-circle", e -> {
    try {
        new Complaints_Admin().show();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}),
            createSidebarButton("Notifications", "fas-bell", e -> {
                try { new Notifications_Admin(myStage); } catch (Exception e1) { e1.printStackTrace(); }
            }),
            createSidebarButton("Settings", "fas-cogs", e -> new SettingsPage_Admin(myStage))
        };

        // Set each button to fill sidebar width nicely, and add margin
        for (Button btn : navButtons) {
            btn.setMaxWidth(Double.MAX_VALUE);  // allow buttons to expand full width of VBox
            VBox.setMargin(btn, new Insets(5, 0, 5, 0)); // vertical margin between buttons
        }

        sidebar.getChildren().add(sidebarTitle);
        sidebar.getChildren().addAll(navButtons);

        // ---------- Summary Grid ----------
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30, 0, 30, 0));
        grid.setHgap(30);
        grid.setVgap(30);
        grid.setAlignment(Pos.TOP_CENTER);

        grid.add(createSummaryCard("fas-user-friends", "Total Users", "Seekers: " + totalSeekers), 0, 0);
        grid.add(createSummaryCard("fas-concierge-bell", "Active", "Providers: " + activeProviders), 1, 0);
        grid.add(createSummaryCard("fas-user-cog", "Total", "Providers: " + totalProviders), 2, 0);
        grid.add(createSummaryCard("fas-tasks", "Total Services", "Posted: " + totalServices), 0, 1);
        grid.add(createSummaryCard("fas-hourglass-half", "Pending", "Approvals"), 1, 1);
        grid.add(createSummaryCard("fas-handshake", "Total Requests", "Bookings: " + totalBookings), 2, 1);
        grid.add(createSummaryCard("fas-tasks", "Completed", "Services"), 0, 2);
        grid.add(createSummaryCard("fas-money-bill", "Revenue This", "Month"), 1, 2);
        grid.add(createSummaryCard("fas-comments", "Complaints", "Received: " + totalComplaints), 2, 2);

        VBox centerWrapper = new VBox(grid);
        centerWrapper.setAlignment(Pos.TOP_CENTER);
        centerWrapper.setPadding(new Insets(30));
        centerWrapper.setStyle("-fx-background-color: #f5f7fa;");

        ScrollPane scrollPane = new ScrollPane(centerWrapper);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color:transparent;");

        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setLeft(sidebar);
        root.setCenter(scrollPane);

        Scene scene = new Scene(root, 1540, 795);
        myStage.setScene(scene);
        myStage.setTitle("Admin Dashboard");
        myStage.getIcons().add(new Image("/Assets//Images//ServiceConnectLogo.jpeg"));
        myStage.show();
    }


    private Button createSidebarButton(String text, String iconLiteral, EventHandler<ActionEvent> handler) {
        Button btn = (iconLiteral != null)
            ? new Button(text, new FontIcon(iconLiteral))
            : new Button(text);
        btn.setPrefHeight(40); // uniform height for buttons
        btn.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white; -fx-font-size: 14px;");
        btn.setAlignment(Pos.CENTER_LEFT); // Align text and icon to the left inside button
        btn.setGraphicTextGap(12); // gap between icon and text

        btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: #3e556e; -fx-text-fill: white; -fx-font-size: 14px;"));
        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white; -fx-font-size: 14px;"));

        if (handler != null) btn.setOnAction(handler);
        return btn;
    }

    private VBox createSummaryCard(String iconName, String title, String value) {
        FontIcon icon = new FontIcon(iconName);
        icon.setIconSize(30);
        icon.setIconColor(Color.DARKBLUE);

        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");

        Label valueLabel = new Label(value);
        valueLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: #333;");

        VBox box = new VBox(10, icon, titleLabel, valueLabel);
        box.setAlignment(Pos.CENTER);
        box.setPrefSize(200, 120);
        box.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 12; -fx-padding: 12;"
                + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.05), 4, 0, 0, 2);"
                + "-fx-border-color: #ddd; -fx-border-radius: 12;");

        box.setOnMouseEntered(e -> box.setStyle("-fx-background-color: #eef1f5; -fx-background-radius: 12; -fx-padding: 12;"
                + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 6, 0, 0, 4);"
                + "-fx-border-color: #ccc; -fx-border-radius: 12; -fx-cursor: hand;"));
        box.setOnMouseExited(e -> box.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 12; -fx-padding: 12;"
                + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.05), 4, 0, 0, 2);"
                + "-fx-border-color: #ddd; -fx-border-radius: 12;"));

        return box;
    }

    public static int getCollectionCount(String collectionName) {
        try {
            String urlStr = "https://firestore.googleapis.com/v1/projects/" + PROJECT_ID + "/databases/(default)/documents/" + collectionName;
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == 200) {
                InputStream inputStream = conn.getInputStream();
                String json = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
                JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
                JsonArray documents = jsonObject.getAsJsonArray("documents");

                return documents != null ? documents.size() : 0;
            } else {
                System.out.println("Error fetching data: " + conn.getResponseCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getActiveProvidersCount() {
        try {
            String urlStr = "https://firestore.googleapis.com/v1/projects/" + PROJECT_ID + "/databases/(default)/documents/provider";
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == 200) {
                InputStream inputStream = conn.getInputStream();
                String json = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
                JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();

                if (!jsonObject.has("documents") || jsonObject.get("documents").isJsonNull()) {
                    System.out.println("⚠️ No documents found in 'provider' collection.");
                    return 0;
                }

                JsonArray documents = jsonObject.getAsJsonArray("documents");
                int count = 0;

                for (JsonElement doc : documents) {
                    JsonObject fields = doc.getAsJsonObject().getAsJsonObject("fields");
                    if (fields != null && fields.has("status")) {
                        String status = fields.getAsJsonObject("status").get("stringValue").getAsString();
                        if (status.equalsIgnoreCase("active")) {
                            count++;
                        }
                    }
                }

                return count;
            } else {
                System.out.println("Failed to fetch provider data: " + conn.getResponseCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
