package com.gym.service.impl;

import com.github.pagehelper.PageHelper;

import com.gym.mbg.mapper.ProductMapper;
import com.gym.mbg.model.PmsBrand;
import com.gym.mbg.model.PmsBrandExample;
import com.gym.mbg.model.Product;
import com.gym.mbg.model.ProductExample;
import com.gym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> listAllProduct() {
        return productMapper.selectByExample(new ProductExample());
    }

    @Override
    public int createProduct(Product product) {
        return productMapper.insertSelective(product);
    }

    @Override
    public int updateProduct(Long id, Product product) {
        product.setId(id);
        return productMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public int deleteProduct(Long id) {
        return productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Product> listProduct(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return productMapper.selectByExample(new ProductExample());
    }

    @Override
    public Product getProduct(Long id) {
        return productMapper.selectByPrimaryKey(id);
    }



}
