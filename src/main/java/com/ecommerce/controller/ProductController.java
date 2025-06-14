package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.config.AppConstants;
import com.ecommerce.dto.ProductDTO;
import com.ecommerce.payload.ProductRequest;
import com.ecommerce.payload.ProductResponse;
import com.ecommerce.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/admin/categories/{categoryId}/product")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductRequest productRequest,
                                                 @PathVariable Long categoryId){
    	ProductDTO productDTO = productService.addProduct(categoryId, productRequest);
        return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }
    
    @GetMapping("/public/products")
    public ResponseEntity<ProductResponse> getAllProducts(
    		@RequestParam(name = "pageNumber" , defaultValue = AppConstants.PAGE_NUMBER ,required = false ) Integer pageNumber,
    		@RequestParam(name = "pageSize" , defaultValue = AppConstants.PAGE_SIZE , required = false) Integer pageSize,
    		@RequestParam(name = "sortBy" , defaultValue = AppConstants.SORT_PRODUCTS_BY) String sortBy,
    		@RequestParam(name = "sortOrder" ,defaultValue = AppConstants.SORT_DIR) String sortOrder
    		
    		){
    	return new ResponseEntity<>(productService.getAllProducts(pageNumber, pageSize, sortBy, sortOrder) , HttpStatus.OK);
    }
    
    @GetMapping("/public/categories/{categoryId}/products")
    public ResponseEntity<ProductResponse> getProductsByCategory(@PathVariable Long categoryId,
                                                                 @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                                 @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                                 @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_PRODUCTS_BY, required = false) String sortBy,
                                                                 @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder){
        ProductResponse productResponse = productService.searchByCategory(categoryId, pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }
    
    @GetMapping("/public/products/keyword/{keyword}")
    public ResponseEntity<ProductResponse> getProductsByKeyword(@PathVariable String keyword,
    		@RequestParam(name = "pageNumber" , defaultValue = AppConstants.PAGE_NUMBER ,required = false ) Integer pageNumber,
    		@RequestParam(name = "pageSize" , defaultValue = AppConstants.PAGE_SIZE , required = false) Integer pageSize,
    		@RequestParam(name = "sortBy" , defaultValue = AppConstants.SORT_PRODUCTS_BY) String sortBy,
    		@RequestParam(name = "sortOrder" ,defaultValue = AppConstants.SORT_DIR) String sortOrder
    		){
        ProductResponse productResponse = productService.searchProductByKeyword(keyword , pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(productResponse, HttpStatus.FOUND);
    }

    @PutMapping("/admin/products/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductRequest productRequest,
                                                    @PathVariable Long productId){
        ProductDTO updatedProductDTO = productService.updateProduct(productId, productRequest);
        return new ResponseEntity<>(updatedProductDTO, HttpStatus.OK);
    }

    @DeleteMapping("/admin/products/{productId}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long productId){
        ProductDTO deletedProduct = productService.deleteProduct(productId);
        return new ResponseEntity<>(deletedProduct, HttpStatus.OK);
    }
}
