package estore.product.repository;

import estore.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByProductIdAndUsername(Long productId,String userId);

    public Optional<Product> findByUsername(String username);
    public List<Product> findAllByUsername(String username);

    public List<Product> findAllByCategory(Long categoryId);
}
