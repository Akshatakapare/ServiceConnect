package com.user.view;

import org.kordamp.ikonli.javafx.FontIcon;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ReviewPage_Admin extends Application {

    private TableView<Review> reviewTable;
    private ObservableList<Review> reviewList;

    @Override
    public void start(Stage myStage) {

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

        // ------------- Topbar -------------
        Label titleLabel = new Label("REVIEWS MANAGEMENT");
        titleLabel.setStyle("-fx-font-weight:bold;-fx-font-size:20px;-fx-text-fill:white");
        BorderPane topBar = new BorderPane();
        topBar.setCenter(titleLabel);
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        topBar.setStyle("-fx-background-color:#1e1e2f;-fx-padding:10;");
        topBar.setPrefHeight(60);

        // -------- Sidebar --------
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

        // --- Filter Bar ---
        ComboBox<String> ratingFilter = new ComboBox<>();
        ratingFilter.getItems().addAll("All", "5 Stars", "4 Stars", "3 Stars", "2 Stars", "1 Star");
        ratingFilter.setValue("All");

        DatePicker filterDate = new DatePicker();
        Button filterButton = new Button("Apply Filter");

        HBox filterBar = new HBox(15, new Label("Filter by Rating:"), ratingFilter,
                new Label("Date:"), filterDate, filterButton);
        filterBar.setPadding(new Insets(10));
        filterBar.setAlignment(Pos.CENTER_LEFT);

        // --- Review Table ---
        reviewTable = new TableView<>();
        reviewTable.setPrefHeight(400);

        TableColumn<Review, String> userCol = new TableColumn<>("User");
        userCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        userCol.setMinWidth(150);

        TableColumn<Review, String> reviewCol = new TableColumn<>("Review");
        reviewCol.setCellValueFactory(new PropertyValueFactory<>("content"));
        reviewCol.setMinWidth(300);

        TableColumn<Review, Integer> ratingCol = new TableColumn<>("Rating");
        ratingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));
        ratingCol.setMinWidth(80);

        TableColumn<Review, String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateCol.setMinWidth(120);

        TableColumn<Review, Void> actionCol = new TableColumn<>("Action");
        actionCol.setCellFactory(col -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(e -> {
                    Review review = getTableView().getItems().get(getIndex());
                    reviewList.remove(review);
                });
                deleteButton.setStyle("-fx-background-color:#c0392b;-fx-text-fill:white;-fx-background-radius:10;");
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });

        reviewTable.getColumns().addAll(userCol, reviewCol, ratingCol, dateCol, actionCol);

        reviewList = FXCollections.observableArrayList();
        reviewTable.setItems(reviewList);
        // fetchReviewsFromFirestore(); // Uncomment when implemented

        filterButton.setOnAction(e -> {
            // TODO: Add filter logic here or connect to DB query
        });

        VBox centerPane = new VBox(20, filterBar, reviewTable);
        centerPane.setStyle("-fx-background-color:#f5f7fa;-fx-padding:25px");

        ScrollPane centerScroll = new ScrollPane(centerPane);
        centerScroll.setFitToWidth(true);

        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setLeft(sidebar);
        root.setCenter(centerScroll);

        Scene scene = new Scene(root, 1540, 795);
        myStage.setScene(scene);
        myStage.setTitle("Admin - Reviews");
        myStage.getIcons().add(new Image("Assets\\ServiceConnectLogo.jpeg"));
        myStage.show();
    }

    // Sidebar Button Factory method for consistent styling
    private Button createSidebarButton(String text, FontIcon icon,
            javafx.event.EventHandler<javafx.event.ActionEvent> handler) {
        Button btn = (icon != null) ? new Button(text, icon) : new Button(text);
        btn.setPrefHeight(40);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white; -fx-font-size: 14px;");
        btn.setAlignment(Pos.CENTER_LEFT);
        btn.setGraphicTextGap(12);

        btn.setOnMouseEntered(
                e -> btn.setStyle("-fx-background-color: #3e556e; -fx-text-fill: white; -fx-font-size: 14px;"));
        btn.setOnMouseExited(
                e -> btn.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white; -fx-font-size: 14px;"));

        if (handler != null)
            btn.setOnAction(handler);
        return btn;
    }

    // --- Model Class ---
    public static class Review {
        private String username;
        private String content;
        private int rating;
        private String date;

        public Review(String username, String content, int rating, String date) {
            this.username = username;
            this.content = content;
            this.rating = rating;
            this.date = date;
        }

        public String getUsername() { return username; }
        public String getContent() { return content; }
        public int getRating() { return rating; }
        public String getDate() { return date; }
    }

}
