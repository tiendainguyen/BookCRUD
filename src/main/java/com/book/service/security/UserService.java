package com.book.service.security;

import com.book.entity.security.User;
import com.book.model.security.UserRequest;

public interface UserService {
  User save(UserRequest user);
}
