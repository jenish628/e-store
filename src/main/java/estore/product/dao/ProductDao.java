package estore.product.dao;

import estore.product.dto.ProductDto;
import estore.product.dto.ProductResponseDto;
import estore.product.entity.Product;

import java.util.List;

public interface ProductDao {

    public List<ProductResponseDto> getAllProducts();

    public ProductResponseDto getById(Long id);

    public ProductResponseDto addProduct(ProductDto product);

    public ProductResponseDto updateProduct(ProductDto product, Long id);

    public void deleteProduct(Long id);

    public List<ProductResponseDto> findByCategory(Long id);

    public List<ProductResponseDto> getAllByUser(String user);

    public String deleteByUser(String user);

    public Long updateByUser(ProductDto productDto, Long user, Long id);


    public Long changeAvailableUnits(ProductDto productDto);
}
