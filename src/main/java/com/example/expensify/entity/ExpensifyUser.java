package com.example.expensify.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@MappedSuperclass
public class ExpensifyUser implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String username;

  private String password;

  @Enumerated(value = EnumType.STRING)
  private Role role;

  protected ExpensifyUser() {}

  public ExpensifyUser(String username, String password, Role role) {
    this.username = username;
    this.password = password;
    this.role = role;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  @JsonIgnore
  public String getPassword() {
    return this.password;
  }

  @Override
  @JsonIgnore
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singleton(new SimpleGrantedAuthority(role.toString()));
  }

  public Integer getId() {
    return this.id;
  }

  public Role getRole() {
    return this.role;
  }

  @Override
  @JsonIgnore
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isEnabled() {
    return true;
  }

  public enum Role {
    ROLE_EMPLOYEE,
    ROLE_REVIEWER;

    public String withoutRolePrefix() {
      return name().substring(5);
    }
  }
}
