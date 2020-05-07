package ro.msg.learning.shop.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ro.msg.learning.shop.models.entities.Customer;

import java.util.Arrays;
import java.util.Collection;

@Data
public class UserPrincipal implements UserDetails {

    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public UserPrincipal(Customer customer) {
        this.id = customer.getId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.username = customer.getUsername();
        this.password = customer.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(() -> "USER");
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
        return true;
    }
}
