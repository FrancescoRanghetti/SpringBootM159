package ch.sistemavotazioni.springbootm159.controller;

import ch.sistemavotazioni.springbootm159.entity.CredentialLogin;
import ch.sistemavotazioni.springbootm159.service.LdapService;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LdapController {

  private static final String LDAP_URL = "ldap://192.168.56.102:50000";
  private LdapService ldapService;
  private UserDetailsContextMapper userDetailsContextMapper;
  private final AuthenticationManager authenticationManager;

  public LdapController(final AuthenticationManager authenticationManager, UserDetailsContextMapper userDetailsContextMapper, LdapService ldapService) {
    this.authenticationManager = authenticationManager;
    this.userDetailsContextMapper = userDetailsContextMapper;
    this.ldapService = ldapService;
  }

  @PostMapping("/loginLdap")
  public ResponseEntity<UsernamePasswordAuthenticationToken> login(@RequestBody CredentialLogin credential) {
    try {
      System.out.println(credential.getName());
      System.out.println(credential.getPassword());
      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(credential.getName(), credential.getPassword());
      Authentication authentication = authenticationManager.authenticate(authenticationToken);
      SecurityContextHolder.getContext().setAuthentication(authentication);
      System.out.println(authentication);
      System.out.println(authenticationToken);
      System.out.println(ldapService.getGroupFromUsername(credential.getName()));
      return new ResponseEntity<>(authenticationToken, HttpStatus.OK);
    } catch (Exception e) {
      System.out.println("Exception: " + e);
      e.printStackTrace();
      return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }
  }

  @GetMapping("/ldap/health")
  public ResponseEntity<String> checkLdap() {
    boolean ldapHealth = checkLdapHealth();
    if (ldapHealth) {
      System.out.println("LDAP è raggiungibile e connesso.");
      return ResponseEntity.ok().build();
    } else {
      System.out.println("LDAP non è raggiungibile o non è possibile connettersi.");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Connessione non riuscita");
    }
  }

  public boolean checkLdapHealth() {
    Hashtable<String, String> env = new Hashtable<>();
    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    env.put(Context.PROVIDER_URL, LDAP_URL);

    try {
      DirContext ctx = new InitialDirContext(env);
      ctx.close();
      return true;
    } catch (NamingException e) {
      e.printStackTrace();
      return false;
    }
  }
}