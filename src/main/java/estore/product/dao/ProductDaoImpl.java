package estore.product.dao;

import estore.product.dto.CategoryDto;
import estore.product.dto.ProductDto;
import estore.product.dto.ProductResponseDto;
import estore.product.entity.Category;
import estore.product.entity.Product;
import estore.product.exception.ProductNotFoundException;
import estore.product.exception.UserNotFoundException;
import estore.product.mapper.ProductMapper;
import estore.product.repository.ProductRepository;

import estore.product.util.AppUtil;
import org.apache.tomcat.Jar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ProductDaoImpl implements ProductDao {

private static final Logger LOGGER = LoggerFactory.getLogger(ProductDaoImpl.class);

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    private final CategoryDao categoryDao;


    @Autowired
    public ProductDaoImpl(ProductRepository productRepository, ProductMapper productMapper, CategoryDao categoryDao) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryDao = categoryDao;
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        return this.productMapper.toDtoList(productRepository.findAll());
    }

    @Override
    public ProductResponseDto getById(Long id) {
        Product product = findById(id);
        LOGGER.info("Product detail",product);
        LOGGER.debug("Product detail",product);
        LOGGER.trace("Product detail",product);

        return this.productMapper.toDto(product);
    }

    @Override
    public ProductResponseDto addProduct(ProductDto productDto) {

        Category category = categoryDao.verifyCategoryWhileAddingProduct(productDto.getCategory());
        Product product = productRepository.save(getEntityByDto(productDto));
        category.addProduct(product);
        return getDtoByEntity(product);
    }

    @Override
    public ProductResponseDto updateProduct(ProductDto productDto, Long id) {

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
    public List<ProductResponseDto> findByCategory(Long id) {

        return this.productMapper.toDtoList(productRepository.findAllByCategory(id));
    }


    @Override
    public List<ProductResponseDto> getAllByUser(String user) {


        return this.productMapper.toDtoList(productRepository.findAllByUsername(user));


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
        Product product = this.productMapper.toEntity(productDto);
        product.setUsername(AppUtil.getCurrentUser());
        return product;

    }


    private ProductResponseDto getDtoByEntity(Product product) {
        return this.productMapper.toDto(product);
    }

}
