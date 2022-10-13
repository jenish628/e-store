package estore.product.mapper;

import estore.product.dto.CategoryDto;
import estore.product.dto.ProductDto;
import estore.product.entity.Category;
import estore.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface CategoryMapper {


    Category toEntity(CategoryDto categoryDto);
    CategoryDto toDto(Category category);
    List<Category> toEntityList(List<CategoryDto> categoryDtoList);
    List<CategoryDto> toDtoList(List<Category> categoryList);

}
