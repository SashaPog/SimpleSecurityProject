package demoapp.newjwtspringsecurity.util;

import demoapp.newjwtspringsecurity.serviceApi.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserComponent {
    private final UserService userService;

    // usefull to check something and easy to debug
    public boolean userHasAccess(String email) {
        if (email.equals("test")){
            return false;
        }
        return true;
//        var auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth == null && (auth instanceof AnonymousAuthenticationToken)){
//            return false;
//        }
//        if (auth.getName().equals(email)){
//            return true;
//        }
//        return false;
    }
}
