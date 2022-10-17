package com.syld.store.repositories;

import com.syld.store.dto.CategoryDto;
import com.syld.store.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    @Query(value = "select  * from category where category_name =?1", nativeQuery = true)
    Category findByCategory_name(String name);

    @Query(value = "select  * from category where parent_id=?1", nativeQuery = true)
    CategoryDto findByParent(String parent_id);

    @Query(value = "select * from  category where parent_id=?1", nativeQuery = true)
    List<Category> findParent(String parent_id);

    @Query(value = "select * from  category where id=?1 and parent_id=?2",nativeQuery = true)
    Category findParentCategory(String parent_id,String parent_mark);

    @Query(value = "select * from category where category_slug=?1", nativeQuery = true)
    Optional<Category> findBySlug(String slug);
}