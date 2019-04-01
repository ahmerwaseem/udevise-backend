package com.udevise.web.security;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.SigningKeyResolverAdapter;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class SigningKeyResolver extends SigningKeyResolverAdapter {

  private Auth0Properties auth0Properties;


  public SigningKeyResolver(Auth0Properties auth0Properties){
    this.auth0Properties = auth0Properties;
  }

  @Override
  public Key resolveSigningKey(JwsHeader jwsHeader, Claims claims) {
    String keyId = jwsHeader.getKeyId();
    Key key = lookupVerificationKey(keyId);
    return key;
  }

  private Key lookupVerificationKey(String keyId) {
    JwkProvider provider = new UrlJwkProvider(auth0Properties.getIssuer());
    try {
      //get public key from auth0
      Jwk jwk = provider.get(keyId);
      return jwk.getPublicKey();
    } catch (JwkException e){
      return null;
    }
  }
}