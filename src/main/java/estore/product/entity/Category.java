package estore.product.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    private String name;
    private String user;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Product> products;


    public void addProduct(Product product) {
        if (this.products == null) this.products = new ArrayList<>();
        this.products.add(product);
        product.setCategory(this);
    }

    public void removeProduct(Product product) {
        if (this.products == null) this.products = new ArrayList<>();
        this.products.remove(product);
        product.setCategory(null);
    }
}
