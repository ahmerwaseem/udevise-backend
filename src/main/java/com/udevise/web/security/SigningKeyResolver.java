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
    //inspect the header and return the signing key
    String keyId = jwsHeader.getKeyId(); //or any other field that you need to inspect
    Key key = lookupVerificationKey(keyId); //implement me
    return key;
  }

  private Key lookupVerificationKey(String keyId) {
    JwkProvider provider = new UrlJwkProvider(auth0Properties.getIssuer());
    try {
      Jwk jwk = provider.get(keyId);
      return jwk.getPublicKey();
    } catch (JwkException e){
      return null;
    }
  }
}