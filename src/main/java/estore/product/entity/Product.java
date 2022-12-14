package estore.product.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import estore.product.enumm.ProductEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@SecondaryTable(name = "available")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String name;
    private String vendor;
    @Column (table = "available")
    private Long availableUnits;
    private double price;
    private String username;

    @ManyToOne
    @JsonManagedReference
    private Category category;

    @Enumerated(EnumType.STRING)
    private ProductEnum productEnum;


}
