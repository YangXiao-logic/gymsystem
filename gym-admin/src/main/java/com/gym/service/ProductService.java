package com.gym.service;



import com.gym.mbg.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> listAllProduct();

    int createProduct(Product brand);

    int updateProduct(Long id, Product brand);

    int deleteProduct(Long id);

    List<Product> listProduct(int pageNum, int pageSize);

    Product getProduct(Long id);
}
