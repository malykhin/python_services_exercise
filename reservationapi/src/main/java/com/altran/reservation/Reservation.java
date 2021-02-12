package com.altran.reservation;

import javax.persistence.*;
import java.util.List;

public class Reservation {
    private int rid;
    private String emailId;
    private int checkInTime;
    private int checkOutTime;
    private String city;
    private boolean confirmed;
    private List<Slot> slotList;

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public List<Slot> getSlotList() {
        return slotList;
    }

    public void setSlotList(List<Slot> slotList) {
        this.slotList = slotList;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

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
