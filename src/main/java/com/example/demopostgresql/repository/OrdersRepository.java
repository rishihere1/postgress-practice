package com.example.demopostgresql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demopostgresql.entity.Orders;

/**
 * @author rishi - created on 01/01/21
 **/
@Repository
public interface OrdersRepository extends JpaRepository<Orders, String> {

  @Query("SELECT o from Orders o where o.id = :id")
  List<Orders> findOut(@Param("id") String id);
}
