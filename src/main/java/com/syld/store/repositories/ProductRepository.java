package com.syld.store.repositories;

import com.syld.store.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {
    @Query(value = "select * from product where product_name = ?1",nativeQuery = true)
    Optional<Product> findByName(String product_name);
    @Query(value = "select * from product where slug = ?1",nativeQuery = true)
    Optional<Product> findBySlug(String slug);
}
