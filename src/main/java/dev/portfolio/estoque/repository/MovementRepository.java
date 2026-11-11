package dev.portfolio.estoque.repository;
import dev.portfolio.estoque.domain.Movement;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MovementRepository extends JpaRepository<Movement,Long> {
 Page<Movement> findByProductId(Long productId,Pageable pageable);
}
