package ch.sistemavotazioni.springbootm159.service;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.ArrayList;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

@Service
public class LdapService {

  @Autowired LdapTemplate ldapTemplate;

  public ArrayList getGroupFromUsername(String usernameCN) {
    return ldapTemplate.search(query().where("cn").is(usernameCN), (AttributesMapper<ArrayList<?>>) attrs -> Collections.list(attrs.get("memberOf").getAll())).get(0);
  }
}
