package com.example.demopostgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demopostgresql.entity.Category;

/**
 * @author rishi - created on 13/02/21
 **/
@Repository
public interface CategoryRepository  extends JpaRepository<Category, String> {
}
