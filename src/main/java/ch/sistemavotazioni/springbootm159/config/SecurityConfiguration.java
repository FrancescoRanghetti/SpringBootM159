package ch.sistemavotazioni.springbootm159.config;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.crypto.RsaKeyConversionServicePostProcessor;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration extends WebSecurityConfiguration {

  @Bean
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    return http.authorizeHttpRequests(auth -> {
      auth.requestMatchers("/**").permitAll();
      auth.anyRequest().authenticated();
    }).csrf(AbstractHttpConfigurer::disable).build();
  }

  @Bean
  public static BeanFactoryPostProcessor conversionServicePostProcessor() {
    return new RsaKeyConversionServicePostProcessor();
  }
}