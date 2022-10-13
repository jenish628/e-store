package estore.product.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProductDto {
    private Long productId;
    private String name;
    private String vendor;
    private String category;
    private Long availableUnits;
    private String username;

}
