package com.book.model.security;

import lombok.Data;

@Data
public class UserRequest {
  private String username;
  private String password;
  private String email;
  private String phone;
  private String name;
  private String businessTitle;
}
