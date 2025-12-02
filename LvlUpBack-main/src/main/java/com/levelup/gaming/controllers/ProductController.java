package com.levelup.gaming.controllers;

import com.levelup.gaming.models.Product;
import com.levelup.gaming.models.Review;
import com.levelup.gaming.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProducts(@RequestParam(required = false) Boolean admin) {
        if (Boolean.TRUE.equals(admin)) {
            return productRepository.findAll();
        }
        return productRepository.findByIsActiveTrue();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/top")
    public List<Product> getTopProducts() {
        List<Product> activeProducts = productRepository.findByIsActiveTrue();
        return activeProducts.stream()
                .limit(4)
                .toList();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id,
            @RequestBody com.levelup.gaming.dto.ProductUpdateDTO productUpdate) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    if (productUpdate.getName() != null)
                        existingProduct.setName(productUpdate.getName());
                    if (productUpdate.getDescription() != null)
                        existingProduct.setDescription(productUpdate.getDescription());
                    if (productUpdate.getImageUrl() != null)
                        existingProduct.setImageUrl(productUpdate.getImageUrl());
                    if (productUpdate.getCategory() != null)
                        existingProduct.setCategory(productUpdate.getCategory());
                    if (productUpdate.getSpecifications() != null)
                        existingProduct.setSpecifications(productUpdate.getSpecifications());
                    if (productUpdate.getPrice() != null)
                        existingProduct.setPrice(productUpdate.getPrice());
                    if (productUpdate.getCountInStock() != null)
                        existingProduct.setCountInStock(productUpdate.getCountInStock());
                    if (productUpdate.getActive() != null)
                        existingProduct.setActive(productUpdate.getActive());

                    Product saved = productRepository.save(existingProduct);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        if (product.getId() == null) {
            product.setId(java.util.UUID.randomUUID().toString());
        }
        Product saved = productRepository.save(product);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/{id}/reviews")
    public ResponseEntity<Product> createReview(@PathVariable String id,
            @RequestBody com.levelup.gaming.dto.ReviewCreateDTO reviewDTO) {
        return productRepository.findById(id)
                .map(product -> {
                    Review newReview = new Review();
                    newReview.setId(java.util.UUID.randomUUID().toString());
                    newReview.setName(reviewDTO.getName());
                    newReview.setRating(reviewDTO.getRating());
                    newReview.setComment(reviewDTO.getComment());
                    newReview.setCreatedAt(LocalDateTime.now());
                    product.getReviews().add(newReview);
                    int totalReviews = product.getReviews().size();
                    double averageRating = product.getReviews().stream()
                            .mapToInt(Review::getRating)
                            .average()
                            .orElse(0.0);

                    product.setNumReviews(totalReviews);
                    product.setRating(Math.round(averageRating * 10.0) / 10.0);

                    Product saved = productRepository.save(product);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
