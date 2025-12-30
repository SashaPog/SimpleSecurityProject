package demoapp.newjwtspringsecurity.mapper;

import demoapp.newjwtspringsecurity.dto.UserVO;
import demoapp.newjwtspringsecurity.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserVOMapper {
    UserVO toDto (User user);
    User toEntity (UserVO uservo);
}