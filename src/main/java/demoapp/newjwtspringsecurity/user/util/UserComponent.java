package demoapp.newjwtspringsecurity.user.util;

import demoapp.newjwtspringsecurity.user.service.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserComponent {
    private final UserService userService;

    // usefull to check something and easy to debug
    public boolean userHasAccess(String email) {
        return !email.equals("test");
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
