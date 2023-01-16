package ir.alikhah.springsecurity.controller;

import ir.alikhah.springsecurity.model.Product;
import ir.alikhah.springsecurity.model.User;
import ir.alikhah.springsecurity.service.MapValidationErrorService;
import ir.alikhah.springsecurity.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService iProductService;

    private final MapValidationErrorService mapValidationErrorService;

    @PostMapping("/create")
    public ResponseEntity<?> createNewUser(@Valid @RequestBody Product product, BindingResult result , Principal principal){
        System.out.println(principal.getName());
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap!=null) return errorMap;

        Product product1 = iProductService.save(product);
        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }
}
