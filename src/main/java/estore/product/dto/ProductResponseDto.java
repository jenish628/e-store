package estore.product.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data


public class ProductResponseDto {
    private Long productId;
    private String name;
    private String vendor;
    private String  category;
    private Long availableUnits;
    private String username;
    private double price;
}
