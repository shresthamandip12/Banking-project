/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.igurash.igurashwallet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author User
 */
@Entity
@Table(name="prduct_table")
public class Product {
    @Id
    @Column(name= "id")
   private  String prdid ; 
    
    @Column(name= "amount")
    double ammout;
    
    @Column(name = "host_username")
     String prd_hostusername;
    
}
