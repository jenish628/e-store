package estore.product.controller;


import estore.product.dao.ProductDao;
import estore.product.dto.ProductDto;
import estore.product.entity.Product;

import eye2web.modelmapper.ModelMapper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Resource
    private ProductDao productDao;

    @GetMapping("/")
    public ResponseEntity<?> getAllTheProducts() {
        return ResponseEntity.ok().body(productDao.getAllProducts());

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok().body(productDao.getById(id));

    }

    @PostMapping("/")
    public ResponseEntity<?> addProducts(@RequestBody ProductDto product) {
        return ResponseEntity.ok(productDao.addProduct(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editProduct(@RequestBody ProductDto product,
                                         @PathVariable Long id) {
        return ResponseEntity.ok(productDao.updateProduct(product, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> DeleteById(@PathVariable Long id) {
        productDao.deleteProduct(id);
        return ResponseEntity.ok("Successfully deleted...");
    }


    @GetMapping("/category/{categoryId}")
    public List<ProductDto> getAllProductsByCategory(@PathVariable Long categoryId) {
        return productDao.findByCategory(categoryId);
    }


    @GetMapping("/user/{user}")
    public ResponseEntity<?> getAllByUser(@PathVariable String user) {
        return ResponseEntity.ok(productDao.getAllByUser(user));
    }


    @PutMapping("/user/{user}/{id}")
    public ResponseEntity<?> updateByUser(@RequestBody ProductDto productDto, @PathVariable Long user,
                             @PathVariable Long id) {
       return ResponseEntity.ok(productDao.updateByUser(productDto, user, id));
    }

    @DeleteMapping("/user/{user}")
    public String deleteByUser(@PathVariable String user) {
        return productDao.deleteByUser(user);

    }

}
