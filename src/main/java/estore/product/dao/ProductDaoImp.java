package estore.product.dao;

import estore.product.dto.ProductDto;
import estore.product.entity.Category;
import estore.product.entity.Product;
import estore.product.exception.UserNotFoundException;
import estore.product.repository.ProductRepository;
import eye2web.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;
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

    @Override
    public List<ProductDto> findByCategory(Long id) {
        return productRepository.findAllByCategory(id).stream()
                .map(a->mapper.map(a, ProductDto.class)).collect(Collectors.toList());
    }


//    USER

    @Override
    public void addByUser(ProductDto productDto) {

    }

    @Override
    public List<ProductDto> getAllByUser(Long user) {

            return productRepository.findAllByUser(user).stream()
                    .map(a->mapper.map(a,ProductDto.class)).collect(Collectors.toList());

    }

    @Override
    public String deleteByUser(Long user) {
        try {
            productRepository.deleteAll(productRepository.findAllByUser(user));
            return "Delete Sucessful";
        }catch (Exception e){
            throw new UserNotFoundException("Cannot delete");
        }

    }

    @Override
    public Long updateByUser(ProductDto product, Long user, Long id) {

        Optional<Product> pro = productRepository.findByUser(user);
        if (pro.isPresent()) {
            Product product1 = productRepository.findById(id).orElseThrow();
            product1.setProductId(product.getProductId());
            product1.setName(product.getName());
            product1.setVendor(product.getVendor());

            product1.setAvailableUnits(product.getAvailableUnits());
            productRepository.save(product1);
            return product.getProductId();

        }else {
            throw new UserNotFoundException("User not found");
        }

    }
}
