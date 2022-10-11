package estore.product.controller;


import estore.product.dao.ProductDao;
import estore.product.dto.ProductDto;
import estore.product.entity.Product;

import eye2web.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Resource
    private ProductDao productDao;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/")
    public List<ProductDto> getAllTheProducts(){
        return productDao.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id){
        return productDao.getById(id);

    }

    @PostMapping("/")
    public void addProducts(@RequestBody ProductDto product){
        productDao.addProduct(product);
    }

    @PutMapping("/{id}")
    public Long editProduct(@RequestBody ProductDto product,
                            @PathVariable Long id){
       return productDao.updateProduct(product,id);

    }

    @DeleteMapping("/{id}")
    public boolean DeleteById(@PathVariable Long id){
        return productDao.deleteProduct(id);
    }

}
