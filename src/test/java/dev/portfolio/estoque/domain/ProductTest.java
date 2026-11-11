package dev.portfolio.estoque.domain;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
class ProductTest {
 @Test void entryAndExitChangeStock(){Product p=new Product("SKU-1","Mouse",new BigDecimal("50.00"),2);p.receive(10);p.dispatch(8);assertEquals(2,p.getQuantity());assertTrue(p.isLowStock());}
 @Test void cannotDispatchBeyondStock(){Product p=new Product("SKU-2","Teclado",new BigDecimal("100.00"),1);assertThrows(IllegalArgumentException.class,()->p.dispatch(1));assertEquals(0,p.getQuantity());}
}
