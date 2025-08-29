package com.user.model;

public class Rating {
    private String reviewerName;  // seeker or provider
    private String revieweeName;  // provider or seeker
    private String serviceName;
    private int stars;
    private String feedback;

    public Rating(String reviewerName, String revieweeName, String serviceName, int stars, String feedback) {
        this.reviewerName = reviewerName;
        this.revieweeName = revieweeName;
        this.serviceName = serviceName;
        this.stars = stars;
        this.feedback = feedback;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public String getRevieweeName() {
        return revieweeName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public int getStars() {
        return stars;
    }

    public String getFeedback() {
        return feedback;
    }
}
