package pl.kamil.backend.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pl.kamil.backend.dto.LoginDto;
import pl.kamil.backend.entity.AppUser;
import pl.kamil.backend.repository.UserRepository;
import pl.kamil.backend.service.SecurityUserDetailsService;

@RestController
public class SpringSessionController {

    @Autowired
    SecurityUserDetailsService securityUserDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    @Autowired
    UserRepository userRepository;

    @PostMapping("/public/login")
    public Map<String, String> login(HttpSession session, @RequestBody LoginDto loginDto) {
        System.out.println("login");
        Optional<AppUser> appUser = userRepository.findOneByUsername(loginDto.getUsername());
        System.out.println(passwordEncoder.matches(loginDto.getPassword(), appUser.get().getPassword()));

        if (!appUser.isPresent() || !passwordEncoder.matches(loginDto.getPassword(), appUser.get().getPassword())) {
            throw new BadCredentialsException("złe credenctails");
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(loginDto.getUsername(),
                loginDto.getPassword(), new ArrayList<GrantedAuthority>());
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
        return Collections.singletonMap("message", "User signed-in successfully!.");
    }

    @PostMapping("/private/logout")
    public Map<String, String> logout(Authentication authentication, HttpServletRequest request,
            HttpServletResponse response) {
        System.out.println("logout");
        logoutHandler.logout(request, response, authentication);
        return Collections.singletonMap("message", "User logged-out successfully!.");
    }

    @PostMapping("/public/register")
    public Map<String, String> addUser(HttpSession session, @RequestBody LoginDto loginDto) {
        System.out.println("register");
        // Authentication authentication = authenticationManager
        // .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(),
        // loginDto.getPassword()));
        // SecurityContext securityContext = SecurityContextHolder.getContext();
        // securityContext.setAuthentication(authentication);
        // session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

        AppUser user = new AppUser();
        user.setUsername("testName");
        user.setPassword(passwordEncoder.encode("pass"));
        securityUserDetailsService.createUser(user);

        return Collections.singletonMap("message", "User registered successfully!.");
    }

}