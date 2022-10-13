package estore.product.dao;

import estore.product.dto.CategoryDto;
import estore.product.entity.Category;

import java.util.List;

public interface  CategoryDao {

    CategoryDto add(CategoryDto categoryDto);
    CategoryDto update(Long id,CategoryDto categoryDto);
    void  delete(Long id);
    CategoryDto findById(Long id);
    List<CategoryDto> findAll();
    List<CategoryDto> findAllByUser(String user);

    Category verifyCategoryWhileAddingProduct(Long id);


}
