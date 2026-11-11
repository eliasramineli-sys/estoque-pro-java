package dev.portfolio.estoque.api;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
public record CreateProductRequest(@NotBlank @Size(max=60) String sku,
 @NotBlank @Size(max=150) String name,@NotNull @DecimalMin("0.00") @Digits(integer=12,fraction=2) BigDecimal price,
 @Min(0) int minStock) {}
