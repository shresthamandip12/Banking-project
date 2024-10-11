/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.igurash.igurashwallet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

/**
 *
 * @author User
 */
@Entity
@Table(name="donation_table")
public class Donation {
     @Id
     @Column(name = "id")
    String id;
    @Column(name = "wallet_id")
    private long wallet_id;
    @Column(name = "targetAmount")
    private double targetAmount;
    
   @Column(name = "targetDuration")
    private int days;
    @Column(name = "creation_time")
     private LocalDateTime datetime;

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getWallet_id() {
        return wallet_id;
    }

    public void setWallet_id(long wallet_id) {
        this.wallet_id = wallet_id;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
    
}
