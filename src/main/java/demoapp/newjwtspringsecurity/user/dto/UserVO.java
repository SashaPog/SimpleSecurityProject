package demoapp.newjwtspringsecurity.user.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class UserVO {
    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String role;
}
