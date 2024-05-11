package ch.sistemavotazioni.springbootm159.service;

import ch.sistemavotazioni.springbootm159.config.JwtConfiguration;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {

  private final JwtConfiguration jwtConfiguration;

  public String generateToken(String nameUser, String role) throws Exception {
    JWTClaimsSet.Builder claimsSetBuilder = new JWTClaimsSet.Builder();
    claimsSetBuilder.subject(nameUser);
    claimsSetBuilder.issuer(jwtConfiguration.getIssuer());
    claimsSetBuilder.issueTime(new Date());
    claimsSetBuilder.expirationTime(new Date(System.currentTimeMillis() + jwtConfiguration.getExpirationTime().toMillis()));
    claimsSetBuilder.claim("role", role);

    JWTClaimsSet claimsSet = claimsSetBuilder.build();
    JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256).type(JOSEObjectType.JWT).build();
    JWSSigner signer = new RSASSASigner(jwtConfiguration.getPrivateKey());
    SignedJWT signedJWT = new SignedJWT(header, claimsSet);
    signedJWT.sign(signer);

    return signedJWT.serialize();
  }

  private Claims decodeJWT(String token) {
    return Jwts.parserBuilder().setSigningKey(jwtConfiguration.getPrivateKey()).build().parseClaimsJws(token).getBody();
  }

  public boolean isValidToken(String token, String username, String role) {
    try {
      if (token == null || token.isEmpty()) {
        throw new IllegalArgumentException("Token is null or empty");
      }
      String[] tokenParts = token.split("\\.");
      if (tokenParts.length < 3) {
        throw new IllegalArgumentException("Invalid token format: missing parts");
      }
      Claims claims = decodeJWT(token);
      if (claims.getSubject().equals(username) && claims.get("role").equals(role) && !isJwtExpired(token)) {
        log.info("Token valid");
        return true;
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return false;
  }

  private boolean isJwtExpired(String token) {
    Claims claims = decodeJWT(token);
    Date expiresAt = claims.getExpiration();
    return expiresAt.before(new Date());
  }
}
