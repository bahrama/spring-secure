package ir.alikhah.springsecurity.controller;

import ir.alikhah.springsecurity.model.User;
import ir.alikhah.springsecurity.security.JWTLoginSucessReponse;
import ir.alikhah.springsecurity.security.JwtTokenProvider;
import ir.alikhah.springsecurity.security.LoginRequest;
import ir.alikhah.springsecurity.service.IUserService;
import ir.alikhah.springsecurity.service.MapValidationErrorService;
import ir.alikhah.springsecurity.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final IUserService iUserService;
    private final MapValidationErrorService mapValidationErrorService;

    private final UserValidator userValidator;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider tokenProvider;

    @PostMapping("/create")
    public ResponseEntity<?> createNewUser(@Valid @RequestBody User user , BindingResult result){
        userValidator.validate(user,result);
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap!=null) return errorMap;

        User user1 = iUserService.save(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }
    @GetMapping("/find/{username}")
   public ResponseEntity<User> findUserByUsername(@PathVariable String username){
          User user = iUserService.findUserByUsername(username);
        return new ResponseEntity<User>(user, HttpStatus.OK);
   }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = "Bearer " +  tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTLoginSucessReponse(true, jwt));
    }
}
