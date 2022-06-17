package tech.dimitar.service.grpc.demo.config;

import java.util.*;
import java.util.stream.Collectors;

import lombok.NoArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

@NoArgsConstructor
public final class KeycloakJwtGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList();
        Iterator authorities = this.getAuthorities(jwt).iterator();

        while(authorities.hasNext()) {
            String authority = (String)authorities.next();
            grantedAuthorities.add(new SimpleGrantedAuthority(authority));
        }

        return grantedAuthorities;
    }

    private Collection<String> getAuthorities(final Jwt jwt) {
        Map<String, Object> rolesMap = jwt.getClaimAsMap("realm_access"); //@todo refactor
        List<String> rolesClaim = (List<String>) rolesMap.get("roles");

        if (rolesClaim != null)
            return rolesClaim.stream()
                    .map(String::new)
                    .collect(Collectors.toSet());
        return Collections.emptySet();
    }
}
