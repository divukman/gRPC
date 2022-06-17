# gRPC
Demo for the gRPC

## Server
Spring Boot Server in the server/demo

### Security with Oauth2 JWT tokens from Keyclaok
To use oauth2 authorization, uncomment the security config annotations in the SecurityConfig class:
```
//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, proxyTargetClass = true)
```
@see https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/jwt.html#oauth2resourceserver-jwt-decoder
for any customizations.

## Clients
### Spring Boot Client
client/spring-boot-client


## Testing
1. Run Server
2. Run Client
3. `curl localhost:8080/ping`

