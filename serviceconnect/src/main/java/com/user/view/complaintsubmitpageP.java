package com.user.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class complaintsubmitpageP extends Application {

    static final String PROJECT_ID = "serviceconnect-eb8b6";
    static final String API_KEY = "AIzaSyAlJon0EBJRYetQUsc2ySdnt3pm41U4KMU";

    @Override
    public void start(Stage stage) {
        Label title = fixedLabel("📮 Complaint Box", 24);
        Label section1 = fixedLabel("👤 Personal Information", 16);
        Label section2 = fixedLabel("📝 Complaint Details", 16);

        TextField providerName = styledTextField("Provider Name");
        TextField contact = styledTextField("Contact Number / Email");
        TextField againstSeeker = styledTextField("Complaint Against (User)");

        DatePicker dateOfIncident = new DatePicker();
        dateOfIncident.setPromptText("Date of Incident");
        dateOfIncident.setStyle(controlStyle());

        TextField subject = styledTextField("Complaint Subject");

        ComboBox<String> type = new ComboBox<>();
        type.getItems().addAll("User Misbehavior", "Non-Payment", "Fake Complaint", "Other");
        type.setPromptText("Complaint Type");
        type.setStyle(controlStyle());

        ComboBox<String> priority = new ComboBox<>();
        priority.getItems().addAll("Low", "Medium", "High", "Urgent");
        priority.setPromptText("Priority");
        priority.setStyle(controlStyle());

        TextArea description = new TextArea();
        description.setPromptText("Write detailed complaint...");
        description.setWrapText(true);
        description.setMaxWidth(360);
        description.setStyle(controlStyle());
        description.setPrefRowCount(4);

        Button attachBtn = new Button("📎 Attach File");
        Label attachedLabel = new Label();
        attachedLabel.setStyle("-fx-text-fill: black; -fx-font-size: 13px;");
        attachBtn.setStyle(buttonStyle("#28a745"));

        final File[] selectedFile = new File[1];

        attachBtn.setOnAction(e -> {
            FileChooser fc = new FileChooser();
            File file = fc.showOpenDialog(stage);
            if (file != null) {
                selectedFile[0] = file;
                attachedLabel.setText("Attached: " + file.getName());
            } else {
                attachedLabel.setText("No file selected");
            }
        });

        Button submitBtn = new Button("✔ Submit");
        Button resetBtn = new Button("🔁 Reset");
        submitBtn.setStyle(buttonStyle("#007bff"));
        resetBtn.setStyle(buttonStyle("#6c757d"));

        Label resultLabel = new Label();

        resetBtn.setOnAction(e -> {
            providerName.clear();
            contact.clear();
            againstSeeker.clear();
            dateOfIncident.setValue(null);
            subject.clear();
            type.setValue(null);
            priority.setValue(null);
            description.clear();
            attachedLabel.setText("");
            resultLabel.setText("");
        });

        submitBtn.setOnAction(e -> {
            String fileUrl = "";
            if (selectedFile[0] != null) {
                fileUrl = uploadFileToFirebaseStorage(selectedFile[0]);
            }

            String providername = providerName.getText();
            String contactInfo = contact.getText();
            String againstSeekerName = againstSeeker.getText();
            String dateOfIncidentStr = (dateOfIncident.getValue() != null) ? dateOfIncident.getValue().toString() : "";
            String subjectText = subject.getText();
            String complaintType = (type.getValue() != null) ? type.getValue() : "";
            String complaintPriority = (priority.getValue() != null) ? priority.getValue() : "";
            String complaintDescription = description.getText();

            Map<String, Object> dataMap = Map.of(
                "providerName", providername,
                "contact", contactInfo,
                "againstSeeker", againstSeekerName,
                "dateOfIncident", dateOfIncidentStr,
                "subject", subjectText,
                "type", complaintType,
                "priority", complaintPriority,
                "description", complaintDescription,
                "attachmentUrl", fileUrl
            );

            String result = submitComplaintToFirestore(dataMap);
            resultLabel.setText(result);

            if (result.contains("successfully")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Complaint Submitted");
                alert.setContentText("✅ Your complaint has been submitted.");
                alert.showAndWait();

                dashboardpageP dashboard = new dashboardpageP();
                Stage currentStage = (Stage) submitBtn.getScene().getWindow();
                dashboard.launchdashboard(currentStage, "Ram");
            }
        });

        HBox buttonBox = new HBox(20, submitBtn, resetBtn);
        buttonBox.setAlignment(Pos.CENTER);

        VBox form = new VBox(12,
                title, section1, providerName, contact, againstSeeker, dateOfIncident,
                section2, subject, type, priority, description,
                new HBox(10, attachBtn, attachedLabel), buttonBox, resultLabel
        );
        form.setAlignment(Pos.CENTER);
        form.setPadding(new Insets(30));
        form.setMaxWidth(440);
        form.setMaxHeight(740);
        form.setStyle("""
            -fx-background-color: rgba(255, 255, 255, 0.75);
            -fx-background-radius: 20;
            -fx-border-radius: 20;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 16, 0.2, 0, 8);
            -fx-border-color: transparent;
        """);

        ScrollPane scroll = new ScrollPane(form);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scroll.setPadding(new Insets(30));
        scroll.setMaxHeight(900);

        ImageView bgImage = new ImageView(new Image("Assets\\Images\\complaint.jpg"));
        bgImage.setFitWidth(1540);
        bgImage.setFitHeight(795);
        bgImage.setPreserveRatio(false);
        bgImage.setEffect(new BoxBlur(5, 9, 4));

        StackPane stack = new StackPane(bgImage, scroll);
        stack.setPadding(new Insets(20));

        Scene scene = new Scene(stack, 1540, 795);
        stage.setScene(scene);
        stage.setTitle("Complaint Page");
        stage.show();
    }

    private Label fixedLabel(String text, int size) {
        Label lbl = new Label(text);
        lbl.setFont(Font.font("Segoe UI", size));
        lbl.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        lbl.setMouseTransparent(true);
        return lbl;
    }

    private TextField styledTextField(String prompt) {
        TextField tf = new TextField();
        tf.setPromptText(prompt);
        tf.setMaxWidth(360);
        tf.setStyle(controlStyle());
        return tf;
    }

    private String controlStyle() {
        return """
            -fx-background-color: white;
            -fx-border-radius: 8;
            -fx-background-radius: 8;
            -fx-border-color: #ccc;
            -fx-font-size: 13px;
            -fx-padding: 8;
        """;
    }

    private String buttonStyle(String color) {
        return "-fx-background-color: " + color + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 10 20;" +
                "-fx-background-radius: 10;" +
                "-fx-cursor: hand;";
    }

    private static String escapeJson(String value) {
        return value.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r");
    }

    private static String buildJsonFromMap(Map<String, Object> data) {
        StringBuilder sb = new StringBuilder();
        sb.append("{ \"fields\": {");

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            String key = entry.getKey();
            String value = escapeJson(entry.getValue().toString());
            sb.append("\"").append(key).append("\": { \"stringValue\": \"").append(value).append("\" },");
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.append("}}");
        return sb.toString();
    }

    private static String submitComplaintToFirestore(Map<String, Object> dataMap) {
        try {
            String endpoint = String.format(
                    "https://firestore.googleapis.com/v1/projects/%s/databases/(default)/documents/ProviderComplaints?key=%s",
                    PROJECT_ID, API_KEY
            );

            String json = buildJsonFromMap(dataMap);
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = json.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int code = connection.getResponseCode();
            System.out.println("Firestore Response Code: " + code);

            return code == 200 ? "✅ Complaint submitted successfully." : "❌ Failed. Response code: " + code;

        } catch (Exception ex) {
            ex.printStackTrace();
            return "❌ Exception: " + ex.getMessage();
        }
    }

    private static String uploadFileToFirebaseStorage(File file) {
        try {
            String bucketName = "serviceconnect-eb8b6.appspot.com";
            String firebasePath = "provider_complaints/" + file.getName();
            String uploadUrl = "https://firebasestorage.googleapis.com/v0/b/" + bucketName + "/o/" + firebasePath.replace("/", "%2F") + "?uploadType=media&name=" + firebasePath;

            HttpURLConnection connection = (HttpURLConnection) new URL(uploadUrl).openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/octet-stream");

            try (OutputStream os = connection.getOutputStream()) {
                os.write(java.nio.file.Files.readAllBytes(file.toPath()));
            }

            int code = connection.getResponseCode();
            if (code == 200) {
                return "https://firebasestorage.googleapis.com/v0/b/" + bucketName + "/o/" + firebasePath.replace("/", "%2F") + "?alt=media";
            } else {
                return "";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
