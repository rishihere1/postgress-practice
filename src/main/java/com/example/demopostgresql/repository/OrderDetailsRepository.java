package com.example.demopostgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demopostgresql.entity.OrderDetails;

/**
 * @author rishi - created on 01/01/21
 **/
@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, String> {

}
