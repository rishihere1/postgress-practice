package com.example.demopostgresql.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demopostgresql.entity.Products;

/**
 * @author rishi - created on 01/01/21
 **/
@Repository
public interface ProductsRepository extends JpaRepository<Products, String> {

  @Query("select p from Products p where p.id in ?1")
  List<Products> findProduct(Set<String> productId);
}
