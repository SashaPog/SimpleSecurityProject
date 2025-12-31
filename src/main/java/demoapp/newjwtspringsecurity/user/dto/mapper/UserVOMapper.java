package demoapp.newjwtspringsecurity.user.dto.mapper;

import demoapp.newjwtspringsecurity.user.dto.UserVO;
import demoapp.newjwtspringsecurity.user.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserVOMapper {
    UserVO toDto (User user);
    User toEntity (UserVO uservo);
}