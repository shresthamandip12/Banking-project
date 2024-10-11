/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.igurash.igurashwallet.repository;

import com.igurash.igurashwallet.entity.ServiceStatustable;
import com.igurash.igurashwallet.entity.Transaction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author User
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
   @Query("SELECT t FROM Transaction t WHERE t.receiver_ID = :walletId")
    List<Transaction> findByReceiver_ID(@Param("walletId") long walletId);
}
