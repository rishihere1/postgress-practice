package com.example.demopostgresql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demopostgresql.entity.Customers;

/**
 * @author rishi - created on 01/01/21
 **/
@Repository
public interface CustomersRepository extends JpaRepository<Customers, String> {

  @Query("SELECT o from Customers o where o.id = :id")
  List<Customers> findOut(@Param("id") String id);
}
