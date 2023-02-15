package com.book.entity.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long id;

  @Column
  private String username;

  @Column
  @JsonIgnore
  private String password;

  @Column
  private String email;

  @Column
  private String phone;

  @Column
  private String name;

  @Column
  private String businessTitle;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(name = "USER_ROLES",
      joinColumns = {
          @JoinColumn(name = "USER_ID")
      },
      inverseJoinColumns = {
          @JoinColumn(name = "ROLE_ID") })
  private Set<Role> roles;
}
