package com.akshayram.cloudstorage.security;

import com.akshayram.cloudstorage.mapper.UserMapper;
import com.akshayram.cloudstorage.model.User;
import com.akshayram.cloudstorage.services.HashService;
import java.util.ArrayList;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements AuthenticationProvider {
  private final UserMapper userMapper;
  private final HashService hashService;

  public AuthenticationService(UserMapper userMapper, HashService hashService) {
    this.userMapper = userMapper;
    this.hashService = hashService;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String password = authentication.getCredentials().toString();

    User user = userMapper.getUser(username);
    if (user != null) {
      String encodedSalt = user.getSalt();
      String hashedPassword = hashService.getHashedValue(password, encodedSalt);
      if (user.getPassword().equals(hashedPassword)) {
        return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
      }
    }

    return null;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
