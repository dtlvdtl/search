package com.ygb.controller;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "account")
public class UserPasswordEntity implements Serializable {
    @Id
    @Column(name = "Username")
    private String user;

    @Column(name = "Password")
    private String password;

    @Column(name = "Position")
    private String position;

    @Column(name = "Power")
    private int power;

    public String getUser(){
        return this.user;
    }
    public String getPassword(){
        return this.password;
    }
    public String getPosition() {
        return position;
    }
    public int getPower() {
        return power;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getUser().equals((String)obj);
    }
}
