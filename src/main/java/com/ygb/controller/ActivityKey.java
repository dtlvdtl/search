package com.ygb.controller;


import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class ActivityKey implements Serializable {


    private int acid;

    private String acName;

    private String time;

    public int getAcid() {
        return acid;
    }

    public String getAcName() {
        return acName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setAcName(String acName) {
        this.acName = acName;
    }

    public void setAcid(int acid) {
        this.acid = acid;
    }
    @Override
    public String toString() {
        return "PeopleKey [acid=" + acid + ", acName=" + acName + ",time=" + time + "]";
    }

    @Override
    public boolean equals(Object obj) {
        return (this.toString() == obj.toString());
    }


}