package com.book.service.security;

import com.book.entity.security.Role;

public interface RoleService {
  Role findByName(String name);
}
