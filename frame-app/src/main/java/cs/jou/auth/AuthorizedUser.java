package cs.jou.auth;

import cs.jou.common.auth.CurrentUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AuthorizedUser extends CurrentUser implements UserDetails {
    public static AuthorizedUser withUserName(String username) {
        return (AuthorizedUser) authorized(0, username);
    }

    public AuthorizedUser id(String id) {
        super.setId(Integer.valueOf(id));
        return this;
    }

    public AuthorizedUser id(Integer id) {
        super.setId(id);
        return this;
    }

    public AuthorizedUser password(String password) {
        setPassword(password);
        return this;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isGrant();
    }
}
