package com.user.Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.google.gson.Gson;

public class FirebaseAuthenticationController {
    private static final String API_KEY="AIzaSyAlJon0EBJRYetQUsc2ySdnt3pm41U4KMU";

    //----------------signIn------------
    public boolean signInWithEmailAndPassword(String email,String password){
        try{
            URL url = new URL("https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key="+API_KEY);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type","application/json");
            conn.setDoOutput(true);
            String payload = String.format("{\"email\":\"%s\",\"password\":\"%s\",\"returnSecureToken\":true}",email,password);
            OutputStream os = null;
            os=conn.getOutputStream();
            os.write(payload.getBytes());
            int responseCode = conn.getResponseCode();
            if (responseCode==200) {
                //Login Successful
                logincontroller.setCurrentUserEmail(email);
                return true;
            }else{
                //Login failed
                try(BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream()))){
                    String line;
                    while ((line=br.readLine())!=null) {
                        System.out.println(line);
                    }
                }
                return false;
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    //---------------signup------------
    public boolean signUpWithEmailAndPassword(String email,String password){
        try{
            URL url = new URL("https://identitytoolkit.googleapis.com/v1/accounts:signUp?key="+API_KEY);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type","application/json");
            conn.setDoOutput(true);
            String payload = String.format("{\"email\":\"%s\",\"password\":\"%s\",\"returnSecureToken\":true}",email,password);
            OutputStream os = null;
            os=conn.getOutputStream();
            os.write(payload.getBytes());
            int responseCode = conn.getResponseCode();
            if (responseCode==200) {
                //Login Successful
                return true;
            }else{
                //Login failed
                try(BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream()))){
                    String line;
                    while ((line=br.readLine())!=null) {
                        System.out.println(line);
                    }
                }
                return false;
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
//-----------------get name----------------//
    public String getNameByEmail(String email, String userType) {
    try {
        URL url = new URL("https://serviceconnect-eb8b6-default-rtdb.firebaseio.com/" + userType + ".json");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            Map<String, Map<String, Object>> data = new Gson().fromJson(response.toString(), Map.class);
            for (Map.Entry<String, Map<String, Object>> entry : data.entrySet()) {
                Map<String, Object> userData = entry.getValue();
                if (email.equals(userData.get("email"))) {
                    return (String) userData.get("name");
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return "User"; // fallback
}

    
}
