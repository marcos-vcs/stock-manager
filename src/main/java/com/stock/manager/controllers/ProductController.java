package com.stock.manager.controllers;

import com.stock.manager.models.EditQuantityAttributes;
import com.stock.manager.models.Product;
import com.stock.manager.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Product product){
        try{
            this.service.create(product);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Product product){
        try{
            this.service.update(product);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/quantity")
    public ResponseEntity updateQuantity(@RequestBody EditQuantityAttributes quantityAttributes){
        try{
            this.service.updateQuantity(quantityAttributes.getId(), quantityAttributes.getQuantity());
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam String id){
        try{
            this.service.delete(id);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Product>> read(@RequestParam int skip, @RequestParam int limit){
        try{
            return new ResponseEntity<List<Product>>(this.service.read(skip, limit), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/filterByName")
    public ResponseEntity<List<Product>> filterByName(@RequestParam int skip, @RequestParam int limit, @RequestParam String name){
        try{
            return new ResponseEntity(this.service.findByName(skip, limit, name), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
