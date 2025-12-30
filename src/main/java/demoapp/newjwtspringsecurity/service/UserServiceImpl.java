package demoapp.newjwtspringsecurity.service;

import demoapp.newjwtspringsecurity.model.User;
import demoapp.newjwtspringsecurity.repository.UserRepository;
import demoapp.newjwtspringsecurity.serviceApi.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @NonNull
    public UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User is not found"));
        return new CustomUserDetailService(user);
    }
}
