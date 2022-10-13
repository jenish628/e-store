package estore.product.repository;


import estore.product.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {


    List<Category> findAllByUser(String username);
    Optional<Category> findByCategoryIdAndUser(Long id,String username);


}
