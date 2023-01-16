package ir.alikhah.springsecurity.service.product;

import ir.alikhah.springsecurity.model.Product;
import ir.alikhah.springsecurity.model.User;

import javax.transaction.Transactional;

public interface IProductService{
    @Transactional
    Product save(Product product);
}
