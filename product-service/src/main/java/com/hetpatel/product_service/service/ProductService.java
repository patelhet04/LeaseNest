package com.hetpatel.product_service.service;

import com.hetpatel.product_service.dto.ProductDto;
import com.hetpatel.product_service.exception.ResourceNotFoundException;
import com.hetpatel.product_service.mapper.ProductMapper;
import com.hetpatel.product_service.model.Product;
import com.hetpatel.product_service.repo.ProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.DataException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {
    private final ProductRepo productRepo;
    private final ProductMapper productMapper;
    public ProductService(ProductRepo productRepo, ProductMapper productMapper) {
        this.productRepo = productRepo;
        this.productMapper = productMapper;
    }

    public List<Product> getAllProducts(){
        try{
            log.info("Getting all products");
            return productRepo.findAll();
        } catch (DataException e) {
            log.error("Error while fetching products from database: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to fetch users");
        }
    }
    public Product getProduct(Long id){
        try {
            log.info("Getting product with id {}", id);
            return productRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product with id " + id + " not found"));

        } catch (DataException e) {
            log.error("Error while fetching product with id: {}:{}", id, e.getMessage(),e);
            throw new RuntimeException("Failed to fetch product with id " + id);
        }
    }
    public Product addProduct(Product product){
        try{
            log.info("Adding product {}", product);
            return productRepo.save(product);
        }catch(DataException e){
            log.error("Error while adding product",e);
            throw new RuntimeException("Failed to add product");
        }
    }

    public Product updateProduct(ProductDto productDto){
        try{
            log.info("Updating product with id: {}", productDto.getId());
            Product product = productRepo.findById(productDto.getId())
                    .orElseThrow(()-> new ResourceNotFoundException("Product with id " + productDto.getId() + " not found"));
            productMapper.updateProductFromDto(productDto, product);
            return productRepo.save(product);
        }catch(DataException e){
            log.error("Error while updating product",e);
            throw new RuntimeException("Failed to update product");
        }
    }

    public void deleteProduct(Long id){
        try{
            Product product = getProduct(id);
            productRepo.delete(product);
        } catch (DataException e) {
            log.error("Error while fetching product with id: {}:{}", id, e.getMessage(),e);
            throw new RuntimeException("Failed to fetch product with id " + id);
        }
    }
}
