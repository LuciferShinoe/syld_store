package com.syld.store.repositories;

import com.syld.store.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, String>  {

    @Query(value = "select * from brand where brand_name =?1", nativeQuery = true)
    Brand findByBrand_name(String name);

    @Query(value = "select * from  brand where brand_desc = ?1", nativeQuery = true)
    Brand findByBrand_desc(String desc);

    @Query(value = "select * from brand where brand_slug = ?1", nativeQuery = true)
    Optional<Brand> findBySlug(String slug);

    @Query(value = "select * from brand where id=?1", nativeQuery = true)
    List<Brand> findId(String id);

    @Query(value = "select * from brand where id=?1", nativeQuery = true)
    Optional<Brand> findById(String id);
}
