package com.springboot.hibernate.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.hibernate.product.Product;
import com.springboot.hibernate.service.ProductService;

@RestController
public class ProductController {
	private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("products/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }
    
    @PutMapping("products/{id}")
    public Product putProductById(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Product existingProduct = productService.getProductById(id);
        
        if (existingProduct != null) {
            // Cập nhật thông tin của đối tượng Product từ dữ liệu yêu cầu
            existingProduct.setName(updatedProduct.getName());
            
            // Lưu đối tượng Product đã cập nhật vào cơ sở dữ liệu
            return productService.saveProduct(existingProduct);
        } else {
            // Xử lý khi không tìm thấy sản phẩm với id tương ứng
        	//throw new NotFoundException("Product not found");
        }
		return existingProduct;
    }
    
    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @DeleteMapping("products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
