package estore.product.dao;

import estore.product.dto.ProductDto;
import estore.product.entity.Product;

import java.util.List;

public interface ProductDao {

    public List<ProductDto> getAllProducts();
    public ProductDto getById(Long id);
    public void addProduct(ProductDto product);
    public Long updateProduct(ProductDto product, Long id);
    public boolean deleteProduct(Long id);


    public List<ProductDto> findByCategory(Long id);

    public void addByUser(ProductDto productDto);
    public List<ProductDto> getAllByUser(Long user);
    public String deleteByUser(Long user);
    public Long updateByUser(ProductDto productDto, Long user, Long id);


}
