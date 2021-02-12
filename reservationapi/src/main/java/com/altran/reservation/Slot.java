package com.altran.reservation;

import javax.persistence.*;

@Entity
@Table(name="slot")
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid",unique = true)
    private int sid;
    @Column(name = "starttime",unique = true,updatable = false)
    private int startTime;
    @Column(name = "endtime",unique = true,updatable = false)
    private int endTime;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }
}
