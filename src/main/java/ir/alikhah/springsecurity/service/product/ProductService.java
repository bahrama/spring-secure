package ir.alikhah.springsecurity.service.product;

import ir.alikhah.springsecurity.exception.user.UserAlreadyExistsException;
import ir.alikhah.springsecurity.model.Product;
import ir.alikhah.springsecurity.model.User;
import ir.alikhah.springsecurity.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ProductService implements IProductService{
    private final ProductRepository productRepository;

    @Transactional
    @Override
    public Product save(Product product){
        try {
            return productRepository.save(product);
        }catch (Exception e){
            throw new UserAlreadyExistsException("user :"  + "already exist");
        }
    }
}
