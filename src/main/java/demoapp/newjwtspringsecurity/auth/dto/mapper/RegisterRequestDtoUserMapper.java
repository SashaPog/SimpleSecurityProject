package demoapp.newjwtspringsecurity.auth.dto.mapper;

import demoapp.newjwtspringsecurity.auth.dto.RegisterRequestDto;
import demoapp.newjwtspringsecurity.user.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisterRequestDtoUserMapper {
    RegisterRequestDto toDto(User user);
    User toEntity (RegisterRequestDto dto);
}
