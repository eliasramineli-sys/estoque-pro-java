package dev.portfolio.estoque.api;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import java.util.NoSuchElementException;
@RestControllerAdvice public class ApiExceptionHandler {
 @ExceptionHandler(NoSuchElementException.class) ResponseEntity<ProblemDetail> missing(NoSuchElementException e){return problem(HttpStatus.NOT_FOUND,e.getMessage());}
 @ExceptionHandler({IllegalArgumentException.class,DataIntegrityViolationException.class,ObjectOptimisticLockingFailureException.class}) ResponseEntity<ProblemDetail> conflict(Exception e){return problem(HttpStatus.CONFLICT,e instanceof IllegalArgumentException?e.getMessage():"Conflito de dados ou atualização concorrente");}
 @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<ProblemDetail> invalid(MethodArgumentNotValidException e){return problem(HttpStatus.BAD_REQUEST,"Requisição inválida: "+e.getBindingResult().getFieldErrors().stream().map(f->f.getField()+" "+f.getDefaultMessage()).toList());}
 private ResponseEntity<ProblemDetail> problem(HttpStatus status,String detail){return ResponseEntity.status(status).body(ProblemDetail.forStatusAndDetail(status,detail));}
}
