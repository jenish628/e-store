package estore.product.entity;

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

    private String username;

    @ManyToOne
    private Category category;



}
