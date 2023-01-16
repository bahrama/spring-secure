package ir.alikhah.springsecurity.repository;

import ir.alikhah.springsecurity.model.Product;
import ir.alikhah.springsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository  extends JpaRepository<Product,Integer> {

}
