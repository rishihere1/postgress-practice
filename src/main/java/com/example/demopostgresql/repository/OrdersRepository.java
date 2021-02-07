package com.example.demopostgresql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
  List<Object> findOut(@Param("id") String id);

  @Modifying(clearAutomatically = true)
  @Query("Delete from Orders where id = ?1")
  void del(String orderId);// This query will not delete the child entity

  /**
   * Open questions :
   *
   How to modify/delete specific child entity if we have parent id? -> Got the answer
   What is the ideal way to insert child entity when inserting parent entity? --> Need to go through code
   */
}
