// package com.user.view;

// import org.kordamp.ikonli.javafx.FontIcon;

// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.*;
// import javafx.scene.image.Image;
// import javafx.scene.layout.*;
// import javafx.scene.paint.Color;
// import javafx.stage.Stage;

// public class Bookings_Admin {
//     public Scene getBookingScene() {
//         return bookingScene;
//     }

//     public void setBookingScene(Scene bookingScene) {
//         this.bookingScene = bookingScene;
//     }

//     public Stage getBookingStage() {
//         return BookingStage;
//     }

//     public void setBookingStage(Stage bookingStage) {
//         BookingStage = bookingStage;
//     }

//     Scene bookingScene;
//     Stage BookingStage;

//     public Bookings_Admin(Stage myStage) throws Exception {

//         // ------------- Icons -------------
//         FontIcon userIcon = new FontIcon("fas-user");
//         userIcon.setIconSize(18);
//         userIcon.setIconColor(Color.WHITE);

//         FontIcon dashboardIcon = new FontIcon("fas-tachometer-alt");
//         dashboardIcon.setIconSize(16);
//         dashboardIcon.setIconColor(Color.BLACK);

//         FontIcon usersIcon = new FontIcon("fas-users");
//         usersIcon.setIconSize(16);
//         usersIcon.setIconColor(Color.BLACK);

//         FontIcon servicesIcon = new FontIcon("fas-tools");
//         servicesIcon.setIconSize(16);
//         servicesIcon.setIconColor(Color.BLACK);

//         FontIcon bookingsIcon = new FontIcon("fas-calendar-check");
//         bookingsIcon.setIconSize(16);
//         bookingsIcon.setIconColor(Color.BLACK);

//         FontIcon usersmanagementIcon = new FontIcon("fas-users");
//         usersmanagementIcon.setIconSize(16);
//         usersmanagementIcon.setIconColor(Color.BLACK);

//         FontIcon disputesIcon = new FontIcon("fas-exclamation-circle");
//         disputesIcon.setIconSize(16);
//         disputesIcon.setIconColor(Color.BLACK);

//         FontIcon notificationsIcon = new FontIcon("fas-bell");
//         notificationsIcon.setIconSize(16);
//         notificationsIcon.setIconColor(Color.BLACK);

//         FontIcon settingsIcon = new FontIcon("fas-cogs");
//         settingsIcon.setIconSize(16);
//         settingsIcon.setIconColor(Color.BLACK);

//         // ------------- Top Bar -------------
//         Label title = new Label("Booking Management");
//         title.setStyle("-fx-font-weight:bold;-fx-font-size:20px;-fx-text-fill:white");

//         BorderPane topbar = new BorderPane();
//         topbar.setPadding(new Insets(10, 20, 10, 20));
//         topbar.setCenter(title);
//         BorderPane.setAlignment(title, Pos.CENTER);
//         topbar.setStyle("-fx-background-color:#1e1e2f;-fx-padding:10;");
//         topbar.setPrefHeight(60);

//         // -------- Sidebar --------
//         VBox sideBar = new VBox(15);
//         sideBar.setStyle("-fx-background-color: #34495E; -fx-padding: 20;");
//         sideBar.setPrefWidth(220);
//         sideBar.setAlignment(Pos.TOP_CENTER);

//         Label sidebarTitle = new Label("MENU");
//         sidebarTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white;");
//         sidebarTitle.setPadding(new Insets(0, 0, 20, 0));

//         Button dashboardButton = createSidebarButton("Dashboard", dashboardIcon, e -> {
//             try {
//                 new Dashboard_Admin().show(myStage);
//             } catch (Exception ex) {
//                 ex.printStackTrace();
//             }
//         });

//         Button usersButton = createSidebarButton("User Management", usersmanagementIcon, e -> {
//             new UserManagementPage_Admin(myStage);
//         });

//         Button servicesButton = createSidebarButton("Service Management", servicesIcon, e -> {
//             new ServiceManagementPage_Admin(myStage);
//         });

//         Button bookingsButton = createSidebarButton("Bookings", bookingsIcon, e -> {
//             try {
//                 new Bookings_Admin(myStage);
//             } catch (Exception ex) {
//                 ex.printStackTrace();
//             }
//         });

//         Button disputesButton = createSidebarButton("Complaints", disputesIcon, e -> {
//             try {
//                 new Complaints_Admin().show();
//             } catch (Exception ex) {
//                 ex.printStackTrace();
//             }
//         });
    
//         Button notificationsButton = createSidebarButton("Notifications", notificationsIcon, e -> {
//             try {
//                 new Notifications_Admin(myStage);
//             } catch (Exception ex) {
//                 ex.printStackTrace();
//             }
//         });


//         Button settingsButton = createSidebarButton("Settings", settingsIcon, e -> {
//             new SettingsPage_Admin(myStage);
//         });

//         sideBar.getChildren().addAll(sidebarTitle, dashboardButton, usersButton, servicesButton, bookingsButton,
//                 disputesButton, notificationsButton, settingsButton);

//         // --------------------------Filter box--------------------------

//         TextField searchField = new TextField();
//         searchField.setPromptText("Search by name, ID or service");
//         searchField.setPrefWidth(250);

//         ComboBox<String> statusFilter = new ComboBox<>();
//         statusFilter.getItems().addAll("All", "Pending", "Confirmed", "Completed", "Cancelled");
//         statusFilter.setValue("All");

//         DatePicker fromDate = new DatePicker();
//         fromDate.setPromptText("From");

//         DatePicker toDate = new DatePicker();
//         toDate.setPromptText("To");

//         Button resetButton = new Button("Reset");
//         resetButton.setStyle("-fx-background-color:grey; -fx-text-fill: black;");

//         HBox filterBox = new HBox(20, searchField, statusFilter, fromDate, toDate, resetButton);
//         filterBox.setAlignment(Pos.CENTER_LEFT);
//         filterBox.setPadding(new Insets(10));
//         filterBox.setStyle("-fx-background-color: #f5f5f5; -fx-border-color: #ccc; -fx-border-radius: 5px;");

//         // ------------------ Booking Table Placeholder -------------------
//         Label tablePlaceholder = new Label("📋 Booking data will appear here");
//         tablePlaceholder.setStyle("-fx-font-size: 16px; -fx-text-fill: gray;");
//         tablePlaceholder.setPadding(new Insets(50));
//         tablePlaceholder.setMaxWidth(Double.MAX_VALUE);
//         tablePlaceholder.setAlignment(Pos.CENTER);

//         VBox centerContent = new VBox(20, filterBox, tablePlaceholder);
//         centerContent.setPadding(new Insets(20));
//         ScrollPane centerScroll = new ScrollPane(centerContent);
//         centerScroll.setFitToWidth(true);

//         BorderPane br = new BorderPane();
//         br.setTop(topbar);
//         br.setLeft(sideBar);
//         br.setCenter(centerScroll);

//         Scene sc = new Scene(br, 1540, 795);
//         myStage.setScene(sc);
//         myStage.getIcons().add(new Image("/Assets//Images//ServiceConnectLogo.jpeg"));
//         myStage.show();

//     }

//     // Sidebar Button Factory for consistent style
//     private Button createSidebarButton(String text, FontIcon icon,
//             javafx.event.EventHandler<javafx.event.ActionEvent> handler) {
//         Button btn = (icon != null) ? new Button(text, icon) : new Button(text);
//         btn.setPrefHeight(40);
//         btn.setMaxWidth(Double.MAX_VALUE);
//         btn.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white; -fx-font-size: 14px;");
//         btn.setAlignment(Pos.CENTER_LEFT);
//         btn.setGraphicTextGap(12);

//         btn.setOnMouseEntered(
//                 e -> btn.setStyle("-fx-background-color: #3e556e; -fx-text-fill: white; -fx-font-size: 14px;"));
//         btn.setOnMouseExited(
//                 e -> btn.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white; -fx-font-size: 14px;"));

//         if (handler != null)
//             btn.setOnAction(handler);
//         return btn;
//     }
// }
package com.user.view;

import com.google.gson.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import java.util.Scanner;

public class Bookings_Admin {
    private Scene bookingScene;
    private Stage BookingStage;

    public Scene getBookingScene() {
        return bookingScene;
    }

    public void setBookingScene(Scene bookingScene) {
        this.bookingScene = bookingScene;
    }

    public Stage getBookingStage() {
        return BookingStage;
    }

    public void setBookingStage(Stage bookingStage) {
        BookingStage = bookingStage;
    }

    public Bookings_Admin(Stage myStage) throws Exception {
        // Sidebar Icons
        FontIcon dashboardIcon = new FontIcon("fas-tachometer-alt");
        dashboardIcon.setIconSize(16);
        dashboardIcon.setIconColor(Color.BLACK);

        FontIcon usersmanagementIcon = new FontIcon("fas-users");
        usersmanagementIcon.setIconSize(16);
        usersmanagementIcon.setIconColor(Color.BLACK);

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

        // Top Bar
        Label title = new Label("Booking Management");
        title.setStyle("-fx-font-weight:bold;-fx-font-size:20px;-fx-text-fill:white");
        BorderPane topbar = new BorderPane();
        topbar.setPadding(new Insets(10, 20, 10, 20));
        topbar.setCenter(title);
        BorderPane.setAlignment(title, Pos.CENTER);
        topbar.setStyle("-fx-background-color:#1e1e2f;-fx-padding:10;");
        topbar.setPrefHeight(60);

        // Sidebar
        VBox sideBar = new VBox(15);
        sideBar.setStyle("-fx-background-color: #34495E; -fx-padding: 20;");
        sideBar.setPrefWidth(220);
        sideBar.setAlignment(Pos.TOP_CENTER);

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

        Button usersButton = createSidebarButton("User Management", usersmanagementIcon, e -> {
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

        sideBar.getChildren().addAll(sidebarTitle, dashboardButton, usersButton, servicesButton, bookingsButton,
                disputesButton, notificationsButton, settingsButton);

        // Filter Section
        TextField searchField = new TextField();
        searchField.setPromptText("Search by name, ID or service");
        searchField.setPrefWidth(250);

        ComboBox<String> statusFilter = new ComboBox<>();
        statusFilter.getItems().addAll("All", "Pending", "Confirmed", "Completed", "Cancelled");
        statusFilter.setValue("All");

        DatePicker fromDate = new DatePicker();
        fromDate.setPromptText("From");

        DatePicker toDate = new DatePicker();
        toDate.setPromptText("To");

        Button resetButton = new Button("Reset");
        resetButton.setStyle("-fx-background-color:grey; -fx-text-fill: black;");

        HBox filterBox = new HBox(20, searchField, statusFilter, fromDate, toDate, resetButton);
        filterBox.setAlignment(Pos.CENTER_LEFT);
        filterBox.setPadding(new Insets(10));
        filterBox.setStyle("-fx-background-color: #f5f5f5; -fx-border-color: #ccc; -fx-border-radius: 5px;");

        // Table for Bookings
        TableView<Booking> bookingTable = new TableView<>();
        bookingTable.setItems(fetchBookings());

        TableColumn<Booking, String> idCol = new TableColumn<>("Booking ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("bookingId"));

        TableColumn<Booking, String> seekerCol = new TableColumn<>("Seeker Name");
        seekerCol.setCellValueFactory(new PropertyValueFactory<>("seekerName"));

        TableColumn<Booking, String> providerCol = new TableColumn<>("Provider Name");
        providerCol.setCellValueFactory(new PropertyValueFactory<>("providerName"));

        TableColumn<Booking, String> serviceCol = new TableColumn<>("Service");
        serviceCol.setCellValueFactory(new PropertyValueFactory<>("service"));

        TableColumn<Booking, String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Booking, String> timeCol = new TableColumn<>("Time");
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));

        TableColumn<Booking, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        bookingTable.getColumns().addAll(idCol, seekerCol, providerCol, serviceCol, dateCol, timeCol, statusCol);
        bookingTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox centerContent = new VBox(20, filterBox, bookingTable);
        centerContent.setPadding(new Insets(20));
        ScrollPane centerScroll = new ScrollPane(centerContent);
        centerScroll.setFitToWidth(true);

        BorderPane br = new BorderPane();
        br.setTop(topbar);
        br.setLeft(sideBar);
        br.setCenter(centerScroll);

        Scene sc = new Scene(br, 1540, 795);
        myStage.setScene(sc);
        myStage.getIcons().add(new Image("/Assets//Images//ServiceConnectLogo.jpeg"));
        myStage.show();
    }

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

    // Fetch Firestore Booking Data
    private ObservableList<Booking> fetchBookings() {
        ObservableList<Booking> list = FXCollections.observableArrayList();
        try {
            URL url = new URL("https://firestore.googleapis.com/v1/projects/serviceconnect-eb8b6/databases/(default)/documents/bookings");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            InputStream is = conn.getInputStream();
            Scanner scanner = new Scanner(is).useDelimiter("\\A");
            String response = scanner.hasNext() ? scanner.next() : "";

            JsonObject json = JsonParser.parseString(response).getAsJsonObject();
            JsonArray documents = json.getAsJsonArray("documents");

            for (JsonElement docElement : documents) {
                JsonObject fields = docElement.getAsJsonObject().getAsJsonObject("fields");

                String bookingId = getFieldValue(fields, "bookingId");
                String seekerName = getFieldValue(fields, "seekerName");
                String providerName = getFieldValue(fields, "providerName");
                String service = getFieldValue(fields, "service");
                String date = getFieldValue(fields, "date");
                String time = getFieldValue(fields, "time");
                String status = getFieldValue(fields, "status");

                list.add(new Booking(bookingId, seekerName, providerName, service, date, time, status));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private String getFieldValue(JsonObject fields, String key) {
        if (fields.has(key)) {
            return fields.getAsJsonObject(key).get("stringValue").getAsString();
        }
        return "";
    }

    // Booking model class
    public class Booking {
        private String bookingId, seekerName, providerName, service, date, time, status;

        public Booking(String bookingId, String seekerName, String providerName, String service,
                       String date, String time, String status) {
            this.bookingId = bookingId;
            this.seekerName = seekerName;
            this.providerName = providerName;
            this.service = service;
            this.date = date;
            this.time = time;
            this.status = status;
        }

        public String getBookingId() { return bookingId; }
        public String getSeekerName() { return seekerName; }
        public String getProviderName() { return providerName; }
        public String getService() { return service; }
        public String getDate() { return date; }
        public String getTime() { return time; }
        public String getStatus() { return status; }
    }
}
