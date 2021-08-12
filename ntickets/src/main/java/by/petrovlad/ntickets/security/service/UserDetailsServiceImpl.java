package by.petrovlad.ntickets.security.service;

import by.petrovlad.ntickets.model.entity.User;
import by.petrovlad.ntickets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final String EXC_USERNAME_NOT_FOUND = "Cannot find a user with username = ";

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository
                .findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException(EXC_USERNAME_NOT_FOUND + s));
        return UserDetailsImpl.build(user);
    }
}
