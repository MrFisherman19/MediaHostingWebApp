package mrfisherman.hosting.web.configuration;

import lombok.AllArgsConstructor;
import mrfisherman.hosting.web.model.Authority;
import mrfisherman.hosting.web.model.User;
import mrfisherman.hosting.web.repository.AuthorityRepository;
import mrfisherman.hosting.web.repository.UserRepository;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byUsername = userRepository.findByUsername(username);

        final var checker = new AccountStatusUserDetailsChecker();

        byUsername.ifPresent(checker::check);

        return byUsername.orElseThrow(() -> new UsernameNotFoundException("User with username:" + username + "not found!"));
    }

    @EventListener(value = ApplicationStartedEvent.class)
    public void addTestUsers() {
        Authority authority = new Authority("ROLE_USER");
        Authority authority1 = new Authority("ROLE_ADMIN");

        authorityRepository.save(authority);
        authorityRepository.save(authority1);

        User user = new User();
        user.setUsername("user");
        user.setPassword("$2y$12$fniCZGqHRvNHY92bSNJ2M.k6TTEXxFauxcRRtpmudwaZDXvv8Qxem");
        user.setEmail("user@gmail.com");
        user.setAuthorities(List.of(authority));
        user.setEnabled(true);
        userRepository.saveAndFlush(user);

        User user2 = new User();
        user2.setUsername("admin");
        user2.setPassword("$2y$12$reFTKg6tTt5R9Wa3HYsXVurnOknUlNfFBhUMZn1nc6I1WKdlhz6vK");
        user2.setEmail("user@gmail.com");
        user2.setAuthorities(List.of(authority1, authority));
        user2.setEnabled(true);
        userRepository.saveAndFlush(user2);
    }

}
