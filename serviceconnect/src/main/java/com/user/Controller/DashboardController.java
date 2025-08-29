package com.user.Controller;

import java.util.Arrays;
import java.util.List;
import com.user.model.Appointment;
import com.user.model.Provider;
import com.user.model.Service;

import javafx.scene.image.Image;

public class DashboardController {

    public List<Service> getAllServices() {
        return Arrays.asList(
            new Service("Cooking", "🍳"),
            new Service("Tutoring", "📚"),
            new Service("Gardening", "🌿"),
            new Service("Storytelling", "📖"),
            new Service("Spiritual Guidance", "🙏"),
            new Service("Music/Singing", "🙏"),
            new Service("Child Care", "👶"),
            new Service("Other", "❓")
        );
    }
     
//     public List<Provider> getFeaturedProviders() {
//     return Arrays.asList(
//         new Provider("Asha", 5.0, new Image(getClass().getResourceAsStream("/Assets/Images/featureprovider.jpg"))),
//         new Provider("Ramesh", 4.8, new Image(getClass().getResourceAsStream("/Assets/Images/featuredpeovider(2).jpg")))
//     );
// }

public List<Provider> getFeaturedProviders() {
    return Arrays.asList(
        new Provider(
            "Asha", 
            "6 years", 
            "Cooking, Meal Planning", 
            "asha@example.com", 
            "Pune", 
            "Available", 
            5,
            new Image(getClass().getResourceAsStream("/Assets/Images/featuredpeovider(2).jpg"))
        ),
        new Provider(
            "Ramesh", 
            "5 years", 
            "North Indian Cooking", 
            "ramesh@example.com", 
            "Mumbai", 
            "Busy", 
            4,
            new Image(getClass().getResourceAsStream("/Assets/Images/featureprovider.jpg"))
        )
    );
}



    public List<Appointment> getAppointments() {
        return Arrays.asList(
            new Appointment("Cooking with Asha", "22 Jul - 4 PM"),
            new Appointment("Music session", "24 Jul - 11 AM")
        );
    }
}
