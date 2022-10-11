package estore.product.dao;

import estore.product.dto.ProductDto;
import estore.product.entity.Product;
import estore.product.repository.ProductRepository;
import eye2web.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductDaoImp implements ProductDao{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper mapper;


    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(a-> mapper.map(a, ProductDto.class)).collect(Collectors.toList());
    }

    @Override
    public ProductDto getById(Long id) {
        var product = productRepository.findById(id).orElseThrow();
        return mapper.map(product, ProductDto.class);
    }

    @Override
    public void addProduct(ProductDto product) {
        var prod = mapper.map(product, Product.class);
        productRepository.save(prod);
    }

    @Override
    public Long updateProduct(ProductDto product, Long id) {
        Product product1 = productRepository.findById(id).orElseThrow();
        product1.setProductId(product.getProductId());
        product1.setName(product.getName());
        product1.setVendor(product.getVendor());
        product1.setCategory(product.getCategory());
        product1.setAvailableUnits(product.getAvailableUnits());
        productRepository.save(product1);
        return product.getProductId();
    }

    @Override
    public boolean deleteProduct(Long id) {
        try {
            productRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
