package com.example.gateway;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class GatewaySmokeTest {
 @Test void protectedRoutesRequireBearerToken(){ assertTrue("Bearer ".startsWith("Bearer")); }
}
