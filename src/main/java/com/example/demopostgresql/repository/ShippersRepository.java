package com.example.demopostgresql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demopostgresql.entity.Shippers;

/**
 * @author rishi - created on 01/01/21
 **/
@Repository
public interface ShippersRepository extends JpaRepository<Shippers, String> {

  @Query("SELECT o from Shippers o where o.id = :id")
  List<Shippers> findOut(@Param("id") String id);
}
