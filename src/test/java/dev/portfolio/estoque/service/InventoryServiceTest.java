package dev.portfolio.estoque.service;
import dev.portfolio.estoque.api.*;
import dev.portfolio.estoque.domain.*;
import dev.portfolio.estoque.repository.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class InventoryServiceTest {
 private final ProductRepository products=mock(ProductRepository.class);
 private final MovementRepository movements=mock(MovementRepository.class);
 private final InventoryService service=new InventoryService(products,movements);
 @Test void rejectsDuplicateSku(){when(products.existsBySku("ABC")).thenReturn(true);assertThrows(IllegalArgumentException.class,()->service.create(new CreateProductRequest("ABC","Produto",BigDecimal.TEN,2)));}
 @Test void recordsMovement(){Product p=new Product("ABC","Produto",BigDecimal.TEN,2);when(products.findById(1L)).thenReturn(Optional.of(p));when(products.saveAndFlush(p)).thenReturn(p);service.move(1L,new MovementRequest(MovementType.ENTRY,5,"Compra"));assertEquals(5,p.getQuantity());verify(movements).save(any(Movement.class));}
}
