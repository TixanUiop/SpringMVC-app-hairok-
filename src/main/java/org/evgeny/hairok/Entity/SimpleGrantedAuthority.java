package org.evgeny.hairok.Entity;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class SimpleGrantedAuthority implements GrantedAuthority {
    private String authority;


    @Override
    public String getAuthority() {
        return authority;
    }
}
