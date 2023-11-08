package pl.kamil.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pl.kamil.backend.config.AppUserPrincipal;
import pl.kamil.backend.entity.AppUser;
import pl.kamil.backend.repository.UserRepository;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<AppUser> user = userRepository.findOneByUsername(username);
        if (user.isPresent()) {
            return new AppUserPrincipal(user.get());
        }
        throw new UsernameNotFoundException(username);
    }

    public void createUser(AppUser user) {
        userRepository.save(user);
    }
}