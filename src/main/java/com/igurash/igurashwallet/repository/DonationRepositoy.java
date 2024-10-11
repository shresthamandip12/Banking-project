/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.igurash.igurashwallet.repository;

import com.igurash.igurashwallet.entity.Donation;
import com.igurash.igurashwallet.entity.ServiceStatustable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author User
 */
@Repository
public interface DonationRepositoy extends JpaRepository<Donation, String> {
    
}
