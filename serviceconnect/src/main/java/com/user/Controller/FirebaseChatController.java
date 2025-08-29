// package com.user.Controller;

// // import com.google.gson.Gson;
// // import com.google.gson.reflect.TypeToken;
// // import com.chat.model.Chat;

// import java.io.*;
// import java.lang.reflect.Type;
// import java.net.HttpURLConnection;
// import java.net.URL;
// import java.util.*;

// import com.google.gson.Gson;
// import com.google.gson.reflect.TypeToken;
// import com.user.model.Chat;

// public class FirebaseChatController {
//     // private static final String DATABASE_URL = "https://your-project-id.firebaseio.com/Chats.json"; // Replace with your actual Firebase DB URL
//     private static final String DATABASE_URL = "https://serviceconnect-eb8b6-default-rtdb.firebaseio.com/Chats.json";
    

//     private static final Gson gson = new Gson();

//     // 1. SEND CHAT MESSAGE TO FIREBASE
//     public boolean sendMessage(Chat chat) {
//         try {
//             URL url = new URL(DATABASE_URL);
//             HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//             conn.setRequestMethod("POST");
//             conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//             conn.setDoOutput(true);

//             String jsonPayload = gson.toJson(chat);
//             try (OutputStream os = conn.getOutputStream()) {
//                 os.write(jsonPayload.getBytes("UTF-8"));
//             }

//             int responseCode = conn.getResponseCode();
//             if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
//                 return true;
//             } else {
//                 System.out.println("Error while sending message: " + responseCode);
//                 return false;
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//             return false;
//         }
//     }

//     // 2. GET CHAT MESSAGES BETWEEN TWO USERS
//     public List<Chat> getMessagesBetweenUsers(String user1, String user2) {
//         List<Chat> chatList = new ArrayList<>();

//         try {
//             URL url = new URL(DATABASE_URL);
//             HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//             conn.setRequestMethod("GET");

//             int responseCode = conn.getResponseCode();
//             if (responseCode == HttpURLConnection.HTTP_OK) {
//                 InputStreamReader reader = new InputStreamReader(conn.getInputStream());
//                 BufferedReader br = new BufferedReader(reader);

//                 StringBuilder response = new StringBuilder();
//                 String line;
//                 while ((line = br.readLine()) != null) {
//                     response.append(line);
//                 }

//                 Type mapType = new TypeToken<Map<String, Chat>>() {}.getType();
//                 Map<String, Chat> chatMap = gson.fromJson(response.toString(), mapType);

//                 for (Chat chat : chatMap.values()) {
//                     boolean isMatch = 
//                         (chat.getSenderId().equals(user1) && chat.getReceiverId().equals(user2)) ||
//                         (chat.getSenderId().equals(user2) && chat.getReceiverId().equals(user1));

//                     if (isMatch) {
//                         chatList.add(chat);
//                     }
//                 }

//                 chatList.sort(Comparator.comparing(Chat::getTimestamp));
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//         }

//         return chatList;
//     }
// }

package com.user.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.user.model.Chat;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class FirebaseChatController {
    private static final String DATABASE_URL = "https://serviceconnect-eb8b6-default-rtdb.firebaseio.com/Chats.json";

    private final Gson gson = new Gson();

    public boolean sendMessage(Chat chat) {
        try {
            URL url = new URL(DATABASE_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setDoOutput(true);

            String json = gson.toJson(chat);
            try (OutputStream os = conn.getOutputStream()) {
                os.write(json.getBytes());
                os.flush();
            }

            int responseCode = conn.getResponseCode();
            return (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Chat> getMessagesBetweenUsers(String senderId, String receiverId) {
        List<Chat> filteredMessages = new ArrayList<>();

        try {
            URL url = new URL(DATABASE_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            InputStream is = conn.getInputStream();
            Reader reader = new InputStreamReader(is);
            Type type = new TypeToken<Map<String, Chat>>() {}.getType();
            Map<String, Chat> messages = gson.fromJson(reader, type);

            if (messages != null) {
                for (Chat msg : messages.values()) {
                    boolean isBetween = (msg.getSenderId().equals(senderId) && msg.getReceiverId().equals(receiverId)) ||
                                        (msg.getSenderId().equals(receiverId) && msg.getReceiverId().equals(senderId));
                    if (isBetween) {
                        filteredMessages.add(msg);
                    }
                }

                // Optional: Sort by timestamp
                filteredMessages.sort(Comparator.comparing(Chat::getTimestamp));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return filteredMessages;
    }
}
