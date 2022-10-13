package estore.product.mapper;


import estore.product.dto.ProductDto;
import estore.product.dto.ProductResponseDto;
import estore.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {


    @Mapping(source = "category", target = "category.categoryId")
    Product toEntity(ProductDto productDto);

    @Mapping(source = "category.name", target = "category")
    ProductResponseDto toDto(Product product);

    List<Product> toEntityList(List<ProductDto> productDtoList);

    List<ProductResponseDto> toDtoList(List<Product> products);


}
