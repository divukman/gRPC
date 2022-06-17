package tech.dimitar.service.grpc.demo.config;

import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.log.LogMessage;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public final class KeycloakJwtGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    public KeycloakJwtGrantedAuthoritiesConverter() {
    }

    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList();
        Iterator var3 = this.getAuthorities(jwt).iterator();

        while(var3.hasNext()) {
            String authority = (String)var3.next();
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

    private Collection<String> castAuthoritiesToCollection(Object authorities) {
        return (Collection)authorities;
    }
}
