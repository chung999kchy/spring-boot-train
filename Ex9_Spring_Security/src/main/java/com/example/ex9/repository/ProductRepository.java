package com.example.ex9.repository;


import com.example.ex9.model.entity.impl.Category;
import com.example.ex9.model.entity.impl.Inventory;
import com.example.ex9.model.entity.impl.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends BaseRepository<Product> {
    Optional<List<Product>> findProductByCategory(Category category);

    Optional<List<Product>> findProductByInventory(Inventory inventory);

    List<Product> findTop10ByOrderByAmountSaleDesc();

    Page<Product> findProductByCategory(Category category, Pageable pageable);

    @Query("SELECT sum(p.quantity) from Product p where p.inventory=:inventory")
    int numberProductsByInventory(Inventory inventory);
}
