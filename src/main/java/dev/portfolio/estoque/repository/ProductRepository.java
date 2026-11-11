package dev.portfolio.estoque.repository;
import dev.portfolio.estoque.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ProductRepository extends JpaRepository<Product,Long> {
 boolean existsBySku(String sku);
 List<Product> findByQuantityLessThanEqual(int threshold);
}
