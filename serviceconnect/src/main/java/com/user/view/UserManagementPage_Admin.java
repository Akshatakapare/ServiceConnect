package com.user.view;

import com.google.gson.*;
import javafx.application.Platform;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class UserManagementPage_Admin {

    private final String PROJECT_ID = "serviceconnect-eb8b6";
    private TableView<User> userTable;
    private ObservableList<User> userList;

    private Stage userStage;

    public UserManagementPage_Admin(Stage myStage) {
        this.userStage = myStage;

        // --- Top Bar ---
        Label titleLabel = new Label("USER MANAGEMENT");
        titleLabel.setStyle("-fx-font-weight:bold;-fx-font-size:20px;-fx-text-fill:white");

        BorderPane topBar = new BorderPane();
        topBar.setCenter(titleLabel);
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        topBar.setStyle("-fx-background-color:#1e1e2f;-fx-padding:10;");
        topBar.setPrefHeight(60);

        // --- Sidebar ---
        VBox sidebar = new VBox(15);
        sidebar.setStyle("-fx-background-color: #34495E; -fx-padding: 20;");
        sidebar.setPrefWidth(220);
        sidebar.setAlignment(Pos.TOP_CENTER);

        Label sidebarTitle = new Label("MENU");
        sidebarTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white;");
        sidebarTitle.setPadding(new Insets(0, 0, 20, 0));

        // Sidebar buttons — exactly as your code
        FontIcon userIcon = new FontIcon("fas-user");
        userIcon.setIconSize(18);
        userIcon.setIconColor(Color.WHITE);

        FontIcon dashboardIcon = new FontIcon("fas-tachometer-alt");
        dashboardIcon.setIconSize(16);
        dashboardIcon.setIconColor(Color.BLACK);

        FontIcon usersIcon = new FontIcon("fas-users");
        usersIcon.setIconSize(16);
        usersIcon.setIconColor(Color.BLACK);

        FontIcon servicesIcon = new FontIcon("fas-tools");
        servicesIcon.setIconSize(16);
        servicesIcon.setIconColor(Color.BLACK);

        FontIcon bookingsIcon = new FontIcon("fas-calendar-check");
        bookingsIcon.setIconSize(16);
        bookingsIcon.setIconColor(Color.BLACK);

        FontIcon disputesIcon = new FontIcon("fas-exclamation-circle");
        disputesIcon.setIconSize(16);
        disputesIcon.setIconColor(Color.BLACK);

        FontIcon notificationsIcon = new FontIcon("fas-bell");
        notificationsIcon.setIconSize(16);
        notificationsIcon.setIconColor(Color.BLACK);

        FontIcon settingsIcon = new FontIcon("fas-cogs");
        settingsIcon.setIconSize(16);
        settingsIcon.setIconColor(Color.BLACK);

        Button dashboardButton = createSidebarButton("Dashboard", dashboardIcon, e -> {
            try {
                new Dashboard_Admin().show(myStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button usersButton = createSidebarButton("User Management", usersIcon, e -> {
            // Optional: reload or ignore
            new UserManagementPage_Admin(userStage);
        });

        Button servicesButton = createSidebarButton("Service Management", servicesIcon, e -> {
            new ServiceManagementPage_Admin(userStage);
        });

        Button bookingsButton = createSidebarButton("Bookings", bookingsIcon, e -> {
            try {
                new Bookings_Admin(userStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button disputesButton = createSidebarButton("Complaints", disputesIcon, e -> {
            try {
                new Complaints_Admin().show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button notificationsButton = createSidebarButton("Notifications", notificationsIcon, e -> {
            try {
                new Notifications_Admin(userStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button settingsButton = createSidebarButton("Settings", settingsIcon, e -> {
            new SettingsPage_Admin(userStage);
        });

        sidebar.getChildren().addAll(sidebarTitle, dashboardButton, usersButton, servicesButton, bookingsButton,
                disputesButton, notificationsButton, settingsButton);

        // --- TableView setup ---
        userTable = new TableView<>();
        userTable.setPrefHeight(500);

        TableColumn<User, Integer> idCol = new TableColumn<>("User ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setMinWidth(80);

        TableColumn<User, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setMinWidth(140);

        TableColumn<User, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailCol.setMinWidth(180);

        TableColumn<User, String> roleCol = new TableColumn<>("Role");
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        roleCol.setMinWidth(100);

        TableColumn<User, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusCol.setMinWidth(100);

        userTable.getColumns().addAll(idCol, nameCol, emailCol, roleCol, statusCol);

        // --- Center pane ---
        VBox centerPane = new VBox(20, new Label("All Registered Seekers:"), userTable);
        centerPane.setStyle("-fx-background-color: #f4f6f8; -fx-padding: 25px;");

        ScrollPane centerScroll = new ScrollPane(centerPane);
        centerScroll.setFitToWidth(true);

        // --- Layout ---
        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setLeft(sidebar);
        root.setCenter(centerScroll);

        Scene scene = new Scene(root, 1540, 795);
        userStage.setScene(scene);
        userStage.setTitle("Admin - User Management");
        userStage.getIcons().add(new Image("/Assets//Images//ServiceConnectLogo.jpeg"));
        userStage.show();

        // ✅ Test: Add dummy data to confirm TableView works
ObservableList<User> testData = FXCollections.observableArrayList(
    new User(1, "Test User", "test@example.com", "Seeker", "Active"),
    new User(2, "Alice Smith", "alice@sample.com", "Seeker", "Inactive")
);
userTable.setItems(testData);


        // --- Fetch seekers data async ---
        new Thread(() -> {
            ObservableList<User> seekers = fetchSeekersFromFirestore();
            Platform.runLater(() -> userTable.setItems(seekers));
        }).start();
    }

    private ObservableList<User> fetchSeekersFromFirestore() {
        ObservableList<User> seekers = FXCollections.observableArrayList();

        try {
            String urlStr = "https://firestore.googleapis.com/v1/projects/" + PROJECT_ID + "/databases/(default)/documents/seeker";
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == 200) {
                InputStream inputStream = conn.getInputStream();
                String json = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
                JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();

                // JsonArray documents = jsonObject.getAsJsonArray("documents");
                JsonArray documents = jsonObject.has("documents") ? jsonObject.getAsJsonArray("documents") : null;
                    if (documents == null) {
                        System.out.println("No documents found or permission denied.");
                        return seekers; // Return empty list
                    }
                if (documents != null) {
                    int idCounter = 1;
                    for (JsonElement docElement : documents) {
                        JsonObject doc = docElement.getAsJsonObject();
                        JsonObject fields = doc.getAsJsonObject("fields");

                        String name = safeGetString(fields, "name");
                        String email = safeGetString(fields, "email");
                        String status = safeGetString(fields, "status");
                        if (status.isEmpty()) status = "Inactive";
                        String role = "Seeker";

                        seekers.add(new User(idCounter++, name, email, role, status));
                    }
                }
            } else {
                System.out.println("Error fetching seeker data: HTTP " + conn.getResponseCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return seekers;
    }

    private String safeGetString(JsonObject fields, String key) {
        if (fields != null && fields.has(key) && fields.getAsJsonObject(key).has("stringValue")) {
            return fields.getAsJsonObject(key).get("stringValue").getAsString();
        }
        return "";
    }

    private Button createSidebarButton(String text, FontIcon icon, javafx.event.EventHandler<javafx.event.ActionEvent> handler) {
        Button btn = (icon != null) ? new Button(text, icon) : new Button(text);
        btn.setPrefHeight(40);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white; -fx-font-size: 14px;");
        btn.setAlignment(Pos.CENTER_LEFT);
        btn.setGraphicTextGap(12);

        btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: #3e556e; -fx-text-fill: white; -fx-font-size: 14px;"));
        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white; -fx-font-size: 14px;"));

        if (handler != null) btn.setOnAction(handler);
        return btn;
    }

    // Model class
    public static class User {
        private final int id;
        private final String name;
        private final String email;
        private final String role;
        private final String status;

        public User(int id, String name, String email, String role, String status) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.role = role;
            this.status = status;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public String getEmail() { return email; }
        public String getRole() { return role; }
        public String getStatus() { return status; }
    }
}


