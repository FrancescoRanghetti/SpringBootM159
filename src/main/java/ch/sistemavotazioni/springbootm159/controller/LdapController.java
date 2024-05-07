package ch.sistemavotazioni.springbootm159.controller;

import ch.sistemavotazioni.springbootm159.entity.CredentialLogin;
import ch.sistemavotazioni.springbootm159.service.JwtService;
import ch.sistemavotazioni.springbootm159.service.LdapService;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/")
public class LdapController {

  private LdapService ldapService;
  private JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public LdapController(final AuthenticationManager authenticationManager, LdapService ldapService, JwtService jwtService) {
    this.authenticationManager = authenticationManager;
    this.ldapService = ldapService;
    this.jwtService = jwtService;
  }

  @PostMapping("/loginLdap")
  public ResponseEntity<Map<String, String>> login(@RequestBody CredentialLogin credential) {
    try {
      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(credential.getName(), credential.getPassword());
      Authentication authentication = authenticationManager.authenticate(authenticationToken);
      SecurityContextHolder.getContext().setAuthentication(authentication);
      String token = jwtService.generateToken(credential.getName(), getRole(credential.getName()));
      Map response = new HashMap<>();
      response.put("token", token);
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      System.out.println("Exception: " + e);
      e.printStackTrace();
      return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }
  }

  public String getRole(String name) {
    String roleUser = ldapService.getGroupFromUsername(name).get(0).toString();
    if (roleUser.contains("Administrator")) {
      return "Administrator";
    }
    return "User";
  }
}
