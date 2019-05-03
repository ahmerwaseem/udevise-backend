package com.udevise.web.filters;

import com.udevise.web.Utilities.UserUtils;
import com.udevise.web.domain.model.User;
import com.udevise.web.security.Auth0Properties;
import com.udevise.web.security.SigningKeyResolver;

import com.udevise.web.services.UserService;
import io.jsonwebtoken.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static com.udevise.web.security.SecurityConstants.HEADER_STRING;
import static com.udevise.web.security.SecurityConstants.TOKEN_PREFIX;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

  private Auth0Properties auth0Properties;
  private UserService userService;


  public JWTAuthorizationFilter(AuthenticationManager authManager, Auth0Properties auth0Properties, UserService userService) {
    super(authManager);
    this.auth0Properties = auth0Properties;
    this.userService = userService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest req,
                                  HttpServletResponse res,
                                  FilterChain chain) throws IOException, ServletException {
    String header = req.getHeader(HEADER_STRING);

    if (header == null || !header.toLowerCase().startsWith(TOKEN_PREFIX.toLowerCase())) {
      chain.doFilter(req, res);
      return;
    }

    UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

    SecurityContextHolder.getContext().setAuthentication(authentication);
    chain.doFilter(req, res);
  }

  private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request)  {
    String token = request.getHeader(HEADER_STRING);
    //System.out.println("token" + token);
    Jws<Claims> claimsJws = null;
    try {
      if (token != null) {
        token = token.replace(TOKEN_PREFIX, "");

        claimsJws = Jwts.parser()
          .setSigningKeyResolver(new SigningKeyResolver(auth0Properties))
          .parseClaimsJws(token);

        User user = processUser(claimsJws);
        if (user != null) {
          return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
        }
        return null;
      }
    } catch (Exception e) {
      logger.debug("token:" + token + "\nClaims: " + claimsJws.toString()+"\nException thrown in getAuthentication:", e);
      return null;
    }
    return null;
  }

  private User processUser(Jws<Claims> claimsJws) {
    LinkedHashMap map = (LinkedHashMap)claimsJws.getBody().get(auth0Properties.getUserClaim());

    String email = (String)map.get("email");
    if (email != null) {
      return userService.findUserByEmail(email.trim().toLowerCase());
    }
    return null;
  }
}