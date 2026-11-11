package dev.portfolio.estoque.api;
import dev.portfolio.estoque.domain.MovementType;
import jakarta.validation.constraints.*;
public record MovementRequest(@NotNull MovementType type,@Min(1) int quantity,@NotBlank @Size(max=250) String reason) {}
