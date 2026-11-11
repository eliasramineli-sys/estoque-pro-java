package dev.portfolio.estoque.domain;
import jakarta.persistence.*;
import java.math.BigDecimal;
@Entity @Table(name="products")
public class Product {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,unique=true,length=60) private String sku;
 @Column(nullable=false,length=150) private String name;
 @Column(nullable=false,precision=14,scale=2) private BigDecimal price;
 @Column(nullable=false) private int quantity;
 @Column(nullable=false) private int minStock;
 @Version private long version;
 protected Product() {}
 public Product(String sku,String name,BigDecimal price,int minStock) {this.sku=sku;this.name=name;this.price=price;this.minStock=minStock;}
 public void receive(int amount) {quantity=Math.addExact(quantity,amount);}
 public void dispatch(int amount) {if(quantity<amount) throw new IllegalArgumentException("Estoque insuficiente");quantity-=amount;}
 public Long getId(){return id;} public String getSku(){return sku;} public String getName(){return name;}
 public BigDecimal getPrice(){return price;} public int getQuantity(){return quantity;} public int getMinStock(){return minStock;}
 public boolean isLowStock(){return quantity<=minStock;}
}
