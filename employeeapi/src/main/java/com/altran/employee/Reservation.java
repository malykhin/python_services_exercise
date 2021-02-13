package com.altran.employee;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Reservation {
    @Email(message = "Email should be valid" )
    private String emailId;
    @Min(value = 8, message = "time should not be less than 1")
    @Max(value = 20, message = "time should not be greater than 20")
    private int checkInTime;
    @Min(value = 8, message = "time should not be less than 1")
    @Max(value = 20, message = "time should not be greater than 20")
    private int checkOutTime;
    @NotNull(message = "city cannot be null")
    private String city;

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

    public int getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(int checkoutTime) {
        this.checkOutTime = checkoutTime;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
