package com.hetpatel.product_service.service;

import com.hetpatel.product_service.dto.ProductDto;
import com.hetpatel.product_service.exception.ResourceNotFoundException;
import com.hetpatel.product_service.mapper.ProductMapper;
import com.hetpatel.product_service.model.LeaseStatus;
import com.hetpatel.product_service.model.Product;
import com.hetpatel.product_service.repo.ProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.DataException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {
    private final ProductRepo productRepo;
    private final ProductMapper productMapper;
    public ProductService(ProductRepo productRepo, ProductMapper productMapper) {
        this.productRepo = productRepo;
        this.productMapper = productMapper;
    }

    public List<ProductDto> getAllProducts() {
        try {
            log.info("Getting all products");
            List<Product> products = productRepo.findAll();
            // Convert the list of Product entities to ProductDto
            return products.stream()
                    .map(productMapper::toDto).toList();
        } catch (DataAccessException e) {
            log.error("Error while fetching products from database: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to fetch products", e);
        }
    }

    public ProductDto getProduct(Long id){
        try {
            log.info("Getting product with id {}", id);
           Product product = productRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product with id " + id + " not found"));
            return productMapper.toDto(product);
        } catch (DataException e) {
            log.error("Error while fetching product with id: {}:{}", id, e.getMessage(),e);
            throw new RuntimeException("Failed to fetch product with id " + id);
        }
    }
    public ProductDto addProduct(ProductDto productDto){
        try{
            log.info("Adding product {}", productDto);
            Product product = productMapper.toEntity(productDto);
            product.setStatus(LeaseStatus.AVAILABLE);
            Product savedProduct = productRepo.save(product);
            return productMapper.toDto(savedProduct);
        }catch(DataException e){
            log.error("Error while adding product",e);
            throw new RuntimeException("Failed to add product");
        }
    }

    public ProductDto updateProduct(ProductDto productDto, Long id){
        try{
            log.info("Updating product with id: {}", id);
            Product product = productRepo.findById(id)
                    .orElseThrow(()-> new ResourceNotFoundException("Product with id " + id + " not found"));
            productMapper.updateProductFromDto(productDto, product);
            product.setUpdatedAt(LocalDateTime.now());

            Product updatedProduct = productRepo.save(product);
            return productMapper.toDto(updatedProduct);
        }catch(DataException e){
            log.error("Error while updating product",e);
            throw new RuntimeException("Failed to update product");
        }
    }

    public void deleteProduct(Long id) {
        try {
            log.info("Attempting to delete product with ID {}", id);
            if (!productRepo.existsById(id)) {
                log.warn("Product with ID {} not found. Cannot delete.", id);
                throw new ResourceNotFoundException("Product not found with ID " + id);
            }
            productRepo.deleteById(id);
            log.info("Product with ID {} successfully deleted.", id);
        } catch (DataAccessException e) {
            log.error("Error while deleting product with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to delete product with ID " + id, e);
        }
    }

//    public ProductDto adjustInventory(Long id, int quantity){
//        log.info("Adjusting inventory with id {}", id);
//        Product product = productRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product with id " + id + " not found"));
//
//    }
}
