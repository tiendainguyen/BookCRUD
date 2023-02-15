package com.book.service.impl;

import com.book.entity.security.Role;
import com.book.entity.security.User;
import com.book.model.security.UserRequest;
import com.book.repository.UserRepository;
import com.book.service.security.RoleService;
import com.book.service.security.UserService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private RoleService roleService;
  @Autowired
  private BCryptPasswordEncoder bcryptEncoder;
  @Override
  public User save(UserRequest user) {
    User userDomain = new User();
    BeanUtils.copyProperties(user,userDomain);
    userDomain.setPassword(bcryptEncoder.encode(user.getPassword()));

    Role role = roleService.findByName("USER");
    Set<Role> roleSet = new HashSet<>();
    roleSet.add(role);

    if(userDomain.getEmail().split("@")[1].equals("admin.edu")){
      role = roleService.findByName("ADMIN");
      roleSet.add(role);
    }

    userDomain.setRoles(roleSet);
    return userRepository.save(userDomain);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);
    if(user == null){
      throw new UsernameNotFoundException("Invalid username or password.");
    }
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
  }
  private Set<SimpleGrantedAuthority> getAuthority(User user) {
    Set<SimpleGrantedAuthority> authorities = new HashSet<>();
    user.getRoles().forEach(role -> {
      authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
    });
    return authorities;
  }
}
