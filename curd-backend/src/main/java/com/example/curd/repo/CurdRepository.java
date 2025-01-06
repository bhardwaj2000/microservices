package com.example.curd.repo;

import com.example.curd.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurdRepository extends JpaRepository<Product, Long> {
}
