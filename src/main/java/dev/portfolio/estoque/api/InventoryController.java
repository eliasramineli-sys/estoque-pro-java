package dev.portfolio.estoque.api;
import dev.portfolio.estoque.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
@RestController @RequestMapping("/api/v1/products")
public class InventoryController {
 private final InventoryService service;
 public InventoryController(InventoryService service){this.service=service;}
 @PostMapping public ResponseEntity<ProductResponse> create(@Valid @RequestBody CreateProductRequest r){
  ProductResponse created=service.create(r);
  return ResponseEntity.created(URI.create("/api/v1/products/"+created.id())).body(created);
 }
 @GetMapping("/{id}") public ProductResponse get(@PathVariable Long id){return service.get(id);}
 @GetMapping public Page<ProductResponse> list(@PageableDefault(size=20,sort="id") Pageable pageable){return service.list(pageable);}
 @GetMapping("/low-stock") public List<ProductResponse> lowStock(){return service.lowStock();}
 @PostMapping("/{id}/movements") public ProductResponse move(@PathVariable Long id,@Valid @RequestBody MovementRequest r){return service.move(id,r);}
 @GetMapping("/{id}/movements") public Page<MovementResponse> history(@PathVariable Long id,@PageableDefault(size=20,sort="id",direction=Sort.Direction.DESC) Pageable pageable){return service.history(id,pageable);}
}
