package ch.sistemavotazioni.springbootm159.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CredentialLogin {

  private String name;
  private String password;
}
