package estore.product.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CategoryDto {
    private Long categoryId;
    @NotNull(message = "Category Name is required")
    private String name;
    private String user;
}
