package com.user.view;

import org.kordamp.ikonli.javafx.FontIcon;

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

public class ServiceManagementPage_Admin {

    Scene ServiceManagementPageScene;
    Stage ServiceManagementPageStage;

    public Scene getServiceManagementPageScene() {
        return ServiceManagementPageScene;
    }

    public void setServiceManagementPageScene(Scene serviceManagementPageScene) {
        ServiceManagementPageScene = serviceManagementPageScene;
    }

    public Stage getServiceManagementPageStage() {
        return ServiceManagementPageStage;
    }

    public void setServiceManagementPageStage(Stage serviceManagementPageStage) {
        ServiceManagementPageStage = serviceManagementPageStage;
    }

    private TableView<Service> serviceTable;
    private ObservableList<Service> serviceList;

    public ServiceManagementPage_Admin(Stage myStage) {

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
        Label titleLabel = new Label("SERVICE MANAGEMENT");
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
                disputesButton, notificationsButton,settingsButton);

        // --- TableView Columns ---
        serviceTable = new TableView<>();
        serviceTable.setPrefHeight(500);

        TableColumn<Service, Integer> idCol = new TableColumn<>("Service ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setMinWidth(100);

        TableColumn<Service, String> nameCol = new TableColumn<>("Service Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setMinWidth(150);

        TableColumn<Service, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryCol.setMinWidth(100);

        TableColumn<Service, String> providerCol = new TableColumn<>("Provider");
        providerCol.setCellValueFactory(new PropertyValueFactory<>("provider"));
        providerCol.setMinWidth(120);

        TableColumn<Service, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusCol.setMinWidth(100);

        TableColumn<Service, Void> actionCol = new TableColumn<>("Action");
        actionCol.setCellFactory(col -> new TableCell<>() {
            private final Button toggleBtn = new Button();

            {
                toggleBtn.setOnAction(e -> {
                    Service service = getTableView().getItems().get(getIndex());
                    if (service.getStatus().equalsIgnoreCase("Active")) {
                        service.setStatus("Inactive");
                    } else {
                        service.setStatus("Active");
                    }
                    serviceTable.refresh();
                });
                toggleBtn.setStyle("-fx-background-radius: 10; -fx-text-fill: white;");
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Service service = getTableView().getItems().get(getIndex());
                    toggleBtn.setText(service.getStatus().equalsIgnoreCase("Active") ? "Deactivate" : "Activate");
                    toggleBtn.setStyle(
                            service.getStatus().equalsIgnoreCase("Active") ?
                                    "-fx-background-color:#c0392b;-fx-text-fill:white;-fx-background-radius:10;" :
                                    "-fx-background-color:#27ae60;-fx-text-fill:white;-fx-background-radius:10;"
                    );
                    setGraphic(toggleBtn);
                }
            }
        });

        serviceTable.getColumns().addAll(idCol, nameCol, categoryCol, providerCol, statusCol, actionCol);

        // --- Sample Data (to be replaced with DB) ---
        serviceList = FXCollections.observableArrayList(
                new Service(1, "Plumbing", "Home Repair", "John Doe", "Active"),
                new Service(2, "Electrician", "Home Repair", "Amit Sharma", "Inactive"),
                new Service(3, "Delivery", "Logistics", "Sneha Patil", "Active")
        );
        serviceTable.setItems(serviceList);

        // --- Layout ---
        VBox centerPane = new VBox(20, new Label("All Services:"), serviceTable);
        centerPane.setStyle("-fx-background-color: #f4f6f8; -fx-padding: 25px;");

        ScrollPane centerScroll = new ScrollPane(centerPane);
        centerScroll.setFitToWidth(true);

        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setLeft(sidebar);
        root.setCenter(centerScroll);

        Scene scene = new Scene(root, 1540, 795);
        myStage.setScene(scene);
        myStage.setTitle("Admin - Service Management");
        myStage.getIcons().add(new Image("Assets\\ServiceConnectLogo.jpeg"));
        myStage.show();
    }

    // --- Sidebar Button Factory ---
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

    // --- Model Class ---
    public static class Service {
        private int id;
        private String name;
        private String category;
        private String provider;
        private String status;

        public Service(int id, String name, String category, String provider, String status) {
            this.id = id;
            this.name = name;
            this.category = category;
            this.provider = provider;
            this.status = status;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getCategory() {
            return category;
        }

        public String getProvider() {
            return provider;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
