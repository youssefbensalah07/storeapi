package com.appStore.storeapi.repositories;

import com.appStore.storeapi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface productsRepository extends JpaRepository<Product ,Integer > {

}
