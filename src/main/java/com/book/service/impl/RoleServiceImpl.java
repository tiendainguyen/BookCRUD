package com.book.service.impl;

import com.book.entity.security.Role;
import com.book.repository.RoleRepository;
import com.book.service.security.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {
  @Autowired
  private RoleRepository roleRepository;

  @Override
  public Role findByName(String name) {
    Role role = roleRepository.findRoleByName(name);
    return role;
  }
}
