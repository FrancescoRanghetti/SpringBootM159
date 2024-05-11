package ch.sistemavotazioni.springbootm159.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.search.LdapUserSearch;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;
import org.springframework.security.ldap.userdetails.LdapUserDetailsService;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;

@Configuration
@EnableWebSecurity(debug = true)
class LdapConfiguration {

  @Bean
  public LdapContextSource contextSource() {
    return new DefaultSpringSecurityContextSource("ldap://192.168.0.200:389/CN=Users,DC=votazioniSpai,DC=local");
  }

  @Bean
  public LdapTemplate ldapTemplate() {
    LdapContextSource contextSource = new LdapContextSource();
    contextSource.setUrl("ldap://192.168.0.200:389");
    contextSource.setBase("CN=Users,DC=votazioniSpai,DC=local");
    contextSource.setUserDn("pippo bello");
    contextSource.setPassword("nonAdmin1234");
    contextSource.afterPropertiesSet();
    return new LdapTemplate(contextSource);
  }

  @Bean
  public AuthenticationManager authenticationManager() {
    LdapBindAuthenticationManagerFactory factory = new LdapBindAuthenticationManagerFactory(contextSource());
    factory.setUserDnPatterns("cn={0}");
    factory.setUserSearchBase("cn=Users,DC=votazioniSpai,DC=local");
    return factory.createAuthenticationManager();
  }

  @Bean
  public LdapUserDetailsService userDetailsService() {
    LdapUserSearch userSearch = new LdapUserSearch() {
      @Override
      public DirContextOperations searchForUser(final String username) throws UsernameNotFoundException {
        return new DirContextAdapter("cn=Users,DC=votazioniSpai,DC=local");
      }
    };
    return new LdapUserDetailsService(userSearch);
  }

  @Bean
  public UserDetailsContextMapper userDetailsContextMapper() {
    return new LdapUserDetailsMapper();
  }
}
