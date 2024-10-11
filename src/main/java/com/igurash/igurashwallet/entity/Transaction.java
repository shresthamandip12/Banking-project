/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.igurash.igurashwallet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

/**
 *
 * @author User
 */
@Entity
@Table(name = "transaction_table")
public class Transaction {
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id")
    private long id; 
    @Column(name = "trans_type")
    private String transactionType;
     @Column(name = "amount")
    private double amount ; 
        @Column(name = "sender_id")
    private long sender_ID; 
@Column(name = "receiver_id")//wallet id 
    private long receiver_ID;
    @Column(name = "transaction_time")
     private LocalDateTime datetime;
    @Column(name = "device_id")
     private String transactionDeviceID;
    @Column(name = "transaction_ipaddress")
     private String transactionIPaddress;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getSender_ID() {
        return sender_ID;
    }

    public void setSender_ID(long sender_ID) {
        this.sender_ID = sender_ID;
    }

    public long getReceiver_ID() {
        return receiver_ID;
    }

    public void setReceiver_ID(long receiver_ID) {
        this.receiver_ID = receiver_ID;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public String getTransactionDeviceID() {
        return transactionDeviceID;
    }

    public void setTransactionDeviceID(String transactionDeviceID) {
        this.transactionDeviceID = transactionDeviceID;
    }

    public String getTransactionIPaddress() {
        return transactionIPaddress;
    }

    public void setTransactionIPaddress(String transactionIPaddress) {
        this.transactionIPaddress = transactionIPaddress;
    }
     
     
    
    
    
}
