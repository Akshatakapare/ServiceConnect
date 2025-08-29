package com.user.view;

import com.google.gson.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Complaints_Admin {

    private TableView<Complaint> disputeTable;
    private static final String PROJECT_ID = "serviceconnect-eb8b6";  // <-- Replace this with your Firebase Project ID

    public void show() {
       Stage stage = new Stage();

        disputeTable = new TableView<>();
        disputeTable.setPlaceholder(new Label("Loading complaints..."));
        disputeTable.setPrefHeight(400);

        setupTableColumns();

        ObservableList<Complaint> complaints = fetchComplaintsFromFirestore();
        if (complaints.isEmpty()) {
            disputeTable.setPlaceholder(new Label("No complaints found."));
        }
        disputeTable.setItems(complaints);

        BorderPane root = new BorderPane(disputeTable);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 1540, 795);
        stage.setTitle("Complaints Admin");
        stage.setScene(scene);
        stage.show();
    }

    private void setupTableColumns() {
        TableColumn<Complaint, String> idCol = new TableColumn<>("Dispute ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setMinWidth(100);

        TableColumn<Complaint, String> againstUserCol = new TableColumn<>("Against User");
        againstUserCol.setCellValueFactory(new PropertyValueFactory<>("againstUser"));
        againstUserCol.setMinWidth(120);

        TableColumn<Complaint, String> contactCol = new TableColumn<>("Contact");
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        contactCol.setMinWidth(150);

        TableColumn<Complaint, String> dateCol = new TableColumn<>("Date of Incident");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("dateOfIncident"));
        dateCol.setMinWidth(120);

        TableColumn<Complaint, String> descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        descriptionCol.setMinWidth(300);

        TableColumn<Complaint, String> priorityCol = new TableColumn<>("Priority");
        priorityCol.setCellValueFactory(new PropertyValueFactory<>("priority"));
        priorityCol.setMinWidth(100);

        TableColumn<Complaint, String> providerNameCol = new TableColumn<>("Provider Name");
        providerNameCol.setCellValueFactory(new PropertyValueFactory<>("providerName"));
        providerNameCol.setMinWidth(150);

        TableColumn<Complaint, String> subjectCol = new TableColumn<>("Subject");
        subjectCol.setCellValueFactory(new PropertyValueFactory<>("subject"));
        subjectCol.setMinWidth(150);

        TableColumn<Complaint, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        typeCol.setMinWidth(100);

        disputeTable.getColumns().addAll(idCol, againstUserCol, contactCol, dateCol, descriptionCol,
                priorityCol, providerNameCol, subjectCol, typeCol);
    }

    private ObservableList<Complaint> fetchComplaintsFromFirestore() {
        ObservableList<Complaint> complaints = FXCollections.observableArrayList();
        try {
            String urlStr = "https://firestore.googleapis.com/v1/projects/" + PROJECT_ID + "/databases/(default)/documents/Complaints";
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                InputStream inputStream = conn.getInputStream();
                String json = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
                JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();

                JsonArray documents = jsonObject.getAsJsonArray("documents");
                if (documents != null && documents.size() > 0) {
                    int idCounter = 1;
                    for (JsonElement docElement : documents) {
                        JsonObject doc = docElement.getAsJsonObject();
                        JsonObject fields = doc.getAsJsonObject("fields");

                        String id = doc.has("name") ? extractDocId(doc.get("name").getAsString()) : "ID" + (idCounter++);

                        String againstUser = safeGetString(fields, "againstUser");
                        String contact = safeGetString(fields, "contact");
                        String dateOfIncident = safeGetString(fields, "dateOfIncident");
                        String description = safeGetString(fields, "description");
                        String priority = safeGetString(fields, "priority");
                        String providerName = safeGetString(fields, "providerName");
                        String subject = safeGetString(fields, "subject");
                        String type = safeGetString(fields, "type");

                        complaints.add(new Complaint(id, againstUser, contact, dateOfIncident,
                                description, priority, providerName, subject, type));
                    }
                }
            } else {
                showAlert("Failed to fetch complaints", "HTTP response code: " + responseCode);
                System.out.println("Error fetching complaints: HTTP " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Exception", "Error fetching complaints: " + e.getMessage());
        }
        return complaints;
    }

    private String extractDocId(String fullDocName) {
        String[] parts = fullDocName.split("/");
        return parts.length > 0 ? parts[parts.length - 1] : fullDocName;
    }

    private String safeGetString(JsonObject fields, String key) {
        try {
            if (fields != null && fields.has(key)) {
                JsonObject valueObj = fields.getAsJsonObject(key);
                if (valueObj.has("stringValue")) {
                    return valueObj.get("stringValue").getAsString();
                }
            }
        } catch (Exception e) {
            // ignore exceptions here, return empty string
        }
        return "";
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static class Complaint {
        private final String id;
        private final String againstUser;
        private final String contact;
        private final String dateOfIncident;
        private final String description;
        private final String priority;
        private final String providerName;
        private final String subject;
        private final String type;

        public Complaint(String id, String againstUser, String contact, String dateOfIncident,
                         String description, String priority, String providerName, String subject, String type) {
            this.id = id;
            this.againstUser = againstUser;
            this.contact = contact;
            this.dateOfIncident = dateOfIncident;
            this.description = description;
            this.priority = priority;
            this.providerName = providerName;
            this.subject = subject;
            this.type = type;
        }

        public String getId() { return id; }
        public String getAgainstUser() { return againstUser; }
        public String getContact() { return contact; }
        public String getDateOfIncident() { return dateOfIncident; }
        public String getDescription() { return description; }
        public String getPriority() { return priority; }
        public String getProviderName() { return providerName; }
        public String getSubject() { return subject; }
        public String getType() { return type; }
    }
}
