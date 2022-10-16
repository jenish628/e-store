package estore.product.dto;

import estore.product.enumm.ProductEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ChangeUnitDto {
    private Long productId;
    private int quantity;
    private ProductEnum status;
}
