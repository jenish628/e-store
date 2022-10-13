package estore.product.dao;

import estore.product.dto.CategoryDto;
import estore.product.entity.Category;
import estore.product.exception.CategoryNotFoundException;
import estore.product.mapper.CategoryMapper;
import estore.product.repository.CategoryRepository;
import estore.product.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CategoryDaoImpl implements CategoryDao {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryDaoImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto add(CategoryDto categoryDto) {
        return toDto(categoryRepository.save(toEntity(categoryDto)));
    }

    @Override
    public CategoryDto update(Long id, CategoryDto categoryDto) {
        findByIdAndUsername(id);
        categoryDto.setCategoryId(id);
        return toDto(categoryRepository.save(toEntity(categoryDto)));
    }

    @Override
    public void delete(Long id) {
        categoryRepository.delete(findByIdAndUsername(id));
    }

    @Override
    public CategoryDto findById(Long id) {
        return toDto(findByIdAndUsername(id));
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> findAllByUser(String user) {
        return categoryRepository
                .findAllByUser(user)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


    private Category toEntity(CategoryDto categoryDto) {
        Category category = this.categoryMapper.toEntity(categoryDto);
        category.setUser(AppUtil.getCurrentUser());
        return category;
    }

    private CategoryDto toDto(Category category) {
        return this.categoryMapper.toDto(category);
    }


    Category findByIdAndUsername(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findByCategoryIdAndUser(id, AppUtil.getCurrentUser());
        if (optionalCategory.isEmpty()) throw new CategoryNotFoundException("Category not found");
        return optionalCategory.get();
    }


    @Override
    public Category verifyCategoryWhileAddingProduct(Long id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isEmpty()) throw new CategoryNotFoundException("Category not found");
        return optionalCategory.get();
    }


    public void saveCategory(Category category){
        this.categoryRepository.saveAndFlush(category);
    }



}

