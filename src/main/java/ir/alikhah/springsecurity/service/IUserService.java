package ir.alikhah.springsecurity.service;

import ir.alikhah.springsecurity.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {
    User save(User user);

    User findUserByUsername(String username);
}
