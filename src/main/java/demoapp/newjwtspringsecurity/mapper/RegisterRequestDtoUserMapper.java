package demoapp.newjwtspringsecurity.mapper;

import demoapp.newjwtspringsecurity.dto.RegisterRequestDto;
import demoapp.newjwtspringsecurity.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisterRequestDtoUserMapper {
    RegisterRequestDto toDto(User user);
    User toEntity (RegisterRequestDto dto);
}
