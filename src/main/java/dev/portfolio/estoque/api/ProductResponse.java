package dev.portfolio.estoque.api;
import dev.portfolio.estoque.domain.Product;
import java.math.BigDecimal;
public record ProductResponse(Long id,String sku,String name,BigDecimal price,int quantity,int minStock,boolean lowStock) {
 public static ProductResponse from(Product p){return new ProductResponse(p.getId(),p.getSku(),p.getName(),p.getPrice(),p.getQuantity(),p.getMinStock(),p.isLowStock());}
}
