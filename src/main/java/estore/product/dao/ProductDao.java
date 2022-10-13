package estore.product.dao;

import estore.product.dto.ProductDto;
import estore.product.entity.Product;

import java.util.List;

public interface ProductDao {

    public List<ProductDto> getAllProducts();

    public ProductDto getById(Long id);

    public ProductDto addProduct(ProductDto product);

    public ProductDto updateProduct(ProductDto product, Long id);

    public void deleteProduct(Long id);

    public List<ProductDto> findByCategory(Long id);

    public List<ProductDto> getAllByUser(String user);

    public String deleteByUser(String user);

    public Long updateByUser(ProductDto productDto, Long user, Long id);


}
