package com.quickmart.repositories;

import com.quickmart.model.Category;
import com.quickmart.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {
    Page<Product> findByCategoryAndActiveTrueOrderByPriceAsc(Category category, Pageable pageDetails);

    Page<Product> findByProductNameLikeIgnoreCaseAndActiveTrue(String keyword, Pageable pageDetails);

    Page<Product> findByActiveTrue(Pageable pageDetails);
}
