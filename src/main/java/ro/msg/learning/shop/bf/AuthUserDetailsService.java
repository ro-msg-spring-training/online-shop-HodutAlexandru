package ro.msg.learning.shop.bf;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.constants.ApplicationConstants;
import ro.msg.learning.shop.exceptions.customer.CustomerNotFoundException;
import ro.msg.learning.shop.models.UserPrincipal;
import ro.msg.learning.shop.models.entities.Customer;
import ro.msg.learning.shop.repositories.CustomerRepository;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Customer customer = this.customerRepository.findByUsername(username).orElseThrow(
                () -> new CustomerNotFoundException(ApplicationConstants.CUSTOMER_NOT_FOUND)
        );

        return new UserPrincipal(customer);
    }

}
