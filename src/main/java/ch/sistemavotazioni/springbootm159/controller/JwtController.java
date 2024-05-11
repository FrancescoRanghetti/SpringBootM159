package ch.sistemavotazioni.springbootm159.controller;

import ch.sistemavotazioni.springbootm159.service.JwtService;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/")
public class JwtController {

  JwtService jwtService;

  public JwtController(JwtService jwtService) {
    this.jwtService = jwtService;
  }

  @GetMapping("/isValidToken/{token}/{username}/{role}")
  public boolean isValidToken(@PathVariable final String token, @PathVariable final String username, @PathVariable final String role) {
    return jwtService.isValidToken(token, username, role);
  }

  @GetMapping("/expiredJwt/{token}")
  public ResponseEntity<Map<String, String>> expiredJwt(@PathVariable final String token) {
    Map response = new HashMap<>();
    response.put("token", "");
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
