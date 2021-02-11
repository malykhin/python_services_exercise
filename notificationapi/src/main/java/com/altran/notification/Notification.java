package com.altran.notification;

import java.util.List;

public class Notification {

    private String emailId;
    private int checkInTime;
    private int checkoutTime;
    private String city;
    private boolean confirmed;
    private List<String> recommendedSlots;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public int getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(int checkInTime) {
        this.checkInTime = checkInTime;
    }

    public int getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(int checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public List<String> getRecommendedSlots() {
        return recommendedSlots;
    }

    public void setRecommendedSlots(List<String> recommendedSlots) {
        this.recommendedSlots = recommendedSlots;
    }
}
