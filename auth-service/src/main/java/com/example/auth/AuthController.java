package com.example.auth;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
 private final SecretKey key;
 public AuthController(@Value("${app.jwt.secret}") String secret){
   key=Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
 }
 @PostMapping("/login")
 public ResponseEntity<?> login(@RequestBody LoginRequest request){
   if(!"admin".equals(request.username()) || !"admin123".equals(request.password()))
     return ResponseEntity.status(401).body(Map.of("error","Invalid credentials"));
   String token=Jwts.builder().subject(request.username()).claim("role","ADMIN")
      .issuedAt(Date.from(Instant.now())).expiration(Date.from(Instant.now().plusSeconds(3600)))
      .signWith(key).compact();
   return ResponseEntity.ok(Map.of("token",token,"type","Bearer","expiresIn",3600));
 }
 record LoginRequest(String username,String password){}
}
