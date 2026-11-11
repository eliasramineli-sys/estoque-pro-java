package dev.portfolio.estoque.domain;
import jakarta.persistence.*;
import java.time.Instant;
@Entity @Table(name="movements")
public class Movement {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="product_id") private Product product;
 @Enumerated(EnumType.STRING) @Column(nullable=false,length=12) private MovementType type;
 @Column(nullable=false) private int quantity;
 @Column(nullable=false,length=250) private String reason;
 @Column(nullable=false) private Instant createdAt;
 protected Movement() {}
 public Movement(Product product,MovementType type,int quantity,String reason) {this.product=product;this.type=type;this.quantity=quantity;this.reason=reason;this.createdAt=Instant.now();}
 public Long getId(){return id;} public MovementType getType(){return type;} public int getQuantity(){return quantity;}
 public String getReason(){return reason;} public Instant getCreatedAt(){return createdAt;}
}
