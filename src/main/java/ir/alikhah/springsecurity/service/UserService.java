package ir.alikhah.springsecurity.service;

import ir.alikhah.springsecurity.exception.user.UserAlreadyExistsException;
import ir.alikhah.springsecurity.exception.user.UserNotFindException;
import ir.alikhah.springsecurity.model.User;
import ir.alikhah.springsecurity.repository.UserRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService{

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    @Override
    public User save(User user){
        try {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }catch (Exception e){
            throw new UserAlreadyExistsException("user :" + user.getUsername() + "already exist");
        }
    }

    @Override
    public User findUserByUsername(String username){
       Optional<User> user =  userRepository.findUsersByUsername(username);
       if(user.isPresent()){
           return user.get();
       }else {
           throw new UserNotFindException("user :" + username + "not find");
       }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUsersByUsername(username).get();
        if(user==null) new UsernameNotFoundException("User not found");
        return user;
    }
}
