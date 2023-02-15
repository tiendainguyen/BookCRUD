package com.book.controller;

import com.book.config.TokenProvider;
import com.book.model.AuthResponse;
import com.book.model.AuthToken;
import com.book.model.security.UserRequest;
import com.book.service.security.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
@Slf4j
public class AuthController {
  @Autowired(required = true)
  private AuthenticationManager authenticationManager;
  @Autowired
  private TokenProvider tokenProvider;
  @Autowired
  private UserService userService;
  @PostMapping("/login")
  public AuthResponse login(@RequestBody UserRequest userRequest){
    final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(),userRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    final String authToken = tokenProvider.generateToken(authentication);
    return AuthResponse.of(HttpStatus.OK.value(),new AuthToken(authToken));
  }
  @PostMapping("/register")
  public AuthResponse saveUser(@RequestBody UserRequest userReq){
    BeanUtils.copyProperties(userService.save(userReq),userReq);
    return AuthResponse.of(HttpStatus.OK.value(),userReq );
  }
}
