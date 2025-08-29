package com.user.view;

import java.time.LocalDate;

import org.kordamp.ikonli.javafx.FontIcon;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Notifications_Admin {

    Scene notificationScene;
    Stage NotificationStage;

    public Scene getNotificationScene() {
        return notificationScene;
    }

    public void setNotificationScene(Scene notificationScene) {
        this.notificationScene = notificationScene;
    }

    public Stage getNotificationStage() {
        return NotificationStage;
    }

    public void setNotificationStage(Stage notificationStage) {
        NotificationStage = notificationStage;
    }

    public Notifications_Admin(Stage myStage) throws Exception {

        // ------------- Icons -------------
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

        // --- Top Bar ---
        Label title = new Label("Notifications Manager");
        title.setStyle("-fx-font-weight:bold;-fx-font-size:20px;-fx-text-fill:white");

        BorderPane topBar = new BorderPane();
        topBar.setPadding(new Insets(10));
        topBar.setCenter(title);
        BorderPane.setAlignment(title, Pos.CENTER);
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

        Button dashboardButton = createSidebarButton("Dashboard", dashboardIcon, e -> {
            try {
                new Dashboard_Admin().show(myStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button usersButton = createSidebarButton("User Management", usersIcon, e -> {
            new UserManagementPage_Admin(myStage);
        });

        Button servicesButton = createSidebarButton("Service Management", servicesIcon, e -> {
            new ServiceManagementPage_Admin(myStage);
        });

        Button bookingsButton = createSidebarButton("Bookings", bookingsIcon, e -> {
            try {
                new Bookings_Admin(myStage);
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
                new Notifications_Admin(myStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button settingsButton = createSidebarButton("Settings", settingsIcon, e -> {
            new SettingsPage_Admin(myStage);
        });

        sidebar.getChildren().addAll(sidebarTitle, dashboardButton, usersButton, servicesButton, bookingsButton,
                disputesButton, notificationsButton, settingsButton);

        // --- Notification Creation UI ---
        Label l1 = new Label("Create new Notification");
        l1.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        BorderPane topbox = new BorderPane();
        topbox.setCenter(l1);

        Text t1 = new Text("Title:");
        TextField titleTextField = new TextField();

        Text t2 = new Text("Message:");
        TextArea messageArea = new TextArea();
        messageArea.setPromptText("Enter Message");
        messageArea.setPrefRowCount(4);

        Text t3 = new Text("Audience:");
        ComboBox<String> audienceCombo = new ComboBox<>();
        audienceCombo.getItems().addAll("All", "Service Seekers", "Service Providers");
        audienceCombo.setValue("All");

        Text t4 = new Text("Channels:");
        CheckBox pushCheck = new CheckBox("Push");
        CheckBox emailCheck = new CheckBox("Email");
        CheckBox smsCheck = new CheckBox("SMS");

        Text t5 = new Text("Schedule:");
        DatePicker scheduleDate = new DatePicker(LocalDate.now());
        TextField timeField = new TextField();
        timeField.setPromptText("HH:MM");

        Button sendBtn = new Button("Send Notification");
        sendBtn.setStyle("-fx-background-color: #27AE60; -fx-text-fill: white; -fx-background-radius: 8;");
        sendBtn.setOnMouseEntered(e -> sendBtn.setStyle("-fx-background-color: #2ECC71; -fx-text-fill: white;"));
        sendBtn.setOnMouseExited(e -> sendBtn.setStyle("-fx-background-color: #27AE60; -fx-text-fill: white;"));

        VBox CreateNotification = new VBox(15, topbox, t1, titleTextField, t2, messageArea, t3, audienceCombo, t4,
                pushCheck, emailCheck, smsCheck, t5, scheduleDate, timeField, sendBtn);
        CreateNotification.setPadding(new Insets(20));
        CreateNotification.setStyle(
                "-fx-background-color: white; -fx-padding: 20; -fx-border-radius: 12; -fx-background-radius: 12; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 12, 0, 4, 4);");

        // --- Sent Notifications History Table ---
        Label tableTitle = new Label("📜 Sent Notifications History");
        tableTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        TableView<NotificationModel> table = new TableView<>();
        table.setPrefHeight(200);

        TableColumn<NotificationModel, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<NotificationModel, String> audienceCol = new TableColumn<>("Audience");
        audienceCol.setCellValueFactory(new PropertyValueFactory<>("audience"));

        TableColumn<NotificationModel, String> channelCol = new TableColumn<>("Channels");
        channelCol.setCellValueFactory(new PropertyValueFactory<>("channels"));

        TableColumn<NotificationModel, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<NotificationModel, String> timeCol = new TableColumn<>("Time");
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));

        table.getColumns().addAll(titleCol, audienceCol, channelCol, statusCol, timeCol);
        table.setPrefHeight(200);

        VBox tableBox = new VBox(10, tableTitle, table);
        tableBox.setPadding(new Insets(20));

        VBox centerContent = new VBox(15, CreateNotification, tableBox);
        centerContent.setPadding(new Insets(20));

        ScrollPane centerScroll = new ScrollPane(centerContent);
        centerScroll.setFitToWidth(true);
        centerScroll.setStyle("-fx-background: #ECF0F1;");

        BorderPane br = new BorderPane();
        br.setTop(topBar);
        br.setLeft(sidebar);
        br.setCenter(centerScroll);

        Scene sc = new Scene(br, 1540, 795);
        myStage.setScene(sc);
        myStage.getIcons().add(new Image("Assets\\ServiceConnectLogo.jpeg"));
        myStage.show();
    }

    // Sidebar Button Factory
    private Button createSidebarButton(String text, FontIcon icon,
            javafx.event.EventHandler<javafx.event.ActionEvent> handler) {
        Button btn = (icon != null) ? new Button(text, icon) : new Button(text);
        btn.setPrefHeight(40);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white; -fx-font-size: 14px;");
        btn.setAlignment(Pos.CENTER_LEFT);
        btn.setGraphicTextGap(12);

        btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: #3e556e; -fx-text-fill: white; -fx-font-size: 14px;"));
        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white; -fx-font-size: 14px;"));

        if (handler != null)
            btn.setOnAction(handler);
        return btn;
    }

    // NotificationModel to represent each notification row in table
    public static class NotificationModel {
        private String title;
        private String audience;
        private String channels;
        private String status;
        private String time;

        public NotificationModel(String title, String audience, String channels, String status, String time) {
            this.title = title;
            this.audience = audience;
            this.channels = channels;
            this.status = status;
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public String getAudience() {
            return audience;
        }

        public String getChannels() {
            return channels;
        }

        public String getStatus() {
            return status;
        }

        public String getTime() {
            return time;
        }
    }
}
