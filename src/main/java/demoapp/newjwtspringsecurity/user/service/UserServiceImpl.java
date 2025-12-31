package demoapp.newjwtspringsecurity.user.service;

import demoapp.newjwtspringsecurity.user.domain.User;
import demoapp.newjwtspringsecurity.user.repository.UserRepository;
import demoapp.newjwtspringsecurity.security.userdetails.CustomUserDetailService;
import demoapp.newjwtspringsecurity.user.service.api.UserService;
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
