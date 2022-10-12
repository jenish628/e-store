package estore.product.controller;


import estore.product.dao.ProductDao;
import estore.product.dto.ProductDto;
import estore.product.entity.Product;

import eye2web.modelmapper.ModelMapper;
import org.apache.catalina.User;
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





    @GetMapping("/user/{user}")
    public List<ProductDto> getAllByUser(@PathVariable Long user){
        return productDao.getAllByUser(user);
    }

    @PostMapping("/user/")
    public void addProductByUser( @RequestBody ProductDto productDto)
    {
        productDao.addByUser(productDto);
    }

    @PutMapping("/user/{user}/{id}")
    public Long updateByUser(@RequestBody ProductDto productDto, @PathVariable Long user,
                             @PathVariable Long id){
        return productDao.updateByUser(productDto,user, id);
    }

    @DeleteMapping("/user/{user}")
    public String deleteByUser(@PathVariable Long user){
         return productDao.deleteByUser(user);

    }

}
