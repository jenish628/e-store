package estore.product.controller;
import estore.product.dao.CategoryDao;
import estore.product.dto.CategoryDto;
import estore.product.dto.ProductDto;
import estore.product.entity.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/categories")

public class CategoryController {


    private final CategoryDao categoryDao;

    public CategoryController(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody @Valid CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryDao.add(categoryDto));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryDao.update(id, categoryDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        categoryDao.delete(id);
        return ResponseEntity.ok("Successfully deleted.");
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryDao.findById(id));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(categoryDao.findAll());
    }


    @GetMapping("/users/{user}")
    public ResponseEntity<?> findAllByUser(@PathVariable String user) {
        return ResponseEntity.ok(categoryDao.findAllByUser(user));
    }

}
