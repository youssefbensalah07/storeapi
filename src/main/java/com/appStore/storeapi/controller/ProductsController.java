package com.appStore.storeapi.controller;

import com.appStore.storeapi.models.*;
import com.appStore.storeapi.repositories.productsRepository;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/products")

public class ProductsController {
    @Autowired
    private productsRepository repos;

    @GetMapping()
    public List<Product> getProducts(){
        return repos.findAll();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id){
        Product product=repos.findById(id).orElse(null);

        if (product==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping()
    public ResponseEntity<Object> createProduct(@Valid @RequestBody ProductDto productDto, BindingResult results){

        double price=0;

        try {
            price=Double.parseDouble(productDto.getPrice());
        }
        catch (Exception e){

            results.addError(new FieldError("ProductsDto","price","the price should be a number"));
        }

        if(results.hasErrors()){
            var errorsList=results.getAllErrors();
            var errorsMap=new HashMap<String,String>();

            for (int i=0;i<errorsList.size();i++){
                var errors=(FieldError) errorsList.get(i);
                errorsMap.put(errors.getField(),errors.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errorsMap);
        }

        Product product=new Product();
        product.setName(productDto.getName());
        product.setBrand(productDto.getBrand());
        product.setCategory(productDto.getCategory());
        product.setPrice(price);
        product.setDescription(productDto.getDescription());
        product.setCreatedAt(new Date());
        product.setImageUrl(productDto.getImageUrl());
        repos.save(product);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@Valid @RequestBody ProductDto productDto, BindingResult results,@PathVariable int id){

        Product product=repos.findById(id).orElse(null);
        if (product==null){
            return ResponseEntity.notFound().build();
        }

        double price=0;
        try {
            price=Double.parseDouble(productDto.getPrice());

        }
        catch (Exception e){
            results.addError(new FieldError("ProductsDto","price","the price should be a number"));
        }


        if(results.hasErrors()){
            var errorsList=results.getAllErrors();
            var errorsMap=new HashMap<String,String>();

            for (int i=0;i<errorsList.size();i++){
                var errors=(FieldError) errorsList.get(i);
                errorsMap.put(errors.getField(),errors.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errorsMap);
        }


        product.setName(productDto.getName());
        product.setBrand(productDto.getBrand());
        product.setCategory(productDto.getCategory());
        product.setPrice(price);
        product.setDescription(productDto.getDescription());
        product.setCreatedAt(new Date());
        product.setImageUrl(productDto.getImageUrl());



        repos.save(product);
        return ResponseEntity.ok (product);


    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object>deleteProduct(@PathVariable int id){
        Product product=repos.findById(id).orElse(null);
        if (product==null){
            return ResponseEntity.notFound().build();
        }
        repos.delete(product);
        return ResponseEntity.ok().build();
    }
    }
