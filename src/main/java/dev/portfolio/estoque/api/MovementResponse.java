package dev.portfolio.estoque.api;
import dev.portfolio.estoque.domain.*;
import java.time.Instant;
public record MovementResponse(Long id,MovementType type,int quantity,String reason,Instant createdAt){
 public static MovementResponse from(Movement m){return new MovementResponse(m.getId(),m.getType(),m.getQuantity(),m.getReason(),m.getCreatedAt());}
}
