package estore.product.dao;

import estore.product.dto.ProductDto;
import estore.product.entity.Product;
import estore.product.exception.ProductNotFoundException;
import estore.product.exception.UserNotFoundException;
import estore.product.repository.ProductRepository;
import estore.product.util.AppUtil;
import eye2web.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper mapper;


    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(a -> mapper.map(a, ProductDto.class)).collect(Collectors.toList());
    }

    @Override
    public ProductDto getById(Long id) {
        return mapper.map(findById(id), ProductDto.class);
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        return getDtoByEntity(productRepository.save(getEntityByDto(productDto)));
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, Long id) {

        Product product = findById(id);
        productDto.setProductId(id);
        product = getEntityByDto(productDto);
        return getDtoByEntity(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.delete(findById(id));

    }

    @Override
    public List<ProductDto> findByCategory(Long id) {
        return productRepository.findAllByCategory(id).stream()
                .map(a -> mapper.map(a, ProductDto.class)).collect(Collectors.toList());
    }


    @Override
    public List<ProductDto> getAllByUser(String user) {

        return productRepository.findAllByUsername(user).stream()
                .map(this::getDtoByEntity).collect(Collectors.toList());

    }

    @Override
    public String deleteByUser(String user) {
        try {
            productRepository.deleteAll(productRepository.findAllByUsername(user));
            return "Delete Sucessful";
        } catch (Exception e) {
            throw new UserNotFoundException("Cannot delete");
        }

    }

    @Override
    public Long updateByUser(ProductDto productDto, Long user, Long id) {

        return null;
    }




    private Product findById(Long id) {
        Optional<Product> productOptional = productRepository.findByProductIdAndUsername(id, AppUtil.getCurrentUser());
        if (productOptional.isEmpty()) throw new ProductNotFoundException("Product not found");
        return productOptional.get();

    }


    private Product getEntityByDto(ProductDto productDto) {
        Product product = mapper.map(productDto, Product.class);
        product.setUsername(AppUtil.getCurrentUser());
        return product;

    }


    private ProductDto getDtoByEntity(Product product) {
        return mapper.map(product, ProductDto.class);
    }

}
