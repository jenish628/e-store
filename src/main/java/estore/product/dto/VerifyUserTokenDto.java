package estore.product.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VerifyUserTokenDto {
    private String email;
}
