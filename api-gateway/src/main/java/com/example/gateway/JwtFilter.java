package com.example.gateway;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.*;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Component
public class JwtFilter implements GlobalFilter, Ordered {
 private final SecretKey key;
 public JwtFilter(@Value("${app.jwt.secret}") String secret){key=Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));}
 @Override public Mono<Void> filter(ServerWebExchange ex, GatewayFilterChain chain){
   String path=ex.getRequest().getURI().getPath();
   if(path.equals("/auth/login") || path.equals("/") || path.startsWith("/index.html") || path.startsWith("/css/") || path.startsWith("/js/"))
     return chain.filter(ex);
   String h=ex.getRequest().getHeaders().getFirst("Authorization");
   if(h==null || !h.startsWith("Bearer ")){ex.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);return ex.getResponse().setComplete();}
   try{Jwts.parser().verifyWith(key).build().parseSignedClaims(h.substring(7));return chain.filter(ex);}
   catch(Exception e){ex.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);return ex.getResponse().setComplete();}
 }
 @Override public int getOrder(){return -1;}
}
