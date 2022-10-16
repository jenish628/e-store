package estore.product.dto;

import estore.product.enumm.ProductEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class ProductDto {
    private Long productId;
    @NotNull(message = "Product name is required")
    private String name;
    @NotNull(message = "Vendor name is required")
    private String vendor;

    @NotNull(message = "Category is required")
    private Long category;
    @NotNull(message = "Available Units is required")
    private Long availableUnits;

    @Min(message = "Please put the price",value = 1)
    private double price;
    private String username;
    private ProductEnum productEnum;

}
