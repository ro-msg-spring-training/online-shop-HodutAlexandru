package ro.msg.learning.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.bf.ProductBf;
import ro.msg.learning.shop.models.dto.ProductDto;
import ro.msg.learning.shop.payload.BaseResponse;
import ro.msg.learning.shop.payload.product.ProductResponse;
import ro.msg.learning.shop.payload.product.ProductsResponse;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductBf productBf;

    @GetMapping("/all")
    public ResponseEntity<ProductsResponse> getProducts() {
        return ResponseEntity.ok(this.productBf.getProducts());
    }

    @GetMapping("/product")
    public ResponseEntity<ProductResponse> getProduct(@Valid @RequestParam int productId){
        return ResponseEntity.ok(this.productBf.getProduct(productId));
    }

    @PostMapping("/product/new")
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductDto productDto) {
        return ResponseEntity.ok(this.productBf.createProduct(productDto));
    }

    @PutMapping("/product/update")
    public ResponseEntity<ProductResponse> updateProduct(@Valid @RequestParam int productId, @Valid @RequestBody ProductDto productDto) {
        return ResponseEntity.ok(this.productBf.updateProduct(productId, productDto));
    }

    @DeleteMapping("/product/delete")
    public ResponseEntity<BaseResponse> deleteProduct(@Valid @RequestParam int productId) {
        return ResponseEntity.ok(this.productBf.deleteProduct(productId));
    }

}
