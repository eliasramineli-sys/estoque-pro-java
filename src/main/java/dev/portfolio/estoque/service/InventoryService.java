package dev.portfolio.estoque.service;
import dev.portfolio.estoque.api.*;
import dev.portfolio.estoque.domain.*;
import dev.portfolio.estoque.repository.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
@Service public class InventoryService {
 private final ProductRepository products;
 private final MovementRepository movements;
 public InventoryService(ProductRepository products,MovementRepository movements){this.products=products;this.movements=movements;}
 @Transactional public ProductResponse create(CreateProductRequest r){
  String sku=r.sku().trim();
  if(products.existsBySku(sku)) throw new IllegalArgumentException("SKU já cadastrado");
  return ProductResponse.from(products.saveAndFlush(new Product(sku,r.name().trim(),r.price(),r.minStock())));
 }
 @Transactional(readOnly=true) public ProductResponse get(Long id){return ProductResponse.from(find(id));}
 @Transactional(readOnly=true) public Page<ProductResponse> list(Pageable pageable){return products.findAll(pageable).map(ProductResponse::from);}
 @Transactional(readOnly=true) public List<ProductResponse> lowStock(){return products.findAll().stream().filter(Product::isLowStock).map(ProductResponse::from).toList();}
 @Transactional public ProductResponse move(Long id,MovementRequest r){
  Product product=find(id);
  if(r.type()==MovementType.ENTRY) product.receive(r.quantity());
  else product.dispatch(r.quantity());
  movements.save(new Movement(product,r.type(),r.quantity(),r.reason().trim()));
  return ProductResponse.from(products.saveAndFlush(product));
 }
 @Transactional(readOnly=true) public Page<MovementResponse> history(Long id,Pageable pageable){find(id);return movements.findByProductId(id,pageable).map(MovementResponse::from);}
 private Product find(Long id){return products.findById(id).orElseThrow(()->new NoSuchElementException("Produto não encontrado: "+id));}
}
