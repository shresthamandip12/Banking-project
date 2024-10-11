/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.igurash.igurashwallet.repository;

import com.igurash.igurashwallet.entity.ServiceStatustable;
import com.igurash.igurashwallet.entity.Transaction;
import com.igurash.igurashwallet.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author User
 */
@Repository
public interface ServiceStatusRepository extends JpaRepository<ServiceStatustable, Long>{
    @Query("SELECT ss FROM ServiceStatustable ss WHERE ss.sender_id = :senderId AND ss.product_id = :productId")
    Optional<ServiceStatustable> findBySenderIdAndProductId(@Param("senderId") String senderId, @Param("productId") String productId);
        List<ServiceStatustable> findByServiceType(String serviceType);

}
